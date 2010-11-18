/**
 * This file is part of Project Control Center (PCC).
 * 
 * Project Control Center (PCC) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Project Control Center (PCC) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Project Control Center (PCC).  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2010 Dmitri Anatol'evich Pisarenko
 **/

package at.silverstrike.pcc.impl.persistence;

import static at.silverstrike.pcc.impl.persistence.ErrorCodes.M_001_OPEN_SESSION;
import static at.silverstrike.pcc.impl.persistence.ErrorCodes.M_002_OPEN_SESSION;
import static at.silverstrike.pcc.impl.persistence.ErrorCodes.M_003_OPEN_SESSION;
import static at.silverstrike.pcc.impl.persistence.ErrorCodes.M_004_OPEN_SESSION;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.DerbyDialect;

import at.silverstrike.pcc.api.model.Booking;
import at.silverstrike.pcc.api.model.ControlProcess;
import at.silverstrike.pcc.api.model.DailyPlan;
import at.silverstrike.pcc.api.model.DailySchedule;
import at.silverstrike.pcc.api.model.DailyToDoList;
import at.silverstrike.pcc.api.model.ProcessState;
import at.silverstrike.pcc.api.model.ProcessType;
import at.silverstrike.pcc.api.model.Resource;
import at.silverstrike.pcc.api.model.ResourceAllocation;
import at.silverstrike.pcc.api.model.Worker;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.persistence.PersistenceState;
import at.silverstrike.pcc.api.tj3bookingsparser.BookingTuple;
import at.silverstrike.pcc.api.tj3deadlinesparser.ProcessEndTimeTuple;

import com.google.inject.Injector;

/**
 * @author Dmitri Pisarenko
 * 
 */
public class DefaultPersistence implements Persistence {
    public static final String DB_NAME = "pcc";
    private static final int DAYS_TO_PLAN_AHEAD = 7;
    private static final String JDBC_CONN_STRING_EXISTING_DB =
            "jdbc:derby:" + DB_NAME;
    private static final String JDBC_CONN_STRING_NEW_DB =
            "jdbc:derby:" + DB_NAME + ";create=true";
    private static final String PROCESS_ID = "${processId}";
    private static final String STATE_BEING_ATTAINED = ":stateBeingAttained";
    private static final String STATE_DELETED = ":stateDeleted";
    private static final String STATE_SCHEDULED = ":stateScheduled";
    private static final String SUB_PROCESSES_WITH_CHILDREN_HQL_TEMPLATE =
            "from DefaultControlProcess p where (p.parent.id = ${processId}) and (state <> "
                    + STATE_DELETED + ") "
                    + "order by priority desc";
    private static final String SUB_PROCESSES_WITH_CHILDREN_TOP_LEVEL_HQL =
            "from DefaultControlProcess p where (p.parent is null) and (state <> "
                    + STATE_DELETED + ") order by priority desc";
    private static final String UNCOMPLETED_TASKS_WITH_ESTIMATED_END_TIME_HQL =
            "from DefaultControlProcess where ((state = " + STATE_SCHEDULED
                    + ") or (state = " + STATE_BEING_ATTAINED + ")) or "
                    + "((averageEstimatedEndDateTime is not null) or "
                    + "(bestEstimatedEndDateTime is not null) or "
                    + "(worstEstimatedEndDateTime is not null))";

    private final Logger LOGGER =
            LoggerFactory.getLogger(DefaultPersistence.class);
    private Session session;
    private SessionFactory sessionFactory;
    private PersistenceState state;

    public DefaultPersistence() {
        state = PersistenceState.INITIAL;
    }

    /**
     * @see at.silverstrike.pcc.api.persistence.Persistence#closeSession()
     */
    @Override
    public void closeSession() {
        state = PersistenceState.CLOSING_CONNECTION;

        if (sessionFactory == null) {
            return;
        }

        final Session sess = sessionFactory.getCurrentSession();
        if (sess.getTransaction().isActive()) {
            sess.getTransaction().commit();
        }

        if (sess.isOpen()) {
            sess.close();
        }

        state = PersistenceState.CONNECTION_CLOSED;
    }

    @Override
    public Booking createBooking() {
        return new DefaultBooking();
    }

    @Override
    public void createChildProcess(final Long parentProcessId) {
        final Transaction tx = session.beginTransaction();
        try {
            ControlProcess parent = null;

            if (parentProcessId != null) {
                parent =
                        (ControlProcess) session.get(
                                DefaultControlProcess.class, parentProcessId);
            }

            final ControlProcess newProcess = new DefaultControlProcess();
            newProcess.setParent(parent);

            session.saveOrUpdate(newProcess);

            tx.commit();
        } catch (final Exception exception) {
            LOGGER.error("", exception);
            tx.rollback();
        }
    }

    @Override
    public Long createHumanResource(final String anAbbreviation,
            final String aFirstName, final String aMiddleName,
            final String aSurname, double dailyMaxWorkTimeInHours) {
        final Transaction tx = session.beginTransaction();
        Long id = null;

        try {
            final DefaultWorker worker = new DefaultWorker();

            worker.setAbbreviation(anAbbreviation);
            worker.setFirstName(aFirstName);
            worker.setMiddleName(aMiddleName);
            worker.setSurname(aSurname);
            worker.setDailyLimitInHours(dailyMaxWorkTimeInHours);

            session.save(worker);

            tx.commit();

            id = worker.getId();
        } catch (final Exception exception) {
            LOGGER.error("", exception);
            tx.rollback();
        }

        return id;
    }

    @Override
    public void createProcessParent(final String name, final Long parentItemId,
            final ProcessType aProcessType) {
        session.beginTransaction();

        final DefaultControlProcess task = new DefaultControlProcess();

        task.setName(name);
        task.setProcessType(aProcessType);

        if (parentItemId != null) {
            final DefaultControlProcess parentTask =
                    (DefaultControlProcess) session.get(
                            DefaultControlProcess.class, parentItemId);

            task.setParent(parentTask);
        }
        session.save(task);
        session.getTransaction().commit();
    }

    @Override
    public void createSiblingProcess(final Long siblingProcessId) {
        final Transaction tx = session.beginTransaction();
        try {
            ControlProcess parent = null;

            if (siblingProcessId != null) {
                final ControlProcess sibling =
                        (ControlProcess) session.get(
                                DefaultControlProcess.class, siblingProcessId);

                parent = sibling.getParent();
            }

            final ControlProcess newProcess = new DefaultControlProcess();
            newProcess.setParent(parent);

            session.saveOrUpdate(newProcess);

            tx.commit();
        } catch (final Exception exception) {
            LOGGER.error("", exception);
            tx.rollback();
        }
    }

    @Override
    public void createSubTask(final String aProcessName,
            final Long aParentProcessId) {
        try {
            ControlProcess parentProcess = null;
            if (aParentProcessId != null) {
                parentProcess =
                        (ControlProcess) session.get(
                                DefaultControlProcess.class, aParentProcessId);
            }

            final ControlProcess newProcess = new DefaultControlProcess();

            newProcess.setParent(parentProcess);
            newProcess.setName(aProcessName);

            session.save(newProcess);
        } catch (final Exception exception) {
            LOGGER.error("", exception);
        }
    }

    @Override
    public Long createTask(final String processName) {
        final DefaultControlProcess task = new DefaultControlProcess();
        task.setName(processName);

        session.beginTransaction();
        session.save(task);
        session.getTransaction().commit();

        return task.getId();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void deleteProcess(final Long selectedProjectId) {
        final Transaction tx = session.beginTransaction();
        try {
            final String hql =
                    "from DefaultControlProcess p where p.parent.id = "
                            + selectedProjectId;

            final Query query = session.createQuery(hql);

            final List<ControlProcess> childProcesses =
                    (List<ControlProcess>) query.list();

            for (final ControlProcess childProcess : childProcesses) {
                childProcess.setParent(null);
            }

            final ControlProcess process =
                    (ControlProcess) session.get(DefaultControlProcess.class,
                            selectedProjectId);

            session.delete(process);

            tx.commit();
        } catch (final Exception exception) {
            LOGGER.error("", exception);
            tx.rollback();
        }

    }

    @Override
    public void generateDailyPlans() {
        final Transaction tx = session.beginTransaction();

        try {
            session.createQuery("delete DefaultDailyPlan").executeUpdate();

            final Date now = new Date();
            final Date lastPlannedDay =
                    DateUtils.addDays(now, DAYS_TO_PLAN_AHEAD);

            createDailyPlans(session, now);

            // Create daily to-do lists
            updateDailyToDoLists(session, now, lastPlannedDay);

            // Create daily schedules
            updateDailySchedules(session, now, lastPlannedDay);

            tx.commit();
        } catch (final Exception exception) {
            LOGGER.error("", exception);
            tx.rollback();
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ControlProcess> getAllIntentsAndGoalRegions() {
        final List<ControlProcess> returnValue =
                new LinkedList<ControlProcess>();

        session.beginTransaction();
        final Query query =
                session.createQuery("from DefaultControlProcess as p "
                        + "where (p.processType = :goalRegion) or "
                        + "(p.processType = :intent)");

        query.setParameter("goalRegion", ProcessType.GOAL_REGION);
        query.setParameter("intent", ProcessType.INTENT);

        final List result = query.list();

        for (final Object record : result) {
            if (record instanceof DefaultControlProcess) {
                returnValue.add((DefaultControlProcess) record);
            }
        }
        session.getTransaction().commit();

        return returnValue;

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ControlProcess> getAllNotDeletedTasks() {
        final List<ControlProcess> returnValue =
                new LinkedList<ControlProcess>();

        session.beginTransaction();
        final Query query =
                session
                        .createQuery("from DefaultControlProcess where (state <> "
                                + STATE_DELETED + ")");

        query.setParameter(STATE_DELETED.substring(1), ProcessState.DELETED);

        final List result = query.list();

        for (final Object record : result) {
            if (record instanceof DefaultControlProcess) {
                returnValue.add((DefaultControlProcess) record);
            }
        }
        session.getTransaction().commit();

        return returnValue;
    }

    @SuppressWarnings( { "unchecked" })
    @Override
    public List<Worker> getAllWorkers() {
        final List<Worker> returnValue = new LinkedList<Worker>();

        session.beginTransaction();
        final List result =
                (List<Worker>) session.createQuery("from DefaultWorker").list();

        LOGGER.debug("result: " + result.size());

        for (final Object record : result) {
            LOGGER.debug("record: " + record.toString());

            if (record instanceof DefaultWorker) {
                returnValue.add((DefaultWorker) record);
            }
        }
        session.getTransaction().commit();

        return returnValue;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ControlProcess> getChildTasks(final ControlProcess aParent) {
        final List<ControlProcess> returnValue =
                new LinkedList<ControlProcess>();

        session.beginTransaction();
        Query query = null;
        if (aParent != null) {
            query =
                    session.createQuery("from DefaultControlProcess p "
                            + "where (p.parent != null) and "
                            + "(p.parent.id = :parentId) and (state <> "
                            + STATE_DELETED + ") ");
            query.setParameter("parentId", aParent.getId());
        } else {
            query =
                    session.createQuery("from DefaultControlProcess p "
                            + "where (p.parent is null) and (state <> "
                            + STATE_DELETED + ")");
        }

        query.setParameter(STATE_DELETED.substring(1), ProcessState.DELETED);

        final List result = query.list();

        for (final Object record : result) {
            if (record instanceof ControlProcess) {
                returnValue.add((ControlProcess) record);
            }
        }
        session.getTransaction().commit();

        return returnValue;
    }

    @Override
    public List<ControlProcess> getChildTasks(final Long aProcessId) {
        try {
            ControlProcess process = null;
            if (aProcessId != null) {
                process =
                        (ControlProcess) session.get(
                                DefaultControlProcess.class, aProcessId);
            }

            return this.getChildTasks(process);

        } catch (final Exception exception) {
            LOGGER.error("", exception);
            return new LinkedList<ControlProcess>();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public DailyPlan getDailyPlan(final Date newDate, final String resource) {
        DailyPlan returnValue = null;

        try {
            final Query query =
                    session.createQuery("from DefaultDailyPlan p where "
                            + "(p.date = :day) and "
                            + "(p.resource.abbreviation = :resource)");

            query.setParameter("day", newDate);
            query.setParameter("resource", resource);

            final List<DailyPlan> plans = (List<DailyPlan>) query.list();

            if (plans.size() > 0) {
                returnValue = plans.get(0);
            }
        } catch (final Exception exception) {
            LOGGER.error("", exception);
            throw new RuntimeException(exception);
        }
        return returnValue;
    }

    /**
     * @see at.silverstrike.pcc.api.persistence.Persistence#getSession()
     */
    @Override
    public Session getSession() {
        if (sessionFactory == null) {
            return null;
        }

        final Session currentSession = sessionFactory.openSession();
        return currentSession;
    }

    /**
     * @see at.silverstrike.pcc.api.persistence.Persistence#getState()
     */
    @Override
    public PersistenceState getState() {
        return state;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ControlProcess> getSubProcessesWithChildren(final Long processId) {
        List<ControlProcess> processes = null;

        try {
            final String hql;

            if (processId != null) {
                hql =
                        SUB_PROCESSES_WITH_CHILDREN_HQL_TEMPLATE.replace(
                                PROCESS_ID, processId.toString());
            } else {
                hql = SUB_PROCESSES_WITH_CHILDREN_TOP_LEVEL_HQL;
            }

            final Query query = session.createQuery(hql);

            query
                    .setParameter(STATE_DELETED.substring(1),
                            ProcessState.DELETED);

            processes = (List<ControlProcess>) query.list();

            if ((processId == null)
                    && ((processes == null) || (processes.size() < 1))) {
                return getAllNotDeletedTasks();
            }
        } catch (final Exception exception) {
            LOGGER.error("", exception);
        }
        return processes;
    }

    @Override
    public ControlProcess getTask(final Object aProcessid) {
        if (aProcessid == null) {
            return null;
        } else {
            return (ControlProcess) session.get(
                    DefaultControlProcess.class, (Serializable) aProcessid);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ControlProcess> getUncompletedTasksWithEstimatedEndTime() {
        List<ControlProcess> processes = new LinkedList<ControlProcess>();

        try {
            final Query query =
                    session
                            .createQuery(UNCOMPLETED_TASKS_WITH_ESTIMATED_END_TIME_HQL);

            query.setParameter(STATE_BEING_ATTAINED.substring(1),
                    ProcessState.IS_BEING_ATTAINED);
            query.setParameter(STATE_SCHEDULED.substring(1),
                    ProcessState.SCHEDULED);

            processes = (List<ControlProcess>) query.list();
        } catch (final Exception exception) {
            LOGGER.error("", exception);
        }
        return processes;
    }

    @Override
    public void handoffProcess(final Long processId, final Long workerId) {
        if (processId == null) {
            LOGGER.error("processId is null");
            return;
        }

        if (workerId == null) {
            LOGGER.error("workerId is null");
            return;
        }

        final Transaction tx = session.beginTransaction();
        try {
            final ControlProcess process =
                    (ControlProcess) session.load(DefaultControlProcess.class,
                            processId);
            final Worker resource =
                    (Worker) session.load(DefaultWorker.class, workerId);
            final ResourceAllocation allocation =
                    new DefaultResourceAllocation();
            allocation.setResource(resource);
            session.save(allocation);

            if (process.getResourceAllocations() != null) {
                process.getResourceAllocations().clear();
            } else {
                process
                        .setResourceAllocations(new HashSet<ResourceAllocation>());
            }

            process.getResourceAllocations().add(allocation);

            session.saveOrUpdate(process);

            tx.commit();
        } catch (final Exception exception) {
            LOGGER.error("", exception);
            tx.rollback();
        }
    }

    /**
     * @see at.silverstrike.pcc.api.persistence.Persistence#openSession()
     */
    @Override
    public void openSession() {
        LOGGER.debug(M_001_OPEN_SESSION);
        try {
            tryToOpenSession(JDBC_CONN_STRING_EXISTING_DB);
            LOGGER.debug(M_002_OPEN_SESSION);
        } catch (final RuntimeException exception) {
            LOGGER.debug(M_003_OPEN_SESSION, exception);
            tryToCreateDb(exception);
        } catch (final Throwable exception) {
            LOGGER.debug(M_004_OPEN_SESSION, exception);
            tryToCreateDb(exception);
        }
    }

    @Override
    public void setInjector(final Injector anInjector) {
    }

    @Override
    public void updateBookings(final List<BookingTuple> bookingTuples) {
        final Transaction tx = session.beginTransaction();
        try {
            session.createQuery("delete from DefaultBooking");

            for (final BookingTuple tuple : bookingTuples) {
                final Booking booking = tuple.getBooking();

                LOGGER.debug("booking ID: {}", tuple.getBooking().getId());

                final ControlProcess process =
                        (ControlProcess) session.load(
                                DefaultControlProcess.class, tuple
                                        .getProcessId());
                final Resource resource =
                        (Resource) session.load(DefaultResource.class, tuple
                                .getResourceId());

                booking.setProcess(process);
                booking.setResource(resource);

                if (tuple.getBooking().getId() == null) {
                    session.save(booking);
                } else {
                    session.update(booking);
                }

            }
            tx.commit();
        } catch (final Exception exception) {
            LOGGER.error("", exception);
            tx.rollback();
        }
    }

    @Override
    public void updateTask(final ControlProcess process) {
        session.beginTransaction();
        session.update(process);
        session.getTransaction().commit();
    }

    @Override
    public void updateTaskEndTimes(final List<ProcessEndTimeTuple> endTimeTuples) {
        LOGGER.debug("updateTaskEndTimes, 1");

        final Transaction tx = session.beginTransaction();

        LOGGER.debug("updateTaskEndTimes, 2");

        try {
            for (final ProcessEndTimeTuple tuple : endTimeTuples) {
                final ControlProcess process =
                        (ControlProcess) session.load(
                                DefaultControlProcess.class, tuple
                                        .getProcessId());

                LOGGER.debug("process ID: {}, process: {}", tuple
                        .getProcessId(), process);

                process.setAverageEstimatedEndDateTime(tuple.getEndDateTime());
                process.setBestEstimatedEndDateTime(tuple.getEndDateTime());
                process.setWorstEstimatedEndDateTime(tuple.getEndDateTime());

                session.update(process);
            }

            tx.commit();
        } catch (final Exception exception) {
            LOGGER.error("", exception);
            tx.rollback();
        }
    }

    @SuppressWarnings("unchecked")
    private void createDailyPlans(final Session session, final Date now) {
        final List<Resource> resources =
                (List<Resource>) session.createQuery("from DefaultResource")
                        .list();

        for (int i = 0; i < DAYS_TO_PLAN_AHEAD; i++) {
            final Date currentDay = DateUtils.addDays(now, i);

            for (final Resource resource : resources) {
                final DailySchedule schedule = new DefaultDailySchedule();
                final DailyToDoList toDoList = new DefaultDailyToDoList();
                final DailyPlan plan = new DefaultDailyPlan();

                plan.setResource(resource);
                plan.setDate(currentDay);
                plan.setSchedule(schedule);
                plan.setToDoList(toDoList);

                session.save(schedule);
                session.save(toDoList);
                session.save(plan);
            }
        }
    }

    private void tryToCreateDb(final Throwable anException) {
        LOGGER.error("tryToCreateDb, 1, ", anException);
        state = PersistenceState.CONNECTION_OPENINING_FAILURE;

        try {
            LOGGER.error("tryToCreateDb, 2");
            tryToOpenSession(JDBC_CONN_STRING_NEW_DB);
            LOGGER.error("tryToCreateDb, 3");
        } catch (final Throwable exception2) {
            LOGGER.error("tryToCreateDb, 4", exception2);
            state = PersistenceState.CONNECTION_OPENINING_FAILURE;
        }
    }

    private void tryToOpenSession(final String aConnectionString)
            throws Throwable {
        LOGGER.debug("tryToOpenSession, 1, aConnectionString: "
                + aConnectionString);

        closeSession();

        LOGGER.debug("tryToOpenSession, 2");

        state = PersistenceState.OPENING_CONNECTION;
        final Configuration cnf = new Configuration();
        cnf.setProperty(Environment.DRIVER,
                "org.apache.derby.jdbc.EmbeddedDriver");
        cnf.setProperty(Environment.URL, aConnectionString);
        cnf.setProperty(Environment.DIALECT, DerbyDialect.class.getName());
        cnf.setProperty(Environment.SHOW_SQL, "true");
        cnf.setProperty(Environment.HBM2DDL_AUTO, "update");
        cnf.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

        cnf.addResource("persistence/DefaultResource.hbm.xml");
        cnf.addResource("persistence/DefaultResourceAllocation.hbm.xml");
        cnf.addResource("persistence/DefaultControlProcess.hbm.xml");
        cnf.addResource("persistence/DefaultBooking.hbm.xml");
        cnf.addResource("persistence/DefaultDailyPlan.hbm.xml");
        cnf.addResource("persistence/DefaultDailySchedule.hbm.xml");
        cnf.addResource("persistence/DefaultDailyToDoList.hbm.xml");

        LOGGER.debug("tryToOpenSession, 3");

        sessionFactory = cnf.buildSessionFactory();

        LOGGER.debug("tryToOpenSession, 4");

        session = getSession();

        LOGGER.debug("tryToOpenSession, 5");

        getAllNotDeletedTasks();

        LOGGER.debug("tryToOpenSession, 6");

        state = PersistenceState.CONNECTION_OPEN;
    }

    @SuppressWarnings("unchecked")
    private void updateDailySchedules(final Session session, final Date now,
            final Date lastPlannedDay) {
        final Query bookingsQuery =
                session.createQuery("from DefaultBooking "
                        + "where (startDateTime >= :minDate) && "
                        + "(startDateTime <= :maxDate)");
        bookingsQuery.setParameter("minDate", now);
        bookingsQuery.setParameter("maxDate", lastPlannedDay);

        final List<Booking> bookings = bookingsQuery.list();

        for (final Booking curBooking : bookings) {
            final Query dailyPlanQuery =
                    session.createQuery("from DefaultDailyPlan "
                            + "where (date = :day) and "
                            + "(resource = :resource)");

            dailyPlanQuery.setParameter("day", curBooking.getStartDateTime());
            dailyPlanQuery.setParameter("resource", curBooking.getResource());

            final List<DailyPlan> foundDailyPlans =
                    (List<DailyPlan>) dailyPlanQuery.list();

            if (!foundDailyPlans.isEmpty()) {
                final DailyPlan dailyPlan = (DailyPlan) foundDailyPlans.get(0);

                dailyPlan.getSchedule().getBookings().add(curBooking);
            } else {
                LOGGER.error("Daily plan not found.");
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void updateDailyToDoLists(final Session session, final Date now,
            final Date lastPlannedDay) {
        final Query query =
                session.createQuery("from DefaultControlProcess " + "where "
                        + "(averageEstimatedEndDateTime >= :minDate)"
                        + " and (averageEstimatedEndDateTime <= :maxDate)");

        query.setParameter("minDate", now);
        query.setParameter("maxDate", lastPlannedDay);
        final List<ControlProcess> processes =
                (List<ControlProcess>) query.list();

        for (final ControlProcess curProcess : processes) {
            for (final ResourceAllocation allocation : curProcess
                    .getResourceAllocations()) {
                final Query dailyPlanQuery =
                        session.createQuery("from DefaultDailyPlan "
                                + "where (date = :day) and "
                                + "(resource = :resource)");

                dailyPlanQuery.setParameter("day", curProcess
                        .getAverageEstimatedEndDateTime());
                dailyPlanQuery.setParameter("resource", allocation
                        .getResource());

                final List<DailyPlan> foundDailyPlans =
                        (List<DailyPlan>) dailyPlanQuery.list();

                if (!foundDailyPlans.isEmpty()) {
                    final DailyPlan dailyPlan =
                            (DailyPlan) foundDailyPlans.get(0);

                    dailyPlan.getToDoList().getTasksToCompleteToday().add(
                            curProcess);
                } else {
                    LOGGER.error("Daily plan not found.");
                }
            }
        }
    }

    @Override
    public void clearDatabase() {
        final Transaction tx = session.beginTransaction();
        try {
            final String[] entitiesToDelete =
                    { "DefaultDailyToDoList",
                            "DefaultDailySchedule", "DefaultDailyPlan",
                            "DefaultBooking",
                            "DefaultResourceAllocation",
                            "DefaultControlProcess", "DefaultResource" };

            for (final String entityToDelete : entitiesToDelete) {
                final Query query = session.createQuery("delete from "
                        + entityToDelete);
                query.executeUpdate();
            }
            tx.commit();
        } catch (final Exception exception) {
            LOGGER.error("", exception);
            tx.rollback();
        }
    }

    @Override
    public Resource getResource(final Long aResourceId) {
        if (aResourceId == null) {
            return null;
        } else {
            return (Resource) session.get(DefaultResource.class,
                    (Serializable) aResourceId);
        }
    }
}

/**
 * This file is part of Project Control Center (PCC).
 * 
 * PCC (Project Control Center) project is intellectual property of 
 * Dmitri Anatol'evich Pisarenko.
 * 
 * Copyright 2010 Dmitri Anatol'evich Pisarenko
 * All rights reserved
 *
 **/

package at.silverstrike.pcc.impl.persistence;

import java.io.Serializable;
import java.util.Date;
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
import at.silverstrike.pcc.api.model.UserData;
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
	private static final int LAST_HOUR = 23;
	private static final int LAST_MINUTE = 59;
	private static final int LAST_SECOND = 59;
	private static final int LAST_MILLISECOND = 999;
	public static final String DB_NAME = "pcc";
	private static final int DAYS_TO_PLAN_AHEAD = 7;
	private static final String JDBC_CONN_STRING_EXISTING_DB = "jdbc:derby:"
			+ DB_NAME;
	private static final String JDBC_CONN_STRING_NEW_DB = "jdbc:derby:"
			+ DB_NAME + ";create=true";
	private static final String PROCESS_ID = "${processId}";
	private static final String STATE_BEING_ATTAINED = ":stateBeingAttained";
	private static final String STATE_DELETED = ":stateDeleted";
	private static final String STATE_ATTAINED = ":stateAttained";
	private static final String STATE_SCHEDULED = ":stateScheduled";
	private static final String SUB_PROCESSES_WITH_CHILDREN_HQL_TEMPLATE = "from DefaultControlProcess p where (p.parent.id = ${processId}) and (state <> "
			+ STATE_DELETED
			+ ") and (state <> "
			+ STATE_ATTAINED
			+ ") order by priority desc";
	private static final String SUB_PROCESSES_WITH_CHILDREN_TOP_LEVEL_HQL = "from DefaultControlProcess p where (p.parent is null) and (state <> "
			+ STATE_DELETED
			+ ") and (state <> "
			+ STATE_ATTAINED
			+ " order by priority desc";
	private static final String UNCOMPLETED_TASKS_WITH_ESTIMATED_END_TIME_HQL = "from DefaultControlProcess where ((state = "
			+ STATE_SCHEDULED
			+ ") or (state = "
			+ STATE_BEING_ATTAINED
			+ ")) or "
			+ "((averageEstimatedEndDateTime is not null) or "
			+ "(bestEstimatedEndDateTime is not null) or "
			+ "(worstEstimatedEndDateTime is not null))";

	private static final Logger LOGGER = LoggerFactory
			.getLogger(DefaultPersistence.class);
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
	public final void closeSession() {
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
	public final Booking createBooking() {
		return new DefaultBooking();
	}

	@Override
	public final void createChildProcess(final Long aParentProcessId) {
		final Transaction tx = session.beginTransaction();
		try {
			ControlProcess parent = null;

			if (aParentProcessId != null) {
				parent = (ControlProcess) session.get(
						DefaultControlProcess.class, aParentProcessId);
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
	public final Long createHumanResource(final String aAbbreviation,
			final String aFirstName, final String aMiddleName,
			final String aSurname, final double aDailyMaxWorkTimeInHours) {
		final Transaction tx = session.beginTransaction();
		Long id = null;

		try {
			final DefaultWorker worker = new DefaultWorker();

			worker.setAbbreviation(aAbbreviation);
			worker.setFirstName(aFirstName);
			worker.setMiddleName(aMiddleName);
			worker.setSurname(aSurname);
			worker.setDailyLimitInHours(aDailyMaxWorkTimeInHours);

			session.save(worker);

			tx.commit();

			id = worker.getId();

			LOGGER.debug("{}: dailyMaxWorkTimeInHours: {}", new Object[] {
					ErrorCodes.M_013_CREATE_HUMAN_RESOURCE2,
					aDailyMaxWorkTimeInHours });
		} catch (final Exception exception) {
			LOGGER.error(ErrorCodes.M_012_CREATE_HUMAN_RESOURCE, exception);
			tx.rollback();
		}

		return id;
	}

	@Override
	public final void createProcessParent(final String aName,
			final Long aParentItemId, final ProcessType aProcessType) {
		final Transaction tx = session.beginTransaction();

		final DefaultControlProcess task = new DefaultControlProcess();

		task.setName(aName);
		task.setProcessType(aProcessType);

		try {
			if (aParentItemId != null) {
				final DefaultControlProcess parentTask = (DefaultControlProcess) session
						.get(DefaultControlProcess.class, aParentItemId);

				task.setParent(parentTask);
			}
			session.save(task);
			tx.commit();
		} catch (final Exception exception) {
			LOGGER.error("", exception);
			tx.rollback();
		}
	}

	@Override
	public final void createSiblingProcess(final Long aSiblingProcessId) {
		final Transaction tx = session.beginTransaction();
		try {
			ControlProcess parent = null;

			if (aSiblingProcessId != null) {
				final ControlProcess sibling = (ControlProcess) session.get(
						DefaultControlProcess.class, aSiblingProcessId);

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
	public final void createSubTask(final String aProcessName,
			final Long aParentProcessId) {
		final Transaction tx = session.beginTransaction();
		try {
			ControlProcess parentProcess = null;
			if (aParentProcessId != null) {
				parentProcess = (ControlProcess) session.get(
						DefaultControlProcess.class, aParentProcessId);
			}

			final ControlProcess newProcess = new DefaultControlProcess();

			newProcess.setParent(parentProcess);
			newProcess.setName(aProcessName);

			session.save(newProcess);
			tx.commit();
		} catch (final Exception exception) {
			LOGGER.error("", exception);
			tx.rollback();
		}
	}

	@Override
	public final Long createTask(final String aProcessName) {
		final Transaction tx = session.beginTransaction();

		final DefaultControlProcess task = new DefaultControlProcess();
		task.setName(aProcessName);

		try {
			session.save(task);
			tx.commit();
		} catch (final Exception exception) {
			LOGGER.error("", exception);
			tx.rollback();
		}

		return task.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public final void deleteProcess(final Long aSelectedProjectId) {
		final Transaction tx = session.beginTransaction();
		try {
			final String hql = "from DefaultControlProcess p where p.parent.id = "
					+ aSelectedProjectId;

			final Query query = session.createQuery(hql);

			final List<ControlProcess> childProcesses = (List<ControlProcess>) query
					.list();

			for (final ControlProcess childProcess : childProcesses) {
				childProcess.setParent(null);
			}

			final ControlProcess process = (ControlProcess) session.get(
					DefaultControlProcess.class, aSelectedProjectId);

			session.delete(process);

			tx.commit();
		} catch (final Exception exception) {
			LOGGER.error("", exception);
			tx.rollback();
		}

	}

	@Override
	public final void generateDailyPlans(final Date aNow) {
		final Transaction tx = session.beginTransaction();

		try {
			session.createQuery("delete DefaultDailyPlan").executeUpdate();

			final Date lastPlannedDay = DateUtils.addDays(aNow,
					DAYS_TO_PLAN_AHEAD);

			createDailyPlans(session, aNow);

			printDailyPlans(ErrorCodes.M_007_DAILY_PLAN_LIST1);

			// Create daily to-do lists
			updateDailyToDoLists(session, aNow, lastPlannedDay);

			// Create daily schedules
			updateDailySchedules(session, aNow, lastPlannedDay);

			tx.commit();
		} catch (final Exception exception) {
			LOGGER.error(ErrorCodes.M_010_GENERATE_DAILY_PLANS, exception);
			tx.rollback();
		}

	}

	@SuppressWarnings({ "rawtypes" })
	@Override
	public final List<ControlProcess> getAllIntentsAndGoalRegions() {
		final List<ControlProcess> returnValue = new LinkedList<ControlProcess>();

		session.beginTransaction();
		final Query query = session
				.createQuery("from DefaultControlProcess as p "
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

	@SuppressWarnings({ "rawtypes" })
	@Override
	public final List<ControlProcess> getAllNotDeletedTasks() {
		final List<ControlProcess> returnValue = new LinkedList<ControlProcess>();
		final Transaction tx = session.beginTransaction();

		try {
			final Query query = session
					.createQuery("from DefaultControlProcess where ((state <> "
							+ STATE_DELETED + ") and (state <> "
							+ STATE_ATTAINED + "))");

			query.setParameter(STATE_DELETED.substring(1), ProcessState.DELETED);
			query.setParameter(STATE_ATTAINED.substring(1),
					ProcessState.ATTAINED);

			final List result = query.list();

			for (final Object record : result) {
				if (record instanceof DefaultControlProcess) {
					returnValue.add((DefaultControlProcess) record);
				}
			}
			tx.commit();

		} catch (Exception exception) {
			LOGGER.error("", exception);
			tx.rollback();
		}

		return returnValue;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public final List<Worker> getAllWorkers() {
		final List<Worker> returnValue = new LinkedList<Worker>();

		final Transaction tx = session.beginTransaction();

		try {
			final List result = (List<Worker>) session.createQuery(
					"from DefaultWorker").list();

			LOGGER.debug("result: " + result.size());

			for (final Object record : result) {
				LOGGER.debug("record: " + record.toString());

				if (record instanceof DefaultWorker) {
					returnValue.add((DefaultWorker) record);
				}
			}
			tx.commit();

		} catch (Exception exception) {
			LOGGER.error("", exception);
			tx.rollback();
		}

		return returnValue;
	}

	@SuppressWarnings({ "rawtypes" })
	@Override
	public final List<ControlProcess> getChildTasks(final ControlProcess aParent) {
		final List<ControlProcess> returnValue = new LinkedList<ControlProcess>();
		final Transaction tx = session.beginTransaction();

		try {
			Query query = null;
			if (aParent != null) {
				query = session.createQuery("from DefaultControlProcess p "
						+ "where (p.parent != null) and "
						+ "(p.parent.id = :parentId) and (state <> "
						+ STATE_DELETED + ") and (state <> " + STATE_ATTAINED
						+ ")");
				query.setParameter("parentId", aParent.getId());
			} else {
				query = session.createQuery("from DefaultControlProcess p "
						+ "where (p.parent is null) and (state <> "
						+ STATE_DELETED + ") and (state <> " + STATE_ATTAINED
						+ ")");
			}

			query.setParameter(STATE_DELETED.substring(1), ProcessState.DELETED);
			query.setParameter(STATE_ATTAINED.substring(1),
					ProcessState.ATTAINED);

			final List result = query.list();

			for (final Object record : result) {
				if (record instanceof ControlProcess) {
					returnValue.add((ControlProcess) record);
				}
			}
			tx.commit();
		} catch (final Exception exception) {
			LOGGER.error("", exception);
			tx.rollback();
		}

		return returnValue;
	}

	@Override
	public final List<ControlProcess> getChildTasks(final Long aProcessId) {
		final Transaction tx = session.beginTransaction();
		try {
			ControlProcess process = null;
			if (aProcessId != null) {
				process = (ControlProcess) session.get(
						DefaultControlProcess.class, aProcessId);
			}

			tx.commit();
			return this.getChildTasks(process);

		} catch (final Exception exception) {
			LOGGER.error("", exception);
			tx.rollback();
			return new LinkedList<ControlProcess>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public final DailyPlan getDailyPlan(final Date aNewDate,
			final String aResource) {
		DailyPlan returnValue = null;

		try {
			printDailyPlans(ErrorCodes.M_008_DAILY_PLAN_LIST2);

			final Query query = session
					.createQuery("from DefaultDailyPlan p where "
							+ "(p.date = :day) and "
							+ "(p.resource.abbreviation = :resource)");

			query.setParameter("day", setTimeTo00(aNewDate));
			query.setParameter("resource", aResource);

			final List<DailyPlan> plans = (List<DailyPlan>) query.list();

			if (plans.size() > 0) {
				returnValue = plans.get(0);
			}
		} catch (final Exception exception) {
			LOGGER.error(ErrorCodes.M_009_GET_DAILY_PLAN, exception);
			throw new RuntimeException(exception);
		}
		return returnValue;
	}

	@SuppressWarnings("unchecked")
	private void printDailyPlans(final String aText) {
		LOGGER.debug("{}: Daily plans (start)", aText);

		final Query debugQuery = session.createQuery("from DefaultDailyPlan");

		final List<DailyPlan> plans2 = (List<DailyPlan>) debugQuery.list();

		for (final DailyPlan plan : plans2) {

			LOGGER.debug(
					"Daily plan, date: {}, resource: {}, tasks: {}",
					new Object[] { plan.getDate(),
							plan.getResource().getAbbreviation(),
							plan.getToDoList().getTasksToCompleteToday().size() });

			if ((plan.getSchedule() != null)
					&& (plan.getSchedule().getBookings() != null)) {
				LOGGER.debug("Schedule items: {}", plan.getSchedule()
						.getBookings().size());
			}
		}

		LOGGER.debug("{}: Daily plans (end)", aText);
	}

	/**
	 * @see at.silverstrike.pcc.api.persistence.Persistence#getSession()
	 */
	@Override
	public final Session getSession() {
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
	public final PersistenceState getState() {
		return state;
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<ControlProcess> getSubProcessesWithChildren(
			final Long aProcessId) {
		List<ControlProcess> processes = null;

		try {
			final String hql;

			if (aProcessId != null) {
				hql = SUB_PROCESSES_WITH_CHILDREN_HQL_TEMPLATE.replace(
						PROCESS_ID, aProcessId.toString());
			} else {
				hql = SUB_PROCESSES_WITH_CHILDREN_TOP_LEVEL_HQL;
			}

			final Query query = session.createQuery(hql);

			query.setParameter(STATE_DELETED.substring(1), ProcessState.DELETED);
			query.setParameter(STATE_ATTAINED.substring(1),
					ProcessState.ATTAINED);

			processes = (List<ControlProcess>) query.list();

			if ((aProcessId == null)
					&& ((processes == null) || (processes.size() < 1))) {
				return getAllNotDeletedTasks();
			}
		} catch (final Exception exception) {
			LOGGER.error("", exception);
		}
		return processes;
	}

	@Override
	public final ControlProcess getTask(final Object aProcessid) {
		if (aProcessid == null) {
			return null;
		} else {
			return (ControlProcess) session.get(DefaultControlProcess.class,
					(Serializable) aProcessid);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<ControlProcess> getUncompletedTasksWithEstimatedEndTime() {
		List<ControlProcess> processes = new LinkedList<ControlProcess>();

		try {
			final Query query = session
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
	public final void handoffProcess(final Long aProcessId, final Long aWorkerId) {
		if (aProcessId == null) {
			LOGGER.error("processId is null");
			return;
		}

		if (aWorkerId == null) {
			LOGGER.error("workerId is null");
			return;
		}

		final Transaction tx = session.beginTransaction();
		try {
			final ControlProcess process = (ControlProcess) session.load(
					DefaultControlProcess.class, aProcessId);
			final Worker resource = (Worker) session.load(DefaultWorker.class,
					aWorkerId);
			final ResourceAllocation allocation = new DefaultResourceAllocation();
			allocation.setResource(resource);

			if (allocation.getId() == null) {
				session.save(allocation);
			} else {
				session.update(allocation);
			}

			if (process.getResourceAllocations() != null) {
				process.getResourceAllocations().clear();
			} else {
				process.setResourceAllocations(new LinkedList<ResourceAllocation>());
			}

			process.getResourceAllocations().add(allocation);

			if (process.getId() == null) {
				session.save(process);
			} else {
				session.update(process);
			}

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
		LOGGER.debug(ErrorCodes.M_001_OPEN_SESSION);
		try {
			tryToOpenSession(JDBC_CONN_STRING_EXISTING_DB);
			LOGGER.debug(ErrorCodes.M_002_OPEN_SESSION);
		} catch (final RuntimeException exception) {
			LOGGER.debug(ErrorCodes.M_003_OPEN_SESSION, exception);
			tryToCreateDb(exception);
		} catch (final Throwable exception) {
			LOGGER.debug(ErrorCodes.M_004_OPEN_SESSION, exception);
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

				final ControlProcess process = (ControlProcess) session.load(
						DefaultControlProcess.class, tuple.getProcessId());
				final Resource resource = (Resource) session.load(
						DefaultResource.class, tuple.getResourceId());

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
		final Transaction tx = session.beginTransaction();

		try {
			session.update(process);
			tx.commit();
		} catch (Exception exception) {
			LOGGER.error("", exception);
			tx.rollback();
		}
	}

	@Override
	public void updateTaskEndTimes(final List<ProcessEndTimeTuple> endTimeTuples) {
		LOGGER.debug("updateTaskEndTimes, 1");

		final Transaction tx = session.beginTransaction();

		LOGGER.debug("updateTaskEndTimes, 2");

		try {
			for (final ProcessEndTimeTuple tuple : endTimeTuples) {
				LOGGER.debug("tuple.getProcessId(): {}", tuple.getProcessId());

				final ControlProcess process = (ControlProcess) session.load(
						DefaultControlProcess.class, tuple.getProcessId());

				LOGGER.debug("process ID: {}, process: {}",
						tuple.getProcessId(), process);

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
		final List<Resource> resources = (List<Resource>) session.createQuery(
				"from DefaultResource").list();

		for (int i = 0; i < DAYS_TO_PLAN_AHEAD; i++) {
			final Date currentDay = DateUtils.addDays(now, i);

			for (final Resource resource : resources) {
				final DailySchedule schedule = new DefaultDailySchedule();
				final DailyToDoList toDoList = new DefaultDailyToDoList();
				final DailyPlan plan = new DefaultDailyPlan();

				plan.setResource(resource);
				plan.setDate(setTimeTo00(currentDay));
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
		final Date startDateTime = setTimeTo00(now);
		final Date endDateTime = setTimeTo2359(lastPlannedDay);

		final Query bookingsQuery = session.createQuery("from DefaultBooking "
				+ "where (startDateTime >= :minDate) and "
				+ "(startDateTime <= :maxDate)");
		bookingsQuery.setParameter("minDate", startDateTime);
		bookingsQuery.setParameter("maxDate", endDateTime);

		final List<Booking> bookings = bookingsQuery.list();

		for (final Booking curBooking : bookings) {
			final Query dailyPlanQuery = session
					.createQuery("from DefaultDailyPlan "
							+ "where (date = :day) and "
							+ "(resource = :resource)");

			final Date day = setTimeTo00(curBooking.getStartDateTime());
			final Resource resource = curBooking.getResource();

			dailyPlanQuery.setParameter("day", day);
			dailyPlanQuery.setParameter("resource", resource);

			final List<DailyPlan> foundDailyPlans = (List<DailyPlan>) dailyPlanQuery
					.list();

			if (!foundDailyPlans.isEmpty()) {
				final DailyPlan dailyPlan = (DailyPlan) foundDailyPlans.get(0);

				List<Booking> dailyPlanBookings = dailyPlan.getSchedule()
						.getBookings();

				if (dailyPlanBookings == null) {
					dailyPlanBookings = new LinkedList<Booking>();
					dailyPlan.getSchedule().setBookings(dailyPlanBookings);
				}

				dailyPlanBookings.add(curBooking);

				session.update(dailyPlan);
				session.update(dailyPlan.getSchedule());
			} else {
				LOGGER.error(
						ErrorCodes.M_005_DAILY_PLAN_NOT_FOUND_SCHEDULE
								+ ": Daily plan for resource '{}' and date '{}' not found.",
						new Object[] { resource.getAbbreviation(), day });
			}
		}
	}

	private Date setTimeTo2359(final Date aLastPlannedDay) {
		Date endDateTime = DateUtils.setHours(aLastPlannedDay, LAST_HOUR);
		endDateTime = DateUtils.setMinutes(endDateTime, LAST_MINUTE);
		endDateTime = DateUtils.setSeconds(endDateTime, LAST_SECOND);
		endDateTime = DateUtils.setMilliseconds(endDateTime, LAST_MILLISECOND);
		return endDateTime;
	}

	private Date setTimeTo00(final Date now) {
		Date startDateTime = DateUtils.setHours(now, 0);
		startDateTime = DateUtils.setMinutes(startDateTime, 0);
		startDateTime = DateUtils.setSeconds(startDateTime, 0);
		startDateTime = DateUtils.setMilliseconds(startDateTime, 0);
		return startDateTime;
	}

	@SuppressWarnings("unchecked")
	private void updateDailyToDoLists(final Session aSession, final Date aNow,
			final Date aLastPlannedDay) {
		final Query query = aSession.createQuery("from DefaultControlProcess "
				+ "where " + "(averageEstimatedEndDateTime >= :minDate)"
				+ " and (averageEstimatedEndDateTime <= :maxDate)");

		final Date minDate = setTimeTo00(aNow);
		final Date maxDate = setTimeTo2359(aLastPlannedDay);

		query.setParameter("minDate", minDate);
		query.setParameter("maxDate", maxDate);

		final List<ControlProcess> processes = (List<ControlProcess>) query
				.list();

		LOGGER.debug("updateDailyToDoLists, minDate: {}, maxDate: {}",
				new Object[] { minDate, maxDate });
		LOGGER.debug("updateDailyToDoLists, processes: {}", processes.size());

		for (final ControlProcess curProcess : processes) {
			for (final ResourceAllocation allocation : curProcess
					.getResourceAllocations()) {
				final Query dailyPlanQuery = aSession
						.createQuery("from DefaultDailyPlan "
								+ "where (date = :day) and "
								+ "(resource = :resource)");

				final Date day = setTimeTo00(curProcess
						.getAverageEstimatedEndDateTime());
				final Resource resource = allocation.getResource();

				dailyPlanQuery.setParameter("day", day);
				dailyPlanQuery.setParameter("resource", resource);

				LOGGER.debug(
						"updateDailyToDoLists, process: {}, day: {}, resource: {}",
						new Object[] { curProcess, day, resource });

				final List<DailyPlan> foundDailyPlans = (List<DailyPlan>) dailyPlanQuery
						.list();

				if (!foundDailyPlans.isEmpty()) {
					final DailyPlan dailyPlan = (DailyPlan) foundDailyPlans
							.get(0);

					dailyPlan.getToDoList().getTasksToCompleteToday()
							.add(curProcess);

					LOGGER.debug("Updating daily plan: {}", dailyPlan);
					aSession.update(dailyPlan);
					LOGGER.debug("Updating daily plan: {} completed", dailyPlan);

				} else {
					LOGGER.error(
							ErrorCodes.M_006_DAILY_PLAN_NOT_FOUND_TO_DO
									+ ": Daily plan for resource {} and day {} not found.",
							new Object[] { resource.getAbbreviation(), day });
				}
			}

		}
	}

	@Override
	public final void clearDatabase() {
		final Transaction tx = session.beginTransaction();
		try {
			final String[] entitiesToDelete = {
					"DefaultDailyLimitResourceAllocation",
					"DefaultResourceAllocation", "DefaultBooking",
					"DefaultDailyPlan", "DefaultDailySchedule",
					"DefaultResource", "DefaultControlProcess",
					"DefaultDailyToDoList", "DefaultWorker" };

			for (final String entityToDelete : entitiesToDelete) {
				final Query query = session.createQuery("delete from "
						+ entityToDelete);
				query.executeUpdate();
			}
			tx.commit();
		} catch (final Exception exception) {
			LOGGER.error(ErrorCodes.M_011_CLEAR_DATABASE, exception);
			tx.rollback();
		}
	}

	@Override
	public final Resource getResource(final Long aResourceId) {
		if (aResourceId == null) {
			return null;
		} else {
			return (Resource) session.get(DefaultResource.class,
					(Serializable) aResourceId);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public final UserData getUserData() {
		final UserData userData = new DefaultUserData();

		final Query bookingsQuery = session.createQuery("from DefaultBooking");
		final List<Booking> bookings = bookingsQuery.list();

		final Query dailyPlanQuery = session
				.createQuery("from DefaultDailyPlan");
		final List<DailyPlan> dailyPlans = dailyPlanQuery.list();

		final Query processesQuery = session
				.createQuery("from DefaultControlProcess");
		final List<ControlProcess> processes = processesQuery.list();

		userData.setBookings(bookings);
		userData.setDailyPlans(dailyPlans);
		userData.setIdentifier("dp");
		userData.setProcesses(processes);

		return userData;
	}
}

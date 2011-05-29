/**
 * This file is part of Project Control Center (PCC).
 * 
 * PCC (Project Control Center) project is intellectual property of 
 * Dmitri Anatol'evich Pisarenko.
 * 
 * Copyright 2010, 2011 Dmitri Anatol'evich Pisarenko
 * All rights reserved
 *
 **/

package at.silverstrike.pcc.api.persistence;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import ru.altruix.commons.api.di.ModuleWithInjectableDependencies;

import at.silverstrike.pcc.api.model.Booking;
import at.silverstrike.pcc.api.model.Event;
import at.silverstrike.pcc.api.model.InvitationRequest;
import at.silverstrike.pcc.api.model.Milestone;
import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.model.DailyPlan;
import at.silverstrike.pcc.api.model.Resource;
import at.silverstrike.pcc.api.model.UserData;
import at.silverstrike.pcc.api.model.Worker;
import at.silverstrike.pcc.api.openid.SupportedOpenIdProvider;
import at.silverstrike.pcc.api.tj3bookingsparser.BookingTuple;
import at.silverstrike.pcc.api.tj3deadlinesparser.ProcessEndTimeTuple;

public interface Persistence extends ModuleWithInjectableDependencies {

    void openSession();

    void closeSession();

    PersistenceState getState();

    Session getSession();

    Long createTask(final String aProcessName);

    Task createSubTask(final String aProcessName, final Long aParentProcessId);

    List<SchedulingObject> getAllNotDeletedTasks();

    Task getTask(final Object aProcessid);

    void updateTask(final Task aProcess);

    void createProcessParent(final String aName, final Long aParentItemId);

    List<Task> getAllIntentsAndGoalRegions();

    Long createHumanResource(final String aAbbreviation,
            final String aFirstName, final String aMiddleName,
            final String aSurname, final double aDailyMaxWorkTimeInHours);

    List<Worker> getAllWorkers();

    List<SchedulingObject> getChildTasks(final SchedulingObject aParent);

    /**
     * Sets the estimate end time dates of the processes.
     * 
     * @param endTimeTuples
     */
    void updateTaskEndTimes(final List<ProcessEndTimeTuple> aEndTimeTuples);

    /**
     * <pre>
     * This method performs following steps:
     * 
     * 1) Start a transaction. 
     * 2) Delete all bookings from all processes. 
     * 3) Add booking information from the given tuples to all referenced 
     * tasks. 
     * 4) Commit transaction.
     * </pre>
     */
    void updateBookings(final List<BookingTuple> aBookingTuples);

    void generateDailyPlans(final Date aNow);

    Booking createBooking();

    /**
     * Returns a list of all tasks, which are not completed yet and have an
     * estimated completion date/time.
     */
    List<Task> getUncompletedTasksWithEstimatedEndTime();

    /**
     * Returns daily plan for specified resource on specified day.
     */
    DailyPlan getDailyPlan(final Date aNewDate, final String aResource);

    /**
     * Returns a list of processes, which
     * 
     * a) are sub-processes of process with the specified ID as their parent
     * process and b) have child processes themselves. Deleted scheduling objects and 
     * completed tasks are not returned.
     * 
     * If processId is null, the top-level processes are returned.
     */
    List<SchedulingObject> getSubProcessesWithChildren(final Long aProcessId);
    
    /**
     * Returns a list of scheduling objects, which
     * 
     * a) are sub-processes of process with the specified ID as their parent
     * process and b) have child processes themselves. Deleted scheduling objects and 
     * completed tasks are not returned.
     * 
     * If processId is null, the top-level processes are returned.
     */    
    List<SchedulingObject> getSubProcessesWithChildrenInclAttainedTasks(final Long aProcessId);

    /**
     * Creates a new process on the same hierarchy level as the process with
     * given process id.
     */
    void createSiblingProcess(final Long aSiblingProcessId);

    /**
     * Creates a process with given process as parent.
     */
    void createChildProcess(final Long aParentProcessId);

    /**
     * Deletes the process with specified ID and sets parent to null for all of
     * its sub-processes.
     */
    void deleteProcess(final Long aSelectedProjectId);

    /**
     * 
     * @param selectedProjectId
     * @return
     */
    List<SchedulingObject> getChildTasks(final Long aProcessId);

    /**
     * Assigns the process with the specifid ID to the specified worker.
     */
    void handoffProcess(final Long aProcessId, final Long aWorkerId);

    /**
     * Removes all data from the database
     */
    void clearDatabase();

    Resource getResource(final Long aResourceId);

    UserData getUserData();

    Milestone createNewMilestone(final String aUser, final String aName,
            final Long aParentTaskId);

    Event
            createSubEvent(final String aProcessName,
                    final Long aParentProcessId);

    boolean deleteTask(final Task aTask);

    boolean deleteEvent(final Event aEvent);

    boolean deleteMilestone(final Milestone aMilestone);

    void updateMilestone(final Milestone aMilestone);

    void updateEvent(final Event aEvent);

    /**
     * Возвращает перечень объектов, которые могут быть предшественниками aObject.
     */
    List<SchedulingObject> getPotentialDependencies(
            final SchedulingObject aObject);
    
    boolean isHighestPriorityObjectInProject(final SchedulingObject aProject, final SchedulingObject aSchedulingObject);
    
    boolean isLowestPriorityObjectInProject(final SchedulingObject aProject, final SchedulingObject aSchedulingObject);

    boolean markTaskAsCompleted(final Task aTask);

    void updateSchedulingObject(final SchedulingObject schedulingObject);

    Worker getCurrentWorker();

    boolean hasChildren(final SchedulingObject aObject);

    List<SchedulingObject> getTopLevelTasks();
    
    void createInvitationRequest(final SupportedOpenIdProvider aOpenIdProvider, final String aEmail);

    List<InvitationRequest> getInvitationRequests();

    void rejectInvitationRequest(final InvitationRequest aSelectedRequest);

    void acceptInvitationRequest(final InvitationRequest aRequest,
            final String aPassword);

    void createSuperUser();

    Long getUserCount();

    UserData getUser(String userName, String password);
}

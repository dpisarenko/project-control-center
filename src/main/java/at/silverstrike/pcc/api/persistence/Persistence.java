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

import at.silverstrike.pcc.api.conventions.ModuleWithInjectableDependencies;
import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.model.Booking;
import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.model.DailyPlan;
import at.silverstrike.pcc.api.model.ProcessType;
import at.silverstrike.pcc.api.model.Resource;
import at.silverstrike.pcc.api.model.UserData;
import at.silverstrike.pcc.api.model.Worker;
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

    void createProcessParent(final String aName, final Long aParentItemId,
            final ProcessType aProcessType);

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
     * process and b) have child processes themselves.
     * 
     * If processId is null, the top-level processes are returned.
     */
    List<SchedulingObject> getSubProcessesWithChildren(final Long aProcessId);

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
    
    
    /**
     * Tries to create a new task.
     * @param aUser Identity of the user this task belongs to.
     * @param aParentTaskId Parent task/project.
     * @return Created task, if it was created successfully.
     */
    Task createNewTask(final String aUser, final Long aParentTaskId) throws PccException;
}

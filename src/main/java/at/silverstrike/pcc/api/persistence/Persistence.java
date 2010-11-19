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

package at.silverstrike.pcc.api.persistence;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import at.silverstrike.pcc.api.conventions.ModuleWithInjectableDependencies;
import at.silverstrike.pcc.api.model.Booking;
import at.silverstrike.pcc.api.model.ControlProcess;
import at.silverstrike.pcc.api.model.DailyPlan;
import at.silverstrike.pcc.api.model.ProcessType;
import at.silverstrike.pcc.api.model.Resource;
import at.silverstrike.pcc.api.model.Worker;
import at.silverstrike.pcc.api.tj3bookingsparser.BookingTuple;
import at.silverstrike.pcc.api.tj3deadlinesparser.ProcessEndTimeTuple;

public interface Persistence extends ModuleWithInjectableDependencies {

    void openSession();

    void closeSession();

    PersistenceState getState();

    Session getSession();

    Long createTask(String processName);

    void createSubTask(final String aProcessName, final Long aParentProcessId);

    List<ControlProcess> getAllNotDeletedTasks();

    ControlProcess getTask(final Object aProcessid);

    void updateTask(ControlProcess process);

    void createProcessParent(String name, Long parentItemId,
            ProcessType aProcessType);

    List<ControlProcess> getAllIntentsAndGoalRegions();

    Long createHumanResource(String abbreviation, String firstName,
            String middleName, String surname, double dailyMaxWorkTimeInHours);

    List<Worker> getAllWorkers();

    List<ControlProcess> getChildTasks(final ControlProcess aParent);

    /**
     * Sets the estimate end time dates of the processes.
     * 
     * @param endTimeTuples
     */
    void updateTaskEndTimes(List<ProcessEndTimeTuple> endTimeTuples);

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
    void updateBookings(List<BookingTuple> bookingTuples);

    void generateDailyPlans(final Date aNow);

    Booking createBooking();

    /**
     * Returns a list of all tasks, which are not completed yet and have an
     * estimated completion date/time.
     */
    List<ControlProcess> getUncompletedTasksWithEstimatedEndTime();

    /**
     * Returns daily plan for specified resource on specified day.
     */
    DailyPlan getDailyPlan(Date newDate, String resource);

    /**
     * Returns a list of processes, which
     * 
     * a) are sub-processes of process with the specified ID as their parent
     * process and b) have child processes themselves.
     * 
     * If processId is null, the top-level processes are returned.
     */
    List<ControlProcess> getSubProcessesWithChildren(final Long processId);

    /**
     * Creates a new process on the same hierarchy level as the process with
     * given process id.
     */
    void createSiblingProcess(final Long siblingProcessId);

    /**
     * Creates a process with given process as parent.
     */
    void createChildProcess(final Long parentProcessId);

    /**
     * Deletes the process with specified ID and sets parent to null for all of
     * its sub-processes.
     */
    void deleteProcess(final Long selectedProjectId);

    /**
     * 
     * @param selectedProjectId
     * @return
     */
    List<ControlProcess> getChildTasks(final Long aProcessId);

    /**
     * Assigns the process with the specifid ID to the specified worker.
     */
    void handoffProcess(final Long processId, final Long workerId);

    /**
     * Removes all data from the database
     */
    void clearDatabase();
    
    Resource getResource(final Long aResourceId);
}

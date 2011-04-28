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

package at.silverstrike.pcc.test.centraleditingpanelbuttonstate;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.google.inject.Injector;

import at.silverstrike.pcc.api.model.Booking;
import at.silverstrike.pcc.api.model.DailyPlan;
import at.silverstrike.pcc.api.model.Event;
import at.silverstrike.pcc.api.model.Milestone;
import at.silverstrike.pcc.api.model.Resource;
import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.model.UserData;
import at.silverstrike.pcc.api.model.Worker;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.persistence.PersistenceState;
import at.silverstrike.pcc.api.tj3bookingsparser.BookingTuple;
import at.silverstrike.pcc.api.tj3deadlinesparser.ProcessEndTimeTuple;
import at.silverstrike.pcc.test.mockpersistence.MockPersistenceAdapter;

/**
 * @author DP118M
 *
 */
public class MockPersistenceHighestLevelTask extends MockPersistenceAdapter implements Persistence {

    /* (non-Javadoc)
     * @see ru.altruix.commons.api.di.ModuleWithInjectableDependencies#setInjector(com.google.inject.Injector)
     */
    @Override
    public void setInjector(Injector arg0) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#closeSession()
     */
    @Override
    public void closeSession() {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#getState()
     */
    @Override
    public PersistenceState getState() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#getSession()
     */
    @Override
    public Session getSession() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#createTask(java.lang.String)
     */
    @Override
    public Long createTask(String aProcessName) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#createSubTask(java.lang.String, java.lang.Long)
     */
    @Override
    public Task createSubTask(String aProcessName, Long aParentProcessId) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#getAllNotDeletedTasks()
     */
    @Override
    public List<SchedulingObject> getAllNotDeletedTasks() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#getTask(java.lang.Object)
     */
    @Override
    public Task getTask(Object aProcessid) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#updateTask(at.silverstrike.pcc.api.model.Task)
     */
    @Override
    public void updateTask(Task aProcess) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#createProcessParent(java.lang.String, java.lang.Long)
     */
    @Override
    public void createProcessParent(String aName, Long aParentItemId) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#getAllIntentsAndGoalRegions()
     */
    @Override
    public List<Task> getAllIntentsAndGoalRegions() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#createHumanResource(java.lang.String, java.lang.String, java.lang.String, java.lang.String, double)
     */
    @Override
    public Long
            createHumanResource(String aAbbreviation, String aFirstName,
                    String aMiddleName, String aSurname,
                    double aDailyMaxWorkTimeInHours) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#getAllWorkers()
     */
    @Override
    public List<Worker> getAllWorkers() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#getChildTasks(at.silverstrike.pcc.api.model.SchedulingObject)
     */
    @Override
    public List<SchedulingObject> getChildTasks(SchedulingObject aParent) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#updateTaskEndTimes(java.util.List)
     */
    @Override
    public void updateTaskEndTimes(List<ProcessEndTimeTuple> aEndTimeTuples) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#generateDailyPlans(java.util.Date)
     */
    @Override
    public void generateDailyPlans(Date aNow) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#createBooking()
     */
    @Override
    public Booking createBooking() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#getUncompletedTasksWithEstimatedEndTime()
     */
    @Override
    public List<Task> getUncompletedTasksWithEstimatedEndTime() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#getDailyPlan(java.util.Date, java.lang.String)
     */
    @Override
    public DailyPlan getDailyPlan(Date aNewDate, String aResource) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#getSubProcessesWithChildren(java.lang.Long)
     */
    @Override
    public List<SchedulingObject> getSubProcessesWithChildren(Long aProcessId) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#createSiblingProcess(java.lang.Long)
     */
    @Override
    public void createSiblingProcess(Long aSiblingProcessId) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#createChildProcess(java.lang.Long)
     */
    @Override
    public void createChildProcess(Long aParentProcessId) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#deleteProcess(java.lang.Long)
     */
    @Override
    public void deleteProcess(Long aSelectedProjectId) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#getChildTasks(java.lang.Long)
     */
    @Override
    public List<SchedulingObject> getChildTasks(Long aProcessId) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#handoffProcess(java.lang.Long, java.lang.Long)
     */
    @Override
    public void handoffProcess(Long aProcessId, Long aWorkerId) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#clearDatabase()
     */
    @Override
    public void clearDatabase() {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#getResource(java.lang.Long)
     */
    @Override
    public Resource getResource(Long aResourceId) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#getUserData()
     */
    @Override
    public UserData getUserData() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#createNewMilestone(java.lang.String, java.lang.String, java.lang.Long)
     */
    @Override
    public Milestone createNewMilestone(String aUser, String aName,
            Long aParentTaskId) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#createSubEvent(java.lang.String, java.lang.Long)
     */
    @Override
    public Event createSubEvent(String aProcessName, Long aParentProcessId) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#deleteTask(at.silverstrike.pcc.api.model.Task)
     */
    @Override
    public boolean deleteTask(Task aTask) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#deleteEvent(at.silverstrike.pcc.api.model.Event)
     */
    @Override
    public boolean deleteEvent(Event aEvent) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#deleteMilestone(at.silverstrike.pcc.api.model.Milestone)
     */
    @Override
    public boolean deleteMilestone(Milestone aMilestone) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#getPotentialDependencies(at.silverstrike.pcc.api.model.SchedulingObject)
     */
    @Override
    public List<SchedulingObject> getPotentialDependencies(
            SchedulingObject aObject) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#isHighestPriorityObjectInProject(at.silverstrike.pcc.api.model.SchedulingObject, at.silverstrike.pcc.api.model.SchedulingObject)
     */
    @Override
    public boolean isHighestPriorityObjectInProject(SchedulingObject arg0,
            SchedulingObject arg1) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#isLowestPriorityObjectInProject(at.silverstrike.pcc.api.model.SchedulingObject, at.silverstrike.pcc.api.model.SchedulingObject)
     */
    @Override
    public boolean isLowestPriorityObjectInProject(SchedulingObject arg0,
            SchedulingObject arg1) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#openSession()
     */
    @Override
    public void openSession() {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#updateBookings(java.util.List)
     */
    @Override
    public void updateBookings(List<BookingTuple> aBookingTuples) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#updateMilestone(at.silverstrike.pcc.api.model.Milestone)
     */
    @Override
    public void updateMilestone(Milestone aMilestone) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#updateEvent(at.silverstrike.pcc.api.model.Event)
     */
    @Override
    public void updateEvent(Event aEvent) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean markTaskAsCompleted(Task aTask) {
        // TODO Auto-generated method stub
        return false;
    }

}

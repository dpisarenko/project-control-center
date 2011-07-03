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
import at.silverstrike.pcc.api.tj3deadlinesparser.ProcessEndTimeTuple;
import at.silverstrike.pcc.test.mockpersistence.MockPersistenceAdapter;

/**
 * @author DP118M
 * 
 */
final class MockPersistenceHighestLevelTask extends MockPersistenceAdapter
        implements Persistence {

    /*
     * (non-Javadoc)
     * 
     * @see
     * ru.altruix.commons.api.di.ModuleWithInjectableDependencies#setInjector
     * (com.google.inject.Injector)
     */
    @Override
    public void setInjector(final Injector aInjector) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see at.silverstrike.pcc.api.persistence.Persistence#closeSession()
     */
    @Override
    public void closeSession() {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see at.silverstrike.pcc.api.persistence.Persistence#getState()
     */
    @Override
    public final PersistenceState getState() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see at.silverstrike.pcc.api.persistence.Persistence#getSession()
     */
    @Override
    public final Session getSession() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * at.silverstrike.pcc.api.persistence.Persistence#createTask(java.lang.
     * String)
     */
    @Override
    public final Long createTask(final String aProcessName) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * at.silverstrike.pcc.api.persistence.Persistence#getTask(java.lang.Object)
     */
    @Override
    public final Task getTask(final Object aProcessid) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * at.silverstrike.pcc.api.persistence.Persistence#updateTask(at.silverstrike
     * .pcc.api.model.Task)
     */
    @Override
    public final void updateTask(final Task aProcess) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * at.silverstrike.pcc.api.persistence.Persistence#createProcessParent(java
     * .lang.String, java.lang.Long)
     */
    @Override
    public final void createProcessParent(final String aName,
            final Long aParentItemId) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * at.silverstrike.pcc.api.persistence.Persistence#getAllIntentsAndGoalRegions
     * ()
     */
    @Override
    public final List<Task> getAllIntentsAndGoalRegions() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * at.silverstrike.pcc.api.persistence.Persistence#createHumanResource(java
     * .lang.String, java.lang.String, java.lang.String, java.lang.String,
     * double)
     */
    @Override
    public final Long createHumanResource(final String aAbbreviation,
            final String aFirstName, final String aMiddleName,
            final String aSurname, final double aDailyMaxWorkTimeInHours) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see at.silverstrike.pcc.api.persistence.Persistence#getAllWorkers()
     */
    @Override
    public final List<Worker> getAllWorkers() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * at.silverstrike.pcc.api.persistence.Persistence#getChildTasks(at.silverstrike
     * .pcc.api.model.SchedulingObject)
     */
    @Override
    public final List<SchedulingObject> getChildTasks(
            final SchedulingObject aParent) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * at.silverstrike.pcc.api.persistence.Persistence#updateTaskEndTimes(java
     * .util.List)
     */
    @Override
    public final void updateTaskEndTimes(
            final List<ProcessEndTimeTuple> aEndTimeTuples) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * at.silverstrike.pcc.api.persistence.Persistence#generateDailyPlans(java
     * .util.Date)
     */
    @Override
    public final void generateDailyPlans(final Date aNow) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see at.silverstrike.pcc.api.persistence.Persistence#createBooking()
     */
    @Override
    public final Booking createBooking() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see at.silverstrike.pcc.api.persistence.Persistence#
     * getUncompletedTasksWithEstimatedEndTime()
     */
    @Override
    public final List<Task> getUncompletedTasksWithEstimatedEndTime() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * at.silverstrike.pcc.api.persistence.Persistence#getDailyPlan(java.util
     * .Date, java.lang.String)
     */
    @Override
    public final DailyPlan getDailyPlan(final Date aNewDate,
            final String aResource) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * at.silverstrike.pcc.api.persistence.Persistence#getSubProcessesWithChildren
     * (java.lang.Long)
     */
    @Override
    public final List<SchedulingObject> getSubProcessesWithChildren(
            final Long aProcessId, final UserData aUser) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * at.silverstrike.pcc.api.persistence.Persistence#createSiblingProcess(
     * java.lang.Long)
     */
    @Override
    public final void createSiblingProcess(final Long aSiblingProcessId) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * at.silverstrike.pcc.api.persistence.Persistence#createChildProcess(java
     * .lang.Long)
     */
    @Override
    public final void createChildProcess(final Long aParentProcessId) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * at.silverstrike.pcc.api.persistence.Persistence#deleteProcess(java.lang
     * .Long)
     */
    @Override
    public final void deleteProcess(final Long aSelectedProjectId) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * at.silverstrike.pcc.api.persistence.Persistence#getChildTasks(java.lang
     * .Long)
     */
    @Override
    public final List<SchedulingObject> getChildTasks(final Long aProcessId) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * at.silverstrike.pcc.api.persistence.Persistence#handoffProcess(java.lang
     * .Long, java.lang.Long)
     */
    @Override
    public final void
            handoffProcess(final Long aProcessId, final Long aWorkerId) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see at.silverstrike.pcc.api.persistence.Persistence#clearDatabase()
     */
    @Override
    public final void clearDatabase() {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * at.silverstrike.pcc.api.persistence.Persistence#getResource(java.lang
     * .Long)
     */
    @Override
    public final Resource getResource(final Long aResourceId) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see at.silverstrike.pcc.api.persistence.Persistence#getUserData()
     */
    @Override
    public final UserData getUserData() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * at.silverstrike.pcc.api.persistence.Persistence#createNewMilestone(java
     * .lang.String, java.lang.String, java.lang.Long)
     */
    @Override
    public final Milestone createNewMilestone(final String aUser,
            final String aName, final Long aParentTaskId) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * at.silverstrike.pcc.api.persistence.Persistence#deleteTask(at.silverstrike
     * .pcc.api.model.Task)
     */
    @Override
    public final boolean deleteTask(final Task aTask) {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * at.silverstrike.pcc.api.persistence.Persistence#deleteEvent(at.silverstrike
     * .pcc.api.model.Event)
     */
    @Override
    public final boolean deleteEvent(final Event aEvent) {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see at.silverstrike.pcc.api.persistence.Persistence#deleteMilestone(at.
     * silverstrike.pcc.api.model.Milestone)
     */
    @Override
    public final boolean deleteMilestone(final Milestone aMilestone) {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * at.silverstrike.pcc.api.persistence.Persistence#getPotentialDependencies
     * (at.silverstrike.pcc.api.model.SchedulingObject)
     */
    @Override
    public final List<SchedulingObject> getPotentialDependencies(
            final SchedulingObject aObject) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see at.silverstrike.pcc.api.persistence.Persistence#
     * isHighestPriorityObjectInProject
     * (at.silverstrike.pcc.api.model.SchedulingObject,
     * at.silverstrike.pcc.api.model.SchedulingObject)
     */
    @Override
    public final boolean isHighestPriorityObjectInProject(
            final SchedulingObject aSO1, final SchedulingObject aSO2) {
        // TODO Auto-generated method stub
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see at.silverstrike.pcc.api.persistence.Persistence#
     * isLowestPriorityObjectInProject
     * (at.silverstrike.pcc.api.model.SchedulingObject,
     * at.silverstrike.pcc.api.model.SchedulingObject)
     */
    @Override
    public final boolean isLowestPriorityObjectInProject(
            final SchedulingObject aSO1, final SchedulingObject aSO2) {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see at.silverstrike.pcc.api.persistence.Persistence#openSession()
     */
    @Override
    public final void openSession() {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see at.silverstrike.pcc.api.persistence.Persistence#updateMilestone(at.
     * silverstrike.pcc.api.model.Milestone)
     */
    @Override
    public final void updateMilestone(final Milestone aMilestone) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * at.silverstrike.pcc.api.persistence.Persistence#updateEvent(at.silverstrike
     * .pcc.api.model.Event)
     */
    @Override
    public final void updateEvent(final Event aEvent) {
        // TODO Auto-generated method stub

    }

    @Override
    public final boolean markTaskAsCompleted(final Task aTask) {
        // TODO Auto-generated method stub
        return false;
    }

}

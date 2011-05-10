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

package at.silverstrike.pcc.test.mockpersistence;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.google.inject.Injector;

import at.silverstrike.pcc.api.model.Booking;
import at.silverstrike.pcc.api.model.Event;
import at.silverstrike.pcc.api.model.Milestone;
import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.model.DailyPlan;
import at.silverstrike.pcc.api.model.Resource;
import at.silverstrike.pcc.api.model.UserData;
import at.silverstrike.pcc.api.model.Worker;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.persistence.PersistenceState;
import at.silverstrike.pcc.api.tj3bookingsparser.BookingTuple;
import at.silverstrike.pcc.api.tj3deadlinesparser.ProcessEndTimeTuple;

public abstract class MockPersistenceAdapter implements Persistence {
    @Override
    public void closeSession() {
    }

    @Override
    public Booking createBooking() {
        return null;
    }

    @Override
    public void createChildProcess(final Long aParentProcessId) {
    }

    @Override
    public Long createHumanResource(final String aAbbreviation,
            final String aFirstName,
            final String aMiddleName, final String aSurname,
            final double aWorkTime) {
        return null;
    }

    @Override
    public void
            createProcessParent(final String aName, final Long aParentItemId) {
    }

    @Override
    public void createSiblingProcess(final Long aSiblingProcessId) {

    }

    @Override
    public Task createSubTask(final String aProcessName,
            final Long aParentProcessId) {
        return null;
    }

    @Override
    public Long createTask(final String aProcessName) {
        return null;
    }

    @Override
    public void deleteProcess(final Long aSelectedProjectId) {

    }

    @Override
    public void generateDailyPlans(final Date aNow) {

    }

    @Override
    public List<Task> getAllIntentsAndGoalRegions() {
        return null;
    }

    @Override
    public List<SchedulingObject> getAllNotDeletedTasks() {
        return null;
    }

    @Override
    public List<Worker> getAllWorkers() {
        return null;
    }

    @Override
    public List<SchedulingObject> getChildTasks(final SchedulingObject aParent) {
        return null;
    }

    @Override
    public List<SchedulingObject> getChildTasks(final Long aProcessId) {
        return null;
    }

    @Override
    public DailyPlan getDailyPlan(final Date aNewDate, final String aResource) {
        return null;
    }

    @Override
    public Session getSession() {
        return null;
    }

    @Override
    public PersistenceState getState() {
        return null;
    }

    @Override
    public List<SchedulingObject> getSubProcessesWithChildren(
            final Long aProcessId) {
        return null;
    }

    @Override
    public Task getTask(final Object aProcessid) {
        return null;
    }

    @Override
    public List<Task> getUncompletedTasksWithEstimatedEndTime() {
        return null;
    }

    @Override
    public void handoffProcess(final Long aProcessId, final Long aWorkerId) {

    }

    @Override
    public void openSession() {
    }

    @Override
    public void updateBookings(final List<BookingTuple> aBookingTuples) {
    }

    @Override
    public void updateTask(final Task aProcess) {
    }

    @Override
    public void
            updateTaskEndTimes(final List<ProcessEndTimeTuple> aEndTimeTuples) {
    }

    @Override
    public void setInjector(final Injector aInjector) {
    }

    @Override
    public void clearDatabase() {
    }

    @Override
    public Resource getResource(final Long aResourceId) {
        return null;
    }

    @Override
    public UserData getUserData() {
        return null;
    }

    @Override
    public Milestone createNewMilestone(final String aUser, final String aName,
            final Long aParentTaskId) {
        return null;
    }

    @Override
    public Event createSubEvent(final String aProcessName,
            final Long aParentProcessId) {
        return null;
    }

    @Override
    public boolean deleteTask(final Task aTask) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteEvent(final Event aEvent) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteMilestone(final Milestone aMilestone) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void updateMilestone(final Milestone aMilestone) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateEvent(final Event aEvent) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<SchedulingObject> getPotentialDependencies(
            SchedulingObject aObject) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isHighestPriorityObjectInProject(SchedulingObject aProject,
            SchedulingObject aSchedulingObject) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isLowestPriorityObjectInProject(SchedulingObject aProject,
            SchedulingObject aSchedulingObject) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean markTaskAsCompleted(Task aTask) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public void
            updateSchedulingObject(final SchedulingObject aSchedulingObject) {
    }

    @Override
    public List<SchedulingObject> getSubProcessesWithChildrenInclAttainedTasks(
            final Long aProcessId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Worker getCurrentWorker() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hasChildren(final SchedulingObject aObject) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<SchedulingObject> getTopLevelTasks() {
        // TODO Auto-generated method stub
        return null;
    }
    
}

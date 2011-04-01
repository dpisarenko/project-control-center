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

package at.silverstrike.pcc.test.projecttreemodel;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.hibernate.Session;

import com.google.inject.Injector;

import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.model.Booking;
import at.silverstrike.pcc.api.model.DailyPlan;
import at.silverstrike.pcc.api.model.ProcessType;
import at.silverstrike.pcc.api.model.Resource;
import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.model.UserData;
import at.silverstrike.pcc.api.model.Worker;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.persistence.PersistenceState;
import at.silverstrike.pcc.api.tj3bookingsparser.BookingTuple;
import at.silverstrike.pcc.api.tj3deadlinesparser.ProcessEndTimeTuple;
import at.silverstrike.pcc.test.model.MockObjectFactory;

/**
 * @author DP118M
 * 
 */
final class MockPersistence implements Persistence {
    private MockObjectFactory mockObjectFactory = new MockObjectFactory();
    private boolean returnSubProcesses = true;
    
    @Override
    public void setInjector(final Injector aInjector) {
        throw new NotImplementedException();

    }

    @Override
    public void openSession() {
        throw new NotImplementedException();

    }

    @Override
    public void closeSession() {
        throw new NotImplementedException();

    }

    @Override
    public PersistenceState getState() {
        throw new NotImplementedException();

    }

    @Override
    public Session getSession() {
        throw new NotImplementedException();

    }

    @Override
    public Long createTask(final String aProcessName) {
        throw new NotImplementedException();

    }

    @Override
    public Task createSubTask(final String aProcessName,
            final Long aParentProcessId) {
        throw new NotImplementedException();

    }

    @Override
    public List<SchedulingObject> getAllNotDeletedTasks() {
        throw new NotImplementedException();

    }

    @Override
    public Task getTask(final Object aProcessid) {
        throw new NotImplementedException();

    }

    @Override
    public void updateTask(final Task aProcess) {
        throw new NotImplementedException();

    }

    @Override
    public void createProcessParent(final String aName,
            final Long aParentItemId,
            ProcessType aProcessType) {
        throw new NotImplementedException();

    }

    @Override
    public List<Task> getAllIntentsAndGoalRegions() {
        throw new NotImplementedException();

    }

    @Override
    public Long
            createHumanResource(final String aAbbreviation,
                    final String aFirstName,
                    final String aMiddleName, final String aSurname,
                    final double aDailyMaxWorkTimeInHours) {
        throw new NotImplementedException();

    }

    @Override
    public List<Worker> getAllWorkers() {
        throw new NotImplementedException();

    }

    @Override
    public List<SchedulingObject> getChildTasks(final SchedulingObject aParent) {
        throw new NotImplementedException();

    }

    @Override
    public void updateTaskEndTimes(
            final List<ProcessEndTimeTuple> aEndTimeTuples) {
        throw new NotImplementedException();

    }

    @Override
    public void updateBookings(final List<BookingTuple> aBookingTuples) {
        throw new NotImplementedException();

    }

    @Override
    public void generateDailyPlans(final Date aNow) {
        throw new NotImplementedException();

    }

    @Override
    public Booking createBooking() {
        throw new NotImplementedException();

    }

    @Override
    public List<Task> getUncompletedTasksWithEstimatedEndTime() {
        throw new NotImplementedException();

    }

    @Override
    public DailyPlan getDailyPlan(final Date aNewDate, final String aResource) {
        throw new NotImplementedException();

    }

    @Override
    public List<SchedulingObject> getSubProcessesWithChildren(
            final Long aProcessId) {
        final List<SchedulingObject> returnValue =
                new LinkedList<SchedulingObject>();

        if (returnSubProcesses) {
            final Task task = this.mockObjectFactory.createControlProcess();
            returnValue.add(task);
            this.returnSubProcesses = false;
        }

        return returnValue;
    }

    @Override
    public void createSiblingProcess(final Long aSiblingProcessId) {
        throw new NotImplementedException();

    }

    @Override
    public void createChildProcess(final Long aParentProcessId) {
        throw new NotImplementedException();

    }

    @Override
    public void deleteProcess(final Long aSelectedProjectId) {
        throw new NotImplementedException();

    }

    @Override
    public List<SchedulingObject> getChildTasks(final Long aProcessId) {
        throw new NotImplementedException();

    }

    @Override
    public void handoffProcess(final Long aProcessId, final Long aWorkerId) {
        throw new NotImplementedException();

    }

    @Override
    public void clearDatabase() {
        throw new NotImplementedException();

    }

    @Override
    public Resource getResource(final Long aResourceId) {
        throw new NotImplementedException();

    }

    @Override
    public UserData getUserData() {
        throw new NotImplementedException();

    }

    @Override
    public Task createNewTask(final String aUser, final Long aParentTaskId)
            throws PccException {
        throw new NotImplementedException();

    }

}

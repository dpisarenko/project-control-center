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

import org.apache.commons.lang.NotImplementedException;
import org.hibernate.Session;

import com.google.inject.Injector;

import at.silverstrike.pcc.api.model.Booking;
import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.model.DailyPlan;
import at.silverstrike.pcc.api.model.ProcessType;
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
    public Long createHumanResource(final String abbreviation,
            final String firstName,
            final String middleName, final String surname,
            final double aWorkTime) {
        throw new NotImplementedException();
    }

    @Override
    public void createProcessParent(final String name, final Long parentItemId,
            final ProcessType aProcessType) {
        throw new NotImplementedException();
    }

    @Override
    public void createSiblingProcess(final Long siblingProcessId) {
        throw new NotImplementedException();
    }

    @Override
    public void createSubTask(final String aProcessName, final Long aParentProcessId) {
        throw new NotImplementedException();
    }

    @Override
    public Long createTask(final String processName) {
        throw new NotImplementedException();
    }

    @Override
    public void deleteProcess(final Long selectedProjectId) {
        throw new NotImplementedException();
    }

    @Override
    public void generateDailyPlans(final Date aNow) {
        throw new NotImplementedException();
    }

    @Override
    public List<Task> getAllIntentsAndGoalRegions() {
        throw new NotImplementedException();
    }

    @Override
    public List<SchedulingObject> getAllNotDeletedTasks() {
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
    public List<SchedulingObject> getChildTasks(final Long aProcessId) {
        throw new NotImplementedException();
    }

    @Override
    public DailyPlan getDailyPlan(final Date newDate, final String resource) {
        throw new NotImplementedException();
    }

    @Override
    public Session getSession() {
        throw new NotImplementedException();
    }

    @Override
    public PersistenceState getState() {
        throw new NotImplementedException();
    }

    @Override
    public List<SchedulingObject> getSubProcessesWithChildren(final Long processId) {
        throw new NotImplementedException();
    }

    @Override
    public Task getTask(final Object aProcessid) {
        throw new NotImplementedException();
    }

    @Override
    public List<Task> getUncompletedTasksWithEstimatedEndTime() {
        throw new NotImplementedException();
    }

    @Override
    public void handoffProcess(final Long processId, final Long workerId) {
        throw new NotImplementedException();
    }

    @Override
    public void openSession() {
    }

    @Override
    public void updateBookings(final List<BookingTuple> bookingTuples) {
    }

    @Override
    public void updateTask(Task process) {
    }

    @Override
    public void updateTaskEndTimes(final List<ProcessEndTimeTuple> endTimeTuples) {
    }

    @Override
    public void setInjector(final Injector anInjector) {
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
}

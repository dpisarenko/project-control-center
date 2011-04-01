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

/**
 * @author DP118M
 * 
 */
final class MockPersistence implements Persistence {
    @Override
    public void setInjector(Injector aInjector) {
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
    public Long createTask(String aProcessName) {
        throw new NotImplementedException();

    }

    @Override
    public Task createSubTask(String aProcessName, Long aParentProcessId) {
        throw new NotImplementedException();

    }

    @Override
    public List<SchedulingObject> getAllNotDeletedTasks() {
        throw new NotImplementedException();

    }

    @Override
    public Task getTask(Object aProcessid) {
        throw new NotImplementedException();

    }

    @Override
    public void updateTask(Task aProcess) {
        throw new NotImplementedException();

    }

    @Override
    public void createProcessParent(String aName, Long aParentItemId,
            ProcessType aProcessType) {
        throw new NotImplementedException();

    }

    @Override
    public List<Task> getAllIntentsAndGoalRegions() {
        throw new NotImplementedException();

    }

    @Override
    public Long
            createHumanResource(String aAbbreviation, String aFirstName,
                    String aMiddleName, String aSurname,
                    double aDailyMaxWorkTimeInHours) {
        throw new NotImplementedException();

    }

    @Override
    public List<Worker> getAllWorkers() {
        throw new NotImplementedException();

    }

    @Override
    public List<SchedulingObject> getChildTasks(SchedulingObject aParent) {
        throw new NotImplementedException();

    }

    @Override
    public void updateTaskEndTimes(List<ProcessEndTimeTuple> aEndTimeTuples) {
        throw new NotImplementedException();

    }

    @Override
    public void updateBookings(List<BookingTuple> aBookingTuples) {
        throw new NotImplementedException();

    }

    @Override
    public void generateDailyPlans(Date aNow) {
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
    public DailyPlan getDailyPlan(Date aNewDate, String aResource) {
        throw new NotImplementedException();

    }

    @Override
    public List<SchedulingObject> getSubProcessesWithChildren(Long aProcessId) {
        throw new NotImplementedException();

    }

    @Override
    public void createSiblingProcess(Long aSiblingProcessId) {
        throw new NotImplementedException();

    }

    @Override
    public void createChildProcess(Long aParentProcessId) {
        throw new NotImplementedException();

    }

    @Override
    public void deleteProcess(Long aSelectedProjectId) {
        throw new NotImplementedException();

    }

    @Override
    public List<SchedulingObject> getChildTasks(Long aProcessId) {
        throw new NotImplementedException();

    }

    @Override
    public void handoffProcess(Long aProcessId, Long aWorkerId) {
        throw new NotImplementedException();

    }

    @Override
    public void clearDatabase() {
        throw new NotImplementedException();

    }

    @Override
    public Resource getResource(Long aResourceId) {
        throw new NotImplementedException();

    }

    @Override
    public UserData getUserData() {
        throw new NotImplementedException();

    }

    @Override
    public Task createNewTask(String aUser, Long aParentTaskId)
            throws PccException {
        throw new NotImplementedException();

    }

}

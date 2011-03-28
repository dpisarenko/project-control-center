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
        throw new NotImplementedException();
    }

    @Override
    public Booking createBooking() {
        throw new NotImplementedException();
    }

    @Override
    public void createChildProcess(Long parentProcessId) {
        throw new NotImplementedException();
    }

    @Override
    public Long createHumanResource(String abbreviation, String firstName,
            String middleName, String surname, final double aWorkTime) {
        throw new NotImplementedException();
    }

    @Override
    public void createProcessParent(String name, Long parentItemId,
            ProcessType aProcessType) {
        throw new NotImplementedException();
    }

    @Override
    public void createSiblingProcess(Long siblingProcessId) {
        throw new NotImplementedException();
    }

    @Override
    public void createSubTask(String aProcessName, Long aParentProcessId) {
        throw new NotImplementedException();
    }

    @Override
    public Long createTask(String processName) {
        throw new NotImplementedException();
    }

    @Override
    public void deleteProcess(Long selectedProjectId) {
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
    public List<Task> getAllNotDeletedTasks() {
        throw new NotImplementedException();
    }

    @Override
    public List<Worker> getAllWorkers() {
        throw new NotImplementedException();
    }

    @Override
    public List<Task> getChildTasks(Task aParent) {
        throw new NotImplementedException();
    }

    @Override
    public List<Task> getChildTasks(Long aProcessId) {
        throw new NotImplementedException();
    }

    @Override
    public DailyPlan getDailyPlan(Date newDate, String resource) {
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
    public List<Task> getSubProcessesWithChildren(Long processId) {
        throw new NotImplementedException();
    }

    @Override
    public Task getTask(Object aProcessid) {
        throw new NotImplementedException();
    }

    @Override
    public List<Task> getUncompletedTasksWithEstimatedEndTime() {
        throw new NotImplementedException();
    }

    @Override
    public void handoffProcess(Long processId, Long workerId) {
        throw new NotImplementedException();
    }

    @Override
    public void openSession() {
        throw new NotImplementedException();
    }

    @Override
    public void updateBookings(List<BookingTuple> bookingTuples) {
        throw new NotImplementedException();
    }

    @Override
    public void updateTask(Task process) {
        throw new NotImplementedException();
    }

    @Override
    public void updateTaskEndTimes(List<ProcessEndTimeTuple> endTimeTuples) {
        throw new NotImplementedException();
    }

    @Override
    public void setInjector(Injector anInjector) {
        throw new NotImplementedException();
    }

    @Override
    public void clearDatabase() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Resource getResource(Long aResourceId) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public UserData getUserData()
    {
    	return null;
    }
}

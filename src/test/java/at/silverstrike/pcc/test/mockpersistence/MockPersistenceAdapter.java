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
import at.silverstrike.pcc.api.model.InvitationRequest;
import at.silverstrike.pcc.api.model.Milestone;
import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.model.DailyPlan;
import at.silverstrike.pcc.api.model.Resource;
import at.silverstrike.pcc.api.model.UserData;
import at.silverstrike.pcc.api.model.Worker;
import at.silverstrike.pcc.api.openid.SupportedOpenIdProvider;
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
            final Long aParentProcessId, final UserData aUser) {
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
    public List<SchedulingObject> getAllNotDeletedTasks(final UserData aUser) {
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
            final Long aProcessId, final UserData aUser) {
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
            final Long aParentProcessId, final UserData aUser) {
        return null;
    }

    @Override
    public boolean deleteTask(final Task aTask) {
        
        return false;
    }

    @Override
    public boolean deleteEvent(final Event aEvent) {
        
        return false;
    }

    @Override
    public boolean deleteMilestone(final Milestone aMilestone) {
        
        return false;
    }

    @Override
    public void updateMilestone(final Milestone aMilestone) {
        

    }

    @Override
    public void updateEvent(final Event aEvent) {
        

    }

    @Override
    public List<SchedulingObject> getPotentialDependencies(
            final SchedulingObject aObject) {
        
        return null;
    }

    @Override
    public boolean isHighestPriorityObjectInProject(final SchedulingObject aProject,
            final SchedulingObject aSchedulingObject) {
        
        return false;
    }

    @Override
    public boolean isLowestPriorityObjectInProject(SchedulingObject aProject,
            SchedulingObject aSchedulingObject) {
        
        return false;
    }

    @Override
    public boolean markTaskAsCompleted(final Task aTask) {
        
        return false;
    }
    @Override
    public void
            updateSchedulingObject(final SchedulingObject aSchedulingObject) {
    }

    @Override
    public List<SchedulingObject> getSubProcessesWithChildrenInclAttainedTasks(
            final Long aProcessId) {
        
        return null;
    }

    @Override
    public Worker getCurrentWorker(final UserData aUser) {
        
        return null;
    }

    @Override
    public boolean hasChildren(final SchedulingObject aObject) {
        
        return false;
    }


    @Override
    public void createInvitationRequest(
            final SupportedOpenIdProvider aOpenIdProvider, final String aUserUrl) {
        
        
    }

    @Override
    public List<InvitationRequest> getInvitationRequests() {
        
        return null;
    }

    @Override
    public void rejectInvitationRequest(final InvitationRequest aSelectedRequest) {
        
        
    }
    @Override
    public void acceptInvitationRequest(final InvitationRequest aRequest,
            final String aUserIdentity) {
    }

    @Override
    public void createSuperUser() {
        
        
    }

    @Override
    public Long getUserCount() {
        
        return null;
    }

    @Override
    public UserData getUser(final String aUuserName, final String aPassword) {
        
        return null;
    }    
    
    @Override
    public List<SchedulingObject> getTopLevelTasks(final UserData aUser) {
        return null;
    }

    @Override
    public void updateUser(final UserData aUser) {
        
        
    }

    @Override
    public List<Booking> getBookings(final UserData aUser) {
        
        return null;
    }

    @Override
    public void removeUserSchedulingObjects(final UserData aUser) {
        
        
    }    
    
}

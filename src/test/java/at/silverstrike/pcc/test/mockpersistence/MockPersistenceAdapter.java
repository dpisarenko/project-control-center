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

package at.silverstrike.pcc.test.mockpersistence;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.hibernate.Session;

import com.google.inject.Injector;

import at.silverstrike.pcc.api.model.Booking;
import at.silverstrike.pcc.api.model.ControlProcess;
import at.silverstrike.pcc.api.model.DailyPlan;
import at.silverstrike.pcc.api.model.ProcessType;
import at.silverstrike.pcc.api.model.Resource;
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
            String middleName, String surname) {
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
    public void generateDailyPlans() {
        throw new NotImplementedException();
    }

    @Override
    public List<ControlProcess> getAllIntentsAndGoalRegions() {
        throw new NotImplementedException();
    }

    @Override
    public List<ControlProcess> getAllNotDeletedTasks() {
        throw new NotImplementedException();
    }

    @Override
    public List<Worker> getAllWorkers() {
        throw new NotImplementedException();
    }

    @Override
    public List<ControlProcess> getChildTasks(ControlProcess aParent) {
        throw new NotImplementedException();
    }

    @Override
    public List<ControlProcess> getChildTasks(Long aProcessId) {
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
    public List<ControlProcess> getSubProcessesWithChildren(Long processId) {
        throw new NotImplementedException();
    }

    @Override
    public ControlProcess getTask(Object aProcessid) {
        throw new NotImplementedException();
    }

    @Override
    public List<ControlProcess> getUncompletedTasksWithEstimatedEndTime() {
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
    public void updateTask(ControlProcess process) {
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
    
}

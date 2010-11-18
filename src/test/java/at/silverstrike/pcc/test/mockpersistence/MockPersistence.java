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
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.hibernate.Session;

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

import com.google.inject.Injector;

class MockPersistence implements Persistence {
    public static final long TASK_ID_1 = 1L;
    
    @Override
    public void closeSession() {
        throw new NotImplementedException();
    }

    @Override
    public Booking createBooking() {
        throw new NotImplementedException();
    }

    @Override
    public void createChildProcess(final Long parentProcessId) {
        throw new NotImplementedException();

    }

    @Override
    public Long createHumanResource(final String abbreviation,
            final String firstName,
            final String middleName, final String surname,
            final double aWorkHours) {
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
    public void createSubTask(final String aProcessName,
            final Long aParentProcessId) {
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
    public List<ControlProcess> getChildTasks(final ControlProcess aParent) {
        return new LinkedList<ControlProcess>();
    }

    @Override
    public List<ControlProcess> getChildTasks(final Long aProcessId) {
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
    public List<ControlProcess> getSubProcessesWithChildren(final Long processId) {
        throw new NotImplementedException();
    }

    @Override
    public ControlProcess getTask(final Object aProcessid) {
        
        throw new NotImplementedException();
    }

    @Override
    public List<ControlProcess> getUncompletedTasksWithEstimatedEndTime() {
        throw new NotImplementedException();
    }

    @Override
    public void handoffProcess(final Long processId, final Long workerId) {
        throw new NotImplementedException();

    }

    @Override
    public void openSession() {
        throw new NotImplementedException();

    }

    @Override
    public void setInjector(final Injector anInjector) {
        throw new NotImplementedException();

    }

    @Override
    public void updateBookings(final List<BookingTuple> bookingTuples) {
        throw new NotImplementedException();

    }

    @Override
    public void updateTask(final ControlProcess process) {
        throw new NotImplementedException();

    }

    @Override
    public void updateTaskEndTimes(final List<ProcessEndTimeTuple> endTimeTuples) {
        throw new NotImplementedException();

    }

    @Override
    public void clearDatabase() {
        throw new NotImplementedException();
    }

    @Override
    public Resource getResource(Long aResourceId) {
        // TODO Auto-generated method stub
        return null;
    }

}

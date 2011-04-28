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

package at.silverstrike.pcc.test.model;

import at.silverstrike.pcc.api.model.Booking;
import at.silverstrike.pcc.api.model.Event;
import at.silverstrike.pcc.api.model.Milestone;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.model.DailyLimitResourceAllocation;
import at.silverstrike.pcc.api.model.DailyPlan;
import at.silverstrike.pcc.api.model.DailySchedule;
import at.silverstrike.pcc.api.model.DailyToDoList;
import at.silverstrike.pcc.api.model.Resource;
import at.silverstrike.pcc.api.model.ResourceAllocation;
import at.silverstrike.pcc.api.model.UserData;
import at.silverstrike.pcc.api.model.Worker;

public class MockObjectFactory {

    public final UserData createUserData() {
        return new MockUserData();
    }

    public final Task createControlProcess(final Long aId) {
        final MockControlProcess mockControlProcess = new MockControlProcess();

        mockControlProcess.setId(aId);

        return mockControlProcess;
    }

    public final Task createControlProcess() {
        return new MockControlProcess();
    }

    public final Booking createBooking() {
        return new MockBooking();
    }

    public final DailyLimitResourceAllocation
            createDailyLimitResourceAllocation() {
        return new MockDailyLimitResourceAllocation();
    }

    public final DailyPlan createDailyPlan() {
        return new MockDailyPlan();
    }

    public final DailySchedule createDailySchedule() {
        return new MockDailySchedule();
    }

    public final DailyToDoList createDailyToDoList() {
        return new MockDailyToDoList();
    }

    public final Resource createResource() {
        return new MockResource();
    }

    public final ResourceAllocation createResourceAllocation() {
        return new MockResourceAllocation();
    }

    public final Worker createWorker() {
        return new MockWorker();
    }

    public final Milestone createMilestone() {
        return new MockMilestone();
    }

    public final Event createEvent() {
        return new MockEvent();
    }

    public final Task createTask() {
        return new MockControlProcess();
    }
}

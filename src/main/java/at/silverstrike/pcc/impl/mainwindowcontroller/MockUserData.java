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

package at.silverstrike.pcc.impl.mainwindowcontroller;

import java.util.List;

import at.silverstrike.pcc.api.model.Booking;
import at.silverstrike.pcc.api.model.ControlProcess;
import at.silverstrike.pcc.api.model.DailyPlan;
import at.silverstrike.pcc.api.model.UserData;

final class MockUserData implements UserData {

    private String identifier;
    private List<ControlProcess> processes;
    private List<DailyPlan> dailyPlans;
    private List<Booking> bookings;

    public String getIdentifier() {
            return identifier;
    }
    public void setIdentifier(final String aIdentifier) {
            this.identifier = aIdentifier;
    }
    public List<ControlProcess> getProcesses() {
            return processes;
    }
    public void setProcesses(final List<ControlProcess> aProcesses) {
            this.processes = aProcesses;
    }
    public List<DailyPlan> getDailyPlans() {
            return dailyPlans;
    }
    public void setDailyPlans(final List<DailyPlan> aDailyPlans) {
            this.dailyPlans = aDailyPlans;
    }
    public List<Booking> getBookings() {
            return bookings;
    }
    public void setBookings(final List<Booking> aBookings) {
            this.bookings = aBookings;
    }
    
}
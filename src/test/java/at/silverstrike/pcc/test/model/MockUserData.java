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

import java.util.List;

import com.google.gdata.client.authn.oauth.GoogleOAuthParameters;

import at.silverstrike.pcc.api.model.Booking;
import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.model.DailyPlan;
import at.silverstrike.pcc.api.model.UserData;

class MockUserData implements UserData {
    private String identifier;
    private List<SchedulingObject> processes;
    private List<DailyPlan> dailyPlans;
    private List<Booking> bookings;
    private String googleUsername;
    private String googlePassword;

    
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(final String aIdentifier) {
        this.identifier = aIdentifier;
    }

    public List<SchedulingObject> getSchedulingData() {
        return processes;
    }

    public void setSchedulingData(final List<SchedulingObject> aProcesses) {
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

    @Override
    public void setUsername(String aUsername) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setPassword(String aPassword) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long getId() {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public String getResourceName() {
        final StringBuilder builder = new StringBuilder();
        
        builder.append("R");
        builder.append(this.identifier);
        
        return builder.toString();
    }

    public String getGoogleUsername() {
        return googleUsername;
    }

    public void setGoogleUsername(String googleUsername) {
        this.googleUsername = googleUsername;
    }

    public String getGooglePassword() {
        return googlePassword;
    }

    public void setGooglePassword(String googlePassword) {
        this.googlePassword = googlePassword;
    }

    @Override
    public void setCalendarOAuthParameters(
            GoogleOAuthParameters aOauthParameters) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public GoogleOAuthParameters getCalendarOAuthParameters() {
        // TODO Auto-generated method stub
        return null;
    }
}

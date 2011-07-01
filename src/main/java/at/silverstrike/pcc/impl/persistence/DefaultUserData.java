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

package at.silverstrike.pcc.impl.persistence;

import java.util.List;

import com.google.gdata.client.authn.oauth.GoogleOAuthParameters;

import at.silverstrike.pcc.api.model.Booking;
import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.model.DailyPlan;
import at.silverstrike.pcc.api.model.UserData;

class DefaultUserData implements UserData {
    private String identifier;
    private List<SchedulingObject> schedulingObjects;
    private List<DailyPlan> dailyPlans;
    private List<Booking> bookings;
    private String username;
    private String password;
    private Long id;
    private String googleUsername;
    private String googlePassword;
    private GoogleOAuthParameters oauthParameters;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(final String aIdentifier) {
        this.identifier = aIdentifier;
    }

    public List<SchedulingObject> getSchedulingData() {
        return schedulingObjects;
    }

    public void setSchedulingData(final List<SchedulingObject> aProcesses) {
        this.schedulingObjects = aProcesses;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long aId) {
        this.id = aId;
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

    public void setGoogleUsername(final String aGoogleUsername) {
        this.googleUsername = aGoogleUsername;
    }

    public String getGooglePassword() {
        return googlePassword;
    }

    public void setGooglePassword(final String aGooglePassword) {
        this.googlePassword = aGooglePassword;
    }

    public void setCalendarOAuthParameters(
            final GoogleOAuthParameters aOauthParameters) {
        oauthParameters = aOauthParameters;
    }

    @Override
    public GoogleOAuthParameters getCalendarOAuthParameters() {
        return oauthParameters;
    }
}

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

import java.util.Date;
import at.silverstrike.pcc.api.model.Booking;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.model.Resource;
import at.silverstrike.pcc.api.model.UserData;

class MockBooking implements Booking {

    private Long id;
    private Task process;
    private Resource resource;
    private Date startDateTime;
    private double durationInHours;
    private Date endDateTime;
    private UserData user;

    public UserData getUserData() {
            return user;
    }

    public void setUserData(UserData user) {
            this.user = user;
    }
    public Long getId() {
        return this.id;
    }

    public void setProcess(final Task aProcess) {
        this.process = aProcess;
    }

    public Task getProcess() {
        return this.process;
    }

    public void setResource(final Resource aResource) {
        this.resource = aResource;
    }

    public Resource getResource() {
        return this.resource;
    }

    public void setStartDateTime(final Date aDate) {
        this.startDateTime = aDate;
    }

    public Date getStartDateTime() {
        return this.startDateTime;
    }

    public void setDuration(final double aDurationInHours) {
        this.durationInHours = aDurationInHours;
    }

    public double getDuration() {
        return this.durationInHours;
    }

    public Date getEndDateTime() {
        return this.endDateTime;
    }

}

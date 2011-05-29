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

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import ru.altruix.commons.impl.util.Utils;
import at.silverstrike.pcc.api.model.Booking;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.model.Resource;
import at.silverstrike.pcc.api.model.UserData;

/**
 * @author Dmitri Pisarenko
 * 
 */
class DefaultBooking implements Booking {
    private Long id;
    private Task process;
    private Resource resource;
    private Date startDateTime;
    private double duration;
    private UserData user;

    public Task getProcess() {
        return process;
    }

    public void setProcess(final Task aProcess) {
        this.process = aProcess;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(final Resource aResource) {
        this.resource = aResource;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(final Date aStartDateTime) {
        this.startDateTime = aStartDateTime;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(final double aDuration) {
        this.duration = aDuration;
    }

    @Override
    public Date getEndDateTime() {
        return Utils.addHours(this.startDateTime, this.duration);
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long aId) {
        this.id = aId;
    }

    @Override
    public String toString() {
        final ToStringBuilder toStringBuilder = new ToStringBuilder(this).
                append("id", id).
                append("process", process).
                append("resource", resource).
                append("startDateTime", startDateTime).
                append("duration", duration);
        return toStringBuilder.toString();
    }

    public UserData getUserData() {
        return user;
    }

    public void setUserData(final UserData aUser) {
        this.user = aUser;
    }
}

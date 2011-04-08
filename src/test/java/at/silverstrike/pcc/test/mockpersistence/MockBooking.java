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

import at.silverstrike.pcc.api.model.Booking;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.model.Resource;
import at.silverstrike.pcc.impl.util.Utils;

public final class MockBooking implements Booking {
    private Long id;
    private Task process;
    private Resource resource;
    private Date startDateTime;
    private double duration;

    public Long getId() {
        return id;
    }

    public void setId(final Long aId) {
        this.id = aId;
    }

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

    public void setStartDateTime(final Date aDateTime) {
        this.startDateTime = aDateTime;
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
}

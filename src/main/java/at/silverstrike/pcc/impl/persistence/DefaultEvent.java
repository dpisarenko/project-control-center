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

import at.silverstrike.pcc.api.model.Event;

/**
 * @author DP118M
 *
 */
class DefaultEvent extends DefaultSchedulingObject implements Event {
    private String place;
    private Date startDateTime;
    private Date endDateTime;
    
    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }
    public Date getStartDateTime() {
        return startDateTime;
    }
    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }
    public Date getEndDateTime() {
        return endDateTime;
    }
    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }
}

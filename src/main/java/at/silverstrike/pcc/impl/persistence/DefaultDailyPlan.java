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

import at.silverstrike.pcc.api.model.DailyPlan;
import at.silverstrike.pcc.api.model.DailySchedule;
import at.silverstrike.pcc.api.model.DailyToDoList;
import at.silverstrike.pcc.api.model.Resource;
import at.silverstrike.pcc.api.model.UserData;

/**
 * @author Dmitri Pisarenko
 * 
 */
class DefaultDailyPlan implements DailyPlan {
    private Resource resource;

    private Date date;

    private DailyToDoList toDoList;

    private DailySchedule schedule;

    private Long id;
    private UserData user;

    public Resource getResource() {
        return resource;
    }

    public void setResource(final Resource aResource) {
        this.resource = aResource;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(final Date aDate) {
        this.date = aDate;
    }

    public DailyToDoList getToDoList() {
        return toDoList;
    }

    public void setToDoList(final DailyToDoList aToDoList) {
        this.toDoList = aToDoList;
    }

    public DailySchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(final DailySchedule aSchedule) {
        this.schedule = aSchedule;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long aId) {
        this.id = aId;
    }

    public UserData getUser() {
        return user;
    }

    public void setUser(final UserData aUser) {
        this.user = aUser;
    }
}

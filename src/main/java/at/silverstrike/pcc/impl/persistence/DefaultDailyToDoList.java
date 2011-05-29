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

import java.util.LinkedList;
import java.util.List;

import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.model.DailyToDoList;
import at.silverstrike.pcc.api.model.UserData;

/**
 * @author Dmitri Pisarenko
 * 
 */
class DefaultDailyToDoList implements DailyToDoList {
    private List<Task> tasksToCompleteToday;
    private Long id;
    private UserData user;

    public DefaultDailyToDoList() {
        this.tasksToCompleteToday = new LinkedList<Task>();
    }

    public List<Task> getTasksToCompleteToday() {
        return tasksToCompleteToday;
    }

    public void setTasksToCompleteToday(
            final List<Task> aTasksToCompleteToday) {
        this.tasksToCompleteToday = aTasksToCompleteToday;
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

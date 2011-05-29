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
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.model.DailyToDoList;
import at.silverstrike.pcc.api.model.UserData;

class MockDailyToDoList implements DailyToDoList {
    private Long id;
    private List<Task> tasksToCompleteToday;
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

    public void setTasksToCompleteToday(
            final List<Task> aTasksToCompleteToday) {
        this.tasksToCompleteToday = aTasksToCompleteToday;
    }

    public List<Task> getTasksToCompleteToday() {
        return this.tasksToCompleteToday;
    }

}

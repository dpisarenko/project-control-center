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

import at.silverstrike.pcc.api.model.ControlProcess;
import at.silverstrike.pcc.api.model.DailyToDoList;

/**
 * @author Dmitri Pisarenko
 * 
 */
class DefaultDailyToDoList implements DailyToDoList {
    private List<ControlProcess> tasksToCompleteToday;
    private Long id;

    public DefaultDailyToDoList() {
        this.tasksToCompleteToday = new LinkedList<ControlProcess>();
    }

    public List<ControlProcess> getTasksToCompleteToday() {
        return tasksToCompleteToday;
    }

    public void setTasksToCompleteToday(
            final List<ControlProcess> aTasksToCompleteToday) {
        this.tasksToCompleteToday = aTasksToCompleteToday;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long aId) {
        this.id = aId;
    }
}

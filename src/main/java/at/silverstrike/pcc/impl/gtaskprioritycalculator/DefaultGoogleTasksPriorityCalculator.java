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

package at.silverstrike.pcc.impl.gtaskprioritycalculator;

import java.util.Map;

import ru.altruix.commons.api.di.PccException;

import com.google.api.services.tasks.v1.model.Task;

import at.silverstrike.pcc.api.gtaskprioritycalculator.GoogleTasksPriorityCalculator;

/**
 * @author DP118M
 *
 */
class DefaultGoogleTasksPriorityCalculator implements
        GoogleTasksPriorityCalculator {
    private Map<String, Task> tasksByTaskIds;
    private Map<String, Long> prioritiesByTaskIds;
    
    @Override
    public void run() throws PccException {
        // TODO Auto-generated method stub

    }

    @Override
    public void setTasks(final Map<String, Task> aTasksByTaskIds) {
        this.tasksByTaskIds = aTasksByTaskIds;
    }

    @Override
    public Map<String, Long> getPrioritiesByTaskIds() {
        return prioritiesByTaskIds;
    }

}

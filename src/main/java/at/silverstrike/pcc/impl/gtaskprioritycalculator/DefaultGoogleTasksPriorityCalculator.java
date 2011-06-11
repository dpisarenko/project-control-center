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

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
    private Map<String, Integer> prioritiesByTaskIds;

    @Override
    public void run() throws PccException {
        final List<GoogleTaskIdAndPriorityTuple> tuples = getTuples();
        Collections.sort(tuples);
        setPriorities(tuples);
    }

    private void
            setPriorities(final List<GoogleTaskIdAndPriorityTuple> aTuples) {
        int curPriority = 1000;
        
        this.prioritiesByTaskIds = new HashMap<String, Integer>();
        
        for (final GoogleTaskIdAndPriorityTuple curTuple : aTuples) {
            this.prioritiesByTaskIds.put(curTuple.getId(), curPriority);
            curPriority--;
        }
    }

    private List<GoogleTaskIdAndPriorityTuple> getTuples() {
        final List<GoogleTaskIdAndPriorityTuple> returnValue =
                new LinkedList<GoogleTaskIdAndPriorityTuple>();
        for (final Task curTask : tasksByTaskIds.values())
        {
            final GoogleTaskIdAndPriorityTuple curTuple = new GoogleTaskIdAndPriorityTuple();
            
            curTuple.setId(curTask.id);
            curTuple.setPosition(curTask.position);
            
            returnValue.add(curTuple);
        }

        return returnValue;
    }

    @Override
    public void setTasks(final Map<String, Task> aTasksByTaskIds) {
        this.tasksByTaskIds = aTasksByTaskIds;
    }

    @Override
    public Map<String, Integer> getPrioritiesByTaskIds() {
        return prioritiesByTaskIds;
    }

}

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

package at.silverstrike.pcc.impl.gtaskrelevance;

import ru.altruix.commons.api.di.PccException;
import at.silverstrike.pcc.api.gtaskrelevance.IsGoogleTaskRelevantCalculator;

/**
 * @author DP118M
 *
 */
class DefaultIsGoogleTaskRelevantCalculator implements
        IsGoogleTaskRelevantCalculator {
    private com.google.api.services.tasks.v1.model.Task task;
    private boolean relevant;
    
    @Override
    public void run() throws PccException {
        this.relevant = (task.completed == null);
    }

    @Override
    public void setGoogleTask(final com.google.api.services.tasks.v1.model.Task aTask) {
        this.task = aTask;
    }

    @Override
    public boolean isRelevant() {
        return this.relevant;
    }
}

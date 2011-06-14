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

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Injector;

import ru.altruix.commons.api.di.PccException;
import at.silverstrike.pcc.api.gtasknoteparser.GoogleTaskNotesParser;
import at.silverstrike.pcc.api.gtasknoteparser.GoogleTaskNotesParserFactory;
import at.silverstrike.pcc.api.gtaskrelevance.IsGoogleTaskRelevantCalculator;

/**
 * @author DP118M
 * 
 */
class DefaultIsGoogleTaskRelevantCalculator implements
        IsGoogleTaskRelevantCalculator {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultIsGoogleTaskRelevantCalculator.class);

    private com.google.api.services.tasks.v1.model.Task task;
    private boolean relevant;
    private Injector injector;

    @Override
    public void run() throws PccException {
        final boolean completed = (task.completed != null);
        final boolean topLevelTask = StringUtils.isBlank(task.parent);
        final boolean effortSpecified = isEffortSpecified(task.notes);
        final boolean titleBlank = StringUtils.isBlank(task.title);

        LOGGER.debug("completed: {}, topLevelTask: {}, effortSpecified: {}",
                new Object[] { completed, topLevelTask, effortSpecified });
        if (titleBlank) {
            this.relevant = false;
        } else if (completed) {
            this.relevant = false;
        } else if (topLevelTask && !effortSpecified) {
            this.relevant = true;
        } else if (!topLevelTask && effortSpecified) {
            this.relevant = true;
        } else if (!topLevelTask && effortSpecified) {
            this.relevant = true;
        } else if (topLevelTask && effortSpecified) {
            this.relevant = true;
        } else {
            this.relevant = false;
        }
    }

    private boolean isEffortSpecified(final String aNotes) {
        final GoogleTaskNotesParserFactory factory =
                this.injector.getInstance(GoogleTaskNotesParserFactory.class);
        final GoogleTaskNotesParser parser = factory.create();
        boolean returnValue = false;

        try {
            parser.setNotes(aNotes);
            parser.run();
            returnValue = parser.isEffortSpecified();
        } catch (final PccException exception) {
            LOGGER.error("", exception);
        }

        return returnValue;
    }

    @Override
    public void setGoogleTask(
            final com.google.api.services.tasks.v1.model.Task aTask) {
        this.task = aTask;
    }

    @Override
    public boolean isRelevant() {
        return this.relevant;
    }

    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;
    }
}

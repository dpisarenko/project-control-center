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

package at.silverstrike.pcc.impl.gtask2pcctaskconverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Injector;

import ru.altruix.commons.api.di.PccException;

import at.silverstrike.pcc.api.gtask2pcctaskconverter.GoogleTask2PccTaskConverter;
import at.silverstrike.pcc.api.gtasknoteparser.GoogleTaskNotesParser;
import at.silverstrike.pcc.api.gtasknoteparser.GoogleTaskNotesParserFactory;
import at.silverstrike.pcc.api.gtasktitleparser.GoogleTaskTitleParser;
import at.silverstrike.pcc.api.gtasktitleparser.GoogleTaskTitleParserFactory;
import at.silverstrike.pcc.api.model.UserData;
import at.silverstrike.pcc.api.persistence.Persistence;

/**
 * @author DP118M
 * 
 */
class DefaultGoogleTask2PccTaskConverter implements GoogleTask2PccTaskConverter {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultGoogleTask2PccTaskConverter.class);

    private com.google.api.services.tasks.v1.model.Task googleTask;
    private at.silverstrike.pcc.api.model.Task task;
    private UserData user;
    private Injector injector;
    private Persistence persistence;
    private GoogleTaskNotesParser notesParser;
    private GoogleTaskTitleParser titleParser;

    @Override
    public void run() throws PccException {
        this.task =
                persistence.createSubTask(googleTask.title, null,
                        this.user);
        try {
            this.notesParser.setNotes(googleTask.notes);
            this.notesParser.run();

            if (this.notesParser.isEffortSpecified()) {
                final double effortInHours =
                        this.notesParser.getEffortInHours();
                this.task.setBestCaseEffort(effortInHours);
                this.task.setWorstCaseEffort(effortInHours);
            }

            this.titleParser.setTitle(googleTask.title);
            this.titleParser.run();

            if (this.titleParser.isLabelSpecified()) {
                this.task.setLabel(this.titleParser.getLabel());
            }
        } catch (final PccException exception) {
            LOGGER.error("", exception);
        }

    }

    @Override
    public void setGoogleTask(
            final com.google.api.services.tasks.v1.model.Task aTask) {
        this.googleTask = aTask;
    }

    @Override
    public at.silverstrike.pcc.api.model.Task getPccTask() {
        return this.task;
    }

    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;
        if (this.injector != null) {
            this.persistence = this.injector.getInstance(Persistence.class);

            final GoogleTaskNotesParserFactory noteParserFactory =
                    this.injector
                            .getInstance(GoogleTaskNotesParserFactory.class);
            this.notesParser = noteParserFactory.create();

            final GoogleTaskTitleParserFactory titleParserFactory =
                    this.injector
                            .getInstance(GoogleTaskTitleParserFactory.class);
            this.titleParser = titleParserFactory.create();
        }
    }

    @Override
    public void setUser(final UserData aUser) {
        this.user = aUser;
    }
}

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

package at.silverstrike.pcc.impl.gcaltasks2pcc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.altruix.commons.api.di.PccException;

import com.google.api.services.tasks.v1.Tasks;
import com.google.inject.Injector;

import at.silverstrike.pcc.api.gcaltasks2pcc.GoogleCalendarTasks2PccImporter;
import at.silverstrike.pcc.api.gtask2pcctaskconverter.GoogleTask2PccTaskConverter;
import at.silverstrike.pcc.api.gtask2pcctaskconverter.GoogleTask2PccTaskConverterFactory;
import at.silverstrike.pcc.api.gtaskrelevance.IsGoogleTaskRelevantCalculator;
import at.silverstrike.pcc.api.gtaskrelevance.IsGoogleTaskRelevantCalculatorFactory;
import at.silverstrike.pcc.api.model.UserData;
import at.silverstrike.pcc.api.persistence.Persistence;

/**
 * @author DP118M
 * 
 */
class DefaultGoogleCalendarTasks2PccImporter implements
        GoogleCalendarTasks2PccImporter {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultGoogleCalendarTasks2PccImporter.class);

    private Injector injector;
    private UserData user;
    private Tasks service;

    @Override
    public void run() throws PccException {
        final Persistence persistence =
                this.injector.getInstance(Persistence.class);

        try {
            final com.google.api.services.tasks.v1.model.Tasks tasks =
                    service.tasks.list("@default").execute();

            final Map<String, com.google.api.services.tasks.v1.model.Task> relevantTasksByIds =
                    new HashMap<String,
                    com.google.api.services.tasks.v1.model.Task>();

            getRelevantGoogleTasks(tasks, relevantTasksByIds);

            LOGGER.debug("Relevant tasks: " + relevantTasksByIds.size());

            // Create tasks in PCC database
            final Map<String, at.silverstrike.pcc.api.model.Task> pccTasksByGoogleIds =
                    createPccTasks(relevantTasksByIds, persistence);

            // Set parents
            setParents(persistence, relevantTasksByIds, pccTasksByGoogleIds);
        } catch (final IOException exception) {
            LOGGER.error("", exception);
        }
    }

    private
            void
            setParents(
                    final Persistence aPersistence,
                    final Map<String, com.google.api.services.tasks.v1.model.Task> aRelevantTasksByIds,
                    final Map<String, at.silverstrike.pcc.api.model.Task> aPccTasksByGoogleIds) {
        for (final String curGoogleTaskId : aRelevantTasksByIds.keySet()) {
            // Fetch child Google Task
            final com.google.api.services.tasks.v1.model.Task childTask =
                    aRelevantTasksByIds.get(curGoogleTaskId);

            // Fetch parent ID
            final String parentId = childTask.parent;

            if (parentId != null) {
                // Fetch child PCC task
                final at.silverstrike.pcc.api.model.Task childPccTask =
                        aPccTasksByGoogleIds.get(curGoogleTaskId);

                // Fetch parent PCC task
                final at.silverstrike.pcc.api.model.Task parentPccTask =
                        aPccTasksByGoogleIds.get(parentId);

                // Update child task
                childPccTask.setParent(parentPccTask);

                aPersistence.updateTask(childPccTask);
            }
        }
    }

    private
            Map<String, at.silverstrike.pcc.api.model.Task>
            createPccTasks(
                    final Map<String, com.google.api.services.tasks.v1.model.Task> aRelevantTasksByIds,
                    final Persistence aPersistence) {
        final GoogleTask2PccTaskConverterFactory factory =
                this.injector
                        .getInstance(GoogleTask2PccTaskConverterFactory.class);
        final GoogleTask2PccTaskConverter converter = factory.create();

        converter.setUser(this.user);
        converter.setInjector(this.injector);

        final Map<String, at.silverstrike.pcc.api.model.Task> pccTasksByGoogleIds =
                new HashMap<String, at.silverstrike.pcc.api.model.Task>();

        for (final com.google.api.services.tasks.v1.model.Task curTask : aRelevantTasksByIds
                .values()) {
            try {
                converter.setGoogleTask(curTask);
                converter.run();

                final at.silverstrike.pcc.api.model.Task pccTask =
                        converter.getPccTask();

                pccTasksByGoogleIds.put(curTask.id, pccTask);
            } catch (final PccException exception) {
                LOGGER.error("", exception);
            }
        }
        return pccTasksByGoogleIds;
    }

    private
            void
            getRelevantGoogleTasks(
                    final com.google.api.services.tasks.v1.model.Tasks tasks,
                    final Map<String, com.google.api.services.tasks.v1.model.Task> relevantTasksByIds) {
        final IsGoogleTaskRelevantCalculatorFactory factory =
                this.injector
                        .getInstance(IsGoogleTaskRelevantCalculatorFactory.class);
        final IsGoogleTaskRelevantCalculator relevanceCalculator =
                factory.create();

        for (final com.google.api.services.tasks.v1.model.Task curTask : tasks.items) {
            LOGGER.debug(
                    "Task list: title='{}', completed='{}', id='{}', kind='{}', notes='{}', parent='{}', position='{}', status='{}', updated='{}'",
                    new Object[] { curTask.title, curTask.completed,
                            curTask.id, curTask.kind, curTask.notes,
                            curTask.parent, curTask.position, curTask.status,
                            curTask.updated });
            
            try {
                relevanceCalculator.setGoogleTask(curTask);
                relevanceCalculator.run();
                if (relevanceCalculator.isRelevant())
                {
                    relevantTasksByIds.put(curTask.id, curTask);
                }
            } catch (final PccException exception) {
                LOGGER.error("", exception);
            }
        }
    }

    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;
    }

    @Override
    public void setUser(final UserData aUser) {
        this.user = aUser;
    }

    @Override
    public void setService(final Tasks aService) {
        this.service = aService;
    }
}

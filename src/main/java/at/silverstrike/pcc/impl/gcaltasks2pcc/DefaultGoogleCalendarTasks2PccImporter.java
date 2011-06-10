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

            if (!"null".equals(parentId)) {
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
        final Map<String, at.silverstrike.pcc.api.model.Task> pccTasksByGoogleIds =
                new HashMap<String, at.silverstrike.pcc.api.model.Task>();

        for (final com.google.api.services.tasks.v1.model.Task curTask : aRelevantTasksByIds
                .values()) {

            final at.silverstrike.pcc.api.model.Task pccTask =
                    aPersistence.createSubTask(curTask.title, null,
                            this.user);

            pccTasksByGoogleIds.put(curTask.id, pccTask);
        }
        return pccTasksByGoogleIds;
    }

    private
            void
            getRelevantGoogleTasks(
                    final com.google.api.services.tasks.v1.model.Tasks tasks,
                    final Map<String, com.google.api.services.tasks.v1.model.Task> relevantTasksByIds) {
        for (final com.google.api.services.tasks.v1.model.Task curTask : tasks.items) {
            LOGGER.debug(
                    "Task list: title='{}', completed='{}', id='{}', kind='{}', notes='{}', parent='{}', position='{}', status='{}', updated='{}'",
                    new Object[] { curTask.title, curTask.completed,
                            curTask.id, curTask.kind, curTask.notes,
                            curTask.parent, curTask.position,
                            curTask.status,
                            curTask.updated });
            if ("null".equals(curTask.completed)) {
                relevantTasksByIds.put(curTask.id, curTask);
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

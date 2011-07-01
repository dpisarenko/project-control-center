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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.altruix.commons.api.di.PccException;

import com.google.api.services.tasks.v1.Tasks;
import com.google.api.services.tasks.v1.model.TaskList;
import com.google.inject.Injector;

import at.silverstrike.pcc.api.gcaltasks2pcc.GoogleCalendarTasks2PccImporter;
import at.silverstrike.pcc.api.gcaltasks2pccimporter.GoogleCalendarTasks2PccImporter2;
import at.silverstrike.pcc.api.gcaltasks2pccimporter.GoogleCalendarTasks2PccImporter2Factory;
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
            persistence.removeUserSchedulingObjects(this.user);

            LOGGER.debug("service: {}", service);
            

            LOGGER.debug("Task lists (START)");

            for (final TaskList curTaskList : service.tasklists.list()
                    .execute().items) {
                LOGGER.debug(
                        "Task list: etag='{}', id='{}', kind='{}', title='{}'",
                        new Object[] { curTaskList.etag, curTaskList.id,
                                curTaskList.kind, curTaskList.title });
            }

            LOGGER.debug("Task lists (END)");

            LOGGER.debug("service.tasks: {}", service.tasks);
            
            final com.google.api.services.tasks.v1.model.Tasks tasks =
                    service.tasks.list("@default").execute();

            final GoogleCalendarTasks2PccImporter2Factory factory =
                    this.injector
                            .getInstance(GoogleCalendarTasks2PccImporter2Factory.class);
            final GoogleCalendarTasks2PccImporter2 importer2 = factory.create();

            importer2.setGoogleTasks(tasks.items);
            importer2.setInjector(this.injector);
            importer2.setUser(this.user);
            importer2.run();
        } catch (final IOException exception) {
            LOGGER.error("", exception);
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

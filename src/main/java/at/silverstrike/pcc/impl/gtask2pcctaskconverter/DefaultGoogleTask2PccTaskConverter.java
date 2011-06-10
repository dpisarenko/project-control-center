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

import com.google.inject.Injector;

import ru.altruix.commons.api.di.PccException;

import at.silverstrike.pcc.api.gtask2pcctaskconverter.GoogleTask2PccTaskConverter;
import at.silverstrike.pcc.api.model.UserData;
import at.silverstrike.pcc.api.persistence.Persistence;

/**
 * @author DP118M
 * 
 */
class DefaultGoogleTask2PccTaskConverter implements GoogleTask2PccTaskConverter {
    private com.google.api.services.tasks.v1.model.Task googleTask;
    private at.silverstrike.pcc.api.model.Task task;
    private UserData user;
    private Injector injector;
    private Persistence persistence;

    @Override
    public void run() throws PccException {
        this.task =
                persistence.createSubTask(googleTask.title, null,
                        this.user);
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
        }
    }

    @Override
    public void setUser(final UserData aUser) {
        this.user = aUser;
    }
}

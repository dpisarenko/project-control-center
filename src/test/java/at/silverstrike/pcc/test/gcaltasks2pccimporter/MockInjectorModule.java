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

package at.silverstrike.pcc.test.gcaltasks2pccimporter;

import at.silverstrike.pcc.api.gcaltasks2pccimporter.GoogleCalendarTasks2PccImporter2Factory;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.impl.gcaltasks2pccimporter.DefaultGoogleCalendarTasks2PccImporter2Factory;

import com.google.inject.AbstractModule;

/**
 * @author DP118M
 * 
 */
final class MockInjectorModule extends AbstractModule {
    private Persistence persistence;

    public MockInjectorModule(final Persistence aPersistence) {
        this.persistence = aPersistence;
    }

    @Override
    protected void configure() {
        bind(GoogleCalendarTasks2PccImporter2Factory.class).toInstance(
                new DefaultGoogleCalendarTasks2PccImporter2Factory());
        bind(Persistence.class).toInstance(this.persistence);
    }

}

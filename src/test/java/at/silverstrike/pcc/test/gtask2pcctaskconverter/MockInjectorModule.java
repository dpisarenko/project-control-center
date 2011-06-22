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

package at.silverstrike.pcc.test.gtask2pcctaskconverter;

import at.silverstrike.pcc.api.gtasknoteparser.GoogleTaskNotesParserFactory;
import at.silverstrike.pcc.api.gtasktitleparser.GoogleTaskTitleParserFactory;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.impl.gtasknoteparser.DefaultGoogleTaskNotesParserFactory;
import at.silverstrike.pcc.impl.gtasktitleparser.DefaultGoogleTaskTitleParserFactory;

import com.google.inject.AbstractModule;

/**
 * @author DP118M
 * 
 */
class MockInjectorModule extends AbstractModule {
    private Persistence persistence;

    public MockInjectorModule(final Persistence aPersistence) {
        this.persistence = aPersistence;
    }

    @Override
    protected void configure() {
        bind(Persistence.class).toInstance(this.persistence);
        bind(GoogleTaskNotesParserFactory.class).toInstance(
                new DefaultGoogleTaskNotesParserFactory());
        bind(GoogleTaskTitleParserFactory.class).toInstance(
                new DefaultGoogleTaskTitleParserFactory());
    }
}

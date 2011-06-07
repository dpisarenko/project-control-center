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

package at.silverstrike.pcc.test.gcaltasks2pcc;

import at.silverstrike.pcc.api.persistence.Persistence;

import com.google.inject.AbstractModule;

/**
 * @author DP118M
 *
 */
class MockInjectorModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Persistence.class).toInstance(new MockPersistence());
    }

}

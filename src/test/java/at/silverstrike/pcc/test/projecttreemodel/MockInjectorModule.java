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

package at.silverstrike.pcc.test.projecttreemodel;

import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.projecttreemodel.ProjectTreeContainerFactory;
import at.silverstrike.pcc.impl.projecttreemodel.DefaultProjectTreeContainerFactory;

import com.google.inject.AbstractModule;

/**
 * @author DP118M
 * 
 */
final class MockInjectorModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ProjectTreeContainerFactory.class).toInstance(
                new DefaultProjectTreeContainerFactory());
        bind(Persistence.class).toInstance(new MockPersistence());
    }

}

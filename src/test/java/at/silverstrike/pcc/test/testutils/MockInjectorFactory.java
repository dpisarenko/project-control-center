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

package at.silverstrike.pcc.test.testutils;

import ru.altruix.commons.api.di.InjectorFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;


public final class MockInjectorFactory implements InjectorFactory {
    private AbstractModule module;

    public MockInjectorFactory(final AbstractModule aModule) {
        this.module = aModule;
    }

    public Injector createInjector() {
        final Injector injector = Guice.createInjector(this.module);

        return injector;
    }
}

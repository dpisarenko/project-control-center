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

package at.silverstrike.pcc.impl.injectorfactory;

import ru.altruix.commons.api.di.InjectorFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class DefaultInjectorFactory implements InjectorFactory {
    private String taskJugglerPath;

    public final Injector createInjector() {
        final InjectorModule injectorModule = new InjectorModule();
        final Injector injector = Guice.createInjector(injectorModule);

        return injector;
    }

    @Override
    public void setTaskJugglerPath(final String aPath) {
        this.taskJugglerPath = aPath;
    }
}

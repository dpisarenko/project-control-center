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

package at.silverstrike.pcc.test.injectorfactory;

import junit.framework.Assert;

import org.junit.Test;

import ru.altruix.commons.api.di.InjectorFactory;
import ru.altruix.commons.api.version.PccVersionReader;

import com.google.inject.ConfigurationException;
import com.google.inject.Injector;

import at.silverstrike.pcc.api.debugids.PccDebugIdRegistry;
import at.silverstrike.pcc.api.entrywindow.EntryWindowFactory;
import at.silverstrike.pcc.api.tj3deadlinesparser.Tj3DeadlinesFileParserFactory;
import at.silverstrike.pcc.impl.injectorfactory.DefaultInjectorFactory;

public final class TestDefaultInjectorFactory {
    @Test
    public void test01() {
        final InjectorFactory injectorFactory = new DefaultInjectorFactory();
        final Injector injector = injectorFactory.createInjector();

        Assert.assertNotNull(injector);

        try {
            injector.getInstance(PccDebugIdRegistry.class);
            injector.getInstance(Tj3DeadlinesFileParserFactory.class);
            injector.getInstance(PccVersionReader.class);
            injector.getInstance(EntryWindowFactory.class);
        } catch (final ConfigurationException exception) {
            Assert.fail(exception.getMessage());
        }
    }
}

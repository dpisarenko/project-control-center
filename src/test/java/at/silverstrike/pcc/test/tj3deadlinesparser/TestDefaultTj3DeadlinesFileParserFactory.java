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

package at.silverstrike.pcc.test.tj3deadlinesparser;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Injector;

import at.silverstrike.pcc.api.tj3deadlinesparser.Tj3DeadlinesFileParser;
import at.silverstrike.pcc.impl.tj3deadlinesparser.DefaultTj3DeadlinesFileParserFactory;
import at.silverstrike.pcc.test.testutils.MockInjectorFactory;

public class TestDefaultTj3DeadlinesFileParserFactory {
    private final static Logger LOGGER =
        LoggerFactory.getLogger(TestDefaultTj3DeadlinesFileParserFactory.class);

    @Before
    public void setupLogger() {
    }

    @Test
    public void testCreate01() {
        final Injector injector =
                new MockInjectorFactory(new MockInjectorModule())
                        .createInjector();
        final DefaultTj3DeadlinesFileParserFactory objectUnderTest =
                new DefaultTj3DeadlinesFileParserFactory();
        
        objectUnderTest.setInjector(injector);
        
        Tj3DeadlinesFileParser parser = null;
        try
        {
            parser = objectUnderTest.create();   
        }
        catch (final Exception exception)
        {
            LOGGER.error("", exception);
            fail(exception.getMessage());
        }

        assertNotNull(parser);
    }
}

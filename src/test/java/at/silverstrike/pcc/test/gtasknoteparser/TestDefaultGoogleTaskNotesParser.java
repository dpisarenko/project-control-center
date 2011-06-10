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

package at.silverstrike.pcc.test.gtasknoteparser;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.altruix.commons.api.di.PccException;

import at.silverstrike.pcc.api.gtasknoteparser.GoogleTaskNotesParser;
import at.silverstrike.pcc.api.gtasknoteparser.GoogleTaskNotesParserFactory;
import at.silverstrike.pcc.impl.gtasknoteparser.DefaultGoogleTaskNotesParserFactory;

/**
 * @author DP118M
 * 
 */
public class TestDefaultGoogleTaskNotesParser {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(TestDefaultGoogleTaskNotesParser.class);

    @Test
    public void testValidEffort1() {
        final GoogleTaskNotesParser objectUnderTest = getObjectUnderTest();

        objectUnderTest.setNotes("1h");
        checkReturnValues(objectUnderTest, true, 1.0);
    }

    @Test
    public void testInvalidEffort1() {
        final GoogleTaskNotesParser objectUnderTest = getObjectUnderTest();

        objectUnderTest.setNotes("bla-bla-bla");
        checkReturnValues(objectUnderTest, false, null);

    }
    
    @Test
    public void testInvalidEffort2() {
        final GoogleTaskNotesParser objectUnderTest = getObjectUnderTest();

        objectUnderTest.setNotes("1");
        checkReturnValues(objectUnderTest, false, null);
    }
    
    private void checkReturnValues(final GoogleTaskNotesParser objectUnderTest,
            final boolean expectedEffortSpecified, Double expectedEffort) {
        try {
            objectUnderTest.run();

            if (expectedEffortSpecified) {
                Assert.assertTrue(objectUnderTest.isEffortSpecified());
                Assert.assertEquals(expectedEffort, objectUnderTest.getEffortInHours(),
                        1. / 1000000.);
            } else {
                Assert.assertFalse(objectUnderTest.isEffortSpecified());
            }
        } catch (final PccException exception) {
            LOGGER.error("", exception);
            Assert.fail(exception.getMessage());
        }
    }

    private GoogleTaskNotesParser getObjectUnderTest() {
        final GoogleTaskNotesParserFactory factory =
                new DefaultGoogleTaskNotesParserFactory();
        final GoogleTaskNotesParser parser = factory.create();

        return parser;
    }

}

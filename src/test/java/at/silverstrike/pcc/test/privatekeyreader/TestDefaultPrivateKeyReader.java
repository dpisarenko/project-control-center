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

package at.silverstrike.pcc.test.privatekeyreader;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.altruix.commons.api.di.PccException;

import at.silverstrike.pcc.api.privatekeyreader.PrivateKeyReader;
import at.silverstrike.pcc.api.privatekeyreader.PrivateKeyReaderFactory;
import at.silverstrike.pcc.impl.privatekeyreader.DefaultPrivateKeyReaderFactory;

/**
 * @author DP118M
 * 
 */
public class TestDefaultPrivateKeyReader {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(TestDefaultPrivateKeyReader.class);

    @Test
    public void testPrivateKeyReading() {
        final PrivateKeyReaderFactory factory =
                new DefaultPrivateKeyReaderFactory();
        final PrivateKeyReader objectUnderTest = factory.create();

        objectUnderTest.setInputStream(getClass().getClassLoader()
                        .getResourceAsStream("privatekey"));
        try {
            objectUnderTest.run();
        } catch (final PccException exception) {
            LOGGER.error("", exception);
            Assert.fail(exception.getMessage());
        }
    }
}

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

package at.silverstrike.pcc.test.version;

import junit.framework.Assert;

import org.junit.Test;

import ru.altruix.commons.api.di.PccException;
import ru.altruix.commons.api.version.PccVersionReader;
import ru.altruix.commons.api.version.PccVersionReaderFactory;
import ru.altruix.commons.impl.version.DefaultPccVersionReaderFactory;


public final class TestDefaultPccVersionReader {

    @Test
    public void testGetVersion() {
        final PccVersionReaderFactory factory =
                new DefaultPccVersionReaderFactory();
        final PccVersionReader reader = factory.create();

        Assert.assertNotNull(reader.getVersion());
        Assert.assertEquals("?", reader.getVersion());
        try {
            reader.run();
        } catch (final PccException exception) {
            Assert.fail(exception.getMessage());
        }
        Assert.assertFalse("?".equals(reader.getVersion()));
    }

}

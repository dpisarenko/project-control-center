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

import static org.apache.commons.io.FileUtils.readLines;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LineReader {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(LineReader.class);

    private LineReader() {

    }

    @SuppressWarnings("unchecked")
    public static List<String> readTrimmedLines(final File anInputFile) {
        List<String> expectedLines = null;

        try {
            expectedLines = trim(readLines(anInputFile));
        } catch (final IOException exception) {
            LOGGER.error("", exception);
            Assert.fail(exception.getMessage());
        }
        return expectedLines;
    }

    private static List<String> trim(final List<String> aLines) {
        final List<String> returnValue = new LinkedList<String>();

        for (final String curLine : aLines) {
            returnValue.add(StringUtils.trimToEmpty(curLine));
        }

        return returnValue;
    }
}

/**
 * This file is part of Project Control Center (PCC).
 * 
 * PCC (Project Control Center) project is intellectual property of 
 * Dmitri Anatol'evich Pisarenko.
 * 
 * Copyright 2010 Dmitri Anatol'evich Pisarenko
 * All rights reserved
 *
 **/

package at.silverstrike.pcc.test.testutils;

import java.util.List;

import junit.framework.Assert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;

/**
 * @author dp118m
 *
 */
public class LineComparer {
    private final static Logger LOGGER =
        LoggerFactory.getLogger(LineComparer.class);
    
    public static void assertLinesEqual(final List<String> anExpectedLines, final List<String> anActualLines)
    {
        final Patch patch = DiffUtils.diff(anExpectedLines, anActualLines);
        final List<Delta> deltas = patch.getDeltas();

        if (deltas.size() > 0) {
            LOGGER.debug("Files are different:");
            for (int i = 0; i < deltas.size(); i++) {
                final Delta curDelta = deltas.get(i);

                final StringBuilder message = new StringBuilder();

                message.append(i + 1);
                message.append(")");
                message.append("Expected: ");
                message.append(curDelta.getOriginal().toString());
                message.append("Actual: ");
                message.append(curDelta.getRevised().toString());

                LOGGER.debug(message.toString());
            }

            Assert.fail("Files are different");
        }        
    }

}

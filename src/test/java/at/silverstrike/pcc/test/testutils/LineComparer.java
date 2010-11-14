/**
 * This file is part of Project Control Center (PCC).
 * 
 * Project Control Center (PCC) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Project Control Center (PCC) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Project Control Center (PCC).  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2010 Dmitri Anatol'evich Pisarenko
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

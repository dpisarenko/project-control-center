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

import static org.apache.commons.io.FileUtils.readLines;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LineReader {
    private final static Logger LOGGER =
        LoggerFactory.getLogger(LineReader.class);

    @SuppressWarnings("unchecked")
    public static List<String> readTrimmedLines(File anInputFile) {
        List<String> expectedLines = null;

        try {
            expectedLines = trim(readLines(anInputFile));
        } catch (final IOException exception) {
            LOGGER.error("", exception);
            Assert.fail(exception.getMessage());
        }
        return expectedLines;
    }
    private static List<String> trim(final List<String> aLines)
    {
        final List<String> returnValue = new LinkedList<String>();
        
        for (final String curLine : aLines)
        {
            returnValue.add(StringUtils.trimToEmpty(curLine));
        }
        
        return returnValue;
    }
}

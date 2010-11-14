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

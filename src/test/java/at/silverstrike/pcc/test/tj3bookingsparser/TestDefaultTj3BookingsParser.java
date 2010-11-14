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

package at.silverstrike.pcc.test.tj3bookingsparser;

import static junit.framework.Assert.fail;
import static org.apache.commons.io.IOUtils.closeQuietly;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Before;
import org.junit.Test;

import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.injectorfactory.InjectorFactory;
import at.silverstrike.pcc.api.tj3bookingsparser.BookingTuple;
import at.silverstrike.pcc.api.tj3bookingsparser.Tj3BookingsParser;
import at.silverstrike.pcc.test.testutils.MockInjectorFactory;

import com.google.inject.Injector;

public class TestDefaultTj3BookingsParser {
    private static final String DIR = "src/test/resources/at/silverstrike/"
            + "pcc/test/tj3bookingsparser/";
    private final static Logger LOGGER =
            LoggerFactory.getLogger(TestDefaultTj3BookingsParser.class);

    @Before
    public void setupLogger() {
    }

    @Test
    public void test01() {
        /**
         * Create the injector
         */
        final InjectorFactory injectorFactory =
                new MockInjectorFactory(new MockInjectorModule());
        final Injector injector = injectorFactory.createInjector();

        /**
         * Get object under test
         */
        final Tj3BookingsParser objectUnderTest =
                injector.getInstance(Tj3BookingsParser.class);
        Assert.assertNotNull(objectUnderTest);

        /**
         * Parse file
         */
        objectUnderTest.setInjector(injector);

        InputStream inputStream = null;
        try {
            inputStream = FileUtils.openInputStream(new File(DIR + "test01"));

            objectUnderTest.setInputStream(inputStream);

            objectUnderTest.run();
        } catch (final IOException exception) {
            LOGGER.error("", exception);
            fail(exception.getMessage());
        } catch (final PccException exception) {
            LOGGER.error("", exception);
            fail(exception.getMessage());
        } finally {
            closeQuietly(inputStream);
        }

        /**
         * Check actual and expected results.
         */
        final List<BookingTuple> bookingTuples = objectUnderTest.getBookings();

        Assert.assertNotNull(bookingTuples);
    }
}

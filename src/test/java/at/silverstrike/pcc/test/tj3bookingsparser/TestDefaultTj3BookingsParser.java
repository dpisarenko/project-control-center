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

package at.silverstrike.pcc.test.tj3bookingsparser;

import static junit.framework.Assert.fail;
import static org.apache.commons.io.IOUtils.closeQuietly;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Before;
import org.junit.Test;

import ru.altruix.commons.api.di.InjectorFactory;
import ru.altruix.commons.api.di.PccException;

import at.silverstrike.pcc.api.tj3bookingsparser.BookingTuple;
import at.silverstrike.pcc.api.tj3bookingsparser.Tj3BookingsParser;
import at.silverstrike.pcc.test.testutils.MockInjectorFactory;

import com.google.inject.Injector;

public final class TestDefaultTj3BookingsParser {
    private static final String DIR = "src/test/resources/at/silverstrike/"
            + "pcc/test/tj3bookingsparser/";
    private static final Logger LOGGER =
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
        Assert.assertTrue(bookingTuples.size() > 0);
    }

    @Test
    public void test02() {
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
            inputStream = FileUtils.openInputStream(new File(DIR + "test02"));

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

        for (final BookingTuple curTuple : bookingTuples) {
            LOGGER.debug(curTuple.toString());
        }

        Assert.assertNotNull(bookingTuples);
        Assert.assertTrue(bookingTuples.size() > 0);

        checkNumberOfBookings(bookingTuples, 7L, 0);
        checkNumberOfBookings(bookingTuples, 11L, 3);
        checkNumberOfBookings(bookingTuples, 12L, 3);
    }
    @Test
    public void test04() {
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
            inputStream = FileUtils.openInputStream(new File(DIR + "test04"));

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

        for (final BookingTuple curTuple : bookingTuples) {
            LOGGER.debug(curTuple.toString());
        }

        Assert.assertNotNull(bookingTuples);
        Assert.assertTrue(bookingTuples.size() > 0);

        checkNumberOfBookings(bookingTuples, 5L, 0);
        checkNumberOfBookings(bookingTuples, 9L, 1);
        checkNumberOfBookings(bookingTuples, 10L, 2);
        checkNumberOfBookings(bookingTuples, 6L, 0);
        checkNumberOfBookings(bookingTuples, 7L, 1);
        checkNumberOfBookings(bookingTuples, 8L, 1);

    }

    private void
            checkNumberOfBookings(final List<BookingTuple> aTuples,
                    final long aSchedulingObjectId,
                    final int aExpectedNumberOfBookings) {
        Assert.assertEquals("SO " + aSchedulingObjectId,
                aExpectedNumberOfBookings,
                findTuplesByProcessId(aTuples, aSchedulingObjectId).size());
    }

    private List<BookingTuple> findTuplesByProcessId(
            final List<BookingTuple> aTuples, final long aProcessId) {
        final List<BookingTuple> foundTuples = new LinkedList<BookingTuple>();
        for (final BookingTuple curTuple : aTuples) {
            if (curTuple.getProcessId() == aProcessId) {
                foundTuples.add(curTuple);
            }
        }
        return foundTuples;
    }

    @Test
    public void test03() {
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
            inputStream = FileUtils.openInputStream(new File(DIR + "test03"));

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
        Assert.assertTrue(bookingTuples.size() > 0);
    }

}

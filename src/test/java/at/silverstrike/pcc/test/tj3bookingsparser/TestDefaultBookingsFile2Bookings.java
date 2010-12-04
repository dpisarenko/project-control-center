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

package at.silverstrike.pcc.test.tj3bookingsparser;

import static java.util.Calendar.OCTOBER;

import java.text.ParseException;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import at.silverstrike.pcc.api.injectorfactory.InjectorFactory;
import at.silverstrike.pcc.api.model.Booking;
import at.silverstrike.pcc.api.model.ControlProcess;
import at.silverstrike.pcc.api.model.Resource;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.tj3bookingsparser.BookingTuple;
import at.silverstrike.pcc.api.tj3bookingsparser.BookingsFile;
import at.silverstrike.pcc.api.tj3bookingsparser.BookingsFile2Bookings;
import at.silverstrike.pcc.api.tj3bookingsparser.BookingsFile2BookingsFactory;
import at.silverstrike.pcc.impl.jruby.RubyDateTimeUtils;
import at.silverstrike.pcc.test.conventions.TestConventions;
import at.silverstrike.pcc.test.testutils.MockInjectorFactory;

import com.google.inject.Injector;

public class TestDefaultBookingsFile2Bookings {
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

        final BookingsFile2BookingsFactory factory =
                injector.getInstance(BookingsFile2BookingsFactory.class);
        final BookingsFile2Bookings objectUnderTest = factory.create();
        
        Assert.assertNotNull(objectUnderTest);
        
        /**
         * Prepare input data
         */
        final BookingsFile bookingsFile = new MockBookingsFile();
        final MockSupplementStatement suppStmt = new MockSupplementStatement();
        final MockIndBooking indBooking = new MockIndBooking();
        final MockBookingStatement bookingStatement = new MockBookingStatement();
        
        
        indBooking.setDuration("2.75h");
        indBooking.setStartTime("2010-10-25-09:00-+0200");
        
        Assert.assertNotNull(bookingStatement.getIndBookings());
        
        bookingStatement.getIndBookings().add(indBooking);
        bookingStatement.setResource("R1210");
        bookingStatement.setScenario("");
        
        Assert.assertNotNull(suppStmt.getBookingStatements());

        suppStmt.getBookingStatements().add(bookingStatement);
        suppStmt.setTaskId("T2010");
        
        Assert.assertNotNull(bookingsFile.getSupplementStatements());
        
        bookingsFile.getSupplementStatements().add(suppStmt);
        
        /**
         * Run method under test
         */
        objectUnderTest.setBookingsFile(bookingsFile);
        objectUnderTest.setPersistence(injector.getInstance(Persistence.class));
        try {
            objectUnderTest.run();
        } catch (final NumberFormatException exception) {
            Assert.fail(exception.getMessage());
        } catch (final ParseException exception) {
            Assert.fail(exception.getMessage());
        }
        
        /**
         * Evaluate results
         */
        final List<BookingTuple> tuples = objectUnderTest.getTuples();
        
        Assert.assertNotNull(tuples);
        Assert.assertEquals(1, tuples.size());
        
        final BookingTuple tuple = tuples.get(0);
        
        Assert.assertEquals(2010L, tuple.getProcessId());
        Assert.assertEquals(1210L, tuple.getResourceId());
        
        final Booking booking = tuple.getBooking();
        
        Assert.assertNotNull(booking);
        
        Assert.assertEquals(2.75, booking.getDuration(), TestConventions.DELTA);
        
        Assert.assertEquals(RubyDateTimeUtils.getDate(2010, OCTOBER, 25, 9, 0), booking.getStartDateTime());
        Assert.assertEquals(RubyDateTimeUtils.getDate(2010, OCTOBER, 25, 11, 45), booking.getEndDateTime());
        
        final ControlProcess process = booking.getProcess();
        
        Assert.assertNull(process);
        
        final Resource resource = booking.getResource();
        
        Assert.assertNull(resource);
    }
}

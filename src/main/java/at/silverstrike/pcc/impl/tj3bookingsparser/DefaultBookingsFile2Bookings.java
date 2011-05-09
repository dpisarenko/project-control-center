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

package at.silverstrike.pcc.impl.tj3bookingsparser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.silverstrike.pcc.api.model.Booking;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.tj3bookingsparser.BookingStatement;
import at.silverstrike.pcc.api.tj3bookingsparser.BookingTuple;
import at.silverstrike.pcc.api.tj3bookingsparser.BookingsFile;
import at.silverstrike.pcc.api.tj3bookingsparser.BookingsFile2Bookings;
import at.silverstrike.pcc.api.tj3bookingsparser.IndBooking;
import at.silverstrike.pcc.api.tj3bookingsparser.SupplementStatement;

class DefaultBookingsFile2Bookings implements BookingsFile2Bookings {
    private static final int TIME_ZONE_LENGTH = 6;
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd-HH:mm";
    private List<BookingTuple> tuples;
    private BookingsFile bookingsFile = null;
    private Persistence persistence = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultBookingsFile2Bookings.class);

    @Override
    public List<BookingTuple> getTuples() {
        return this.tuples;
    }

    @Override
    public void run() throws ParseException {
        this.tuples = new LinkedList<BookingTuple>();

        for (final SupplementStatement suppStmt : this.bookingsFile
                .getSupplementStatements()) {
            final long taskId =
                    taskOrResourceIdStringToLong(suppStmt.getTaskId());

            for (final BookingStatement bookingStmt : suppStmt
                    .getBookingStatements()) {
                final long resourceId =
                        taskOrResourceIdStringToLong(bookingStmt.getResource());

                for (final IndBooking indBooking : bookingStmt.getIndBookings()) {
                    final Booking booking = this.persistence.createBooking();

                    final Date startTime =
                            parseDateTime(indBooking.getStartTime());

                    booking.setStartDateTime(startTime);
                    booking.setDuration(parseDuration(indBooking.getDuration()));

                    final BookingTuple tuple = new DefaultBookingTuple();

                    tuple.setProcessId(taskId);
                    tuple.setResourceId(resourceId);
                    tuple.setBooking(booking);

                    this.tuples.add(tuple);
                }
            }
        }
        LOGGER.debug("tuples: {}", this.tuples.size());
    }

    private double parseDuration(final String aDuration) {
        return Double.parseDouble(aDuration.replace("h", ""));
    }

    private Date parseDateTime(final String aDateTime) throws ParseException {
        /**
         * Strip timezone
         */

        final String dateTimeWithoutTimeZone =
                aDateTime.substring(0, aDateTime.length() - TIME_ZONE_LENGTH);

        /**
         * Parse date/time
         */
        return getDateTimeFormat().parse(dateTimeWithoutTimeZone);
    }

    private static DateFormat getDateTimeFormat() {
        return new SimpleDateFormat(DATE_TIME_FORMAT);
    }

    private long taskOrResourceIdStringToLong(final String aIdAsString) {
        LOGGER.debug("taskOrResourceIdStringToLong, aIdAsString: {}", aIdAsString);
        
        final String[] ids = aIdAsString.split("\\.");
        final String taskId = ids[ids.length-1];
        return Long.parseLong(taskId.substring(1));
    }

    @Override
    public void setBookingsFile(final BookingsFile aBookingsFile) {
        this.bookingsFile = aBookingsFile;
    }

    @Override
    public void setPersistence(final Persistence aPersistence) {
        this.persistence = aPersistence;
    }
}

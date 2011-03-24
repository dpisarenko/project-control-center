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

import at.silverstrike.pcc.api.model.Booking;
import at.silverstrike.pcc.api.tj3bookingsparser.BookingTuple;

class DefaultBookingTuple implements BookingTuple {
    private long processId;
    private long resourceId;
    private Booking booking;

    public long getProcessId() {
        return processId;
    }

    public void setProcessId(final long aProcessId) {
        this.processId = aProcessId;
    }

    public long getResourceId() {
        return resourceId;
    }

    public void setResourceId(final long aResourceId) {
        this.resourceId = aResourceId;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(final Booking aBooking) {
        this.booking = aBooking;
    }
}

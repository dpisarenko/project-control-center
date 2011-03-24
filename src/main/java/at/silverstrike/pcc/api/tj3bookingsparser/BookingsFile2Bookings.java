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

package at.silverstrike.pcc.api.tj3bookingsparser;

import java.text.ParseException;
import java.util.List;

import at.silverstrike.pcc.api.persistence.Persistence;

/**
 * @author Dmitri Pisarenko
 * 
 */
public interface BookingsFile2Bookings {
    void setBookingsFile(final BookingsFile aBookingsFile);

    void setPersistence(final Persistence aPersistence);

    void run() throws ParseException;

    List<BookingTuple> getTuples();
}

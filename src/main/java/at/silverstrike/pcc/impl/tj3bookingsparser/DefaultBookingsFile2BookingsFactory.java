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

package at.silverstrike.pcc.impl.tj3bookingsparser;

import at.silverstrike.pcc.api.tj3bookingsparser.BookingsFile2Bookings;
import at.silverstrike.pcc.api.tj3bookingsparser.BookingsFile2BookingsFactory;

/**
 * @author Dmitri Pisarenko
 * 
 */
public class DefaultBookingsFile2BookingsFactory
        implements BookingsFile2BookingsFactory {

    @Override
    public final BookingsFile2Bookings create() {
        return new DefaultBookingsFile2Bookings();
    }
}

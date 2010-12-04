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

package at.silverstrike.pcc.api.tj3bookingsparser;

import at.silverstrike.pcc.api.model.Booking;

/**
 * Instances of this interface represent data read from bookings file.
 * We cannot use Booking interface for passing data from parser to the
 * recipient of these data because at the end of parser we have
 * 
 * *) process IDs and resource abbreviations and
 * *) 
 * @author Dmitri Pisarenko
 */
public interface BookingTuple {
	void setProcessId(long aProcessId);
	long getProcessId();
	
	void setResourceId(long aResourceId);
	long getResourceId();
	
	void setBooking(Booking aBooking);
	Booking getBooking();
}

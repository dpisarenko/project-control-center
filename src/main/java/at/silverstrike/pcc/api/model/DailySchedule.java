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

package at.silverstrike.pcc.api.model;

import java.util.List;

import at.silverstrike.pcc.api.conventions.UniquelyIdentifiableObject;

/**
 * @author Dmitri Pisarenko
 *
 */
public interface DailySchedule extends UniquelyIdentifiableObject {
	void setBookings(final List<Booking> aBookings);
	List<Booking> getBookings();
}

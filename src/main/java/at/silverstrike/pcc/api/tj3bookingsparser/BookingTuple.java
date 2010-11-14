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

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

import java.io.InputStream;
import java.util.List;

import at.silverstrike.pcc.api.conventions.ModuleWithInjectableDependencies;
import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.conventions.SingleActivityModule;

/**
 * Instances of this interface parse the bookings file produced by TaskJuggler III.
 * Example of such file can be found at pcc\doc\2010_05_28_prototype1\ref.
 * 
 * @author Dmitri Pisarenko
 *
 */
public interface Tj3BookingsParser extends ModuleWithInjectableDependencies, SingleActivityModule {

	void setInputStream(InputStream anInputStream);
	
	void run() throws PccException;
	
	List<BookingTuple> getBookings();
}

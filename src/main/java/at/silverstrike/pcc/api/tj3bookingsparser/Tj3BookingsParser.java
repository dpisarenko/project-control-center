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

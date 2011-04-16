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

import at.silverstrike.pcc.api.tj3bookingsparser.Tj3BookingsParser;
import at.silverstrike.pcc.api.tj3bookingsparser.Tj3BookingsParserFactory;

/**
 * @author Dmitri Pisarenko
 *
 */
public class DefaultTj3BookingsParserFactory implements
		Tj3BookingsParserFactory {

	/**
	 * @see ru.altruix.commons.api.conventions.Factory#create()
	 */
	@Override
	public final Tj3BookingsParser create() {
		return new DefaultTj3BookingsParser();
	}

}

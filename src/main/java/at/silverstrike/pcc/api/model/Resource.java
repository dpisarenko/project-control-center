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
package at.silverstrike.pcc.api.model;

import at.silverstrike.pcc.api.conventions.UniquelyIdentifiableObject;

public interface Resource extends UniquelyIdentifiableObject {
	void setAbbreviation(final String aAbbreviation);
	String getAbbreviation();

	void setDailyLimitInHours(final double aDailyLimit);
	double getDailyLimitInHours();
}

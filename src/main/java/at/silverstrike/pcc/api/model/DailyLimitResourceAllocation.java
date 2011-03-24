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

/**
 * allocate dp {limits {dailymax 3h}}
 * @author Dmitri Pisarenko
 *
 */
public interface DailyLimitResourceAllocation extends ResourceAllocation {
	void setDailyLimit(final double aDailyLimitHours);
	double getDailyLimit();
}

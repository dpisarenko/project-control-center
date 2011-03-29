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

package at.silverstrike.pcc.test.model;

import at.silverstrike.pcc.api.model.DailyLimitResourceAllocation;
import at.silverstrike.pcc.api.model.Resource;


class MockDailyLimitResourceAllocation implements DailyLimitResourceAllocation {
	
	private Resource resource;
	private Long id;
	private double dailyLimitHours;

	public void setResource(final Resource aResource) {
		this.resource = aResource;
	}

	public Resource getResource() {
		return this.resource;
	}

	public Long getId() {
		return this.id;
	}

	public void setDailyLimit(final double aDailyLimitHours) {
		this.dailyLimitHours = aDailyLimitHours;
	}

	public double getDailyLimit() {
		return this.dailyLimitHours;
	}

}

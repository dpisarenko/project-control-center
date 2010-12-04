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

package at.silverstrike.pcc.impl.persistence;

import at.silverstrike.pcc.api.model.Resource;

class DefaultResource implements Resource {
	private String abbreviation;
	private Long id;
	private double dailyLimitInHours;

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(final String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public double getDailyLimitInHours() {
		return dailyLimitInHours;
	}

	public void setDailyLimitInHours(final double dailyLimitInHours) {
		this.dailyLimitInHours = dailyLimitInHours;
	}
}

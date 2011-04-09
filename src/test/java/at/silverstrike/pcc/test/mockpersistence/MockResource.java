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

package at.silverstrike.pcc.test.mockpersistence;

import at.silverstrike.pcc.api.model.Resource;

class MockResource implements Resource {
    private String abbreviation;
    private double dailyLimitInHours;
    private Long id;

    public String getAbbreviation() {
        return abbreviation;
    }

    public double getDailyLimitInHours() {
        return dailyLimitInHours;
    }

    public Long getId() {
        return id;
    }

    public void setAbbreviation(final String aAbbreviation) {
        this.abbreviation = aAbbreviation;
    }

    public void setDailyLimitInHours(final double aDailyLimitInHours) {
        this.dailyLimitInHours = aDailyLimitInHours;
    }

    public void setId(final Long aId) {
        this.id = aId;
    }

}

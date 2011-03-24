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

    public void setAbbreviation(final String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public void setDailyLimitInHours(final double dailyLimitInHours) {
        this.dailyLimitInHours = dailyLimitInHours;
    }

    public void setId(final Long id) {
        this.id = id;
    }

}

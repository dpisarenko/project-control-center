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

import at.silverstrike.pcc.api.model.Resource;

class MockResource implements Resource {

    private Long id;
    private String abbreviation;
    private double dailyLimit;

    public Long getId() {
        return this.id;
    }

    public void setAbbreviation(final String aAbbreviation) {
        this.abbreviation = aAbbreviation;
    }

    public String getAbbreviation() {
        return this.abbreviation;
    }

    public void setDailyLimitInHours(final double aDailyLimit) {
        this.dailyLimit = aDailyLimit;
    }

    public double getDailyLimitInHours() {
        return this.dailyLimit;
    }

}

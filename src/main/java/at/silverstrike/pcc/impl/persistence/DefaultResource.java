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

package at.silverstrike.pcc.impl.persistence;

import at.silverstrike.pcc.api.model.Resource;

class DefaultResource implements Resource {
    private String abbreviation;
    private Long id;
    private double dailyLimitInHours;

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(final String aAbbreviation) {
        this.abbreviation = aAbbreviation;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long aId) {
        this.id = aId;
    }

    public double getDailyLimitInHours() {
        return dailyLimitInHours;
    }

    public void setDailyLimitInHours(final double aDailyLimitInHours) {
        this.dailyLimitInHours = aDailyLimitInHours;
    }
}

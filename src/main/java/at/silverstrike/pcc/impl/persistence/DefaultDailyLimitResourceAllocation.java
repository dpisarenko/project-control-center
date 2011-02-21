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

import org.apache.commons.lang.builder.ToStringBuilder;

import at.silverstrike.pcc.api.model.DailyLimitResourceAllocation;

class DefaultDailyLimitResourceAllocation extends
        DefaultResourceAllocation implements DailyLimitResourceAllocation {
    private double dailyLimit;
    private Long resourceAllocationId;

    public double getDailyLimit() {
        return dailyLimit;
    }

    public void setDailyLimit(final double aDailyLimit) {
        this.dailyLimit = aDailyLimit;
    }

    public Long getResourceAllocationId() {
        return resourceAllocationId;
    }

    public void setResourceAllocationId(final Long aResourceAllocationId) {
        this.resourceAllocationId = aResourceAllocationId;
    }

    @Override
    public String toString() {
        final ToStringBuilder builder = new ToStringBuilder(this);

        builder.append("id", this.getId());
        builder.append("resource", this.getResource());
        builder.append("dailyLimit", this.dailyLimit);
        builder.append("resourceAllocationId", this.resourceAllocationId);

        return builder.toString();

    }
}

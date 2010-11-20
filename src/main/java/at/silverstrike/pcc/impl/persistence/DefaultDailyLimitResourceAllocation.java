/**
 * This file is part of Project Control Center (PCC).
 * 
 * Project Control Center (PCC) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Project Control Center (PCC) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Project Control Center (PCC).  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2010 Dmitri Anatol'evich Pisarenko
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

	public void setDailyLimit(final double dailyLimit) {
		this.dailyLimit = dailyLimit;
	}

	public Long getResourceAllocationId() {
		return resourceAllocationId;
	}

	public void setResourceAllocationId(final Long resourceAllocationId) {
		this.resourceAllocationId = resourceAllocationId;
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

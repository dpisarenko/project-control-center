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

package at.silverstrike.pcc.api.model;

import java.util.Date;
import java.util.Set;

import at.silverstrike.pcc.api.conventions.UniquelyIdentifiableObject;

public interface ControlProcess extends UniquelyIdentifiableObject {
    public static final int HIGHEST_PRIORITY = 1000;
    public static final int LOWEST_PRIORITY = 0;
    
	String getName();
	void setName(final String aName);
	
	ProcessState getState();
	void setState(final ProcessState aState);
	
	/**
	 * Get effort estimate in hours (best case).
	 */
	Double getBestCaseEffort();
	/**
	 * Set effort estimate in hours (best case).
	 */
	void setBestCaseEffort(final Double anEffortInHours);

	/**
	 * Get effort estimate in hours (average case).
	 */
	double getAverageCaseEffort();

	/**
	 * Get effort estimate in hours (worst case).
	 */
	Double getWorstCaseEffort();
	/**
	 * Set effort estimate in hours (worst case).
	 */
	void setWorstCaseEffort(final Double anEffortInHours);

	void setResourceAllocations(Set<ResourceAllocation> aResourceAllocations);
	Set<ResourceAllocation> getResourceAllocations();
	
	void setPriority(final Integer aPriority);
	Integer getPriority();

	ControlProcess getParent();
	void setParent(ControlProcess aParentProcess);
	
	void setProcessType(ProcessType aType);
	ProcessType getProcessType();
	
	/**
	 * Get estimated end time (completion date) of the task for the average
	 * case scenario (based on average effort estimates of this and other
	 * tasks).
	 */
	Date getAverageEstimatedEndDateTime();
	void setAverageEstimatedEndDateTime(final Date aDate);
	
	/**
	 * This method is not implemented now (prototype 1).
	 */
	Date getBestEstimatedEndDateTime();
	/**
	 * This method is not implemented now (prototype 1).
	 */
	void setBestEstimatedEndDateTime(final Date aDate);

	/**
	 * This method is not implemented now (prototype 1).
	 */
	Date getWorstEstimatedEndDateTime();
	/**
	 * This method is not implemented now (prototype 1).
	 */
	void setWorstEstimatedEndDateTime(final Date aDate);

	void setPredecessors(Set<ControlProcess> aPredecessors);
	Set<ControlProcess> getPredecessors();

}

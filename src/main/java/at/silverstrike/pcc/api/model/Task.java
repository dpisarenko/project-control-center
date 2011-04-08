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

import java.util.Date;
import java.util.List;

public interface Task extends SchedulingObject {
    /**
     * Get effort estimate in hours (best case).
     */
    Double getBestCaseEffort();

    /**
     * Set effort estimate in hours (best case).
     */
    void setBestCaseEffort(final Double aEffortInHours);

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
    void setWorstCaseEffort(final Double aEffortInHours);

    void setResourceAllocations(final List<ResourceAllocation> aResourceAllocations);

    List<ResourceAllocation> getResourceAllocations();

    /**
     * Get estimated end time (completion date) of the task for the average case
     * scenario (based on average effort estimates of this and other tasks).
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
}

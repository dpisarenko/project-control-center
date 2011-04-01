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

import at.silverstrike.pcc.api.conventions.UniquelyIdentifiableObject;

/**
 * @author DP118M
 *
 */
public interface SchedulingObject extends UniquelyIdentifiableObject {
    int HIGHEST_PRIORITY = 1000;
    int LOWEST_PRIORITY = 0;

    /**
     * Label is used for displaying of the scheduling object in the
     * project network graph.
     */
    void setLabel(final String aLabel);
    String getLabel();
    
    void setPriority(final Integer aPriority);
    Integer getPriority();

    SchedulingObject getParent();
    void setParent(SchedulingObject aParentProcess);

    
    String getName();
    void setName(final String aName);
}

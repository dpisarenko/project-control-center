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

/**
 * @author DP118M
 *
 */
public interface Milestone extends SchedulingObjectWithDependencies {
    String getName();
    void setName(final String aName);

}

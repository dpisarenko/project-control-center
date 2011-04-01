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

import java.util.Set;

/**
 * @author DP118M
 *
 */
interface SchedulingObjectWithDependencies extends SchedulingObject {
    void setPredecessors(final Set<SchedulingObject> aPredecessors);
    Set<SchedulingObject> getPredecessors();
}

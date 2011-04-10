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

package at.silverstrike.pcc.api.projectnetworkdatacreator;

import java.util.List;

import at.silverstrike.pcc.api.conventions.ModuleWithInjectableDependencies;
import at.silverstrike.pcc.api.conventions.SingleActivityModule;
import at.silverstrike.pcc.api.projectnetworkgraphcreator.SchedulingObjectDependencyTuple;

/**
 * @author DP118M
 *
 */
public interface ProjectNetworkDataCreator extends SingleActivityModule, 
    ModuleWithInjectableDependencies {
    void setCurrentProjectId(final Long aId);
    
    List<SchedulingObjectDependencyTuple> getDependencyTuples();
}

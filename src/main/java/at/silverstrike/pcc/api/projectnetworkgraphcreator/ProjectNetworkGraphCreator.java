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

package at.silverstrike.pcc.api.projectnetworkgraphcreator;

import java.util.List;

import edu.uci.ics.jung.graph.Graph;

import at.silverstrike.pcc.api.conventions.SingleActivityModule;

/**
 * @author DP118M
 *
 */
public interface ProjectNetworkGraphCreator extends SingleActivityModule {
    void setDependencies(final List<SchedulingObjectDependencyTuple> aDependencies);
    
    Graph<String, String> getGraph();
}

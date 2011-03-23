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

package at.silverstrike.pcc.api.graph2resource;

import at.silverstrike.pcc.api.conventions.SingleActivityModule;
import at.silverstrike.pcc.api.projectnetworkgraphcreator.ProjectNetworkGraph;

/**
 * @author DP118M
 *
 */
public interface Graph2ResourceConverter extends SingleActivityModule {
    void setGraph(final ProjectNetworkGraph aGraph);
    JungResource getResource();
}

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

package at.silverstrike.pcc.impl.projectgraph;

import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import at.silverstrike.pcc.api.projectgraph.ProjectNetwork;
import at.silverstrike.pcc.api.projectgraph.ProjectNetworkVertex;

/**
 * @author DP118M
 * 
 */
class DefaultProjectNetwork extends
        DirectedSparseMultigraph<ProjectNetworkVertex, String> implements
        ProjectNetwork {
    private static final long serialVersionUID = 1L;

}

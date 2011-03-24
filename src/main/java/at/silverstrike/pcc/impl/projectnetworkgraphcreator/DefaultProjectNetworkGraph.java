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

package at.silverstrike.pcc.impl.projectnetworkgraphcreator;

import at.silverstrike.pcc.api.projectnetworkgraphcreator.ProjectNetworkGraph;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;

/**
 * @author DP118M
 * 
 */
class DefaultProjectNetworkGraph extends
        DirectedSparseMultigraph<String, String> implements ProjectNetworkGraph {
    private static final long serialVersionUID = 1L;
    private String initialEventVertex;
    private String finalEventVertex;

    public String getInitialEventVertex() {
        return initialEventVertex;
    }

    public void setInitialEventVertex(final String aInitialEventVertex) {
        this.initialEventVertex = aInitialEventVertex;
    }

    public String getFinalEventVertex() {
        return finalEventVertex;
    }

    public void setFinalEventVertex(final String aFinalEventVertex) {
        this.finalEventVertex = aFinalEventVertex;
    }

}

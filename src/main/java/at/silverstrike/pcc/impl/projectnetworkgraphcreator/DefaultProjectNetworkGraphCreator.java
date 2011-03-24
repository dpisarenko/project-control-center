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

import java.util.LinkedList;
import java.util.List;

import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.projectnetworkgraphcreator.ProjectNetworkGraph;
import at.silverstrike.pcc.api.projectnetworkgraphcreator.ProjectNetworkGraphCreator;
import at.silverstrike.pcc.api.projectnetworkgraphcreator.SchedulingObjectDependencyTuple;

/**
 * @author DP118M
 * 
 */
class DefaultProjectNetworkGraphCreator implements ProjectNetworkGraphCreator {
    private List<SchedulingObjectDependencyTuple> dependencies;
    private DefaultProjectNetworkGraph graph;
    private int edgeCounter;
    private String initialVertexLabel;
    private String finalVertexLabel;

    @Override
    public void run() throws PccException {
        this.graph = new DefaultProjectNetworkGraph();

        if (this.dependencies == null) {
            return;
        }

        edgeCounter = 1;

        final String initialEventVertex = this.initialVertexLabel;
        final String finalEventVertex = this.finalVertexLabel;

        this.graph.setInitialEventVertex(initialEventVertex);
        this.graph.setFinalEventVertex(finalEventVertex);

        for (final SchedulingObjectDependencyTuple curTuple : this.dependencies) {
            /**
             * Add target vertex (if not created already)
             */
            final String curTargetVertex = curTuple.getLabel();

            addVertexIfNecessary(curTargetVertex);

            if (curTuple.getDependencies() != null) {
                /**
                 * Add edges
                 */
                for (final String curSourceVertex : curTuple.getDependencies()) {
                    /**
                     * Add source vertices (if necessary)
                     */

                    addVertexIfNecessary(curSourceVertex);

                    addEdge(curSourceVertex, curTargetVertex);
                }

            }
        }

        /**
         * Add edges from initial event vertex
         */
        final List<String> verticesWithoutIncomingEdges =
                new LinkedList<String>();
        final List<String> verticesWithoutOutgoingEdges =
                new LinkedList<String>();

        for (final String curVertex : this.graph.getVertices()) {
            if (this.graph.inDegree(curVertex) == 0) {
                verticesWithoutIncomingEdges.add(curVertex);
            }
            if (this.graph.outDegree(curVertex) == 0) {
                verticesWithoutOutgoingEdges.add(curVertex);
            }
        }

        for (final String curTarget : verticesWithoutIncomingEdges) {
            addEdge(initialEventVertex, curTarget);
        }

        /**
         * Add edges to final event vertex
         */
        for (final String curSource : verticesWithoutOutgoingEdges) {
            addEdge(curSource, finalEventVertex);
        }
    }

    private void addEdge(final String aCurSourceVertex,
            final String aCurTargetVertex) {
        this.graph.addEdge(String.valueOf(edgeCounter),
                aCurSourceVertex, aCurTargetVertex);

        edgeCounter++;
    }

    private void addVertexIfNecessary(final String aVertex) {
        if (!this.graph.containsVertex(aVertex)) {
            this.graph.addVertex(aVertex);
        }
    }

    @Override
    public void setDependencies(
            final List<SchedulingObjectDependencyTuple> aDependencies) {
        this.dependencies = aDependencies;
    }

    @Override
    public ProjectNetworkGraph getGraph() {
        return graph;
    }

    @Override
    public void setInitialVertexLabel(final String aLabel) {
        this.initialVertexLabel = aLabel;
    }

    @Override
    public void setFinalVertexLabel(final String aLabel) {
        this.finalVertexLabel = aLabel;
    }
}

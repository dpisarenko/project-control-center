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

import java.util.List;

import com.google.inject.Injector;

import eu.livotov.tpt.i18n.TM;

import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.model.ControlProcess;
import at.silverstrike.pcc.api.projectgraph.ProjectGraphBuilder;
import at.silverstrike.pcc.api.projectgraph.ProjectNetwork;
import at.silverstrike.pcc.api.projectgraph.ProjectNetworkVertex;
import at.silverstrike.pcc.api.projectgraph.ProjectNetworkVertexType;
import at.silverstrike.pcc.api.projectnetworklayout.ProjectNetworkVertexCreator;

/**
 * @author DP118M
 *
 */
class DefaultProjectGraphBuilder implements ProjectGraphBuilder {
    private List<ControlProcess> schedulingObjects;
    private ProjectNetwork projectNetwork;
    private Injector injector = null;
    
    @Override
    public void setSchedulingObjects(final List<ControlProcess> aTasks) {
        this.schedulingObjects = aTasks;
    }

    @Override
    public void run() throws PccException {
        /**
         * Create task vertices
         */
        final ProjectNetworkVertexCreator vertexCreator =
            this.injector.getInstance(ProjectNetworkVertexCreator.class);
    
        vertexCreator.setTasks(this.schedulingObjects);
        vertexCreator.run();
        
        final List<ProjectNetworkVertex> vertices = vertexCreator.getVertices();

        /**
         * Create the graph
         */
        this.projectNetwork = createProjectNetwork();
        
        /**
         * Add all task vertices
         */
        addTaskVertices(this.projectNetwork, vertices);
        
        /**
         * Add initial event vertex
         */
        final ProjectNetworkVertex initialEvent = getInitialEventVertex();
        
        this.projectNetwork.addVertex(initialEvent);
        
        /**
         * Add final event vertex
         */
        final ProjectNetworkVertex finalEvent = getFinalEventVertex();
        
        this.projectNetwork.addVertex(finalEvent);
        
        /**
         * Add inter-process edges
         */
        addInterProcessEdges(vertices);
        
        /**
         * Connect initial event vertex to process edges without incoming edges
         */
        addInitialEventEdges(initialEvent, this.projectNetwork);
        
        /**
         * Connect process edges without outgoing edges to the final event vertex
         */
        addFinalEventEdges(initialEvent, this.projectNetwork);
        
        /**
         * Calculate ranks of the vertices
         */
                
        /**
         * Set positions of all task vertices on the imaginary grid
         */
    }

    private void addFinalEventEdges(final ProjectNetworkVertex aInitialEvent,
            final ProjectNetwork aProjectNetwork) {
        // TODO Auto-generated method stub
        
    }

    private void addInitialEventEdges(final ProjectNetworkVertex aInitialEvent,
            final ProjectNetwork aProjectNetwork) {
        // TODO Auto-generated method stub
        
    }

    private void addInterProcessEdges(final List<ProjectNetworkVertex> aVertices) {
        // TODO Auto-generated method stub
        
    }

    private ProjectNetworkVertex getFinalEventVertex() {
        final ProjectNetworkVertex vertex = new DefaultProjectNetworkVertex();
        
        vertex.setTaskId(null);
        vertex.setLabel(TM.get("projectgraph.1-final-event"));
        vertex.setType(ProjectNetworkVertexType.FINAL_EVENT);
        
        return vertex;
    }

    private ProjectNetwork createProjectNetwork() {
        return new DefaultProjectNetwork();
    }

    private void addTaskVertices(final ProjectNetwork aProjectNetwork,
            final List<ProjectNetworkVertex> aVertices) {
        // TODO Auto-generated method stub
        
    }

    private ProjectNetworkVertex getInitialEventVertex() {
        final ProjectNetworkVertex vertex = new DefaultProjectNetworkVertex();
        
        vertex.setTaskId(null);
        vertex.setLabel(TM.get("projectgraph.2-initial-event"));
        vertex.setType(ProjectNetworkVertexType.FINAL_EVENT);
        
        return vertex;

    }

    @Override
    public ProjectNetwork getProjectNetwork() {
        return this.projectNetwork;
    }
    
    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;
    }
}

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

package at.silverstrike.pcc.impl.graphdemopanel;

import java.util.List;

import com.google.inject.Injector;

import at.silverstrike.pcc.api.conventions.ModuleWithInjectableDependencies;
import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.conventions.SingleActivityModule;
import at.silverstrike.pcc.api.model.ControlProcess;
import at.silverstrike.pcc.api.projectgraph.ProjectNetworkVertex;
import at.silverstrike.pcc.api.projectnetworklayout.ProjectNetworkVertexCreator;

/**
 * @author DP118M
 * 
 */
class ProjectNetworkGraphCreator implements SingleActivityModule,
        ModuleWithInjectableDependencies {
    private List<ControlProcess> processes;
    private Injector injector;

    public void setTasks(final List<ControlProcess> aProcesses) {
        this.processes = aProcesses;
    }

    @Override
    public void run() throws PccException {
        /**
         * Add all task vertices
         */
        final ProjectNetworkVertexCreator vertexCreator =
                this.injector.getInstance(ProjectNetworkVertexCreator.class);
        
        vertexCreator.setTasks(this.processes);
        vertexCreator.run();
        
        final List<ProjectNetworkVertex> vertices = vertexCreator.getVertices();
        
        /**
         * Add initial event vertex
         */
        
        
        
        /**
         * Add final event vertex
         */

        /**
         * Add inter-process edges
         */
        
        /**
         * Connect initial event vertex to process edges without incoming edges
         */
        
        /**
         * Connect process edges without outgoing edges to the final event vertex
         */
        
        
    }

    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;
    }
}

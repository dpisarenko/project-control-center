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

package at.silverstrike.pcc.impl.projectnetworklayout;

import java.awt.Dimension;
import java.awt.geom.Point2D;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.graph.Graph;
import at.silverstrike.pcc.api.projectgraph.ProjectNetworkVertex;
import at.silverstrike.pcc.api.projectnetworklayout.ProjectNetworkLayout;

/**
 * @author DP118M
 *
 */
class DefaultProjectNetworkLayout implements ProjectNetworkLayout {

    @Override
    public void initialize() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setInitializer(
            Transformer<ProjectNetworkVertex, Point2D> initializer) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setGraph(final Graph<ProjectNetworkVertex, String> aGraph) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Graph<ProjectNetworkVertex, String> getGraph() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void reset() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setSize(final Dimension aDimension) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Dimension getSize() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void lock(final ProjectNetworkVertex aVertex, final boolean aState) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isLocked(final ProjectNetworkVertex aVertex) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setLocation(final ProjectNetworkVertex aVertex, final Point2D aLocation) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Point2D transform(final ProjectNetworkVertex aVertex) {
        // TODO Auto-generated method stub
        return null;
    }

}

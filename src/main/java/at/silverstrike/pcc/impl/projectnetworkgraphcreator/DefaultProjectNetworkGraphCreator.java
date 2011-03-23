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

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.conventions.SingleActivityModule#run()
     */
    @Override
    public void run() throws PccException {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.projectnetworkgraphcreator.ProjectNetworkGraphCreator#setDependencies(java.util.List)
     */
    @Override
    public void setDependencies(
            List<SchedulingObjectDependencyTuple> aDependencies) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.projectnetworkgraphcreator.ProjectNetworkGraphCreator#getGraph()
     */
    @Override
    public ProjectNetworkGraph getGraph() {
        // TODO Auto-generated method stub
        return null;
    }

}

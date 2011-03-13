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

package at.silverstrike.pcc.api.projectnetworklayout;

import java.util.List;

import at.silverstrike.pcc.api.conventions.SingleActivityModule;
import at.silverstrike.pcc.api.model.ControlProcess;
import at.silverstrike.pcc.api.projectgraph.ProjectNetworkVertex;

/**
 * @author DP118M
 *
 */
public interface ProjectNetworkVertexCreator extends SingleActivityModule {
    void setTasks(final List<ControlProcess> aProcesses);

    List<ProjectNetworkVertex> getVertices();
}

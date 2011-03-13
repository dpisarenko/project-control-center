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

package at.silverstrike.pcc.api.projectgraph;

import java.util.List;

import at.silverstrike.pcc.api.conventions.ModuleWithInjectableDependencies;
import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.conventions.SingleActivityModule;
import at.silverstrike.pcc.api.model.ControlProcess;

/**
 * @author DP118M
 * 
 */
public interface ProjectGraphBuilder extends SingleActivityModule,
        ModuleWithInjectableDependencies {
    void setSchedulingObjects(final List<ControlProcess> aTasks);

    void run() throws PccException;

    ProjectNetwork getProjectNetwork();
}

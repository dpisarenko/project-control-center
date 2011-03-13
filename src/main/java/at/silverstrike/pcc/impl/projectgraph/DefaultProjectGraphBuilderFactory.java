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

import at.silverstrike.pcc.api.projectgraph.ProjectGraphBuilder;
import at.silverstrike.pcc.api.projectgraph.ProjectGraphBuilderFactory;

/**
 * @author DP118M
 *
 */
public class DefaultProjectGraphBuilderFactory implements
        ProjectGraphBuilderFactory {

    @Override
    public ProjectGraphBuilder create() {
        return new DefaultProjectGraphBuilder();
    }

}

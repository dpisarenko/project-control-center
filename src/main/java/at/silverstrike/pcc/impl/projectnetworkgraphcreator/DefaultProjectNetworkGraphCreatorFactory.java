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

import at.silverstrike.pcc.api.projectnetworkgraphcreator.ProjectNetworkGraphCreator;
import at.silverstrike.pcc.api.projectnetworkgraphcreator.ProjectNetworkGraphCreatorFactory;

/**
 * @author DP118M
 *
 */
public class DefaultProjectNetworkGraphCreatorFactory implements
        ProjectNetworkGraphCreatorFactory {

    @Override
    public final ProjectNetworkGraphCreator create() {
        return new DefaultProjectNetworkGraphCreator();
    }

}

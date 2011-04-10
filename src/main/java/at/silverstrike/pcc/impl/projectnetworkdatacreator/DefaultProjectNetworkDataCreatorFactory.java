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

package at.silverstrike.pcc.impl.projectnetworkdatacreator;

import at.silverstrike.pcc.api.projectnetworkdatacreator.ProjectNetworkDataCreator;
import at.silverstrike.pcc.api.projectnetworkdatacreator.ProjectNetworkDataCreatorFactory;

/**
 * @author DP118M
 *
 */
public final class DefaultProjectNetworkDataCreatorFactory implements
        ProjectNetworkDataCreatorFactory {

    @Override
    public ProjectNetworkDataCreator create() {
        return new DefaultProjectNetworkDataCreator();
    }

}

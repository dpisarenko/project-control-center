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

package at.silverstrike.pcc.impl.projectnetworkgraphpanel;

import at.silverstrike.pcc.api.projectnetworkgraphpanel.ProjectNetworkGraphPanel;
import at.silverstrike.pcc.api.projectnetworkgraphpanel.ProjectNetworkGraphPanelFactory;

/**
 * @author DP118M
 * 
 */
public final class DefaultProjectNetworkGraphPanelFactory implements
        ProjectNetworkGraphPanelFactory {

    @Override
    public ProjectNetworkGraphPanel create() {
        return new DefaultProjectNetworkGraphPanel();
    }

}

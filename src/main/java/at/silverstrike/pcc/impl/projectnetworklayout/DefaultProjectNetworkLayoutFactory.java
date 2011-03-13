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

import at.silverstrike.pcc.api.projectnetworklayout.ProjectNetworkLayout;
import at.silverstrike.pcc.api.projectnetworklayout.ProjectNetworkLayoutFactory;

/**
 * @author DP118M
 *
 */
public class DefaultProjectNetworkLayoutFactory implements
        ProjectNetworkLayoutFactory {
    @Override
    public final ProjectNetworkLayout create() {
        return new DefaultProjectNetworkLayout();
    }

}

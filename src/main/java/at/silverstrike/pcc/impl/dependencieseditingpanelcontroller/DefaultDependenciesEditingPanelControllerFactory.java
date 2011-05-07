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

package at.silverstrike.pcc.impl.dependencieseditingpanelcontroller;

import at.silverstrike.pcc.api.dependencieseditingpanelcontroller.DependenciesEditingPanelController;
import at.silverstrike.pcc.api.dependencieseditingpanelcontroller.DependenciesEditingPanelControllerFactory;

/**
 * @author DP118M
 *
 */
public final class DefaultDependenciesEditingPanelControllerFactory implements
        DependenciesEditingPanelControllerFactory {

    @Override
    public DependenciesEditingPanelController create() {
        return new DefaultDependenciesEditingPanelController();
    }

}

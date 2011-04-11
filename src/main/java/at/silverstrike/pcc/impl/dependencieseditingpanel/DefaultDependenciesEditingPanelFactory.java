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

package at.silverstrike.pcc.impl.dependencieseditingpanel;

import at.silverstrike.pcc.api.dependencieseditingpanel.DependenciesEditingPanel;
import at.silverstrike.pcc.api.dependencieseditingpanel.DependenciesEditingPanelFactory;

/**
 * @author Dmitri Pisarenko
 * 
 */
public class DefaultDependenciesEditingPanelFactory implements
        DependenciesEditingPanelFactory {

    @Override
    public final DependenciesEditingPanel create() {
        return new DefaultDependenciesEditingPanel();
    }

}

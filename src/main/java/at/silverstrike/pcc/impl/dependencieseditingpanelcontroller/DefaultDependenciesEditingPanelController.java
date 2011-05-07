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

import com.google.inject.Injector;
import com.vaadin.ui.Panel;

import at.silverstrike.pcc.api.dependencieseditingpanel.DependenciesEditingPanel;
import at.silverstrike.pcc.api.dependencieseditingpanel.DependenciesEditingPanelFactory;
import at.silverstrike.pcc.api.dependencieseditingpanelcontroller.DependenciesEditingPanelController;

/**
 * @author DP118M
 * 
 */
class DefaultDependenciesEditingPanelController implements
        DependenciesEditingPanelController {
    private transient Injector injector;

    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;
    }

    @Override
    public Panel initGui() {
        final DependenciesEditingPanelFactory factory =
                injector.getInstance(DependenciesEditingPanelFactory.class);
        final DependenciesEditingPanel panel = factory.create();
        
        panel.initGui();

        return panel.toPanel();
    }
}

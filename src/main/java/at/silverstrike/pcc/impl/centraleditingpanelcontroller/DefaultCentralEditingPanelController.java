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

package at.silverstrike.pcc.impl.centraleditingpanelcontroller;

import com.google.inject.Injector;

import at.silverstrike.pcc.api.centraleditingpanelcontroller.CentralEditingPanelController;
import at.silverstrike.pcc.api.dependencieseditingwindow.DependenciesEditingPanel;
import at.silverstrike.pcc.api.dependencieseditingwindow.DependenciesEditingPanelFactory;

class DefaultCentralEditingPanelController implements
        CentralEditingPanelController {
    private Injector injector = null;

    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;
    }

    @Override
    public void increasePriorityButtonClicked() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void decreasePriorityButtonClicked() {
        // TODO Auto-generated method stub
        
    }

}

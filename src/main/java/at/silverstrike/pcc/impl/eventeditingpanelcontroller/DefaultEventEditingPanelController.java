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

package at.silverstrike.pcc.impl.eventeditingpanelcontroller;

import com.google.inject.Injector;
import com.vaadin.ui.Panel;

import at.silverstrike.pcc.api.dependencieseditingwindow.DependenciesEditingPanel;
import at.silverstrike.pcc.api.dependencieseditingwindow.DependenciesEditingPanelFactory;
import at.silverstrike.pcc.api.eventeditingpanelcontroller.EventEditingPanelController;
import at.silverstrike.pcc.api.model.Task;

/**
 * @author DP118M
 * 
 */
class DefaultEventEditingPanelController implements
        EventEditingPanelController {
    private Injector injector = null;

    @Override
    public void dependEditButtonClicked() {
        final DependenciesEditingPanelFactory factory =
                this.injector
                        .getInstance(DependenciesEditingPanelFactory.class);
        final DependenciesEditingPanel panel = factory.create();
        panel.setInjector(injector);
        panel.initGui();
    }

    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;
    }

    @Override
    public Panel initGui() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void taskCreated(Task aNewTask) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void taskCreationFailure() {
        // TODO Auto-generated method stub
        
    }

}
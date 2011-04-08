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
import at.silverstrike.pcc.api.eventeditingpanel.EventEditingPanel;
import at.silverstrike.pcc.api.eventeditingpanel.EventEditingPanelFactory;
import at.silverstrike.pcc.api.eventeditingpanelcontroller.EventEditingPanelController;
import at.silverstrike.pcc.api.model.Event;
import at.silverstrike.pcc.api.model.Milestone;
import at.silverstrike.pcc.api.model.Task;

/**
 * @author DP118M
 * 
 */
class DefaultEventEditingPanelController implements
        EventEditingPanelController {
    private Injector injector = null;
    private EventEditingPanel panel;

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
        final EventEditingPanelFactory factory = this.injector.getInstance(EventEditingPanelFactory.class);
        this.panel = factory.create();
        this.panel.setInjector(this.injector);
        this.panel.initGui();
        return this.panel.toPanel();
    }

    @Override
    public void taskCreated(final Task aNewTask) {
        // TODO Auto-generated method stub

    }

    @Override
    public void taskCreationFailure() {
        // TODO Auto-generated method stub

    }

    @Override
    public void taskEdited(final Task aTask) {
        // TODO Auto-generated method stub

    }

    @Override
    public void eventCreated(final Event aNewEvent) {
        // TODO Auto-generated method stub

    }

    @Override
    public void eventCreationFailure() {
        // TODO Auto-generated method stub

    }

    @Override
    public void setData(final Event aEvent) {
        this.panel.setEventName(aEvent.getName());
    }

    @Override
    public void milestoneCreated(Milestone aMilestone) {
        // TODO Auto-generated method stub

    }

    @Override
    public void milestoneCreationFailure() {
        // TODO Auto-generated method stub

    }

}
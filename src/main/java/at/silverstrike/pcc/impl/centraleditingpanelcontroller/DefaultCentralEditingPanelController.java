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
import com.vaadin.ui.Panel;

import eu.livotov.tpt.i18n.TM;

import at.silverstrike.pcc.api.centraleditingpanel.CentralEditingPanel;
import at.silverstrike.pcc.api.centraleditingpanel.CentralEditingPanelFactory;
import at.silverstrike.pcc.api.centraleditingpanelcontroller.CentralEditingPanelController;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.webguibus.WebGuiBus;

class DefaultCentralEditingPanelController implements
        CentralEditingPanelController {
    private Injector injector = null;
    private Persistence persistence;
    private WebGuiBus webGuiBus;
    private CentralEditingPanel panel;

    @Override
    public void setInjector(final Injector aInjector) {
        if (aInjector != null) {
            this.injector = aInjector;
            this.persistence = this.injector.getInstance(Persistence.class);
            this.webGuiBus = this.injector.getInstance(WebGuiBus.class);
        }
    }

    @Override
    public void increasePriorityButtonClicked() {
        // TODO Auto-generated method stub

    }

    @Override
    public void decreasePriorityButtonClicked() {
        // TODO Auto-generated method stub

    }

    @Override
    public void createMilestone(final String aUserIdentity,
            final Long aProjectIdCurrentlySelectedInTree) {
    }

    @Override
    public void createTask(final String aUserIdentity,
            final Long aProjectIdCurrentlySelectedInTree) {
        final String taskName =
                TM.get("centraleditingpanelcontroller.1-new-task-name");
        final Task newTask = this.persistence.createSubTask(
                taskName + " + ctl",
                aProjectIdCurrentlySelectedInTree);

        if (newTask != null) {
            this.webGuiBus.broadcastTaskCreatedMessage(newTask);
        } else {
            this.webGuiBus.broadcastTaskCreationFailureMessage();
        }
    }

	@Override
    public void createEvent(final String aUserIdentity,
            final Long aProjectIdCurrentlySelectedInTree) {
        // TODO Auto-generated method stub

    }

    @Override
    public void taskCreated(final Task aNewTask) {
        this.panel.taskCreated(aNewTask);
    }

    @Override
    public void taskCreationFailure() {
        this.panel.taskCreationFailure();
    }

    @Override
    public Panel initGui() {
        this.webGuiBus.addListener(this);
        
        final CentralEditingPanelFactory factory = this.injector.getInstance(CentralEditingPanelFactory.class);
        panel = factory.create();
        
        panel.setGuiController(this);
        panel.setInjector(this.injector);
        panel.initGui();

        return panel.toPanel();
    }

    @Override
    public void taskEdited(final Task aTask) {
        // TODO Auto-generated method stub
        
    }
}

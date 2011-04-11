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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Injector;
import com.vaadin.ui.Panel;

import eu.livotov.tpt.i18n.TM;

import at.silverstrike.pcc.api.centraleditingpanel.CentralEditingPanel;
import at.silverstrike.pcc.api.centraleditingpanel.CentralEditingPanelFactory;
import at.silverstrike.pcc.api.centraleditingpanelcontroller.CentralEditingPanelController;
import at.silverstrike.pcc.api.model.Milestone;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.model.Event;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.webguibus.WebGuiBus;
import at.silverstrike.pcc.impl.webguibus.WebGuiBusListenerAdapter;

class DefaultCentralEditingPanelController extends WebGuiBusListenerAdapter implements
        CentralEditingPanelController {
    private Injector injector = null;
    private Persistence persistence = null;
    private WebGuiBus webGuiBus = null;
    private CentralEditingPanel panel = null;
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultCentralEditingPanelController.class);

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
        final String milestoneName = TM
                .get("centraleditingpanelcontroller.3-new-milestone-name");
        final Milestone newMilestone =
                this.persistence.createNewMilestone(null,
                        milestoneName + " + ctl",
                        aProjectIdCurrentlySelectedInTree);

        if (milestoneName != null) {
            this.webGuiBus.broadcastMilestoneCreatedMessage(newMilestone);
        } else {
            this.webGuiBus.broadcastMilestoneCreationFailureMessage();
        }
    }

    @Override
    public void createTask(final String aUserIdentity,
            final Long aProjectIdCurrentlySelectedInTree) {
        final String taskName = TM
                .get("centraleditingpanelcontroller.1-new-task-name");
        final Task newTask = this.persistence.createSubTask(
                taskName + " + ctl", aProjectIdCurrentlySelectedInTree);

        if (newTask != null) {
            this.webGuiBus.broadcastTaskCreatedMessage(newTask);
        } else {
            this.webGuiBus.broadcastTaskCreationFailureMessage();
        }
    }

    @Override
    public void createEvent(final String aUserIdentity,
            final Long aProjectIdCurrentlySelectedInTree) {
        final String eventName = TM
                .get("centraleditingpanelcontroller.2-new-event-name");
        final Event newEvent = this.persistence.createSubEvent(eventName
                + " + ctl", aProjectIdCurrentlySelectedInTree);

        if (newEvent != null) {
            this.webGuiBus.broadcastEventCreatedMessage(newEvent);
        } else {
            this.webGuiBus.broadcastEventCreationFailureMessage();
        }
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

        final CentralEditingPanelFactory factory = this.injector
                .getInstance(CentralEditingPanelFactory.class);
        panel = factory.create();

        panel.setGuiController(this);
        panel.setInjector(this.injector);
        panel.initGui();

        return panel.toPanel();
    }

    @Override
    public void taskEdited(final Task aTask) {
        this.panel.updateTaskNodeInTree(aTask);
    }

    @Override
    public void eventCreated(final Event aNewEvent) {
        this.panel.eventCreated(aNewEvent);

    }

    @Override
    public void eventCreationFailure() {
        this.panel.eventCreationFailure();

    }

    @Override
    public void milestoneCreated(final Milestone aMilestone) {
        this.panel.milestoneCreated(aMilestone);

    }

    @Override
    public void milestoneCreationFailure() {
        this.panel.milestoneCreationFailure();

    }

    @Override
    public void taskDeleted(final Task aTask) {
        LOGGER.debug("Task deleted: " + aTask);
        this.panel.schedulingObjectDeleted(aTask);
    }

    @Override
    public void taskDeletionFailure() {
        LOGGER.debug("Task deletion failure");
        this.panel.taskDeletionFailure();

    }

    @Override
    public void eventDeletionFailure() {
        this.panel.eventDeletionFailure();

    }

    @Override
    public void milestoneDeletionFailure() {
        this.panel.milestoneDeletionFailure();

    }

    @Override
    public void eventDeleted(final Event aNewEvent) {
        this.panel.schedulingObjectDeleted(aNewEvent);
    }

    @Override
    public void milestoneDeleted(final Milestone aMilestone) {
        this.panel.schedulingObjectDeleted(aMilestone);
    }

	@Override
	public void milestoneEdited(Milestone aMilestone) {
		//this.panel.updateTaskNodeInTree(aMilestone);
		
	}

	@Override
	public void eventEdited(Event aEvent) {
		//this.panel.updateTaskNodeInTree(aEvent);
		
	}

}

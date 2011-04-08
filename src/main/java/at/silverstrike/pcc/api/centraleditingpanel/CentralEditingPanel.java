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

package at.silverstrike.pcc.api.centraleditingpanel;

import at.silverstrike.pcc.api.centraleditingpanelcontroller.CentralEditingPanelController;
import at.silverstrike.pcc.api.conventions.AbstractedPanel;
import at.silverstrike.pcc.api.conventions.ExternallyControlledGuiComponent;
import at.silverstrike.pcc.api.conventions.ModuleWithInjectableDependencies;
import at.silverstrike.pcc.api.model.Event;
import at.silverstrike.pcc.api.model.Milestone;
import at.silverstrike.pcc.api.model.Task;

public interface CentralEditingPanel extends ModuleWithInjectableDependencies,
        AbstractedPanel,
        ExternallyControlledGuiComponent<CentralEditingPanelController> {

    void taskCreated(final Task aNewTask);

    void taskCreationFailure();

    void updateTaskNodeInTree(final Task aTask);

    void eventCreated(final Event aNewEvent);

    void eventCreationFailure();

	void milestoneCreated(final Milestone aMilestone);

	void milestoneCreationFailure();

	void taskDeleted(final Task aTask);

	void taskDeletionFailure();
	
    void eventDeleted(final Event aNewEvent);

    void eventDeletionFailure();

	void milestoneDeleted(final Milestone aMilestone);

	void milestoneDeletionFailure();
}

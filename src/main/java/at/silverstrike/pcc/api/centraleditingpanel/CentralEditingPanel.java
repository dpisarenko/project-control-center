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

import com.vaadin.ui.Panel;

import ru.altruix.commons.api.di.ModuleWithInjectableDependencies;
import ru.altruix.commons.api.gui.ExternallyControlledGuiComponent;
import ru.altruix.commons.api.vaadin.AbstractedPanel;
import at.silverstrike.pcc.api.centraleditingpanelcontroller.CentralEditingPanelController;
import at.silverstrike.pcc.api.model.Event;
import at.silverstrike.pcc.api.model.Milestone;
import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.model.Task;

public interface CentralEditingPanel extends ModuleWithInjectableDependencies,
        AbstractedPanel,
        ExternallyControlledGuiComponent<CentralEditingPanelController, Panel> {

    void taskCreated(final Task aNewTask);

    void taskCreationFailure();

    void updateTaskNodeInTree(final Task aTask);

    void eventCreated(final Event aNewEvent);

    void eventCreationFailure();

    void milestoneCreated(final Milestone aMilestone);

    void taskDeletionFailure();

    void eventDeletionFailure();

    void milestoneDeletionFailure();

    void milestoneCreationFailure();

    void schedulingObjectDeleted(final SchedulingObject aSchedulingObject);

    void updateMilestoneNodeInTree(final Milestone aMilestone);

    void updateEventNodeInTree(final Event aEvent);

    void taskDependenciesChanged(final SchedulingObject aObject);

    void taskCompleted(final Task aTask);

    void tasksImportedFromGoogle();
}

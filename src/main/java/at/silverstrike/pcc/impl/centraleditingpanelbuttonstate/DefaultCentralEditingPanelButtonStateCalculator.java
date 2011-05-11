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

package at.silverstrike.pcc.impl.centraleditingpanelbuttonstate;

import com.google.inject.Injector;

import ru.altruix.commons.api.di.PccException;
import at.silverstrike.pcc.api.centraleditingpanelbuttonstate.CentralEditingPanelButtonStateCalculator;
import at.silverstrike.pcc.api.model.Event;
import at.silverstrike.pcc.api.model.Milestone;
import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.webguibus.WebGuiBus;

/**
 * @author DP118M
 * 
 */
class DefaultCentralEditingPanelButtonStateCalculator implements
        CentralEditingPanelButtonStateCalculator {
    private Injector injector = null;
    private Persistence persistence = null;
    private SchedulingObject currentSelection;
    private boolean increasePriorityButtonEnabled;
    private boolean decreasePriorityButtonEnabled;
    private boolean newTaskButtonEnabled;
    private boolean newEventButtonEnabled;
    private boolean newMilestoneButtonEnabled;

    @Override
    public void setInjector(final Injector aInjector) {
        if (aInjector != null) {
            this.injector = aInjector;
            this.persistence = this.injector.getInstance(Persistence.class);
        }
    }

    @Override
    public void run() throws PccException {
        if (this.currentSelection == null) {
            this.newTaskButtonEnabled = true;
            this.newEventButtonEnabled = true;
            this.newMilestoneButtonEnabled = true;
            this.increasePriorityButtonEnabled = false;
            this.decreasePriorityButtonEnabled = false;
        } else if (this.currentSelection instanceof Task) {
            this.newTaskButtonEnabled = true;
            this.newEventButtonEnabled = true;
            this.newMilestoneButtonEnabled = true;
            this.increasePriorityButtonEnabled =
                    !persistence.isHighestPriorityObjectInProject(
                            this.currentSelection.getParent(),
                            this.currentSelection);
            this.decreasePriorityButtonEnabled =
                    !persistence.isLowestPriorityObjectInProject(
                            this.currentSelection.getParent(),
                            this.currentSelection);
        } else if ((this.currentSelection instanceof Milestone)
                || (this.currentSelection instanceof Event)) {
            this.newTaskButtonEnabled = false;
            this.newEventButtonEnabled = false;
            this.newMilestoneButtonEnabled = false;
            this.increasePriorityButtonEnabled =
                    !persistence.isHighestPriorityObjectInProject(
                            this.currentSelection.getParent(),
                            this.currentSelection);
            this.decreasePriorityButtonEnabled =
                    !persistence.isLowestPriorityObjectInProject(
                            this.currentSelection.getParent(),
                            this.currentSelection);
        }
    }

    @Override
    public void setCurrentSelection(final SchedulingObject aObject) {
        this.currentSelection = aObject;
    }

    @Override
    public Boolean isIncreasePriorityButtonEnabled() {
        return this.increasePriorityButtonEnabled;
    }

    @Override
    public Boolean isDecreasePriorityButtonEnabled() {
        return this.decreasePriorityButtonEnabled;
    }

    public boolean isNewTaskButtonEnabled() {
        return newTaskButtonEnabled;
    }

    public boolean isNewEventButtonEnabled() {
        return newEventButtonEnabled;
    }

    public boolean isNewMilestoneButtonEnabled() {
        return newMilestoneButtonEnabled;
    }
}

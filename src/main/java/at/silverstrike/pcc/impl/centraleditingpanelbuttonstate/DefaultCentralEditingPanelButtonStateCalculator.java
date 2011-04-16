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

import ru.altruix.commons.api.di.PccException;
import at.silverstrike.pcc.api.centraleditingpanelbuttonstate.CentralEditingPanelButtonStateCalculator;
import at.silverstrike.pcc.api.model.Event;
import at.silverstrike.pcc.api.model.Milestone;
import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.model.Task;

/**
 * @author DP118M
 * 
 */
class DefaultCentralEditingPanelButtonStateCalculator implements
        CentralEditingPanelButtonStateCalculator {
    private SchedulingObject currentSelection;
    private boolean increasePriorityButtonEnabled;
    private boolean decreasePriorityButtonEnabled;
    private boolean newTaskButtonEnabled;
    private boolean newEventButtonEnabled;
    private boolean newMilestoneButtonEnabled;

    @Override
    public void run() throws PccException {
        this.increasePriorityButtonEnabled = false;
        this.decreasePriorityButtonEnabled = false;        
        
        if (this.currentSelection == null) {
            this.newTaskButtonEnabled = true;
            this.newEventButtonEnabled = true;
            this.newMilestoneButtonEnabled = true;
        } else if (this.currentSelection instanceof Task) {
            this.newTaskButtonEnabled = true;
            this.newEventButtonEnabled = true;
            this.newMilestoneButtonEnabled = true;
        } else if ((this.currentSelection instanceof Milestone)
                || (this.currentSelection instanceof Event)) {
            this.newTaskButtonEnabled = false;
            this.newEventButtonEnabled = false;
            this.newMilestoneButtonEnabled = false;
        }
    }

    @Override
    public void setCurrentSelection(final SchedulingObject aObject) {
        this.currentSelection = aObject;
    }

    @Override
    public boolean isIncreasePriorityButtonEnabled() {
        return this.increasePriorityButtonEnabled;
    }

    @Override
    public boolean isDecreasePriorityButtonEnabled() {
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

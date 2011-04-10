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

import at.silverstrike.pcc.api.centraleditingpanelbuttonstate.CentralEditingPanelButtonStateCalculator;
import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.model.SchedulingObject;

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
        // TODO Auto-generated method stub
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

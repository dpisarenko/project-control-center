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
    private Injector injector;
    
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
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isDecreasePriorityButtonEnabled() {
        // TODO Auto-generated method stub
        return false;
    }
    
    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;
    }
}

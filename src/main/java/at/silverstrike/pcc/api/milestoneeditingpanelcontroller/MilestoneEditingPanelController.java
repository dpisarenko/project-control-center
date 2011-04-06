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

package at.silverstrike.pcc.api.milestoneeditingpanelcontroller;

import com.vaadin.ui.Panel;

import at.silverstrike.pcc.api.conventions.GuiController;
import at.silverstrike.pcc.api.conventions.ModuleWithInjectableDependencies;

public interface MilestoneEditingPanelController extends
        ModuleWithInjectableDependencies, GuiController<Panel> {
    void dependEditButtonClicked();
}
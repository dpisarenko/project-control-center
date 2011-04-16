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

import ru.altruix.commons.api.di.ModuleWithInjectableDependencies;
import ru.altruix.commons.api.gui.GuiController;

import com.vaadin.ui.Panel;

import at.silverstrike.pcc.api.model.Milestone;

public interface MilestoneEditingPanelController extends
        ModuleWithInjectableDependencies, GuiController<Panel> {
    void dependEditButtonClicked();

    void setData(final Milestone aMilestone);
    
	void deleteMilestone(final Milestone aMilestone);

	void saveMilestone(final Milestone aMilestone);
}

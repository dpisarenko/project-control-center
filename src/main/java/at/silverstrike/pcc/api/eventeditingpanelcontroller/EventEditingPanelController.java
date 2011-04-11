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

package at.silverstrike.pcc.api.eventeditingpanelcontroller;

import com.vaadin.ui.Panel;

import at.silverstrike.pcc.api.conventions.GuiController;
import at.silverstrike.pcc.api.conventions.ModuleWithInjectableDependencies;
import at.silverstrike.pcc.api.model.Event;

public interface EventEditingPanelController extends
        ModuleWithInjectableDependencies, GuiController<Panel> {
    void dependEditButtonClicked();

    void setData(final Event aNewEvent);

	void deleteEvent(final at.silverstrike.pcc.api.model.Event aEvent);

	void saveEvent(final at.silverstrike.pcc.api.model.Event aEvent);
}

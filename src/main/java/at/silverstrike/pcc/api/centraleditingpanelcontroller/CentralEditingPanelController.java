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

package at.silverstrike.pcc.api.centraleditingpanelcontroller;

import ru.altruix.commons.api.di.ModuleWithInjectableDependencies;
import ru.altruix.commons.api.gui.GuiController;

import com.vaadin.ui.Panel;


public interface CentralEditingPanelController extends
        ModuleWithInjectableDependencies, GuiController<Panel> {
    void increasePriorityButtonClicked(final Long parentProjectId);

    void decreasePriorityButtonClicked(final Long parentProjectId);

    void createMilestone(final String aUserIdentity,
            final Long aProjectIdCurrentlySelectedInTree);

    void createTask(final String aUserIdentity,
            final Long aProjectIdCurrentlySelectedInTree);

    void createEvent(final String aUserIdentity,
            final Long aProjectIdCurrentlySelectedInTree);
}

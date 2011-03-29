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

import com.vaadin.ui.Panel;

import at.silverstrike.pcc.api.conventions.ModuleWithInjectableDependencies;

public interface CentralEditingPanelController extends ModuleWithInjectableDependencies {
    void increasePriorityButtonClicked();
    void decreasePriorityButtonClicked();
    Panel getTaskPanel();
    Panel getMeetingPanel();
    Panel getMilestonePanel();
    void newTaskButtonClicked();
    void newMeetingButtonClicked();
    void newMilestoneButtonClicked();
    
}

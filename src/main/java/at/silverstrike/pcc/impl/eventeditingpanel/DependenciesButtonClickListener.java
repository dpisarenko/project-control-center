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

package at.silverstrike.pcc.impl.eventeditingpanel;

import at.silverstrike.pcc.api.eventeditingpanelcontroller.EventEditingPanelController;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

/**
 * @author DP118M
 * 
 */
class DependenciesButtonClickListener implements
        ClickListener {
    private static final long serialVersionUID = 1L;
    private transient EventEditingPanelController controller;

    public DependenciesButtonClickListener(
            final EventEditingPanelController aController) {
        this.controller = aController;
    }

    @Override
    public void buttonClick(final ClickEvent aEvent) {
        this.controller.dependEditButtonClicked();
    }
}
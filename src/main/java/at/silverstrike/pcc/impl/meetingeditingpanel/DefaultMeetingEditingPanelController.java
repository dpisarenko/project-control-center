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

package at.silverstrike.pcc.impl.meetingeditingpanel;

import com.google.inject.Injector;

import at.silverstrike.pcc.api.dependencieseditingwindow.DependenciesEditingPanel;
import at.silverstrike.pcc.api.dependencieseditingwindow.DependenciesEditingPanelFactory;

/**
 * @author DP118M
 * 
 */
class DefaultMeetingEditingPanelController implements
        MeetingEditingPanelController {
    private Injector injector = null;

    @Override
    public void dependEditButtonClicked() {
        final DependenciesEditingPanelFactory factory =
                this.injector
                        .getInstance(DependenciesEditingPanelFactory.class);
        final DependenciesEditingPanel panel = factory.create();
        panel.setInjector(injector);
        panel.initGui();
    }

    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;
    }

}
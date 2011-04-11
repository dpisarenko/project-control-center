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

package at.silverstrike.pcc.impl.milestoneeditingpanelcontroller;

import at.silverstrike.pcc.api.dependencieseditingwindow.DependenciesEditingPanel;
import at.silverstrike.pcc.api.dependencieseditingwindow.DependenciesEditingPanelFactory;
import at.silverstrike.pcc.api.milestoneeditingpanel.MilestoneEditingPanel;
import at.silverstrike.pcc.api.milestoneeditingpanel.MilestoneEditingPanelFactory;
import at.silverstrike.pcc.api.milestoneeditingpanelcontroller.MilestoneEditingPanelController;
import at.silverstrike.pcc.api.model.Milestone;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.webguibus.WebGuiBus;
import at.silverstrike.pcc.impl.webguibus.WebGuiBusListenerAdapter;

import com.google.inject.Injector;
import com.vaadin.ui.Panel;

class DefaultMilestoneEditingPanelController extends WebGuiBusListenerAdapter implements
        MilestoneEditingPanelController {
    private Injector injector = null;
    private transient Persistence persistence = null;
    private transient WebGuiBus webGuiBus = null;
    private MilestoneEditingPanel panel = null;

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
        if (aInjector != null) {
            this.persistence = this.injector.getInstance(Persistence.class);
            this.webGuiBus = this.injector.getInstance(WebGuiBus.class);
        }
    }

    @Override
    public Panel initGui() {
        final MilestoneEditingPanelFactory factory =
            this.injector.getInstance(MilestoneEditingPanelFactory.class);
    this.panel = factory.create();
    this.panel.setInjector(this.injector);
    this.panel.initGui();
    return this.panel.toPanel();
    }


    @Override
    public void setData(final Milestone aMilestone) {
    	this.panel.setMilestone(aMilestone);

    }

	@Override
	public void deleteMilestone(Milestone aMilestone) {
        if (this.persistence.deleteMilestone(aMilestone)) {
            this.webGuiBus.broadcastMilestoneDeletedMessage(aMilestone);
        } else {
            this.webGuiBus.broadcastMilestoneDeletionFailureMessage();
        }			
	}

	@Override
	public void saveMilestone(Milestone aMilestone) {
		// TODO Auto-generated method stub
		
	}

}
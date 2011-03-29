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

package at.silverstrike.pcc.impl.centraleditingpanelcontroller;

import com.google.inject.Injector;
import com.vaadin.ui.Panel;

import at.silverstrike.pcc.api.centraleditingpanelcontroller.CentralEditingPanelController;
import at.silverstrike.pcc.api.meetingeditingpanel.MeetingEditingPanel;
import at.silverstrike.pcc.api.meetingeditingpanel.MeetingEditingPanelFactory;
import at.silverstrike.pcc.api.milestoneeditingpanel.MilestoneEditingPanel;
import at.silverstrike.pcc.api.milestoneeditingpanel.MilestoneEditingPanelFactory;
import at.silverstrike.pcc.api.taskeditingpanel.TaskEditingPanel;
import at.silverstrike.pcc.api.taskeditingpanel.TaskEditingPanelFactory;


class DefaultCentralEditingPanelController implements
        CentralEditingPanelController {
    private Injector injector = null;

    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;
    }

    @Override
    public void increasePriorityButtonClicked() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void decreasePriorityButtonClicked() {
        // TODO Auto-generated method stub
        
    }

	@Override
	public Panel getTaskPanel() {
		final TaskEditingPanelFactory factory =
            this.injector.getInstance(TaskEditingPanelFactory.class);
        final TaskEditingPanel panel = factory.create();
        panel.setInjector(this.injector);
        panel.initGui();
		return panel.toPanel();
	}

	@Override
	public Panel getMeetingPanel() {
		final MeetingEditingPanelFactory factory =
            this.injector.getInstance(MeetingEditingPanelFactory.class);
        final MeetingEditingPanel panel = factory.create();
        panel.setInjector(this.injector);
        panel.initGui();
		return panel.toPanel();
	}

	@Override
	public Panel getMilestonePanel() {
		final MilestoneEditingPanelFactory factory =
            this.injector.getInstance(MilestoneEditingPanelFactory.class);
        final MilestoneEditingPanel panel = factory.create();
        panel.setInjector(this.injector);
        panel.initGui();
		return panel.toPanel();
	}
	    
	   @Override
	 public void newTaskButtonClicked() {
	   	// TODO Auto-generated method stub
	    	
	    }
	    
	    @Override
	    public void newMeetingButtonClicked() {
	        // TODO Auto-generated method stub
	        
	    }
	    
	    @Override
	    public void newMilestoneButtonClicked() {
	        // TODO Auto-generated method stub
	        
	    }

}

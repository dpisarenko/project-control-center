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

package at.silverstrike.pcc.impl.eventeditingpanelcontroller;

import com.google.inject.Injector;
import com.vaadin.ui.Panel;

import at.silverstrike.pcc.api.dependencieseditingpanel.DependenciesEditingPanel;
import at.silverstrike.pcc.api.dependencieseditingpanel.DependenciesEditingPanelFactory;
import at.silverstrike.pcc.api.eventeditingpanel.EventEditingPanel;
import at.silverstrike.pcc.api.eventeditingpanel.EventEditingPanelFactory;
import at.silverstrike.pcc.api.eventeditingpanelcontroller.EventEditingPanelController;
import at.silverstrike.pcc.api.model.Event;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.webguibus.WebGuiBus;
import at.silverstrike.pcc.impl.webguibus.WebGuiBusListenerAdapter;

/**
 * @author DP118M
 * 
 */
class DefaultEventEditingPanelController extends WebGuiBusListenerAdapter implements
        EventEditingPanelController {
    private Injector injector = null;
    private transient Persistence persistence = null;
    private transient WebGuiBus webGuiBus = null;
    private EventEditingPanel panel = null;

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
        final EventEditingPanelFactory factory =
                this.injector.getInstance(EventEditingPanelFactory.class);
        this.panel = factory.create();
        this.panel.setInjector(this.injector);
        this.panel.initGui();
        return this.panel.toPanel();
    }

    @Override
    public void eventCreated(final Event aNewEvent) {
        // TODO Auto-generated method stub

    }

    @Override
    public void eventCreationFailure() {
        // TODO Auto-generated method stub

    }

    @Override
    public void setData(final Event aEvent) {
        this.panel.setEvent(aEvent);
    }


	@Override
	public void deleteEvent(Event aEvent) {
        if (this.persistence.deleteEvent(aEvent)) {
            this.webGuiBus.broadcastEventDeletedMessage(aEvent);
        } else {
            this.webGuiBus.broadcastEventDeletionFailureMessage();
        }		
	}

	@Override
	public void saveEvent(Event aEvent) {
		// TODO Auto-generated method stub
		
	}

}
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

package at.silverstrike.pcc.impl.invitationrequestadminpanel;

import java.util.Date;

import com.google.inject.Injector;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.Button.ClickListener;

import eu.livotov.tpt.i18n.TM;

import at.silverstrike.pcc.api.invitationrequestadminpanel.InvitationRequestAdminPanel;
import at.silverstrike.pcc.api.invitationrequestadminpanelcontroller.InvitationRequestAdminPanelController;
import at.silverstrike.pcc.api.model.InvitationRequestStatus;

/**
 * @author DP118M
 * 
 */
public class DefaultInvitationRequestAdminPanel implements
        InvitationRequestAdminPanel, ClickListener {
    private static final long serialVersionUID = 1L;
    private InvitationRequestAdminPanelController controller;
    private Injector injector;
    private Panel panel;
    private Table table;

    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;
    }

    @Override
    public Panel toPanel() {
        return panel;
    }

    @Override
    public void initGui() {
        this.table = new Table();

        this.table.setWidth("80%");
        this.table.setHeight("170px");
        this.table.addContainerProperty(
                TM.get("invitationrequestadminpanel.1-id"), Long.class,
                null);
        this.table.addContainerProperty(
                TM.get("invitationrequestadminpanel.2-submissionDate"),
                Date.class,
                null);
        this.table.addContainerProperty(
                TM.get("invitationrequestadminpanel.3-openIdProvider"),
                String.class,
                null);
        this.table.addContainerProperty(
                TM.get("invitationrequestadminpanel.4-enteredId"),
                String.class,
                null);
        this.table.addContainerProperty(
                TM.get("invitationrequestadminpanel.5-status"),
                InvitationRequestStatus.class,
                null);
        this.table.addContainerProperty(
                TM.get("invitationrequestadminpanel.6-identity"), String.class,
                null);
        this.table.setSelectable(true);
        this.table.setMultiSelect(false);
        this.table.setImmediate(true);

        final Button refreshButton = new Button(TM.get("invitationrequestadminpanel.7-refreshButton"));
        final Button acceptButton = new Button(TM.get("invitationrequestadminpanel.8-acceptButton"));
        final Button rejectButton = new Button(TM.get("invitationrequestadminpanel.9-rejectButton"));
        
        refreshButton.addListener(this);
        acceptButton.addListener(this);
        rejectButton.addListener(this);
        
        final HorizontalLayout buttonLayout = new HorizontalLayout();
        
        buttonLayout.addComponent(refreshButton);
        buttonLayout.addComponent(acceptButton);
        buttonLayout.addComponent(rejectButton);
        
        this.panel.addComponent(this.table);
        this.panel.addComponent(buttonLayout);
    }

    @Override
    public void setGuiController(
            final InvitationRequestAdminPanelController aController) {
        this.controller = aController;
    }

    @Override
    public void buttonClick(final ClickEvent aEvent) {
        // TODO Auto-generated method stub
        
    }
}

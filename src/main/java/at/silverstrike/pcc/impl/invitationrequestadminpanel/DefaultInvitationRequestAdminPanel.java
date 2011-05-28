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
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Injector;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickListener;

import eu.livotov.tpt.i18n.TM;

import at.silverstrike.pcc.api.invitationrequestadminpanel.InvitationRequestAdminPanel;
import at.silverstrike.pcc.api.invitationrequestadminpanelcontroller.InvitationRequestAdminPanelController;
import at.silverstrike.pcc.api.model.InvitationRequest;
import at.silverstrike.pcc.api.model.InvitationRequestStatus;

/**
 * @author DP118M
 * 
 */
class DefaultInvitationRequestAdminPanel implements
        InvitationRequestAdminPanel, ClickListener {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultInvitationRequestAdminPanel.class);
    private InvitationRequestAdminPanelController controller;
    private Injector injector;
    private Panel panel;
    private Table table;
    private List<InvitationRequest> data;
    private String refreshButtonCaption;
    private String acceptButtonCaption;
    private String rejectButtonCaption;
    private TextField userIdentityTextField;

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
        this.panel = new Panel();
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

        refreshButtonCaption =
                TM.get("invitationrequestadminpanel.7-refreshButton");
        acceptButtonCaption =
                TM.get("invitationrequestadminpanel.8-acceptButton");
        rejectButtonCaption =
                TM.get("invitationrequestadminpanel.9-rejectButton");

        final Button refreshButton =
                new Button(
                        refreshButtonCaption);
        final Button acceptButton =
                new Button(acceptButtonCaption);
        final Button rejectButton =
                new Button(rejectButtonCaption);

        refreshButton.addListener(this);
        acceptButton.addListener(this);
        rejectButton.addListener(this);

        final HorizontalLayout buttonLayout = new HorizontalLayout();

        buttonLayout.addComponent(refreshButton);
        buttonLayout.addComponent(acceptButton);
        buttonLayout.addComponent(rejectButton);

        this.userIdentityTextField = new TextField();
        this.userIdentityTextField.setColumns(100);
        this.userIdentityTextField.setImmediate(true);

        final Label userIdentityLabel =
                new Label(
                        TM.get("invitationrequestadminpanel.10-userIdentityLabel"));
        final HorizontalLayout userIdentityLayout = new HorizontalLayout();

        userIdentityLayout.addComponent(userIdentityLabel);
        userIdentityLayout.addComponent(this.userIdentityTextField);

        this.panel.addComponent(this.table);
        this.panel.addComponent(userIdentityLayout);
        this.panel.addComponent(buttonLayout);
    }

    @Override
    public void setGuiController(
            final InvitationRequestAdminPanelController aController) {
        this.controller = aController;
    }

    @Override
    public void buttonClick(final ClickEvent aEvent) {
        final String pressedButtonCaption = aEvent.getButton().getCaption();
        final InvitationRequest selectedRequest =
                (InvitationRequest) this.table.getValue();

        if (this.acceptButtonCaption.equals(pressedButtonCaption)) {
            LOGGER.debug("acceptButton pressed");
            final String userIdentity = (String)this.userIdentityTextField.getValue();
            this.controller.acceptButtonPressed(userIdentity, selectedRequest);
        } else if (this.rejectButtonCaption.equals(pressedButtonCaption)) {
            LOGGER.debug("rejectButtonCaption pressed");
            this.controller.rejectButtonPressed(selectedRequest);
        } else if (this.refreshButtonCaption.equals(pressedButtonCaption)) {
            LOGGER.debug("refreshButtonCaption pressed");
            this.controller.refreshButtonPressed();
        }
    }

    @Override
    public void setData(final List<InvitationRequest> aRequests) {
        this.data = aRequests;
    }

    @Override
    public void updateView() {
        this.table.removeAllItems();
        for (final InvitationRequest curRequest : this.data) {
            final Object[] data = new Object[6];

            data[0] = curRequest.getId();
            data[1] = curRequest.getSubmissionDateTime();
            data[2] = curRequest.getOpenIdProvider();
            data[3] = curRequest.getEnteredId();
            data[4] = curRequest.getStatus();
            data[5] = curRequest.getUserIdentity();

            this.table.addItem(data, curRequest);
        }
    }
}

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

package at.silverstrike.pcc.impl.invitationrequestadminpanelcontroller;

import java.util.List;

import com.google.inject.Injector;
import com.vaadin.ui.Panel;

import at.silverstrike.pcc.api.invitationrequestadminpanel.InvitationRequestAdminPanel;
import at.silverstrike.pcc.api.invitationrequestadminpanel.InvitationRequestAdminPanelFactory;
import at.silverstrike.pcc.api.invitationrequestadminpanelcontroller.InvitationRequestAdminPanelController;
import at.silverstrike.pcc.api.model.InvitationRequest;
import at.silverstrike.pcc.api.persistence.Persistence;

/**
 * @author DP118M
 * 
 */
class DefaultInvitationRequestAdminPanelController implements
        InvitationRequestAdminPanelController {
    private Injector injector;
    private InvitationRequestAdminPanel panel;
    private Persistence persistence;

    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;
        if (aInjector != null) {
            this.persistence = aInjector.getInstance(Persistence.class);
        }
    }

    @Override
    public Panel initGui() {
        final InvitationRequestAdminPanelFactory factory =
                this.injector
                        .getInstance(InvitationRequestAdminPanelFactory.class);
        panel = factory.create();

        panel.setInjector(this.injector);
        panel.initGui();

        return panel.toPanel();
    }

    @Override
    public void acceptButtonPressed() {
        // TODO Auto-generated method stub
        this.panel.updateView();
    }

    @Override
    public void rejectButtonPressed() {
        // TODO Auto-generated method stub
        this.panel.updateView();
    }

    @Override
    public void refreshButtonPressed() {
        final List<InvitationRequest> requests = this.persistence.getInvitationRequests();
        this.panel.updateView();
    }
}

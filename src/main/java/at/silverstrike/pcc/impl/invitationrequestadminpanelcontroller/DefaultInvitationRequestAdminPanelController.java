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

import com.google.inject.Injector;
import com.vaadin.ui.Panel;

import at.silverstrike.pcc.api.invitationrequestadminpanel.InvitationRequestAdminPanel;
import at.silverstrike.pcc.api.invitationrequestadminpanel.InvitationRequestAdminPanelFactory;
import at.silverstrike.pcc.api.invitationrequestadminpanelcontroller.InvitationRequestAdminPanelController;

/**
 * @author DP118M
 * 
 */
class DefaultInvitationRequestAdminPanelController implements
        InvitationRequestAdminPanelController {
    private Injector injector;

    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;
    }

    @Override
    public Panel initGui() {
        final InvitationRequestAdminPanelFactory factory =
                this.injector
                        .getInstance(InvitationRequestAdminPanelFactory.class);
        final InvitationRequestAdminPanel panel = factory.create();
        
        panel.setInjector(this.injector);
        panel.initGui();
        
        return panel.toPanel();
    }
}

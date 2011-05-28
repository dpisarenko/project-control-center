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

import at.silverstrike.pcc.api.invitationrequestadminpanel.InvitationRequestAdminPanel;
import at.silverstrike.pcc.api.invitationrequestadminpanel.InvitationRequestAdminPanelFactory;

/**
 * @author DP118M
 *
 */
public final class DefaultInvitationRequestAdminPanelFactory implements
        InvitationRequestAdminPanelFactory {

    @Override
    public InvitationRequestAdminPanel create() {
        return new DefaultInvitationRequestAdminPanel();
    }

}

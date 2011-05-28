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

import at.silverstrike.pcc.api.invitationrequestadminpanelcontroller.InvitationRequestAdminPanelController;
import at.silverstrike.pcc.api.invitationrequestadminpanelcontroller.InvitationRequestAdminPanelControllerFactory;

/**
 * @author DP118M
 *
 */
public final class DefaultInvitationRequestAdminPanelControllerFactory
        implements InvitationRequestAdminPanelControllerFactory {

    @Override
    public InvitationRequestAdminPanelController create() {
        return new DefaultInvitationRequestAdminPanelController();
    }

}

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

package at.silverstrike.pcc.impl.invitationrequestadminpanelvisibility;

import org.apache.commons.lang.StringUtils;

import ru.altruix.commons.api.di.PccException;
import at.silverstrike.pcc.api.invitationrequestadminpanelvisibility.InvitationRequestAdminPanelVisibilityCalculator;

/**
 * @author DP118M
 * 
 */
final class DefaultInvitationRequestAdminPanelVisibilityCalculator implements
        InvitationRequestAdminPanelVisibilityCalculator {
    private String invitationAdmins;
    private boolean invitationPanelVisible;
    private String currentUsername;

    public boolean isInvitationPanelVisible() {
        return invitationPanelVisible;
    }

    public void setInvitationAdmins(final String aInvitationAdmins) {
        this.invitationAdmins = aInvitationAdmins;
    }

    @Override
    public void run() throws PccException {
        if (this.invitationAdmins == null) {
            this.invitationPanelVisible = false;
        } else if (StringUtils.isBlank(this.currentUsername)) {
            this.invitationPanelVisible = false;
        } else {
            this.invitationPanelVisible =
                    this.invitationAdmins.contains(this.currentUsername);
        }
    }

    public void setCurrentUsername(final String aCurrentUsername) {
        this.currentUsername = aCurrentUsername;
    }
}

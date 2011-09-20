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

package at.silverstrike.pcc.api.invitationrequestadminpanelvisibility;

import ru.altruix.commons.api.conventions.SingleActivityModule;

/**
 * @author DP118M
 * 
 */
public interface InvitationRequestAdminPanelVisibilityCalculator extends
        SingleActivityModule {
    void setInvitationAdmins(final String aInvitationAdmins);
    void setCurrentUsername(final String aUsername);

    boolean isInvitationPanelVisible();
}

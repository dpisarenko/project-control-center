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

import at.silverstrike.pcc.api.invitationrequestadminpanelvisibility.InvitationRequestAdminPanelVisibilityCalculator;
import at.silverstrike.pcc.api.invitationrequestadminpanelvisibility.InvitationRequestAdminPanelVisibilityCalculatorFactory;

/**
 * @author DP118M
 *
 */
public class DefaultInvitationRequestAdminPanelVisibilityCalculatorFactory
        implements InvitationRequestAdminPanelVisibilityCalculatorFactory {

    @Override
    public InvitationRequestAdminPanelVisibilityCalculator create() {
        return new DefaultInvitationRequestAdminPanelVisibilityCalculator();
    }

}

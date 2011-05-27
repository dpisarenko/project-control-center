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

package at.silverstrike.pcc.impl.invitationgui3;

import at.silverstrike.pcc.api.invitationgui3.InvitationRequestWindowStep3;
import at.silverstrike.pcc.api.invitationgui3.InvitationRequestWindowStep3Factory;

/**
 * @author DP118M
 * 
 */
public class DefaultInvitationRequestWindowStep3Factory implements
        InvitationRequestWindowStep3Factory {

    @Override
    public InvitationRequestWindowStep3 create() {
        return new DefaultInvitationRequestWindowStep3();
    }

}

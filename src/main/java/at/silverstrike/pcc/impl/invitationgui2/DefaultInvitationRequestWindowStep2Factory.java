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

package at.silverstrike.pcc.impl.invitationgui2;

import at.silverstrike.pcc.api.invitationgui2.InvitationRequestWindowStep2;
import at.silverstrike.pcc.api.invitationgui2.InvitationRequestWindowStep2Factory;

/**
 * @author DP118M
 *
 */
public class DefaultInvitationRequestWindowStep2Factory implements
        InvitationRequestWindowStep2Factory {

    @Override
    public InvitationRequestWindowStep2 create() {
        return new DefaultInvitationRequestWindowStep2();
    }
}

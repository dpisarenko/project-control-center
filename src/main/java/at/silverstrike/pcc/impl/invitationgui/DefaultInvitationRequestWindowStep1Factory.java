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

package at.silverstrike.pcc.impl.invitationgui;

import at.silverstrike.pcc.api.invitationgui.InvitationRequestWindowStep1;
import at.silverstrike.pcc.api.invitationgui.InvitationRequestWindowStep1Factory;

/**
 * @author DP118M
 *
 */
public class DefaultInvitationRequestWindowStep1Factory implements
        InvitationRequestWindowStep1Factory {

    @Override
    public InvitationRequestWindowStep1 create() {
        return new DefaultInvitationRequestWindowStep1();
    }

}

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

package at.silverstrike.pcc.impl.invitationgui2validator;

import at.silverstrike.pcc.api.invitationgui2validator.InvitationRequestWindowStep2Validator;
import at.silverstrike.pcc.api.invitationgui2validator.InvitationRequestWindowStep2ValidatorFactory;

/**
 * @author DP118M
 *
 */
public final class DefaultInvitationRequestWindowStep2ValidatorFactory
        implements InvitationRequestWindowStep2ValidatorFactory {

    @Override
    public InvitationRequestWindowStep2Validator create() {
        return new DefaultInvitationRequestWindowStep2Validator();
    }

}

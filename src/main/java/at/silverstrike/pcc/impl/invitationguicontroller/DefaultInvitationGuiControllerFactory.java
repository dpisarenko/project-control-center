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

package at.silverstrike.pcc.impl.invitationguicontroller;

import at.silverstrike.pcc.api.invitationguicontroller.InvitationGuiController;
import at.silverstrike.pcc.api.invitationguicontroller.InvitationGuiControllerFactory;

/**
 * @author DP118M
 *
 */
public class DefaultInvitationGuiControllerFactory implements
        InvitationGuiControllerFactory {

    @Override
    public InvitationGuiController create() {
        return new DefaultInvitationGuiController();
    }

}

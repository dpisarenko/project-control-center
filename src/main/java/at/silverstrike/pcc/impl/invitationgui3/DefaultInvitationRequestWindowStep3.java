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

import com.vaadin.ui.Window;

import at.silverstrike.pcc.api.invitationgui3.InvitationRequestWindowStep3;
import at.silverstrike.pcc.api.invitationguicontroller.InvitationGuiController;

/**
 * @author DP118M
 *
 */
public class DefaultInvitationRequestWindowStep3 implements
        InvitationRequestWindowStep3 {
    private InvitationGuiController controller;
    private Window window;
    
    @Override
    public void initGui() {
        // TODO Auto-generated method stub

    }

    @Override
    public Window toWindow() {
        return window;
    }

    @Override
    public void setGuiController(final InvitationGuiController aController) {
        this.controller = aController;
    }
}

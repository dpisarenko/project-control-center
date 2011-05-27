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

import com.google.inject.Injector;
import com.vaadin.ui.Window;

import at.silverstrike.pcc.api.invitationgui.InvitationRequestWindowStep1;
import at.silverstrike.pcc.api.invitationgui.InvitationRequestWindowStep1Factory;
import at.silverstrike.pcc.api.invitationguicontroller.InvitationGuiController;

/**
 * @author DP118M
 * 
 */
class DefaultInvitationGuiController implements InvitationGuiController {
    private Injector injector;

    @Override
    public void setInjector(final Injector aInjector) {
        injector = aInjector;
    }

    @Override
    public Window initGui() {
        final InvitationRequestWindowStep1Factory factory =
                this.injector.getInstance(InvitationRequestWindowStep1Factory.class);
        final InvitationRequestWindowStep1 window = factory.create();
        
        window.setGuiController(this);

        window.initGui();
        
        return window.toWindow();
    }

    @Override
    public void nextButtonInStep1Pressed() {
        // TODO Auto-generated method stub
        
    }
}

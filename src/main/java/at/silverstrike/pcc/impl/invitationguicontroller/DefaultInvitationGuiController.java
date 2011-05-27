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

import at.silverstrike.pcc.api.invitationgui.InvitationRequestWindow;
import at.silverstrike.pcc.api.invitationgui.InvitationRequestWindowFactory;
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
        final InvitationRequestWindowFactory factory =
                this.injector.getInstance(InvitationRequestWindowFactory.class);
        final InvitationRequestWindow window = factory.create();
        
        window.setGuiController(this);
        window.setInjector(this.injector);

        window.initGui();
        
        return window.toWindow();
    }
}

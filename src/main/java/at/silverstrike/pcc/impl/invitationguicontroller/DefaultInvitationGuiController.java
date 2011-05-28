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

import eu.livotov.tpt.TPTApplication;

import at.silverstrike.pcc.api.invitationgui2.InvitationRequestWindowStep2;
import at.silverstrike.pcc.api.invitationgui2.InvitationRequestWindowStep2Factory;
import at.silverstrike.pcc.api.invitationgui3.InvitationRequestWindowStep3;
import at.silverstrike.pcc.api.invitationgui3.InvitationRequestWindowStep3Factory;
import at.silverstrike.pcc.api.invitationguicontroller.InvitationGuiController;
import at.silverstrike.pcc.api.openid.SupportedOpenIdProvider;
import at.silverstrike.pcc.api.persistence.Persistence;

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
        final InvitationRequestWindowStep2Factory factory =
                this.injector
                        .getInstance(InvitationRequestWindowStep2Factory.class);
        final InvitationRequestWindowStep2 window = factory.create();

        window.setGuiController(this);
        window.setInjector(this.injector);
        window.initGui();

        return window.toWindow();
    }

    @Override
    public void nextButtonInStep2Pressed(
            final SupportedOpenIdProvider aOpenIdProvider,
            final String aUserUrl) {
        final Persistence persistence =
                this.injector.getInstance(Persistence.class);

        persistence.createInvitationRequest(aOpenIdProvider, aUserUrl);

        final InvitationRequestWindowStep3Factory factory =
                this.injector
                        .getInstance(InvitationRequestWindowStep3Factory.class);
        final InvitationRequestWindowStep3 window = factory.create();

        window.setGuiController(this);
        window.initGui();

        TPTApplication.getCurrentApplication().setMainWindow(window.toWindow());
    }
}

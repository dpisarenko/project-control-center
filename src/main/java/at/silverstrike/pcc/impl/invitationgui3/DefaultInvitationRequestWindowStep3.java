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

import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

import eu.livotov.tpt.i18n.TM;

import at.silverstrike.pcc.api.invitationgui3.InvitationRequestWindowStep3;
import at.silverstrike.pcc.api.invitationguicontroller.InvitationGuiController;

/**
 * @author DP118M
 * 
 */
public class DefaultInvitationRequestWindowStep3 implements
        InvitationRequestWindowStep3 {
    private Window window;

    @Override
    public void initGui() {
        this.window =
                new Window(TM.get("invitationgui3.1-title"));
        this.window.setSizeFull();

        final Label headerLabel =
                new Label(TM.get("invitationgui3.2-headerLabel"));
        final Label bodyTextLabel =
                new Label(TM.get("invitationgui3.3-bodyTextLabel"));

        this.window.addComponent(headerLabel);
        this.window.addComponent(bodyTextLabel);
    }

    @Override
    public Window toWindow() {
        return window;
    }

    @Override
    public void setGuiController(final InvitationGuiController aController) {
    }
}

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

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

import eu.livotov.tpt.i18n.TM;

import at.silverstrike.pcc.api.invitationgui.InvitationRequestWindowStep1;
import at.silverstrike.pcc.api.invitationguicontroller.InvitationGuiController;

/**
 * @author DP118M
 * 
 */
class DefaultInvitationRequestWindowStep1 implements
        InvitationRequestWindowStep1, ClickListener {
    private static final long serialVersionUID = 1L;
    private Window window;

    @Override
    public void initGui() {
        this.window =
                new Window(TM.get("invitationgui.1-title"));
        this.window.setSizeFull();
        
        final Label headerLabel = new Label(TM.get("invitationgui.2-headerLabel"));
        final Label bodyTextLabel = new Label(TM.get("invitationgui.3-bodyTextLabel"));
        final Button nextButton = new Button(TM.get("invitationgui.4-nextButton"));
        
        nextButton.addListener(this);
        
        this.window.addComponent(headerLabel);
        this.window.addComponent(bodyTextLabel);
        this.window.addComponent(nextButton);
    }

    @Override
    public Window toWindow() {
        return window;
    }

    @Override
    public void setGuiController(final InvitationGuiController aController) {
    }

    @Override
    public void buttonClick(final ClickEvent aEvent) {
    }
}

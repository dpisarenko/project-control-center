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

import com.google.inject.Injector;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

import eu.livotov.tpt.TPTApplication;
import eu.livotov.tpt.i18n.TM;

import at.silverstrike.pcc.api.entrywindow.EntryWindow;
import at.silverstrike.pcc.api.entrywindow.EntryWindowFactory;
import at.silverstrike.pcc.api.invitationgui3.InvitationRequestWindowStep3;
import at.silverstrike.pcc.api.invitationguicontroller.InvitationGuiController;

/**
 * @author DP118M
 * 
 */
class DefaultInvitationRequestWindowStep3 implements
        InvitationRequestWindowStep3, ClickListener {
    private static final long serialVersionUID = 1L;
    private Window window;
    private Injector injector;

    @Override
    public void initGui() {
        this.window =
                new Window(TM.get("invitationgui3.1-title"));
        this.window.setSizeFull();

        final Label headerLabel =
                new Label(TM.get("invitationgui3.2-headerLabel"));
        final Label bodyTextLabel =
                new Label(TM.get("invitationgui3.3-bodyTextLabel"));

        final Button button =
                new Button(TM.get("invitationgui3.4-goback-button"));

        button.addListener(this);

        headerLabel.setContentMode(Label.CONTENT_XHTML);
        bodyTextLabel.setContentMode(Label.CONTENT_XHTML);

        this.window.addComponent(headerLabel);
        this.window.addComponent(bodyTextLabel);
        this.window.addComponent(button);
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
        final EntryWindowFactory factory =
                this.injector.getInstance(EntryWindowFactory.class);
        final EntryWindow window = factory.create();

        window.setInjector(this.injector);
        window.initGui();

        TPTApplication.getCurrentApplication().removeWindow(
                TPTApplication.getCurrentApplication().getMainWindow());
        TPTApplication.getCurrentApplication().setMainWindow(
                window.toWindow());
    }

    @Override
    public void setInjector(final Injector aInjector) {
        injector = aInjector;
    }
}

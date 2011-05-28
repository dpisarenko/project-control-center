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

package at.silverstrike.pcc.impl.invitationgui2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.altruix.commons.api.di.PccException;

import com.google.inject.Injector;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Window.Notification;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

import eu.livotov.tpt.TPTApplication;
import eu.livotov.tpt.i18n.TM;

import at.silverstrike.pcc.api.invitationgui2.InvitationRequestWindowStep2;
import at.silverstrike.pcc.api.invitationgui2validator.InvitationRequestWindowStep2Validator;
import at.silverstrike.pcc.api.invitationgui2validator.InvitationRequestWindowStep2ValidatorFactory;
import at.silverstrike.pcc.api.invitationguicontroller.InvitationGuiController;

/**
 * @author DP118M
 * 
 */
class DefaultInvitationRequestWindowStep2 implements
        InvitationRequestWindowStep2, ClickListener {
    private static final long serialVersionUID = 1L;
    private InvitationGuiController controller;
    private InvitationRequestWindowStep2Validator validator;
    private Window window;
    private TextField emailTextField;
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultInvitationRequestWindowStep2.class);

    @Override
    public void initGui() {
        this.window =
                new Window(TM.get("invitationgui2.1-title"));
        this.window.setSizeFull();

        final Label headerLabel =
                new Label(TM.get("invitationgui2.2-headerLabel"));

        headerLabel.setContentMode(Label.CONTENT_XHTML);

        final Label bodyTextTopLabel =
                new Label(TM.get("invitationgui2.3-bodyTextLabel-top"));
        final Label bodyTextBottomLabel =
                new Label(TM.get("invitationgui2.8-bodyTextLabel-bottom"));
        final Button nextButton =
                new Button(TM.get("invitationgui2.4-nextButton"));

        nextButton.addListener(this);

        final HorizontalLayout textFieldLayout = createTextfieldLayout();

        this.window.addComponent(headerLabel);
        this.window.addComponent(bodyTextTopLabel);
        this.window.addComponent(textFieldLayout);
        this.window.addComponent(bodyTextBottomLabel);
        this.window.addComponent(nextButton);
    }

    private HorizontalLayout createTextfieldLayout() {
        final HorizontalLayout layout = new HorizontalLayout();

        layout.setSizeFull();
        
        final Label faceBookLabel =
                new Label(TM.get("invitationgui2.5-email"));
        emailTextField = new TextField("");

        layout.addComponent(faceBookLabel);
        layout.addComponent(emailTextField);

        return layout;
    }

    @Override
    public Window toWindow() {
        return this.window;
    }

    @Override
    public void setGuiController(final InvitationGuiController aController) {
        this.controller = aController;
    }

    @Override
    public void buttonClick(final ClickEvent aEvent) {
        this.validator
                .setFacebookId((String) this.emailTextField.getValue());

        try {
            this.validator.run();

            if (this.validator.isValid()) {
                this.controller.nextButtonInStep2Pressed(
                        this.validator.getOpenIdProvider(),
                        this.validator.getUserName());
            } else {
                TPTApplication
                        .getCurrentApplication()
                        .getMainWindow()
                        .showNotification(
                                TM.get("invitationgui2.9-validation-error-title"),
                                TM.get("invitationgui2.10-validation-error-text"),
                                Notification.TYPE_ERROR_MESSAGE);
            }
        } catch (final PccException exception) {
            LOGGER.error("", exception);
        }
    }

    @Override
    public void setInjector(final Injector aInjector) {
        if (aInjector != null) {
            final InvitationRequestWindowStep2ValidatorFactory factory =
                    aInjector
                            .getInstance(InvitationRequestWindowStep2ValidatorFactory.class);
            this.validator = factory.create();
        }
    }
}

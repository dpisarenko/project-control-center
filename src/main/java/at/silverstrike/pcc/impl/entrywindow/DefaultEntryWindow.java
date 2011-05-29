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

package at.silverstrike.pcc.impl.entrywindow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.inject.Injector;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

import eu.livotov.tpt.TPTApplication;
import eu.livotov.tpt.i18n.TM;
import at.silverstrike.pcc.api.entrywindow.EntryWindow;
import at.silverstrike.pcc.api.invitationguicontroller.InvitationGuiController;
import at.silverstrike.pcc.api.invitationguicontroller.InvitationGuiControllerFactory;

class DefaultEntryWindow implements EntryWindow, ClickListener {
    private static final int OPEN_ID_TEXT_FIELD_COLUMNS = 30;
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultEntryWindow.class);
    private Window window;
    private Panel authPanel;
    private Label emailLabel;
    private TextField emailTextField;
    private PasswordField passwordTextField;
    private Button loginButton;
    private Label signupLabel;
    private transient Injector injector;
    private Label passwordLabel;

    @Override
    public void setInjector(final Injector aInjector) {
        if (aInjector != null) {
            this.injector = aInjector;
        }
    }

    @Override
    public void initGui() {
        window = new Window();
        window.setSizeFull();

        final GridLayout layout = new GridLayout(2, 1);

        layout.setSizeFull();
        initAuthPanel();

        final Button requestInviteButton =
                new Button(TM.get("entrywindow.9-requestInviteButton"));
        requestInviteButton.addListener(this);

        this.signupLabel = new Label("", Label.CONTENT_XHTML);

        layout.addComponent(requestInviteButton, 0, 0);
        layout.addComponent(this.authPanel, 1, 0);

        window.addComponent(layout);
        updateControls();
    }

    @Override
    public Window toWindow() {
        return this.window;
    }

    private void updateControls() {
        this.window.setCaption(TM.get("entrywindow.1-title"));
        this.signupLabel.setValue(TM.get("entrywindow.4-signuplabel"));
        updateCaptionsAuthPanel();
    }

    private void updateCaptionsAuthPanel() {
        this.emailLabel.setValue(TM.get("entrywindow.2-openIdLabel"));
        this.loginButton.setCaption(TM
                .get("entrywindow.3-authenticateButton"));
        this.passwordLabel.setCaption(TM.get("entrywindow.8-password-label"));

        this.emailLabel.setSizeFull();
        this.loginButton.setSizeFull();
    }

    private void initAuthPanel() {
        final GridLayout gridLayout = new GridLayout(2, 3);

        this.authPanel = new Panel();

        emailLabel = new Label();
        emailTextField = new TextField();
        emailTextField.setColumns(OPEN_ID_TEXT_FIELD_COLUMNS);

        passwordLabel = new Label();
        this.passwordTextField = new PasswordField();

        loginButton = new Button();

        gridLayout.addComponent(emailLabel, 0, 0);
        gridLayout.addComponent(emailTextField, 1, 0);

        gridLayout.addComponent(passwordLabel, 0, 1);
        gridLayout.addComponent(passwordTextField, 1, 1);

        gridLayout.addComponent(loginButton, 0, 2, 1, 2);

        this.authPanel.addComponent(gridLayout);
        this.authPanel.setSizeFull();
    }

    @Override
    public void buttonClick(final ClickEvent aEvent) {
        LOGGER.debug("buttonClick");
        final InvitationGuiControllerFactory invitationGuiControllerFactory =
                injector.getInstance(InvitationGuiControllerFactory.class);
        final InvitationGuiController controller =
                invitationGuiControllerFactory.create();

        controller.setInjector(injector);

        final Window invitationRequestWindow = controller.initGui();

        LOGGER.debug("buttonClick, 2, invitationRequestWindow: {}",
                invitationRequestWindow);

        TPTApplication.getCurrentApplication().removeWindow(
                TPTApplication.getCurrentApplication().getMainWindow());
        TPTApplication.getCurrentApplication().setMainWindow(
                invitationRequestWindow);
    }
}

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

import org.apache.commons.lang.StringUtils;
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
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.Notification;

import eu.livotov.tpt.TPTApplication;
import eu.livotov.tpt.i18n.TM;
import at.silverstrike.pcc.api.entrywindow.EntryWindow;
import at.silverstrike.pcc.api.mainwindowcontroller.MainWindowController;
import at.silverstrike.pcc.api.mainwindowcontroller.MainWindowControllerFactory;
import at.silverstrike.pcc.api.model.UserData;
import at.silverstrike.pcc.api.persistence.Persistence;

class DefaultEntryWindow implements EntryWindow, ClickListener {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultEntryWindow.class);
    private Window window;
    private TextField emailTextField;
    private PasswordField passwordTextField;
    private transient Injector injector;
    private String requestInviteButtonCaption;
    private String loginButtonCaption;
    private TextField invitationEmailTextField;

    @Override
    public void setInjector(final Injector aInjector) {
        if (aInjector != null) {
            this.injector = aInjector;
        }
    }

    @Override
    public void initGui() {
        final GridLayout mainLayout = new GridLayout(2, 2);

        window = new Window(TM.get("entrywindow.1-title"));
        window.setContent(mainLayout);
        window.setStyleName("window");
        window.setSizeFull();

        final VerticalLayout leftLayout = getLeftLayout();
        final VerticalLayout rightLayout = getRightLayout();

        final Label copyright =
                new Label(TM.get("entrywindow.20-copyright"),
                        Label.CONTENT_XHTML);
        copyright.setStyleName("copyright");

        leftLayout.setStyleName("leftLayout");
        rightLayout.setStyleName("rightLayout");

        mainLayout.setStyleName("mainLayout");

        mainLayout.addComponent(leftLayout, 0, 0);
        mainLayout.addComponent(rightLayout, 1, 0);
        mainLayout.addComponent(copyright, 0, 1, 1, 1);
    }

    private VerticalLayout getRightLayout() {
        final VerticalLayout layout = new VerticalLayout();

        final VerticalLayout loginPanel = new VerticalLayout();

        loginPanel.addStyleName("loginPanel");

        final Label privateBetaTesterLogin =
                new Label(TM.get("entrywindow.13-privateBetaTesterLogin"),
                        Label.CONTENT_XHTML);

        loginButtonCaption = TM.get("entrywindow.22-loginButton");

        final Button loginButton =
                new Button(loginButtonCaption);
        loginButton.addListener(this);
        final Label usernameLettering =
                new Label(TM.get("entrywindow.23-usernameLettering"),
                        Label.CONTENT_XHTML);
        final Label passwordLettering =
                new Label(TM.get("entrywindow.24-passwortLettering"),
                        Label.CONTENT_XHTML);

        emailTextField = new TextField();
        passwordTextField = new PasswordField();

        emailTextField.setSizeUndefined();
        emailTextField.setColumns(10);

        passwordTextField.setSizeUndefined();
        passwordTextField.setColumns(10);

        privateBetaTesterLogin.setStyleName("privateBetaTesterLogin");
        loginButton.setStyleName("loginButton");
        usernameLettering.setStyleName("usernameLettering");
        emailTextField.setStyleName("usernameTextField");
        passwordLettering.setStyleName("passwordLettering");
        passwordTextField.setStyleName("passwordTextField");

        loginPanel.addComponent(privateBetaTesterLogin);
        loginPanel.addComponent(loginButton);
        loginPanel.addComponent(usernameLettering);
        loginPanel.addComponent(emailTextField);
        loginPanel.addComponent(passwordLettering);
        loginPanel.addComponent(passwordTextField);
        loginPanel.addComponent(loginButton);

        layout.addComponent(loginPanel);

        return layout;
    }

    private VerticalLayout getLeftLayout() {
        final VerticalLayout layout = new VerticalLayout();

        final Label titleLettering =
                new Label(TM.get("entrywindow.11-titleLettering"),
                        Label.CONTENT_XHTML);
        final Label tagline = new Label(TM.get("entrywindow.12-tagline"),
                Label.CONTENT_XHTML);
        final Label theProblemText =
                new Label(TM.get("entrywindow.15-theProblemText"),
                        Label.CONTENT_XHTML);

        final Panel invitationRequestPanel = getInvitationRequestPanel();

        titleLettering.setStyleName("titleLettering");
        tagline.setStyleName("tagline");
        theProblemText.setStyleName("theProblemText");
        invitationRequestPanel.setStyleName("invitationRequestPanel");

        layout.addComponent(titleLettering);
        layout.addComponent(tagline);
        layout.addComponent(theProblemText);
        layout.addComponent(invitationRequestPanel);

        return layout;
    }

    private Panel getInvitationRequestPanel() {
        final Panel panel = new Panel();

        final Label invitationPanelTitle =
                new Label(TM.get("entrywindow.21-invitationPanelTitle"),
                        Label.CONTENT_XHTML);
        invitationEmailTextField = new TextField();

        requestInviteButtonCaption =
                TM.get("entrywindow.25-submitInviationRequestButton");
        final Button submitInviationRequestButton =
                new Button(
                        requestInviteButtonCaption);

        submitInviationRequestButton.addListener(this);

        invitationPanelTitle.setStyleName("invitationPanelTitle");
        invitationEmailTextField.setStyleName("emailTextField");
        submitInviationRequestButton
                .setStyleName("submitInviationRequestButton");

        panel.setStyleName("invitationRequestPanel");
        panel.addComponent(invitationPanelTitle);
        panel.addComponent(invitationEmailTextField);
        panel.addComponent(submitInviationRequestButton);

        return panel;
    }

    @Override
    public Window toWindow() {
        return this.window;
    }

    @Override
    public void buttonClick(final ClickEvent aEvent) {
        final String buttonCaption = aEvent.getButton().getCaption();

        LOGGER.debug(
                "buttonCaption: ${buttonCaption}, requestInviteButtonCaption: "
                        +
                        "${requestInviteButtonCaption}," +
                        " loginButtonCaption: ${loginButtonCaption}",
                new Object[] { buttonCaption, requestInviteButtonCaption,
                        loginButtonCaption });

        if (this.requestInviteButtonCaption.equals(buttonCaption)) {
            LOGGER.debug("buttonClick");

            final String email =
                    (String) this.invitationEmailTextField.getValue();
            
            if (StringUtils.isBlank(email))
            {
                TPTApplication
                .getCurrentApplication()
                .getMainWindow()
                .showNotification(this.window.getCaption(),
                        TM.get("entrywindow.26-no-invitation-email"),
                        Notification.TYPE_ERROR_MESSAGE);
            }
            else
            {
                final Persistence persistence =
                    this.injector.getInstance(Persistence.class);
                persistence.createInvitationRequest(null, email);

                TPTApplication
                .getCurrentApplication()
                .getMainWindow()
                .showNotification(this.window.getCaption(),
                        TM.get("entrywindow.27-invitation-request-submitted"),
                        Notification.TYPE_ERROR_MESSAGE);
            }
        } else if (this.loginButtonCaption.equals(buttonCaption)) {
            final String userName = (String) this.emailTextField.getValue();
            final String password = (String) this.passwordTextField.getValue();
            final Persistence persistence =
                    this.injector.getInstance(Persistence.class);

            final UserData user = persistence.getUser(userName, password);

            if (user != null) {
                TPTApplication.getCurrentApplication().setUser(user);

                final MainWindowControllerFactory mainWindowControllerFactory =
                        injector.getInstance(MainWindowControllerFactory.class);
                final MainWindowController controller =
                        mainWindowControllerFactory.create();

                controller.setInjector(injector);

                final Window mainWindow = controller.initGui();

                final Window curMainWindow =
                        TPTApplication.getCurrentApplication().getMainWindow();
                TPTApplication.getCurrentApplication().removeWindow(
                        curMainWindow);
                TPTApplication.getCurrentApplication()
                        .setMainWindow(mainWindow);

            } else {
                TPTApplication
                        .getCurrentApplication()
                        .getMainWindow()
                        .showNotification(this.window.getCaption(),
                                TM.get("entrywindow.10-errorMessage"),
                                Notification.TYPE_ERROR_MESSAGE);
                TPTApplication.getCurrentApplication().setUser(null);
            }
        }
    }
}

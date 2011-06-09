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

package at.silverstrike.pcc.impl.usersettingspanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Injector;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import eu.livotov.tpt.TPTApplication;
import eu.livotov.tpt.i18n.TM;

import at.silverstrike.pcc.api.model.UserData;
import at.silverstrike.pcc.api.usersettingspanel.UserSettingsPanel;
import at.silverstrike.pcc.api.usersettingspanelcontroller.UserSettingsPanelController;

/**
 * @author DP118M
 * 
 */
class DefaultUserSettingsPanel extends Panel implements UserSettingsPanel,
        ClickListener {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultUserSettingsPanel.class);

    private static final long serialVersionUID = 1L;
    private UserSettingsPanelController controller;
    private Injector injector;
    private String saveButtonCaption;
    private String fetchDataButtonCaption;
    private String writeDataToGoogleButtonCaption;
    private TextField usernameTextField;
    private TextField passwordTextField;

    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;
    }

    @Override
    public Panel toPanel() {
        return this;
    }

    @Override
    public void initGui() {
        final GridLayout gridLayout = new GridLayout(2, 3);

        gridLayout.setSizeFull();

        final Label usernameLabel =
                new Label(TM.get("usersettingspanel.1-google-username"));
        final Label passwordLabel =
                new Label(TM.get("usersettingspanel.2-google-password"));
        usernameTextField = new TextField();
        passwordTextField = new TextField();
        final VerticalLayout buttonPanel = getButtonPanel();

        gridLayout.addComponent(usernameLabel, 0, 0);
        gridLayout.addComponent(passwordLabel, 0, 1);
        gridLayout.addComponent(usernameTextField, 1, 0);
        gridLayout.addComponent(passwordTextField, 1, 1);

        gridLayout.addComponent(buttonPanel, 0, 2, 1, 2);

        this.addComponent(gridLayout);

        final UserData userData =
                (UserData) TPTApplication.getCurrentApplication().getUser();

        this.usernameTextField.setValue(userData.getGoogleUsername());
        this.passwordTextField.setValue(userData.getGooglePassword());
    }

    private VerticalLayout getButtonPanel() {
        saveButtonCaption = TM.get("usersettingspanel.3-save");
        final Button saveButton =
                new Button(saveButtonCaption);
        fetchDataButtonCaption = TM.get("usersettingspanel.4-fetch-tasks");
        final Button fetchDataButton =
                new Button(fetchDataButtonCaption);
        writeDataToGoogleButtonCaption =
                TM.get("usersettingspanel.5-write-bookings");
        final Button writeDataToGoogleButton =
                new Button(writeDataToGoogleButtonCaption);

        final VerticalLayout buttonPanel = new VerticalLayout();

        saveButton.addListener(this);
        fetchDataButton.addListener(this);
        writeDataToGoogleButton.addListener(this);

        buttonPanel.addComponent(saveButton);
        buttonPanel.addComponent(fetchDataButton);
        buttonPanel.addComponent(writeDataToGoogleButton);
        return buttonPanel;
    }

    @Override
    public void setGuiController(final UserSettingsPanelController aController) {
        this.controller = aController;
    }

    @Override
    public void buttonClick(final ClickEvent aEvent) {
        final String username = (String) this.usernameTextField.getValue();
        final String password = (String) this.passwordTextField.getValue();
        final String buttonCaption = aEvent.getButton().getCaption();

        LOGGER.debug("buttonCaption: '{}', this.fetchDataButtonCaption: '{}'",
                new Object[] { buttonCaption, this.fetchDataButtonCaption });

        if (this.saveButtonCaption.equals(buttonCaption)) {
            this.controller.saveGoogleAccessData(
                    username,
                    password);
        } else if (this.fetchDataButtonCaption.equals(buttonCaption)) {
            this.controller.fetchData(username, password);
        } else if (this.writeDataToGoogleButtonCaption.equals(buttonCaption)) {
            this.controller.writeDataToGoogle(username, password);
        }
    }
}

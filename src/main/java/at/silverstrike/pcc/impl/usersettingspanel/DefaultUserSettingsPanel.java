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

import com.google.inject.Injector;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import eu.livotov.tpt.i18n.TM;

import at.silverstrike.pcc.api.usersettingspanel.UserSettingsPanel;
import at.silverstrike.pcc.api.usersettingspanelcontroller.UserSettingsPanelController;

/**
 * @author DP118M
 * 
 */
class DefaultUserSettingsPanel extends Panel implements UserSettingsPanel {
    private static final long serialVersionUID = 1L;
    private UserSettingsPanelController controller;
    private Injector injector;

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

        final Label usernameLabel =
                new Label(TM.get("usersettingspanel.1-google-username"));
        final Label passwordLabel =
                new Label(TM.get("usersettingspanel.2-google-password"));
        final TextField usernameTextField = new TextField();
        final TextField passwordTextField = new TextField();
        final Button saveButton =
                new Button(TM.get("usersettingspanel.3-save"));
        final Button fetchDataButton =
                new Button(TM.get("usersettingspanel.4-fetch-tasks"));
        final Button writeDataToGoogleButton =
                new Button(TM.get("usersettingspanel.5-write-bookings"));

        final VerticalLayout buttonPanel = new VerticalLayout();
        
        buttonPanel.addComponent(saveButton);
        buttonPanel.addComponent(fetchDataButton);
        buttonPanel.addComponent(writeDataToGoogleButton);

        
        gridLayout.addComponent(usernameLabel, 0, 0);
        gridLayout.addComponent(passwordLabel, 0, 1);
        gridLayout.addComponent(usernameTextField, 1, 0);
        gridLayout.addComponent(passwordTextField, 1, 1);

        gridLayout.addComponent(buttonPanel, 0, 2, 1, 2);
        
        this.addComponent(gridLayout);
    }

    @Override
    public void setGuiController(final UserSettingsPanelController aController) {
        this.controller = aController;
    }
}

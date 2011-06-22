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

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
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
class DefaultUserSettingsPanel extends Panel implements UserSettingsPanel,
        ClickListener {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultUserSettingsPanel.class);

    private static final long serialVersionUID = 1L;
    private UserSettingsPanelController controller;
    private String requestAuthCodeCaption;
    private String calculateSyncDataButtonCaption;
    private String logoutButtonCaption;
    private TextField googleCodeTextField;

    @Override
    public Panel toPanel() {
        return this;
    }

    @Override
    public void initGui() {
        final GridLayout gridLayout = new GridLayout(2, 3);

        gridLayout.setSizeFull();

        final Label googleCodeLabel =
                new Label(TM.get("usersettingspanel.6-google-code"));

        this.googleCodeTextField = new TextField();
        final VerticalLayout buttonPanel = getButtonPanel();

        gridLayout.addComponent(googleCodeLabel, 0, 0);
        gridLayout.addComponent(googleCodeTextField, 1, 0);

        gridLayout.addComponent(buttonPanel, 0, 1, 1, 1);

        this.addComponent(gridLayout);
    }

    private VerticalLayout getButtonPanel() {
        requestAuthCodeCaption =
                TM.get("usersettingspanel.7-request-auth-code");
        final Button requestAuthCodeButton =
                new Button(requestAuthCodeCaption);
        calculateSyncDataButtonCaption =
                TM.get("usersettingspanel.4-fetch-tasks");
        final Button calculateSyncDataButton =
                new Button(calculateSyncDataButtonCaption);

        this.logoutButtonCaption = TM.get("usersettingspanel.8-logout");
        final Button logoutButton = new Button(this.logoutButtonCaption);

        final VerticalLayout buttonPanel = new VerticalLayout();

        requestAuthCodeButton.addListener(this);
        calculateSyncDataButton.addListener(this);

        buttonPanel.addComponent(requestAuthCodeButton);
        buttonPanel.addComponent(calculateSyncDataButton);
        buttonPanel.addComponent(logoutButton);
        return buttonPanel;
    }

    @Override
    public void setGuiController(final UserSettingsPanelController aController) {
        this.controller = aController;
    }

    @Override
    public void buttonClick(final ClickEvent aEvent) {
        final String buttonCaption = aEvent.getButton().getCaption();

        LOGGER.debug("buttonCaption: '{}', this.fetchDataButtonCaption: '{}'",
                new Object[] { buttonCaption,
                        this.calculateSyncDataButtonCaption });
        if (this.requestAuthCodeCaption.equals(buttonCaption)) {
            this.controller.requestGoogleAuthorizationCode();
        } else if (this.calculateSyncDataButtonCaption.equals(buttonCaption)) {
            this.controller
                    .calculateAndSyncData((String) this.googleCodeTextField
                            .getValue());
        } else if (this.logoutButtonCaption.equals(buttonCaption)) {

        }
    }
}

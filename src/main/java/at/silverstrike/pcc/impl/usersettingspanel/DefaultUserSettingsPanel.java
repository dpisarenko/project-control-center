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
    private String requestAuthCodeCaption;
    private String calculateSyncDataButtonCaption;
    private String logoutButtonCaption;
    private TextField googleCodeTextField;

    private String writeBookingsToCalendarButtonCaption;

    private String writeBookingsToCalendarButtonCaption2;

    private String sendMessageToQueueCaption;

    private String requestImmediateRecalculationButtonCaption;

    private String grantAccessToGoogleTasksButtonCaption;

    private String grantAccessToGoogleCalendarButtonCaption;

    @Override
    public Panel toPanel() {
        return this;
    }

    @Override
    public void initGui() {
        final GridLayout gridLayout = new GridLayout(2, 5);

        gridLayout.setSizeFull();

        final Label googleCodeLabel =
                new Label(TM.get("usersettingspanel.6-google-code"));

        this.googleCodeTextField = new TextField();
        final VerticalLayout buttonPanel = getButtonPanel();

        gridLayout.addComponent(googleCodeLabel, 0, 0);
        gridLayout.addComponent(googleCodeTextField, 1, 0);

        final UserData user =
                (UserData) TPTApplication.getCurrentApplication().getUser();

        final String googleCalendarAccessStatusText =
                getGoogleCalendarAccessStatusText(user);

        final Label googleCalendarAccessStatusLabel =
                new Label(googleCalendarAccessStatusText);

        gridLayout.addComponent(googleCalendarAccessStatusLabel, 0, 1, 1, 1);

        final String googleTasksAccessStatusText =
                getGoogleTasksAccessStatusText(user);

        final Label googleTasksAccessStatusLabel =
                new Label(googleTasksAccessStatusText);

        gridLayout.addComponent(googleTasksAccessStatusLabel, 0, 2, 1, 2);

        final Label revokeLabel =
                new Label(TM.get("usersettingspanel.17-revokeAccess"));

        gridLayout.addComponent(revokeLabel, 0, 3, 1, 3);

        gridLayout.addComponent(buttonPanel, 0, 4, 1, 4);

        this.addComponent(gridLayout);
    }

    private String getGoogleTasksAccessStatusText(final UserData aUser) {
        if (aUser.isGoogleTasksAccessGranted()) {
            return TM.get("usersettingspanel.15-tasksAccessStatusGranted");
        } else {
            return TM.get("usersettingspanel.16-tasksAccessStatusNotGranted");
        }
    }

    private String getGoogleCalendarAccessStatusText(final UserData user) {
        final String googleCalendarAccessStatusText;
        if (user.isGoogleCalendarAccessGranted()) {
            googleCalendarAccessStatusText =
                    TM.get("usersettingspanel.13-calendarAccessStatusGranted");
        } else {
            googleCalendarAccessStatusText =
                    TM.get("usersettingspanel.14-calendarAccessStatusNotGranted");
        }
        return googleCalendarAccessStatusText;
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

        writeBookingsToCalendarButtonCaption =
                TM.get("usersettingspanel.5-write-bookings");

        final Button writeBookingsToCalendarButton =
                new Button(writeBookingsToCalendarButtonCaption);
        writeBookingsToCalendarButton.addListener(this);

        writeBookingsToCalendarButtonCaption2 =
                TM.get("usersettingspanel.9-write-bookings2");

        final Button writeBookingsToCalendarButton2 =
                new Button(writeBookingsToCalendarButtonCaption2);
        writeBookingsToCalendarButton2.addListener(this);

        this.logoutButtonCaption = TM.get("usersettingspanel.8-logout");
        final Button logoutButton = new Button(this.logoutButtonCaption);

        this.sendMessageToQueueCaption =
                TM.get("usersettingspanel.10-sendMessageToQueueCaption");
        final Button sendMessageToQueueButton =
                new Button(this.sendMessageToQueueCaption);

        sendMessageToQueueButton.addListener(this);

        logoutButton.addListener(this);

        final VerticalLayout buttonPanel = new VerticalLayout();

        requestAuthCodeButton.addListener(this);
        calculateSyncDataButton.addListener(this);

        requestImmediateRecalculationButtonCaption =
                TM.get("usersettingspanel.18-requestImmediateRecalculationButtonCaption");

        final Button requestImmediateRecalculationButton =
                new Button(requestImmediateRecalculationButtonCaption);
        requestImmediateRecalculationButton.addListener(this);

        // usersettingspanel.11-grantAccessToGoogleTasksButtonCaption = Grant
        // access to Google Tasks
        // usersettingspanel.12-grantAccessToGoogleCalendarButtonCaption = Grant
        // access to Google Calendar

        grantAccessToGoogleTasksButtonCaption =
                TM.get("usersettingspanel.11-grantAccessToGoogleTasksButtonCaption");
        grantAccessToGoogleCalendarButtonCaption =
                TM.get("usersettingspanel.12-grantAccessToGoogleCalendarButtonCaption");

        final Button grantAccessToGoogleTasksButton =
                new Button(grantAccessToGoogleTasksButtonCaption);
        final Button grantAccessToGoogleCalendarButton =
                new Button(grantAccessToGoogleCalendarButtonCaption);

        grantAccessToGoogleTasksButton.addListener(this);
        grantAccessToGoogleCalendarButton.addListener(this);

        buttonPanel.addComponent(grantAccessToGoogleCalendarButton);
        buttonPanel.addComponent(grantAccessToGoogleTasksButton);

        buttonPanel.addComponent(requestAuthCodeButton);
        buttonPanel.addComponent(calculateSyncDataButton);
        buttonPanel.addComponent(writeBookingsToCalendarButton);
        buttonPanel.addComponent(writeBookingsToCalendarButton2);
        buttonPanel.addComponent(sendMessageToQueueButton);
        buttonPanel.addComponent(requestImmediateRecalculationButton);
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

        LOGGER.debug(
                "buttonCaption: '{}', this.fetchDataButtonCaption: '{}', this.logoutButtonCaption: '{}'",
                new Object[] { buttonCaption,
                        this.calculateSyncDataButtonCaption,
                        this.logoutButtonCaption });
        if (this.requestAuthCodeCaption.equals(buttonCaption)) {
            this.controller.requestGoogleAuthorizationCode();
        } else if (this.calculateSyncDataButtonCaption.equals(buttonCaption)) {
            this.controller
                    .calculateAndSyncData((String) this.googleCodeTextField
                            .getValue());
        } else if (this.logoutButtonCaption.equals(buttonCaption)) {
            this.controller.logout();
        } else if (writeBookingsToCalendarButtonCaption.equals(buttonCaption)) {
            this.controller.writeBookingsToCalendar();
        } else if (writeBookingsToCalendarButtonCaption2.equals(buttonCaption)) {
            this.controller
                    .writeBookingsToCalendar2((String) this.googleCodeTextField
                            .getValue());
        } else if (this.sendMessageToQueueCaption.equals(buttonCaption)) {
            this.controller.sendMessageToQueue();
        } else if (this.requestImmediateRecalculationButtonCaption
                .equals(buttonCaption)) {
            this.controller.requestImmediateRecalculation();
        } else if (this.grantAccessToGoogleCalendarButtonCaption
                .equals(buttonCaption)) {
            this.controller.initiateGoogleCalendarAuthorization();
        } else if (this.grantAccessToGoogleTasksButtonCaption
                .equals(buttonCaption)) {
            this.controller.initiateGoogleTasksAuthorization();
        }

    }
}

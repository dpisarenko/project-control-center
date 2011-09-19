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

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
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
    private static final long serialVersionUID = 1L;
    private UserSettingsPanelController controller;
    private String logoutButtonCaption;
    private String requestImmediateRecalculationButtonCaption;
    private String grantAccessToGoogleTasksButtonCaption;
    private String grantAccessToGoogleCalendarButtonCaption;
    private String automaticReschedulingCheckBoxLettering;
    private CheckBox automaticReschedulingCheckBox;

    @Override
    public Panel toPanel() {
        return this;
    }

    @Override
    public void initGui() {
        final GridLayout gridLayout = new GridLayout(2, 5);

        gridLayout.setSizeFull();

        final VerticalLayout buttonPanel = getButtonPanel();

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
        
        final Boolean automaticScheduling = user.isAutomaticScheduling();
        if (automaticScheduling != null)
        {
            this.automaticReschedulingCheckBox.setValue(automaticScheduling);
        }
        
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
        this.logoutButtonCaption = TM.get("usersettingspanel.8-logout");
        final Button logoutButton = new Button(this.logoutButtonCaption);

        logoutButton.addListener(this);

        final VerticalLayout buttonPanel = new VerticalLayout();

        requestImmediateRecalculationButtonCaption =
                TM.get("usersettingspanel.18-requestImmediateRecalculationButtonCaption");

        final Button requestImmediateRecalculationButton =
                new Button(requestImmediateRecalculationButtonCaption);
        requestImmediateRecalculationButton.addListener(this);

        grantAccessToGoogleTasksButtonCaption =
                TM.get("usersettingspanel.11-grantAccessToGoogleTasksButtonCaption");
        grantAccessToGoogleCalendarButtonCaption =
                TM.get("usersettingspanel.12-grantAccessToGoogleCalendarButtonCaption");

        final Button grantAccessToGoogleTasksButton =
                new Button(grantAccessToGoogleTasksButtonCaption);
        final Button grantAccessToGoogleCalendarButton =
                new Button(grantAccessToGoogleCalendarButtonCaption);

        automaticReschedulingCheckBoxLettering =
                TM.get("usersettingspanel.19-automaticPlanUpdateCheckBoxLettering");
        automaticReschedulingCheckBox = new CheckBox(
                automaticReschedulingCheckBoxLettering);
        automaticReschedulingCheckBox.setImmediate(true);
        automaticReschedulingCheckBox.addListener(this); // react to clicks

        grantAccessToGoogleTasksButton.addListener(this);
        grantAccessToGoogleCalendarButton.addListener(this);

        buttonPanel.addComponent(grantAccessToGoogleCalendarButton);
        buttonPanel.addComponent(grantAccessToGoogleTasksButton);
        buttonPanel.addComponent(automaticReschedulingCheckBox);
        
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

        if (this.logoutButtonCaption.equals(buttonCaption)) {
            this.controller.logout();
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
        else if (this.automaticReschedulingCheckBoxLettering.equals(buttonCaption))
        {
            this.controller.setAutomaticScheduling(aEvent.getButton().booleanValue());
        }
    }
}

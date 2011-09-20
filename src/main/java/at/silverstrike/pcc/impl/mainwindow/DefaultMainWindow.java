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

package at.silverstrike.pcc.impl.mainwindow;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.altruix.commons.api.di.PccException;
import ru.altruix.commons.api.version.PccVersionReader;

import com.google.inject.Injector;
import com.vaadin.terminal.ParameterHandler;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import eu.livotov.tpt.TPTApplication;
import eu.livotov.tpt.i18n.TM;

import at.silverstrike.pcc.api.invitationrequestadminpanelcontroller.InvitationRequestAdminPanelController;
import at.silverstrike.pcc.api.invitationrequestadminpanelcontroller.InvitationRequestAdminPanelControllerFactory;
import at.silverstrike.pcc.api.invitationrequestadminpanelvisibility.InvitationRequestAdminPanelVisibilityCalculator;
import at.silverstrike.pcc.api.mainwindow.MainWindow;
import at.silverstrike.pcc.api.mainwindowcontroller.MainWindowController;
import at.silverstrike.pcc.api.model.UserData;
import at.silverstrike.pcc.api.usersettingspanelcontroller.UserSettingsPanelController;
import at.silverstrike.pcc.api.usersettingspanelcontroller.UserSettingsPanelControllerFactory;

class DefaultMainWindow implements MainWindow, ParameterHandler {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultMainWindow.class);
    private Injector injector = null;
    private Window mainWindow;
    private TabSheet tabSheet;
    private UserSettingsPanelController userSettingsPanelController;

    public DefaultMainWindow() {
    }

    @Override
    public Window toWindow() {
        return this.mainWindow;
    }

    public void initGui() {
        final PccVersionReader versionReader =
                this.injector.getInstance(PccVersionReader.class);

        mainWindow =
                new Window(TM.get("mainwindow.1-title",
                        versionReader.getVersion()));
        mainWindow.setSizeFull();
        mainWindow.setDebugId("011.001");
        mainWindow.addParameterHandler(this);

        this.tabSheet = new TabSheet();
        this.tabSheet.setDebugId("011.002");

        this.tabSheet.setSizeFull();

        final VerticalLayout mainLayout = new VerticalLayout();

        LOGGER.debug("injector: {}", this.injector);

        this.tabSheet.addTab(getUserSettingsTab(), TM
                .get("mainwindow.22-user-settings-tab"), null);

        if (isInvitationAdminPanelVisible()) {
            this.tabSheet.addTab(getInvitationRequestTab(), TM
                    .get("mainwindow.21-invitation-tab"), null);
        }

        mainLayout.addComponent(this.tabSheet);

        mainWindow.setContent(mainLayout);
    }

    private boolean isInvitationAdminPanelVisible() {
        final InvitationRequestAdminPanelVisibilityCalculator calculator =
                this.injector
                        .getInstance(InvitationRequestAdminPanelVisibilityCalculator.class);
        
        final UserData user =
            (UserData) TPTApplication.getCurrentApplication()
                    .getUser();
        calculator.setCurrentUsername(user.getUsername());
        try {
            calculator.run();
        } catch (final PccException exception) {
            LOGGER.error("", exception);
        }
        
        return calculator.isInvitationPanelVisible();
    }

    private Component getUserSettingsTab() {
        final UserSettingsPanelControllerFactory factory =
                this.injector
                        .getInstance(UserSettingsPanelControllerFactory.class);
        userSettingsPanelController = factory.create();

        userSettingsPanelController.setInjector(this.injector);

        return userSettingsPanelController.initGui();
    }

    private Component getInvitationRequestTab() {
        final InvitationRequestAdminPanelControllerFactory factory =
                this.injector
                        .getInstance(InvitationRequestAdminPanelControllerFactory.class);
        final InvitationRequestAdminPanelController controller =
                factory.create();

        controller.setInjector(this.injector);

        return controller.initGui();
    }

    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;
    }

    @Override
    public void setGuiController(final MainWindowController aController) {
    }

    @Override
    public void handleParameters(final Map<String, String[]> aParameters) {
        this.userSettingsPanelController.processGoogleResponse(aParameters);
    }
}

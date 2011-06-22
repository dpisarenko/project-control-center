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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.altruix.commons.api.version.PccVersionReader;

import com.google.inject.Injector;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import eu.livotov.tpt.i18n.TM;

import at.silverstrike.pcc.api.invitationrequestadminpanelcontroller.InvitationRequestAdminPanelController;
import at.silverstrike.pcc.api.invitationrequestadminpanelcontroller.InvitationRequestAdminPanelControllerFactory;
import at.silverstrike.pcc.api.mainwindow.MainWindow;
import at.silverstrike.pcc.api.mainwindowcontroller.MainWindowController;
import at.silverstrike.pcc.api.schedulingguicontroller.SchedulingPanelController;
import at.silverstrike.pcc.api.schedulingguicontroller.SchedulingPanelControllerFactory;
import at.silverstrike.pcc.api.usersettingspanelcontroller.UserSettingsPanelController;
import at.silverstrike.pcc.api.usersettingspanelcontroller.UserSettingsPanelControllerFactory;

class DefaultMainWindow implements MainWindow {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultMainWindow.class);
    private Injector injector = null;
    private Window mainWindow;
    private TabSheet tabSheet;
//    private PccDebugIdRegistry debugIdRegistry;

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

        this.tabSheet = new TabSheet();
        this.tabSheet.setDebugId("011.002");

        this.tabSheet.setSizeFull();

        final VerticalLayout mainLayout = new VerticalLayout();

        final SchedulingPanelControllerFactory factory =
                this.injector
                        .getInstance(SchedulingPanelControllerFactory.class);
        final SchedulingPanelController schedulingPanelController =
                factory.create();

        LOGGER.debug("injector: {}", this.injector);
        schedulingPanelController.setInjector(this.injector);

        this.tabSheet.addTab(getUserSettingsTab(), TM
                .get("mainwindow.22-user-settings-tab"), null);
        this.tabSheet.addTab(getInvitationRequestTab(), TM
                .get("mainwindow.21-invitation-tab"), null);
        
        mainLayout.addComponent(this.tabSheet);

        mainWindow.setContent(mainLayout);
    }

    private Component getUserSettingsTab() {
        final UserSettingsPanelControllerFactory factory =
                this.injector
                        .getInstance(UserSettingsPanelControllerFactory.class);
        final UserSettingsPanelController controller = factory.create();
        
        controller.setInjector(this.injector);
        
        return controller.initGui();
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
}

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

import com.google.inject.Injector;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import eu.livotov.tpt.i18n.TM;

import at.silverstrike.pcc.api.centraleditingpanel.CentralEditingPanel;
import at.silverstrike.pcc.api.centraleditingpanel.CentralEditingPanelFactory;
import at.silverstrike.pcc.api.conventions.MessageCodePrefixRegistry;
import at.silverstrike.pcc.api.dailyplanpanel.DailyPlanPanel;
import at.silverstrike.pcc.api.dailyplanpanel.DailyPlanPanelFactory;
import at.silverstrike.pcc.api.debugids.DebugIdRegistry;
import at.silverstrike.pcc.api.estimatedcompletiontimespanel.EstimatedCompletionTimesPanel;
import at.silverstrike.pcc.api.estimatedcompletiontimespanel.EstimatedCompletionTimesPanelFactory;
import at.silverstrike.pcc.api.graphdemopanel.GraphDemoPanel;
import at.silverstrike.pcc.api.graphdemopanel.GraphDemoPanelFactory;
import at.silverstrike.pcc.api.mainwindow.MainWindow;
import at.silverstrike.pcc.api.mainwindowcontroller.MainWindowController;
import at.silverstrike.pcc.api.schedulingpanel.SchedulingPanel;
import at.silverstrike.pcc.api.schedulingpanel.SchedulingPanelFactory;
import at.silverstrike.pcc.api.version.PccVersionReader;
import at.silverstrike.pcc.api.workerpanel.WorkerPanel;
import at.silverstrike.pcc.api.workerpanel.WorkerPanelFactory;
import at.silverstrike.pcc.impl.mainwindowcontroller.DefaultMainWindowControllerFactory;

class DefaultMainWindow implements MainWindow {
    private Injector injector = null;
    private Window mainWindow;
    private TabSheet tabSheet;
    private DebugIdRegistry debugIdRegistry;
    private Label indicator;
    private transient MainWindowController controller;

    public DefaultMainWindow() {
    }

    @Override
    public Window getWindow() {
        return this.mainWindow;
    }

    public void initGui() {
        this.debugIdRegistry = this.injector.getInstance(DebugIdRegistry.class);

        final PccVersionReader versionReader =
                this.injector.getInstance(PccVersionReader.class);

        mainWindow =
                new Window(TM.get("mainwindow.1-title",
                        versionReader.getVersion()));
        mainWindow.setSizeFull();
        mainWindow.setDebugId(this.debugIdRegistry
                .getDebugId(MessageCodePrefixRegistry.Module.mainwindow, "1"));

        this.tabSheet = new TabSheet();
        this.tabSheet.setDebugId(this.debugIdRegistry
                .getDebugId(MessageCodePrefixRegistry.Module.mainwindow,
                        "2-tab-sheet"));

        this.tabSheet.setSizeFull();

        final VerticalLayout mainLayout = new VerticalLayout();

        final MenuBar menubar = createMenuBar();
        mainLayout.addComponent(menubar);

        indicator = getStatus();
        indicator.setContentMode(Label.CONTENT_XHTML);

        mainLayout.addComponent(indicator);

        this.tabSheet.addTab(getCentralEditingPanel(), TM
                .get("mainwindow.13-central-editing-panel"), null);
        this.tabSheet.addTab(getDailyPlanPanel(), TM
                .get("mainwindow.11-daily-plan-panel"), null);
        this.tabSheet.addTab(getEstimatedCompletionDateTimesPanel(), TM
                .get("mainwindow.12-estimated-completion-times-panel"), null);
        this.tabSheet.addTab(getWorkerPanelTab(), TM
                .get("mainwindow.8-human-resource-tab"), null);
        this.tabSheet.addTab(getSchedulingPanelTab(), TM
                .get("mainwindow.9-scheduling-tab"), null);

        this.tabSheet.addTab(getGraphTestPanel(), TM
                .get("mainwindow.14-graph-test"), null);

        mainLayout.addComponent(this.tabSheet);

        mainWindow.setContent(mainLayout);

    }

    private MainWindowController getController() {
        final DefaultMainWindowControllerFactory factory =
                this.injector
                        .getInstance(DefaultMainWindowControllerFactory.class);
        final MainWindowController returnValue =
                factory.create();
        returnValue.setInjector(this.injector);
        return returnValue;
    }

    private Label getStatus() {
        indicator = new Label("<p>Plan completed</p>");
        return indicator;
    }

    private MenuBar createMenuBar() {
        final MenuBar menubar = new MenuBar();
        menubar.setWidth("100%");

        // Save reference to individual items so we can add sub-menu items to
        // them
        final MenuBar.MenuItem file =
                menubar.addItem(
                        TM.get("mainwindow.15-menu-file"), null);
        file.addItem(TM.get("mainwindow.16-menu-exportXML"),
                exportToXMLCommand);
        file.addItem(TM.get("mainwindow.17-menu-importXML"),
                importFromXMLCommand);
        file.addSeparator();
        file.addItem(TM.get("mainwindow.18-menu-exit"),
                menuCommand);

        menubar.addItem(TM.get("mainwindow.19-menu-other"), null);
        return menubar;
    }

    private Command importFromXMLCommand = new Command() {

        private static final long serialVersionUID = 1L;

        public void menuSelected(final MenuItem aSelectedItem) {
            if (controller == null) {
                controller = getController();
            }
            controller.importFromXML();
        }
    };

    private Command exportToXMLCommand = new Command() {

        private static final long serialVersionUID = 1L;

        public void menuSelected(final MenuItem aSelectedItem) {
            if (controller == null) {
                controller = getController();
            }
            controller.exportToXML();
        }
    };

    private Command menuCommand = new Command() {
        private static final long serialVersionUID = 1L;

        public void menuSelected(final MenuItem aSelectedItem) {
            getWindow().showNotification("Action " + aSelectedItem.getText());
        }
    };

    private Component getGraphTestPanel() {
        final GraphDemoPanelFactory factory =
                this.injector.getInstance(GraphDemoPanelFactory.class);
        final GraphDemoPanel panel = factory.create();

        panel.initGui();

        return panel.toPanel();
    }

    private Component getEstimatedCompletionDateTimesPanel() {
        final EstimatedCompletionTimesPanelFactory factory =
                this.injector
                        .getInstance(EstimatedCompletionTimesPanelFactory.class);
        final EstimatedCompletionTimesPanel panel = factory.create();
        panel.setInjector(this.injector);
        panel.initGui();
        return panel.toPanel();
    }

    private Component getDailyPlanPanel() {
        final DailyPlanPanelFactory factory =
                this.injector.getInstance(DailyPlanPanelFactory.class);
        final DailyPlanPanel panel = factory.create();

        panel.setInjector(this.injector);
        panel.attach();
        panel.initGui();

        return panel.toPanel();
    }

    private Component getCentralEditingPanel() {
        final CentralEditingPanelFactory factory =
                this.injector.getInstance(CentralEditingPanelFactory.class);
        final CentralEditingPanel panel = factory.create();
        panel.setInjector(this.injector);
        panel.initGui();

        return panel.toPanel();
    }

    private Component getSchedulingPanelTab() {
        final SchedulingPanelFactory factory =
                this.injector.getInstance(SchedulingPanelFactory.class);
        final SchedulingPanel panel = factory.create();

        panel.setInjector(this.injector);
        panel.initGui();

        return panel.toPanel();
    }

    private Component getWorkerPanelTab() {
        final WorkerPanelFactory factory =
                this.injector.getInstance(WorkerPanelFactory.class);
        final WorkerPanel panel = factory.create();

        panel.setInjector(this.injector);
        panel.initGui();

        return panel.toPanel();
    }

    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;
    }
}

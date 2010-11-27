/**
 * This file is part of Project Control Center (PCC).
 * 
 * Project Control Center (PCC) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Project Control Center (PCC) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Project Control Center (PCC).  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2010 Dmitri Anatol'evich Pisarenko
 **/

package at.silverstrike.pcc.impl.mainwindow;

import com.google.inject.Injector;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Window;

import eu.livotov.tpt.i18n.TM;

import at.silverstrike.pcc.api.dailyplanpanel.DailyPlanPanel;
import at.silverstrike.pcc.api.dailyplanpanel.DailyPlanPanelFactory;
import at.silverstrike.pcc.api.debugids.DebugIdRegistry;
import at.silverstrike.pcc.api.estimatedcompletiontimespanel.EstimatedCompletionTimesPanel;
import at.silverstrike.pcc.api.estimatedcompletiontimespanel.EstimatedCompletionTimesPanelFactory;
import at.silverstrike.pcc.api.mainprocesseditingpanel.MainProcessEditingPanel;
import at.silverstrike.pcc.api.mainprocesseditingpanel.MainProcessEditingPanelFactory;
import at.silverstrike.pcc.api.mainwindow.MainWindow;
import at.silverstrike.pcc.api.schedulingpanel.SchedulingPanel;
import at.silverstrike.pcc.api.schedulingpanel.SchedulingPanelFactory;
import at.silverstrike.pcc.api.workerpanel.WorkerPanel;
import at.silverstrike.pcc.api.workerpanel.WorkerPanelFactory;

class DefaultMainWindow implements MainWindow {
    private Injector injector;
    private Window mainWindow;
    private TabSheet tabSheet;
    private DebugIdRegistry debugIdRegistry;
    
    public DefaultMainWindow() {
    }

    @Override
    public Window getWindow() {
        return this.mainWindow;
    }

    
    public void initGui() {
        this.debugIdRegistry = this.injector.getInstance(DebugIdRegistry.class);
        
        mainWindow = new Window(TM.get("mainwindow.1-title"));
        mainWindow.setDebugId(this.debugIdRegistry.getDebugId("mainwindow.1"));
        
        
        this.tabSheet = new TabSheet();
        this.tabSheet.setDebugId(this.debugIdRegistry.getDebugId("mainwindow.2-tab-sheet"));
        
        this.tabSheet.setHeight("500px");
        this.tabSheet.setWidth("1200px");

        this.tabSheet.addTab(getMainProcessEditingPanel(), TM
                .get("mainwindow.10-main-process-editing-panel"), null);
        this.tabSheet.addTab(getDailyPlanPanel(), TM
                .get("mainwindow.11-daily-plan-panel"), null);
        this.tabSheet.addTab(getEstimatedCompletionDateTimesPanel(), TM
                .get("mainwindow.12-estimated-completion-times-panel"), null);
        this.tabSheet.addTab(getWorkerPanelTab(), TM
                .get("mainwindow.8-human-resource-tab"), null);
        this.tabSheet.addTab(getSchedulingPanelTab(), TM
                .get("mainwindow.9-scheduling-tab"), null);

        mainWindow.addComponent(tabSheet);
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

    private Component getMainProcessEditingPanel() {
        final MainProcessEditingPanelFactory factory =
                this.injector.getInstance(MainProcessEditingPanelFactory.class);
        final MainProcessEditingPanel panel = factory.create();
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
    public void setInjector(final Injector anInjector) {
        this.injector = anInjector;
    }
}

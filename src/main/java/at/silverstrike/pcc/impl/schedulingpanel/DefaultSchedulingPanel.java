/**
 * This file is part of Project Control Center (PCC).
 * 
 * PCC (Project Control Center) project is intellectual property of 
 * Dmitri Anatol'evich Pisarenko.
 * 
 * Copyright 2010 Dmitri Anatol'evich Pisarenko
 * All rights reserved
 *
 **/

package at.silverstrike.pcc.impl.schedulingpanel;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.silverstrike.pcc.api.conventions.MessageCodePrefixRegistry;
import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.debugids.DebugIdRegistry;
import at.silverstrike.pcc.api.export2tj3.InvalidDurationException;
import at.silverstrike.pcc.api.model.Resource;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.projectscheduler.ProjectScheduler;
import at.silverstrike.pcc.api.projectscheduler.ProjectSchedulerFactory;
import at.silverstrike.pcc.api.schedulingpanel.SchedulingPanel;

import com.google.inject.Injector;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Panel;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window;

import eu.livotov.tpt.i18n.TM;

class DefaultSchedulingPanel extends Panel implements SchedulingPanel {
    private static final int LOGGING_TEXT_AREA_COLUMNS = 20;

    private static final int LOGGING_TEXT_AREA_ROWS = 20;

    private static final int ONE_MONTH = 1;

    private static final String NEWLINE = System.getProperty("line.separator");

    private static final long serialVersionUID = 1L;

    private transient Injector injector;
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultSchedulingPanel.class);
    private ProgressIndicator progressIndicator;

    private TextField loggingTextArea;
    private transient Persistence persistence;
    private transient DebugIdRegistry debugIdRegistry;

    public DefaultSchedulingPanel() {
    }

    @Override
    public void initGui() {
        this.setWidth("500px");
        this.setHeight("100%");

        this.setDebugId(this.debugIdRegistry.getDebugId(
                MessageCodePrefixRegistry.Module.schedulingpanel,
                "3-main-panel"));

        setCaption(TM.get("schedulingpanel.1-caption"));

        final VerticalLayout layout = new VerticalLayout();

        final HorizontalLayout progressAndButtonPanel = new HorizontalLayout();
        progressIndicator = new ProgressIndicator();
        progressIndicator.setIndeterminate(true);
        progressIndicator.setEnabled(false);

        final NativeButton startButton = new NativeButton(
                TM.get("schedulingpanel.2-start-button"));
        startButton.setDebugId(this.debugIdRegistry.getDebugId(
                MessageCodePrefixRegistry.Module.schedulingpanel,
                "2-startButton"));

        startButton.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final ClickEvent aEvent) {
                startButtonClicked();
            }
        });

        progressAndButtonPanel.addComponent(progressIndicator);
        progressAndButtonPanel.addComponent(startButton);

        layout.addComponent(progressAndButtonPanel);

        loggingTextArea = new TextField();
        loggingTextArea.setRows(LOGGING_TEXT_AREA_ROWS);
        loggingTextArea.setColumns(LOGGING_TEXT_AREA_COLUMNS);

        layout.addComponent(loggingTextArea);

        addComponent(layout);
    }

    @Override
    public void setInjector(final Injector aInjector) {
        injector = aInjector;

        if (aInjector != null) {
            this.persistence = aInjector.getInstance(Persistence.class);
            this.debugIdRegistry = aInjector.getInstance(DebugIdRegistry.class);
        }
    }

    @Override
    public Panel toPanel() {
        return this;
    }

    protected void startButtonClicked() {
        progressIndicator.setEnabled(true);

        final ProjectSchedulerFactory factory = injector
                .getInstance(ProjectSchedulerFactory.class);
        final ProjectScheduler scheduler = factory.create();

        scheduler.getProjectExportInfo().setControlProcessesToExport(
                this.persistence.getAllNotDeletedTasks());

        final List<Resource> resources = new LinkedList<Resource>();
        resources.addAll(this.persistence.getAllWorkers());

        scheduler.getProjectExportInfo().setResourcesToExport(resources);

        scheduler.getProjectExportInfo().setProjectName("pcc");

        final Date now = new Date();

        scheduler.getProjectExportInfo().setNow(now);
        scheduler.getProjectExportInfo().setCopyright("Dmitri Pisarenko");
        scheduler.getProjectExportInfo().setCurrency("EUR");
        scheduler.getProjectExportInfo().setSchedulingHorizonMonths(ONE_MONTH);

        appendToLoggingTextArea("0");

        scheduler.setDirectory(System.getProperty("user.dir") + "/");
        scheduler.setInjector(injector);
        scheduler.setNow(now);

        try {
            appendToLoggingTextArea("1");
            scheduler.run();
            appendToLoggingTextArea("2");
            progressIndicator.setEnabled(false);
        } catch (final InvalidDurationException exception) {
            final Window subwindow = new Window(
                    TM.get("schedulingpanel.3-start-validation-error-caption"));

            subwindow.setDebugId(this.debugIdRegistry.getDebugId(
                    MessageCodePrefixRegistry.Module.schedulingpanel,
                    "4-invalid-time-error-message"));

            subwindow.setModal(true);
            final Label message = new Label(TM.get(
                    "schedulingpanel.4-start-validation-error-description",
                    exception.getTaskNumber(), exception.getTaskName()));
            subwindow.addComponent(message);

            if (subwindow.getParent() != null) {
                // window is already showing
                getWindow().showNotification("Window is already open");
            } else {
                // Open the subwindow by adding it to the parent
                // window
                getWindow().addWindow(subwindow);
            }
            progressIndicator.setEnabled(false);
        }

        catch (final PccException exception) {
            appendToLoggingTextArea("3" + exception.toString());
            LOGGER.error("", exception);
            progressIndicator.setEnabled(false);
            appendToLoggingTextArea("4");
        }
    }

    private void appendToLoggingTextArea(final String aStuffToAppend) {
        loggingTextArea.setValue(loggingTextArea.getValue() + NEWLINE
                + aStuffToAppend);
    }
}
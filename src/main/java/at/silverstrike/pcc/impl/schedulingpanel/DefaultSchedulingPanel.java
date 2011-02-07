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

import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.model.Resource;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.projectscheduler.ProjectScheduler;
import at.silverstrike.pcc.api.projectscheduler.ProjectSchedulerFactory;
import at.silverstrike.pcc.api.schedulingpanel.SchedulingPanel;

import com.google.inject.Injector;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import eu.livotov.tpt.i18n.TM;

class DefaultSchedulingPanel extends Panel implements SchedulingPanel {
    private static final int ONE_MONTH = 1;

    private static final String NEWLINE = System.getProperty("line.separator");

    private static final long serialVersionUID = 1L;

    private transient Injector injector;
    private final static Logger LOGGER =
        LoggerFactory.getLogger(DefaultSchedulingPanel.class);
    private ProgressIndicator progressIndicator;

    private TextField loggingTextArea;
    private transient Persistence persistence;

    public DefaultSchedulingPanel() {
    }

    @Override
    public void initGui() {
        this.setWidth("500px");
        this.setHeight("400px");

        setCaption(TM.get("schedulingpanel.1-caption"));

        final VerticalLayout layout = new VerticalLayout();

        final HorizontalLayout progressAndButtonPanel = new HorizontalLayout();
        progressIndicator = new ProgressIndicator();
        progressIndicator.setIndeterminate(true);
        progressIndicator.setEnabled(false);

        final Button startButton =
                new Button(TM.get("schedulingpanel.2-start-button"));

        startButton.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final ClickEvent event) {
                startButtonClicked();
            }
        });

        progressAndButtonPanel.addComponent(progressIndicator);
        progressAndButtonPanel.addComponent(startButton);

        layout.addComponent(progressAndButtonPanel);

        loggingTextArea = new TextField();
        loggingTextArea.setRows(20);
        loggingTextArea.setColumns(20);

        layout.addComponent(loggingTextArea);

        addComponent(layout);
    }

    @Override
    public void setInjector(final Injector anInjector) {
        injector = anInjector;
        
        if (anInjector != null)
        {
            this.persistence = anInjector.getInstance(Persistence.class);
        }
    }

    @Override
    public Panel toPanel() {
        return this;
    }

    protected void startButtonClicked() {
        progressIndicator.setEnabled(true);

        final ProjectSchedulerFactory factory =
                injector.getInstance(ProjectSchedulerFactory.class);
        final ProjectScheduler scheduler = factory.create();

        scheduler.getProjectExportInfo().setControlProcessesToExport(this.persistence.getAllNotDeletedTasks());
        
        final List<Resource> resources = new LinkedList<Resource>();
        resources.addAll(this.persistence.getAllWorkers());
        
        scheduler.getProjectExportInfo().setResourcesToExport(resources);
        
        scheduler.getProjectExportInfo().setProjectName("pcc");
        
        final Date now = new Date();
        
        scheduler.getProjectExportInfo().setNow(now );
        scheduler.getProjectExportInfo().setCopyright("Dmitri Pisarenko");
        scheduler.getProjectExportInfo().setCurrency("EUR");
        scheduler.getProjectExportInfo().setSchedulingHorizonMonths(ONE_MONTH);
        
        appendToLoggingTextArea("0");
        
        scheduler.setDirectory(System.getProperty("user.dir") + "/");
        scheduler.setInjector(injector);
        scheduler.setNow(now );

        try {
            appendToLoggingTextArea("1");
            scheduler.run();
            appendToLoggingTextArea("2");
            progressIndicator.setEnabled(false);
        } catch (final PccException exception) {
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
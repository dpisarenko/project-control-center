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

package at.silverstrike.pcc.impl.meetingeditingpanel;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Injector;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.meetingeditingpanel.MeetingEditingPanel;
import at.silverstrike.pcc.api.processpanel.ProcessPanelListener;
import at.silverstrike.pcc.api.taskeditingpanel.TaskEditingPanel;
import at.silverstrike.pcc.api.testtablecreator.TestTableCreator;
import eu.livotov.tpt.i18n.TM;

class DefaultMeetingEditingPanel extends Panel implements
                MeetingEditingPanel, ProcessPanelListener, ClickListener {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultMeetingEditingPanel.class);
    private static final long serialVersionUID = 1L;
    private static final String NOTIFICATION = "Smth happend";

    public static final Object PROJECT_PROPERTY_NAME = "name";

    private static final int PROCESS_NAME_TEXT_FIELD_COLUMNS = 30;
    private static final int PROCESS_NAME_TEXT_FIELD_ROWS = 5;

    private static final String[] DURATION_STEPS = new String[] { "15 min",
            "30 min",
            "45 min" };
    private static final String[] TEST_COLUMN_NAMES = new String[] { "¹",
            "Project", "Name" };
    private static final List<String[]> TEST_TABLE_DATA =
            Arrays.asList(
                    new String[] { "1.1", "Project 1", "Task 1" },
                    new String[] { "2.1", "Project 4", "Task 5" });

    private transient Injector injector;
    private transient final MeetingEditingPanelController controller =
            new DefaultMeetingEditingPanelController();

    @Override
    public void setInjector(final Injector aInjector) {
        if (aInjector != null) {
            injector = aInjector;
            this.controller.setInjector(aInjector);
            // this.setInjector(aInjector);
        }
    }

    @Override
    public void initGui() {

    }

    private VerticalLayout getTaskLayout() {
        final VerticalLayout verticalLayoutRight = new VerticalLayout();

        final Label taskLabel =
                new Label(TM.get("centraleditingprocesspanel.10-label-task"));
        taskLabel.setContentMode(Label.CONTENT_TEXT);
        verticalLayoutRight.addComponent(taskLabel);

        final HorizontalLayout buttonsTaskLayout = new HorizontalLayout();
        buttonsTaskLayout.setSpacing(true);

        final Button saveButton =
                new Button(TM.get("centraleditingprocesspanel.11-button-save"));
        saveButton.addListener(this); // react to clicks
        buttonsTaskLayout.addComponent(saveButton);

        final Button doneButton =
                new Button(TM.get("centraleditingprocesspanel.12-button-done"));
        doneButton.addListener(this); // react to clicks
        buttonsTaskLayout.addComponent(doneButton);

        final Button deleteButton =
                new Button(
                        TM.get("centraleditingprocesspanel.13-button-delete"));
        deleteButton.addListener(this); // react to clicks
        buttonsTaskLayout.addComponent(deleteButton);

        verticalLayoutRight.addComponent(buttonsTaskLayout);

        final TextField taskNameTextField = new TextField();
        taskNameTextField.setColumns(PROCESS_NAME_TEXT_FIELD_COLUMNS);
        taskNameTextField.setRows(PROCESS_NAME_TEXT_FIELD_ROWS);
        verticalLayoutRight.addComponent(taskNameTextField);

        final Label effortLabel =
                new Label(TM.get("centraleditingprocesspanel.14-label-effort"));
        effortLabel.setContentMode(Label.CONTENT_TEXT);
        verticalLayoutRight.addComponent(effortLabel);

        final HorizontalLayout effortLayout = getEffortPanel();

        verticalLayoutRight.addComponent(effortLayout);

        final HorizontalLayout dependLayout = new HorizontalLayout();
        dependLayout.setSpacing(true);

        final Label dependLabel =
                new Label(
                        TM.get("centraleditingprocesspanel.17-label-dependencies"));
        dependLayout.addComponent(dependLabel);

        final Button dependEditButton = createDependEditButton();
        dependLayout.addComponent(dependEditButton);

        verticalLayoutRight.addComponent(dependLayout);

        final Table table = createTestTable();
        verticalLayoutRight.addComponent(table);
        return verticalLayoutRight;
    }

    private HorizontalLayout getEffortPanel() {
        final HorizontalLayout effortLayout = new HorizontalLayout();
        effortLayout.setSpacing(true);

        final Label fromLabel =
                new Label(TM.get("centraleditingprocesspanel.15-label-from"));
        fromLabel.setContentMode(Label.CONTENT_TEXT);
        effortLayout.addComponent(fromLabel);

        final ComboBox from = new ComboBox();
        for (int i = 0; i < DURATION_STEPS.length - 1; i++) {
            from.addItem(DURATION_STEPS[i]);
        }
        effortLayout.addComponent(from);

        final Label toLabel =
                new Label(TM.get("centraleditingprocesspanel.16-label-to"));
        toLabel.setContentMode(Label.CONTENT_TEXT);
        effortLayout.addComponent(toLabel);

        final ComboBox to = new ComboBox();
        for (int i = 1; i < DURATION_STEPS.length; i++) {
            to.addItem(DURATION_STEPS[i]);
        }
        effortLayout.addComponent(to);
        return effortLayout;
    }

    private Button createDependEditButton() {
        final Button dependEditButton =
                new Button(TM.get("centraleditingprocesspanel.18-button-edit"));
        dependEditButton.addListener(new DependenciesButtonClickListener(
                controller));
        return dependEditButton;
    }

    private Table createTestTable() {
        final TestTableCreator creator =
                this.injector.getInstance(TestTableCreator.class);
        creator.setColumnNames(TEST_COLUMN_NAMES);
        creator.setData(TEST_TABLE_DATA);
        try {
            creator.run();
        } catch (final PccException exception) {
            LOGGER.error(ErrorCodes.M_001_TEST_TABLE_CREATION, exception);
        }
        final Table table = creator.getTable();
        return table;
    }

    /*
     * Shows a notification when a button is clicked.
     */
    public void buttonClick(final ClickEvent aEvent) {
        getWindow().showNotification(NOTIFICATION);
    }

    @Override
    public void taskAdded() {
    }

    @Override
    public Layout toLayout() {
        return getTaskLayout();
    }
}
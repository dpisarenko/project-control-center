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
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.debugids.DebugIdRegistry;
import at.silverstrike.pcc.api.meetingeditingpanel.MeetingEditingPanel;
import at.silverstrike.pcc.api.meetingeditingpanelcontroller.MeetingEditingPanelController;
import at.silverstrike.pcc.api.testtablecreator.TestTableCreator;
import eu.livotov.tpt.i18n.TM;

class DefaultMeetingEditingPanel extends Panel implements
                MeetingEditingPanel, ClickListener {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultMeetingEditingPanel.class);
    private static final long serialVersionUID = 1L;
    private static final String NOTIFICATION = "Smth happend";

    public static final Object PROJECT_PROPERTY_NAME = "name";

    private static final int PROCESS_NAME_TEXT_FIELD_ROWS = 5;

    private static final String[] TEST_COLUMN_NAMES = new String[] { "�",
            "Project", "Name" };
    private static final List<String[]> TEST_TABLE_DATA =
            Arrays.asList(
                    new String[] { "1.1", "Project 1", "Task 1" },
                    new String[] { "2.1", "Project 4", "Task 5" });

    private static final String SAVE_MEETING_BUTTON = "029.001";
    private static final String DELETE_MEETING_BUTTON = "029.002";

    private transient Injector injector;
    private transient MeetingEditingPanelController controller;
    private transient DebugIdRegistry debugIdRegistry;

    @Override
    public void setInjector(final Injector aInjector) {
        if (aInjector != null) {
            injector = aInjector;
            this.controller =
                    this.injector
                            .getInstance(MeetingEditingPanelController.class);
            this.controller.setInjector(this.injector);
            this.debugIdRegistry =
                    this.injector.getInstance(DebugIdRegistry.class);
        }
    }

    @Override
    public void initGui() {
        final Panel verticalLayoutRight = new Panel();

        final Label taskLabel =
                new Label(TM.get("meetingeditingprocesspanel.1-label-meeting"));
        taskLabel.setContentMode(Label.CONTENT_TEXT);
        verticalLayoutRight.addComponent(taskLabel);

        final HorizontalLayout buttonsTaskLayout = new HorizontalLayout();
        buttonsTaskLayout.setSpacing(true);

        final Button saveButton =
                new Button(TM.get("meetingeditingprocesspanel.2-button-save"));
        saveButton.addListener(this); // react to clicks
        buttonsTaskLayout.addComponent(saveButton);

        final Button deleteButton =
                new Button(
                        TM.get("meetingeditingprocesspanel.3-button-delete"));
        deleteButton.addListener(this); // react to clicks
        buttonsTaskLayout.addComponent(deleteButton);

        verticalLayoutRight.addComponent(buttonsTaskLayout);

        final TextField taskNameTextField = new TextField();
        taskNameTextField.setWidth("100%");
        taskNameTextField.setRows(PROCESS_NAME_TEXT_FIELD_ROWS);
        verticalLayoutRight.addComponent(taskNameTextField);

        final HorizontalLayout placeLayout = getPlacePanel();
        verticalLayoutRight.addComponent(placeLayout);

        final HorizontalLayout datesLayout = getIntervalDatesPanel();
        verticalLayoutRight.addComponent(datesLayout);

        final HorizontalLayout dependLayout = new HorizontalLayout();
        dependLayout.setSpacing(true);

        final Label dependLabel =
                new Label(
                        TM.get("meetingeditingprocesspanel.4-label-dependencies"));
        dependLayout.addComponent(dependLabel);

        final Button dependEditButton = createDependEditButton();
        dependLayout.addComponent(dependEditButton);

        verticalLayoutRight.addComponent(dependLayout);

        final Table table = createTestTable();
        verticalLayoutRight.addComponent(table);
        this.addComponent(verticalLayoutRight);
    }

    private HorizontalLayout getPlacePanel() {
        final HorizontalLayout placeLayout = new HorizontalLayout();
        placeLayout.setSpacing(true);

        final Label placeLabel =
                new Label(TM.get("meetingeditingprocesspanel.6-label-place"));
        placeLabel.setContentMode(Label.CONTENT_TEXT);
        placeLayout.addComponent(placeLabel);

        final TextField placeTextField = new TextField();
        placeLayout.addComponent(placeTextField);

        return placeLayout;
    }

    private HorizontalLayout getIntervalDatesPanel() {
        final HorizontalLayout datesLayout = new HorizontalLayout();
        datesLayout.setSpacing(true);

        final InlineDateField startDate =
                new InlineDateField(
                        TM.get("meetingeditingprocesspanel.7-label-start"));

        // Set the value of the PopupDateField to current date
        startDate.setValue(new java.util.Date());

        // Set the correct resolution
        startDate.setResolution(InlineDateField.RESOLUTION_MIN);

        datesLayout.addComponent(startDate);

        final InlineDateField finishDate =
                new InlineDateField(
                        TM.get("meetingeditingprocesspanel.8-label-finish"));

        // Set the value of the PopupDateField to current date
        finishDate.setValue(new java.util.Date());

        // Set the correct resolution
        finishDate.setResolution(InlineDateField.RESOLUTION_MIN);

        datesLayout.addComponent(finishDate);

        return datesLayout;
    }

    private Button createDependEditButton() {
        final Button dependEditButton =
                new Button(TM.get("meetingeditingprocesspanel.5-button-edit"));
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
    public Panel toPanel() {
        return this;
    }
}
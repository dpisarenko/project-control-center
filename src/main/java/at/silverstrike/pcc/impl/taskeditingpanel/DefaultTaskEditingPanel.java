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

package at.silverstrike.pcc.impl.taskeditingpanel;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.altruix.commons.api.di.PccException;
import ru.altruix.commons.api.gui.ModalDialogResult;

import com.google.inject.Injector;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import at.silverstrike.pcc.api.debugids.PccDebugIdRegistry;
import at.silverstrike.pcc.api.dependencieseditingdialogcontroller.DependenciesEditingDialogController;
import at.silverstrike.pcc.api.dependencieseditingdialogcontroller.DependenciesEditingDialogControllerFactory;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.pcc.PccFunctionalBlock;
import at.silverstrike.pcc.api.taskeditingpanel.TaskEditingPanel;
import at.silverstrike.pcc.api.taskeditingpanelcontroller.TaskEditingPanelController;
import at.silverstrike.pcc.api.testtablecreator.TestTableCreator;
import eu.livotov.tpt.TPTApplication;
import eu.livotov.tpt.gui.dialogs.OptionDialog;
import eu.livotov.tpt.i18n.TM;

class DefaultTaskEditingPanel extends Panel implements
        TaskEditingPanel, ClickListener {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultTaskEditingPanel.class);
    private static final long serialVersionUID = 1L;

    public static final Object PROJECT_PROPERTY_NAME = "name";

    private static final int PROCESS_NAME_TEXT_FIELD_COLUMNS = 30;
    private static final int PROCESS_NAME_TEXT_FIELD_ROWS = 5;

    private static final Double[] DURATION_STEPS = new Double[] {
            0.17,
            0.25,
            0.5,
            0.75,
            1.0,
            2.0,
            3.0,
            4.0,
            5.0,
            8.0,
            16.0,
            24.0,
            40.0 };
    private static final String[] TEST_COLUMN_NAMES = new String[] { "�",
            "Project", "Name" };
    private static final List<String[]> TEST_TABLE_DATA =
            Arrays.asList(
                    new String[] { "1.1", "Project 1", "Task 1" },
                    new String[] { "2.1", "Project 4", "Task 5" });
    private static final String SAVE_TASK_BUTTON = "028.001";
    private static final String DONE_TASK_BUTTON = "028.002";
    private static final String DELETE_TASK_BUTTON = "028.003";
    private static final String DEPENDENCIES_BUTTON = "028.004";

    private transient Injector injector;
    private transient TaskEditingPanelController controller;
    private transient PccDebugIdRegistry debugIdRegistry;

    private TextField taskNameTextField;
    private ComboBox from;
    private ComboBox to;
    private transient Task task;
    private String[] effortList;
    private HashMap<String, Double> hm;

    @Override
    public void setInjector(final Injector aInjector) {
        if (aInjector != null) {
            injector = aInjector;
            this.controller =
                    this.injector
                            .getInstance(TaskEditingPanelController.class);
            this.controller.setInjector(aInjector);
            this.debugIdRegistry =
                    this.injector.getInstance(PccDebugIdRegistry.class);
        }
    }

    @Override
    public void initGui() {
        final Panel verticalLayoutRight = new Panel();

        final Label taskLabel =
                new Label(TM.get("taskeditingpanel.10-label-task"));
        taskLabel.setContentMode(Label.CONTENT_TEXT);
        verticalLayoutRight.addComponent(taskLabel);

        final HorizontalLayout buttonsTaskLayout = new HorizontalLayout();
        buttonsTaskLayout.setSpacing(true);

        final Button saveButton =
                new Button(TM.get("taskeditingpanel.11-button-save"));
        saveButton.setDebugId(this.debugIdRegistry
                .getDebugId(
                        PccFunctionalBlock.taskeditingpanel,
                        "1-button-save"));
        saveButton.addListener(this); // react to clicks
        buttonsTaskLayout.addComponent(saveButton);

        final Button doneButton =
                new Button(TM.get("taskeditingpanel.12-button-done"));
        doneButton.setDebugId(this.debugIdRegistry
                .getDebugId(
                        PccFunctionalBlock.taskeditingpanel,
                        "2-button-done"));
        doneButton.addListener(this); // react to clicks
        buttonsTaskLayout.addComponent(doneButton);

        final Button deleteButton =
                new Button(
                        TM.get("taskeditingpanel.13-button-delete"));
        deleteButton.setDebugId(this.debugIdRegistry
                .getDebugId(
                        PccFunctionalBlock.taskeditingpanel,
                        "3-button-delete"));

        deleteButton.addListener(this); // react to clicks
        buttonsTaskLayout.addComponent(deleteButton);

        verticalLayoutRight.addComponent(buttonsTaskLayout);

        taskNameTextField = new TextField();
        taskNameTextField.setColumns(PROCESS_NAME_TEXT_FIELD_COLUMNS);
        taskNameTextField.setRows(PROCESS_NAME_TEXT_FIELD_ROWS);
        taskNameTextField.setImmediate(true);
        verticalLayoutRight.addComponent(taskNameTextField);

        final Label effortLabel =
                new Label(TM.get("taskeditingpanel.14-label-effort"));
        effortLabel.setContentMode(Label.CONTENT_TEXT);
        verticalLayoutRight.addComponent(effortLabel);

        final HorizontalLayout effortLayout = getEffortPanel();

        verticalLayoutRight.addComponent(effortLayout);

        final HorizontalLayout dependLayout = new HorizontalLayout();
        dependLayout.setSpacing(true);

        final Label dependLabel =
                new Label(
                        TM.get("taskeditingpanel.17-label-dependencies"));
        dependLayout.addComponent(dependLabel);

        final Button dependEditButton = createDependEditButton();
        dependLayout.addComponent(dependEditButton);

        verticalLayoutRight.addComponent(dependLayout);

        final Table table = createTestTable();
        verticalLayoutRight.addComponent(table);
        this.addComponent(verticalLayoutRight);
    }

    private HorizontalLayout getEffortPanel() {
        final HorizontalLayout effortLayout = new HorizontalLayout();
        effortLayout.setSpacing(true);

        final Label fromLabel =
                new Label(TM.get("taskeditingpanel.15-label-from"));
        fromLabel.setContentMode(Label.CONTENT_TEXT);
        effortLayout.addComponent(fromLabel);

        effortList = (TM.get("taskeditingpanel.19-combobox-effort")).split(",");

        from = new ComboBox();
        for (int i = 0; i < effortList.length; i++) {
            from.addItem(effortList[i]);
        }
        effortLayout.addComponent(from);

        final Label toLabel =
                new Label(TM.get("taskeditingpanel.16-label-to"));
        toLabel.setContentMode(Label.CONTENT_TEXT);
        effortLayout.addComponent(toLabel);

        to = new ComboBox();
        for (int i = 0; i < effortList.length; i++) {
            to.addItem(effortList[i]);
        }
        effortLayout.addComponent(to);

        // implement convert to double
        hm = new HashMap<String, Double>();
        for (int i = 0; i < effortList.length; i++) {
            hm.put(effortList[i], DURATION_STEPS[i]);
        }

        return effortLayout;
    }

    private Button createDependEditButton() {
        final Button dependEditButton =
                new Button(TM.get("taskeditingpanel.18-button-edit"));
        dependEditButton.setDebugId(this.debugIdRegistry.getDebugId(
                PccFunctionalBlock.taskeditingpanel,
                "4-button-dependencies"));
        dependEditButton.addListener(this);

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
        final String debugId = aEvent.getButton().getDebugId();

        LOGGER.debug(
                "at.silverstrike.pcc.impl.taskeditingpanel.DefaultTaskEditingPanel.buttonClick(ClickEvent), debugId: {}",
                debugId);

        if (SAVE_TASK_BUTTON.equals(debugId)) {
            final OptionDialog dialog =
                    new OptionDialog(TPTApplication.getCurrentApplication());

            dialog.showConfirmationDialog("Task", "Debug ID: " + debugId
                    + ", this.task: " + this.task, null);

            if (this.task != null) {
                this.task.setName((String) this.taskNameTextField.getValue());

                String fromEffort = (String) this.from.getValue();
                Double fromDouble = (Double) hm.get(fromEffort);

                String toEffort = (String) this.to.getValue();
                Double toDouble = (Double) hm.get(toEffort);

                if (fromDouble <= toDouble) {

                    this.task.setWorstCaseEffort(fromDouble);
                    this.task.setBestCaseEffort(toDouble);

                    this.controller.saveTask(this.task);
                } else {
                    dialog.showErrorMessage("Task",
                            TM.get("taskeditingpanel.20-combobox-effort"), true);
                }
            }
        } else if (DONE_TASK_BUTTON.equals(debugId)) {
            controller.markTaskAsCompleted(this.task);
        } else if (DELETE_TASK_BUTTON.equals(debugId)) {
            controller.deleteTask(this.task);
        } else if (DEPENDENCIES_BUTTON.equals(debugId)) {
            letUserEnterDependencies();
        }
    }

    private void letUserEnterDependencies() {
        final DependenciesEditingDialogControllerFactory factory =
                this.injector
                        .getInstance(DependenciesEditingDialogControllerFactory.class);
        final DependenciesEditingDialogController controller = factory.create();

        controller.setInjector(this.injector);
        controller.setParentWindow(TPTApplication.getCurrentApplication()
                .getMainWindow());
        controller.setSchedulingObject(this.task);
        try {
            controller.run();
        } catch (final PccException exception) {
            LOGGER.error("", exception);
        }

        if (ModalDialogResult.CLOSED_WITH_OK.equals(controller
                .getDialogResult())) {
            this.task.setPredecessors(controller.getNewDependencies());
        }
    }

    @Override
    public Panel toPanel() {
        return this;
    }

    public String getTaskName() {
        return taskNameTextField.toString();
    }

    @Override
    public void setData(final Task aTask) {
        this.task = aTask;
        if (this.task != null) {
            taskNameTextField.setValue(this.task.getName());
            Double fromDbl = this.task.getWorstCaseEffort();
            Double toDbl = this.task.getBestCaseEffort();

            LOGGER.debug("HashMap is:" + hm);
            LOGGER.debug("fromEffort is:" + fromDbl);
            LOGGER.debug("fromHM is:" + hm.get(fromDbl));
            if ((fromDbl != null) && (toDbl != null)) {

                from.setValue(getKeyByValue(fromDbl));
                to.setValue(getKeyByValue(toDbl));
            } else {
                from.setValue(null);
                to.setValue(null);
            }
        } else {
            taskNameTextField.setValue("");
        }
    }

    private String getKeyByValue(Double value) {
        for (String key : hm.keySet()) {
            if (hm.get(key).equals(value)) {
                return key;
            }
        }
        return null;
    }
}
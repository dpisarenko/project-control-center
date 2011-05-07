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

import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Injector;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window.Notification;

import at.silverstrike.pcc.api.debugids.PccDebugIdRegistry;
import at.silverstrike.pcc.api.dependencieseditingpanelcontroller.DependenciesEditingPanelController;
import at.silverstrike.pcc.api.dependencieseditingpanelcontroller.DependenciesEditingPanelControllerFactory;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.pcc.PccFunctionalBlock;
import at.silverstrike.pcc.api.taskeditingpanel.TaskEditingPanel;
import at.silverstrike.pcc.api.taskeditingpanelcontroller.TaskEditingPanelController;
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
    private static final String SAVE_TASK_BUTTON = "028.001";
    private static final String DONE_TASK_BUTTON = "028.002";
    private static final String DELETE_TASK_BUTTON = "028.003";

    private transient Injector injector;
    private transient TaskEditingPanelController controller;
    private transient PccDebugIdRegistry debugIdRegistry;

    private TextField taskNameTextField;
    private ComboBox from;
    private ComboBox to;
    private transient Task task;
    private String[] effortList;
    private HashMap<String, Double> hm;
    private DependenciesEditingPanelController dependenciesPanelController;

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
//        final Panel verticalLayoutRight = new Panel();

        final Label taskLabel =
                new Label(TM.get("taskeditingpanel.10-label-task"));
        taskLabel.setContentMode(Label.CONTENT_TEXT);
        this.addComponent(taskLabel);

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

        this.addComponent(buttonsTaskLayout);

        taskNameTextField = new TextField();
        taskNameTextField.setColumns(PROCESS_NAME_TEXT_FIELD_COLUMNS);
        taskNameTextField.setRows(PROCESS_NAME_TEXT_FIELD_ROWS);
        taskNameTextField.setImmediate(true);
        this.addComponent(taskNameTextField);

        final Label effortLabel =
                new Label(TM.get("taskeditingpanel.14-label-effort"));
        effortLabel.setContentMode(Label.CONTENT_TEXT);
        this.addComponent(effortLabel);

        final HorizontalLayout effortLayout = getEffortPanel();

        this.addComponent(effortLayout);

        final DependenciesEditingPanelControllerFactory factory =
                this.injector
                        .getInstance(DependenciesEditingPanelControllerFactory.class);
        dependenciesPanelController = factory.create();
        dependenciesPanelController.setInjector(this.injector);
        this.addComponent(dependenciesPanelController.initGui());
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

    /*
     * Shows a notification when a button is clicked.
     */
    public void buttonClick(final ClickEvent aEvent) {
        final String debugId = aEvent.getButton().getDebugId();

        LOGGER.debug(
                "at.silverstrike.pcc.impl.taskeditingpanel.DefaultTaskEditingPanel.buttonClick(ClickEvent), debugId: {}",
                debugId);

        if (SAVE_TASK_BUTTON.equals(debugId)) {
            if (this.task != null) {
                this.task.setName((String) this.taskNameTextField.getValue());

                String fromEffort = (String) this.from.getValue();
                Double fromDouble = (Double) hm.get(fromEffort);

                String toEffort = (String) this.to.getValue();
                Double toDouble = (Double) hm.get(toEffort);

                LOGGER.debug("fromDouble: {}, toDouble: {}", new Object[] {
                        fromDouble, toDouble });

                if ((fromDouble != null) && (toDouble != null)
                        && (fromDouble > toDouble)) {
                    getWindow()
                            .showNotification(
                                    TM.get("taskeditingpanel.21-validation-error-title"),
                                    TM.get("taskeditingpanel.20-combobox-effort"),
                                    Notification.TYPE_ERROR_MESSAGE);
                } else {
                    this.task.setWorstCaseEffort(fromDouble);
                    this.task.setBestCaseEffort(toDouble);
                    this.controller.saveTask(this.task);
                }
                
                dependenciesPanelController.setData(this.task);
            }
        } else if (DONE_TASK_BUTTON.equals(debugId)) {
            controller.markTaskAsCompleted(this.task);
        } else if (DELETE_TASK_BUTTON.equals(debugId)) {
            controller.deleteTask(this.task);
        } 
//        else if (DEPENDENCIES_BUTTON.equals(debugId)) {
//            letUserEnterDependencies();
//        }
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
            this.dependenciesPanelController.setData(this.task);
        } else {
            taskNameTextField.setValue("");
        }
    }

    private String getKeyByValue(final Double value) {
        for (final String key : hm.keySet()) {
            if (hm.get(key).equals(value)) {
                return key;
            }
        }
        return null;
    }
}
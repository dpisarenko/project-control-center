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

package at.silverstrike.pcc.impl.editingprocesspanel;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.silverstrike.pcc.api.conventions.MessageCodePrefixRegistry;
import at.silverstrike.pcc.api.debugids.DebugIdRegistry;
import at.silverstrike.pcc.api.editingprocesspanel.EditingProcessPanel;
import at.silverstrike.pcc.api.model.ControlProcess;
import at.silverstrike.pcc.api.model.ProcessState;
import at.silverstrike.pcc.api.model.ProcessType;
import at.silverstrike.pcc.api.model.Resource;
import at.silverstrike.pcc.api.model.ResourceAllocation;
import at.silverstrike.pcc.api.model.Worker;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.webguibus.WebGuiBus;
import at.silverstrike.pcc.api.webguibus.WorkerAddedMessage;

import com.google.inject.Injector;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window.Notification;

import eu.livotov.tpt.i18n.TM;

class DefaultEditingProcessPanel extends Panel implements EditingProcessPanel {
    private static final int PRIORITY_ROW = 5;
    private static final int MAX_EFFORT_ROW = 4;
    private static final int MIN_EFFORT_ROW = 3;
    private static final int NAME_TEXT_AREA_COLUMNS = 25;
    private static final int NAME_TEXT_AREA_ROWS = 5;
    private static final String ABBREVIATED_PROCESS_NAME =
            "ABBREVIATED_PROCESS_NAME";
    private static final String EFFORT_UNIT_DAY = TM
            .get("editingprocesspanel.21-time-unit");
    private static final String EFFORT_UNIT_HOUR = TM
            .get("editingprocesspanel.20-time-unit");
    private static final String EFFORT_UNIT_MINUTE = TM
            .get("editingprocesspanel.19-time-unit");
    private static final double MINUTES_IN_DAY = 24 * 60.0;
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultEditingProcessPanel.class);
    private static final int MAX_PROCESS_NAME_LENGTH = 50;
    private static final long serialVersionUID = 1L;
    private static final String TYPE_MILESTONE = TM
            .get("editingprocesspanel.10-milestone");
    private static final String TYPE_PROCESS = TM
            .get("editingprocesspanel.9-process");
    private static final String VMSG_MAX_EFFORT =
            TM.get("editingprocesspanel.30-save-validation-error-"
                    + "MAX_EFFORT");
    private static final String VMSG_MAX_EFFORT_EMPTY = TM
            .get("editingprocesspanel.37-save-validation-error-"
                    + "MAX_EFFORT_EMPTY");
    private static final String VMSG_MAX_EFFORT_NEGATIVE = TM
            .get("editingprocesspanel.41-save-validation-error-"
                    + "MAX_EFFORT_NEGATIVE");
    private static final String VMSG_MAX_EFFORT_NOT_NUMERIC = TM
            .get("editingprocesspanel.38-save-validation-error-"
                    + "MAX_EFFORT_NOT_NUMERIC");
    private static final String VMSG_MIN_EFFORT_EMPTY = TM
            .get("editingprocesspanel.29-save-validation-error-"
                    + "MIN_EFFORT_EMPTY");
    private static final String VMSG_MIN_EFFORT_LARGER_THAN_MAX_EFFORT = TM
            .get("editingprocesspanel.39-save-validation-error-"
                    + "MIN_EFFORT_LARGER_THAN_MAX_EFFORT");

    private static final String VMSG_MIN_EFFORT_NEGATIVE = TM
            .get("editingprocesspanel.40-save-validation-error-"
                    + "MIN_EFFORT_NEGATIVE");

    private static final String VMSG_MIN_EFFORT_NOT_NUMERIC = TM
            .get("editingprocesspanel.36-save-validation-error-"
                    + "MIN_EFFORT_NOT_NUMERIC");

    private static final String VMSG_PRIORITY_EMPTY = TM
            .get("editingprocesspanel.33-save-validation-error-"
                    + "PRIORITY_EMPTY");
    private static final String VMSG_PRIORITY_NOT_NUMERIC = TM
            .get("editingprocesspanel.31-save-validation-error-"
                    + "PRIORITY_NOT_NUMERIC");
    private static final String VMSG_PRIORITY_TOO_LARGE = TM
            .get("editingprocesspanel.35-save-validation-error-"
                    + "PRIORITY_TOO_LARGE");
    private static final String VMSG_PRIORITY_TOO_SMALL = TM
            .get("editingprocesspanel.34-save-validation-error-"
                    + "PRIORITY_TOO_SMALL");

    private static final double HOURS_IN_DAY = 24.0;
    private static final double HOURS_IN_HOUR = 1.0;
    private static final double MINUTES_IN_HOUR = 60.0;
    private static final double HOURS_IN_MINUTE = 1. / MINUTES_IN_HOUR;

    private class SaveButtonRelevantListener implements
            Property.ValueChangeListener {
        private static final long serialVersionUID = 1L;

        private final Button button;

        public SaveButtonRelevantListener(final Button aButton) {
            button = aButton;
        }

        @Override
        public void valueChange(final ValueChangeEvent aEvent) {
            LOGGER.debug("SaveButtonRelevantListener.valueChange: this.button="
                    + button);
            if ((button != null) && !button.isEnabled()) {
                LOGGER.debug("SaveButtonRelevantListener.valueChange, 2");

                button.setEnabled(true);
            }
        }
    }

    private enum SaveValidationResult {
        EVERYTHING_OK, MAX_EFFORT, MAX_EFFORT_EMPTY, MAX_EFFORT_NEGATIVE,
        MAX_EFFORT_NOT_NUMERIC, MIN_EFFORT_EMPTY,
        MIN_EFFORT_LARGER_THAN_MAX_EFFORT, MIN_EFFORT_NEGATIVE,
        MIN_EFFORT_NOT_NUMERIC, PRIORITY_EMPTY, PRIORITY_NOT_NUMERIC,
        PRIORITY_TOO_LARGE, PRIORITY_TOO_SMALL
    }

    private Button cancelButton;
    private boolean changesToSaveAvailable;
    private ComboBox controlSubjectComboBox;
    private Button deleteButton;
    private Button handoffButton;
    private Button markAsCompleted;
    private TextField maxEffortTextBox;

    private ComboBox maxTimeUnitComboBox;

    private TextField minEffortTextBox;

    private ComboBox minTimeUnitComboBox;

    private Persistence persistence;
    private TextField priorityTextBox;

    private ControlProcess process;

    private TextField processNameTextArea;

    private Button reactivateButton;

    private Button saveButton;

    private Map<SaveValidationResult, String> saveErrorMessagesByValidationResults;

    private Button startButton;

    private Button stopButton;

    private ComboBox typeComboBox;

    private DebugIdRegistry debugIdRegistry;

    private WebGuiBus webGuiBus;

    public DefaultEditingProcessPanel() {
        initSaveErrorMessagesByValidationResults();
    }

    @Override
    public void initGui() {
        this.webGuiBus.addListener(this);

        this.setSizeFull();

        setCaption(TM.get("editingprocesspanel.15-panel-caption"));
        this.setDebugId(this.debugIdRegistry.getDebugId(
                MessageCodePrefixRegistry.Module.editingprocesspanel, "1"));

        final GridLayout buttonPanel = createButtonPanel();

        buttonPanel.setDebugId(this.debugIdRegistry.getDebugId(
                MessageCodePrefixRegistry.Module.editingprocesspanel,
                "2-buttonPanel"));
        addComponent(buttonPanel);

        final GridLayout grid = new GridLayout(3, 7);
        grid.setSizeFull();

        processNameTextArea = new TextField("");
        processNameTextArea.setRows(NAME_TEXT_AREA_ROWS);
        processNameTextArea.setColumns(NAME_TEXT_AREA_COLUMNS);
        processNameTextArea.setWidth("100%");
        processNameTextArea.setDebugId(this.debugIdRegistry.getDebugId(
                MessageCodePrefixRegistry.Module.editingprocesspanel,
                "3-processNameTextArea"));

        grid.addComponent(processNameTextArea, 0, 0, 2, 0);

        createHandOffControls(grid);

        createTypeControls(grid);

        createEffortEstimateControls(grid);

        createPriorityControls(grid);

        setButtonEnabledness();

        setupSaveRelevantListeners();

        addComponent(grid);
    }

    @Override
    public void setData(final Object aProcessid) {
        process = persistence.getTask(aProcessid);

        final String stateCaption = getStateCaption(aProcessid);

        setCaption(stateCaption);

        if ((process != null)
                && (!ProcessState.DELETED.equals(process.getState()))) {
            processNameTextArea.setValue(process.getName());

            final Worker worker = getWorker(process);
            controlSubjectComboBox.setValue(worker);
        } else if ((process != null)
                && ProcessState.DELETED.equals(process.getState())) {
            processNameTextArea.setValue(process.getName());
            controlSubjectComboBox.setValue(null);
        }

        setButtonEnabledness();
    }

    @Override
    public void setInjector(final Injector aInjector) {
        if (aInjector != null) {
            this.persistence = aInjector.getInstance(Persistence.class);
            this.debugIdRegistry = aInjector.getInstance(DebugIdRegistry.class);
            this.webGuiBus = aInjector.getInstance(WebGuiBus.class);
        }
    }

    @Override
    public Panel toPanel() {
        return this;
    }

    protected void cancelButtonClicked() {
        changeProcessState(ProcessState.CANCELLED);
    }

    protected void deleteButtonClicked() {
        changeProcessState(ProcessState.DELETED);
    }

    protected void enableEffortControls(final boolean aEnable) {
        minEffortTextBox.setEnabled(aEnable);
        minTimeUnitComboBox.setEnabled(aEnable);
        maxEffortTextBox.setEnabled(aEnable);
        maxTimeUnitComboBox.setEnabled(aEnable);
    }

    protected void handoffButtonClicked() {
        final Worker worker = (Worker) controlSubjectComboBox.getValue();

        persistence.handoffProcess(process.getId(), worker.getId());
    }

    protected void markAsCompletedButtonClicked() {
        changeProcessState(ProcessState.ATTAINED);
    }

    protected void reactivateButtonClicked() {
        changeProcessState(ProcessState.IS_BEING_ATTAINED);
    }

    protected void saveButtonClicked() {
        final SaveValidationResult validationResult =
                validateSaveRelevantInputs();

        if (!SaveValidationResult.EVERYTHING_OK.equals(validationResult)) {
            String errorMessage =
                    saveErrorMessagesByValidationResults.get(validationResult);

            if (StringUtils.isEmpty(errorMessage)) {
                errorMessage =
                        TM.get("editingprocesspanel.28-unexpected-error");
            }

            getWindow().showNotification(errorMessage,
                    Notification.TYPE_ERROR_MESSAGE);
        } else {
            // Save type changes
            process.setProcessType(getProcessType());

            // Save min. effort
            process.setBestCaseEffort(getEffortInHours(this.minEffortTextBox,
                    this.minTimeUnitComboBox));

            // Save max. effort
            process.setWorstCaseEffort(getEffortInHours(this.maxEffortTextBox,
                    this.maxTimeUnitComboBox));

            // Save priority
            process.setPriority(this.getPriority());

            // Send changes to database
            persistence.updateTask(process);
        }
    }

    private Integer getPriority() {
        Integer priority;

        try {
            priority =
                    Integer.parseInt((String) this.priorityTextBox.getValue());
        } catch (final NumberFormatException exception) {
            priority = -1;
        }

        return priority;
    }

    private Double getEffortInHours(final TextField aTextField,
            final ComboBox aUnitComboBox) {
        double effortInUnit;

        try {
            effortInUnit = Double.parseDouble((String) aTextField.getValue());
        } catch (final NumberFormatException exception) {
            effortInUnit = -1.;
        }

        return convertEffort(effortInUnit, aUnitComboBox, HOURS_IN_MINUTE,
                HOURS_IN_HOUR, HOURS_IN_DAY);
    }

    protected void startButtonClicked() {
        changeProcessState(ProcessState.IS_BEING_ATTAINED);
    }

    protected void stopButtonClicked() {
        changeProcessState(ProcessState.PAUSED);
    }

    private String abbreviateName(final String aProcessName) {
        return StringUtils.abbreviate(aProcessName, MAX_PROCESS_NAME_LENGTH);
    }

    private void changeProcessState(final ProcessState aNewState) {
        process.setState(aNewState);
        persistence.updateTask(process);
        setData(process.getId());
    }

    private GridLayout createButtonPanel() {
        final GridLayout buttonPanel = new GridLayout(2, 3);

        startButton = new Button(TM.get("editingprocesspanel.1-start-caption"));
        startButton.setDebugId(this.debugIdRegistry.getDebugId(
                MessageCodePrefixRegistry.Module.editingprocesspanel,
                "9-startButton"));
        startButton.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final ClickEvent aEvent) {
                DefaultEditingProcessPanel.this.startButtonClicked();
            }
        });
        stopButton = new Button(TM.get("editingprocesspanel.2-pause-caption"));
        stopButton.setDebugId(this.debugIdRegistry.getDebugId(
                MessageCodePrefixRegistry.Module.editingprocesspanel,
                "10-stopButton"));
        stopButton.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final ClickEvent aEvent) {
                DefaultEditingProcessPanel.this.stopButtonClicked();
            }
        });
        markAsCompleted =
                new Button(
                        TM.get("editingprocesspanel.3-mark-as-completed-caption"));
        markAsCompleted.setDebugId(this.debugIdRegistry.getDebugId(
                MessageCodePrefixRegistry.Module.editingprocesspanel,
                "11-markAsCompleted"));
        markAsCompleted.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final ClickEvent aEvent) {
                DefaultEditingProcessPanel.this.markAsCompletedButtonClicked();

            }
        });
        cancelButton =
                new Button(
                        TM.get("editingprocesspanel.4-mark-as-completed-caption"));
        cancelButton.setDebugId(this.debugIdRegistry.getDebugId(
                MessageCodePrefixRegistry.Module.editingprocesspanel,
                "12-cancelButton"));
        cancelButton.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final ClickEvent aEvent) {
                DefaultEditingProcessPanel.this.cancelButtonClicked();
            }
        });

        reactivateButton =
                new Button(TM.get("editingprocesspanel.6-reactivate-caption"));
        reactivateButton.setDebugId(this.debugIdRegistry.getDebugId(
                MessageCodePrefixRegistry.Module.editingprocesspanel,
                "13-reactivateButton"));
        reactivateButton.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final ClickEvent aEvent) {
                DefaultEditingProcessPanel.this.reactivateButtonClicked();
            }
        });

        deleteButton =
                new Button(
                        TM.get("editingprocesspanel.5-mark-as-completed-caption"));
        deleteButton.setDebugId(this.debugIdRegistry.getDebugId(
                MessageCodePrefixRegistry.Module.editingprocesspanel,
                "14-deleteButton"));
        deleteButton.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final ClickEvent aEvent) {
                DefaultEditingProcessPanel.this.deleteButtonClicked();
            }
        });

        saveButton = new Button(TM.get("editingprocesspanel.12-save"));
        saveButton.setDebugId(this.debugIdRegistry.getDebugId(
                MessageCodePrefixRegistry.Module.editingprocesspanel,
                "15-saveButton"));
        saveButton.setImmediate(true);
        saveButton.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final ClickEvent aEvent) {
                DefaultEditingProcessPanel.this.saveButtonClicked();
            }
        });

        buttonPanel.addComponent(startButton, 0, 0);
        buttonPanel.addComponent(stopButton, 1, 0);
        buttonPanel.addComponent(markAsCompleted, 0, 1);
        buttonPanel.addComponent(cancelButton, 1, 1);
        buttonPanel.addComponent(deleteButton, 0, 2);
        buttonPanel.addComponent(saveButton, 1, 2);
        return buttonPanel;
    }

    private void createEffortEstimateControls(final GridLayout aGrid) {
        final Label minEffortLabel =
                new Label(TM.get("editingprocesspanel.17-min-effort-label"));
        minEffortTextBox = new TextField();
        minEffortTextBox.setDebugId(this.debugIdRegistry.getDebugId(
                MessageCodePrefixRegistry.Module.editingprocesspanel,
                "20-minEffortTextBox"));
        minEffortTextBox.setImmediate(true);

        minTimeUnitComboBox = new ComboBox();
        minTimeUnitComboBox.setDebugId(this.debugIdRegistry.getDebugId(
                MessageCodePrefixRegistry.Module.editingprocesspanel,
                "21-minTimeUnitComboBox"));
        minTimeUnitComboBox.setImmediate(true);

        final Label maxEffortLabel =
                new Label(TM.get("editingprocesspanel.18-max-effort-label"));
        maxEffortTextBox = new TextField();
        maxEffortTextBox.setDebugId(this.debugIdRegistry.getDebugId(
                MessageCodePrefixRegistry.Module.editingprocesspanel,
                "22-maxEffortTextBox"));
        maxEffortTextBox.setImmediate(true);

        maxTimeUnitComboBox = new ComboBox();
        maxTimeUnitComboBox.setDebugId(this.debugIdRegistry.getDebugId(
                MessageCodePrefixRegistry.Module.editingprocesspanel,
                "23-maxTimeUnitComboBox"));
        maxTimeUnitComboBox.setImmediate(true);

        aGrid.addComponent(minEffortLabel, 0, MIN_EFFORT_ROW);
        aGrid.addComponent(minEffortTextBox, 1, MIN_EFFORT_ROW);
        aGrid.addComponent(minTimeUnitComboBox, 2, MIN_EFFORT_ROW);

        aGrid.addComponent(maxEffortLabel, 0, MAX_EFFORT_ROW);
        aGrid.addComponent(maxEffortTextBox, 1, MAX_EFFORT_ROW);
        aGrid.addComponent(maxTimeUnitComboBox, 2, MAX_EFFORT_ROW);

        final List<String> timeUnits = new LinkedList<String>();

        timeUnits.add(TM.get("editingprocesspanel.19-time-unit"));
        timeUnits.add(TM.get("editingprocesspanel.20-time-unit"));
        timeUnits.add(TM.get("editingprocesspanel.21-time-unit"));

        for (final String timeUnit : timeUnits) {
            minTimeUnitComboBox.addItem(timeUnit);
            maxTimeUnitComboBox.addItem(timeUnit);
        }
    }

    private void createHandOffControls(final GridLayout aGrid) {
        final Label controlSubjectLabel =
                new Label(TM.get("editingprocesspanel.7-control-subject"));
        controlSubjectLabel.setDebugId(this.debugIdRegistry.getDebugId(
                MessageCodePrefixRegistry.Module.editingprocesspanel,
                "4-controlSubjectLabel"));

        controlSubjectComboBox = new ComboBox();
        controlSubjectComboBox.setDebugId(this.debugIdRegistry.getDebugId(
                MessageCodePrefixRegistry.Module.editingprocesspanel,
                "5-controlSubjectComboBox"));
        controlSubjectComboBox
                .setItemCaptionMode(AbstractSelect.ITEM_CAPTION_MODE_PROPERTY);
        controlSubjectComboBox
                .setItemCaptionPropertyId(ABBREVIATED_PROCESS_NAME);
        controlSubjectComboBox.addContainerProperty(ABBREVIATED_PROCESS_NAME,
                String.class, null);

        fillWorkerComboBox();

        handoffButton = new Button(TM.get("editingprocesspanel.11-handoff"));
        handoffButton.setDebugId(this.debugIdRegistry.getDebugId(
                MessageCodePrefixRegistry.Module.editingprocesspanel,
                "6-handoffButton"));
        handoffButton.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final ClickEvent aEvent) {
                handoffButtonClicked();
            }
        });

        aGrid.addComponent(controlSubjectLabel, 0, 1);
        aGrid.addComponent(controlSubjectComboBox, 1, 1);
        aGrid.addComponent(handoffButton, 2, 1);
    }

    private void fillWorkerComboBox() {
        controlSubjectComboBox.removeAllItems();
        final List<Worker> workers = persistence.getAllWorkers();

        for (final Worker worker : workers) {
            final Item item = controlSubjectComboBox.addItem(worker);

            final String template =
                    TM.get("editingprocesspanel.14-resource-combobox-item");
            final String resourceText =
                    template.replace("${abbreviation}",
                            worker.getAbbreviation())
                            .replace("${firstName}", worker.getFirstName())
                            .replace("${middleName}", worker.getMiddleName())
                            .replace("${surname}", worker.getSurname());

            final String abbreviatedName =
                    StringUtils.abbreviate(resourceText, 30);

            item.getItemProperty(ABBREVIATED_PROCESS_NAME).setValue(
                    abbreviatedName);
        }
    }

    private void createPriorityControls(final GridLayout aGrid) {
        final Label priorityLabel =
                new Label(TM.get("editingprocesspanel.22-priority-caption"));
        priorityTextBox = new TextField();
        priorityTextBox.setImmediate(true);
        priorityTextBox.setDebugId(this.debugIdRegistry.getDebugId(
                MessageCodePrefixRegistry.Module.editingprocesspanel,
                "7-priorityTextBox"));

        aGrid.addComponent(priorityLabel, 0, PRIORITY_ROW);
        aGrid.addComponent(priorityTextBox, 1, PRIORITY_ROW);
    }

    private void createTypeControls(final GridLayout aGrid) {
        final Label typeLabel = new Label(TM.get("editingprocesspanel.8-type"));
        typeComboBox = new ComboBox();
        typeComboBox.setDebugId(this.debugIdRegistry.getDebugId(
                MessageCodePrefixRegistry.Module.editingprocesspanel,
                "8-typeComboBox"));

        aGrid.addComponent(typeLabel, 0, 2);
        aGrid.addComponent(typeComboBox, 1, 2);

        typeComboBox.addItem(TYPE_PROCESS);
        typeComboBox.addItem(TYPE_MILESTONE);

        typeComboBox.addListener(new Property.ValueChangeListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void valueChange(final ValueChangeEvent aEvent) {
                final ProcessType curProcessType = getProcessType();

                if (ProcessType.MILE_STONE.equals(curProcessType)
                        || ProcessType.UNKNOWN.equals(curProcessType)) {
                    enableEffortControls(false);
                } else {
                    enableEffortControls(true);
                }
            }
        });
    }

    private boolean getCancelButtonState(final ProcessState aProcessState) {
        return (ProcessState.IS_BEING_ATTAINED.equals(aProcessState)
                || ProcessState.SCHEDULED.equals(aProcessState) || ProcessState.WELL_DEFINED
                .equals(aProcessState));
    }

    private double getEffortInMinutes(final double aEffortInUnit,
            final ComboBox aUnitComboBox) {
        return convertEffort(aEffortInUnit, aUnitComboBox, 1.0,
                MINUTES_IN_HOUR, MINUTES_IN_DAY);
    }

    private double convertEffort(final double aEffortInUnit,
            final ComboBox aUnitComboBox, final double aConversionFactorMinute,
            final double aConversionFactorHour,
            final double aConversionFactorDay) {
        double conversionFactor = 0.;
        final String unit = (String) aUnitComboBox.getValue();

        if (EFFORT_UNIT_MINUTE.equals(unit)) {
            conversionFactor = aConversionFactorMinute;
        } else if (EFFORT_UNIT_HOUR.equals(unit)) {
            conversionFactor = aConversionFactorHour;
        } else if (EFFORT_UNIT_DAY.equals(unit)) {
            conversionFactor = aConversionFactorDay;
        }

        return aEffortInUnit * conversionFactor;
    }

    private boolean getMarkAsCompletedState(final ProcessState aProcessState) {
        return ProcessState.IS_BEING_ATTAINED.equals(aProcessState);
    }

    private ProcessType getProcessType() {
        final Object typeInComboBox = typeComboBox.getValue();

        if (TYPE_PROCESS.equals(typeInComboBox)) {
            return ProcessType.GOAL;
        } else if (TYPE_PROCESS.equals(typeInComboBox)) {
            return ProcessType.MILE_STONE;
        } else {
            return ProcessType.UNKNOWN;
        }
    }

    private boolean getReactivateButtonState(final ProcessState aState) {
        return (ProcessState.ATTAINED.equals(aState) || ProcessState.CANCELLED
                .equals(aState));
    }

    private boolean getStartButtonState(final ProcessState aState) {
        return (ProcessState.REPORTED.equals(aState)
                || ProcessState.SCHEDULED.equals(aState) || ProcessState.WELL_DEFINED
                .equals(aState));

    }

    private String getStateCaption(final Object aProcessId) {
        if (process == null) {
            return TM.get("editingprocesspanel.27-caption-invalid-process",
                    aProcessId);
        } else if (!ProcessState.DELETED.equals(process.getState())) {
            return TM.get("editingprocesspanel.13-caption", aProcessId,
                    abbreviateName(process.getName()));
        } else if (ProcessState.DELETED.equals(process.getState())) {
            return TM.get("editingprocesspanel.16-caption-deleted-process",
                    aProcessId, abbreviateName(process.getName()));
        } else if (ProcessState.ATTAINED.equals(process.getState())) {
            return TM.get("editingprocesspanel.26-caption-goal-attained",
                    aProcessId, abbreviateName(process.getName()));
        } else {
            return TM.get("editingprocesspanel.27-caption-invalid-process",
                    aProcessId);
        }
    }

    private boolean getStopButtonState(final ProcessState aProcessState) {
        return ProcessState.IS_BEING_ATTAINED.equals(aProcessState);
    }

    private Worker getWorker(final ControlProcess aProcess) {
        final List<ResourceAllocation> allocations =
                aProcess.getResourceAllocations();
        final Worker worker;

        LOGGER.debug("allocations: " + allocations);
        LOGGER.debug("(allocations != null): " + (allocations != null));

        if ((allocations != null) && (!allocations.isEmpty())) {
            final ResourceAllocation allocation = allocations.iterator().next();

            final Resource resource = allocation.getResource();
            LOGGER.debug("allocation.getResource(): " + resource);

            if (resource != null) {
                LOGGER.debug("allocation.getResource().getClass(): "
                        + resource.getClass());
                LOGGER.debug("resource.getAbbreviation(): "
                        + resource.getAbbreviation());
                LOGGER.debug("(resource instanceof Worker): "
                        + (resource instanceof Worker));

            }

            // worker = (Worker)allocation.getResource();
            worker = null;
        } else {
            worker = null;
        }
        return worker;
    }

    private void initSaveErrorMessagesByValidationResults() {
        saveErrorMessagesByValidationResults =
                new HashMap<SaveValidationResult, String>();

        saveErrorMessagesByValidationResults.put(
                SaveValidationResult.MIN_EFFORT_EMPTY, VMSG_MIN_EFFORT_EMPTY);
        saveErrorMessagesByValidationResults.put(
                SaveValidationResult.MAX_EFFORT, VMSG_MAX_EFFORT);
        saveErrorMessagesByValidationResults.put(
                SaveValidationResult.PRIORITY_NOT_NUMERIC,
                VMSG_PRIORITY_NOT_NUMERIC);
        saveErrorMessagesByValidationResults.put(
                SaveValidationResult.PRIORITY_EMPTY, VMSG_PRIORITY_EMPTY);
        saveErrorMessagesByValidationResults.put(
                SaveValidationResult.PRIORITY_TOO_SMALL,
                VMSG_PRIORITY_TOO_SMALL);
        saveErrorMessagesByValidationResults.put(
                SaveValidationResult.PRIORITY_TOO_LARGE,
                VMSG_PRIORITY_TOO_LARGE);
        saveErrorMessagesByValidationResults.put(
                SaveValidationResult.MIN_EFFORT_NOT_NUMERIC,
                VMSG_MIN_EFFORT_NOT_NUMERIC);
        saveErrorMessagesByValidationResults.put(
                SaveValidationResult.MAX_EFFORT_EMPTY, VMSG_MAX_EFFORT_EMPTY);
        saveErrorMessagesByValidationResults.put(
                SaveValidationResult.MAX_EFFORT_NOT_NUMERIC,
                VMSG_MAX_EFFORT_NOT_NUMERIC);
        saveErrorMessagesByValidationResults.put(
                SaveValidationResult.MIN_EFFORT_LARGER_THAN_MAX_EFFORT,
                VMSG_MIN_EFFORT_LARGER_THAN_MAX_EFFORT);
        saveErrorMessagesByValidationResults.put(
                SaveValidationResult.MIN_EFFORT_NEGATIVE,
                VMSG_MIN_EFFORT_NEGATIVE);
        saveErrorMessagesByValidationResults.put(
                SaveValidationResult.MAX_EFFORT_NEGATIVE,
                VMSG_MAX_EFFORT_NEGATIVE);
    }

    private void setButtonEnabledness() {
        if (process != null) {
            startButton.setEnabled(getStartButtonState(process.getState()));
            stopButton.setEnabled(getStopButtonState(process.getState()));
            markAsCompleted.setEnabled(getMarkAsCompletedState(process
                    .getState()));
            cancelButton.setEnabled(getCancelButtonState(process.getState()));
            reactivateButton.setEnabled(getReactivateButtonState(process
                    .getState()));

            final boolean notDeleted =
                    !ProcessState.DELETED.equals(process.getState());

            deleteButton.setEnabled(notDeleted);
            handoffButton.setEnabled(notDeleted);

            changesToSaveAvailable = false;
            saveButton.setEnabled(changesToSaveAvailable);
        } else {
            final List<Button> buttons = new LinkedList<Button>();

            buttons.add(handoffButton);
            buttons.add(cancelButton);
            buttons.add(deleteButton);
            buttons.add(markAsCompleted);
            buttons.add(reactivateButton);
            buttons.add(saveButton);
            buttons.add(startButton);
            buttons.add(stopButton);

            for (final Button button : buttons) {
                button.setEnabled(false);
            }
        }
    }

    private void setupSaveRelevantListeners() {
        LOGGER.debug("setupSaveRelevantListeners, this.saveButton="
                + saveButton);

        final SaveButtonRelevantListener saveRelevantListener =
                new SaveButtonRelevantListener(saveButton);
        final List<AbstractField> saveRelevantComponents =
                new LinkedList<AbstractField>();

        saveRelevantComponents.add(processNameTextArea);
        saveRelevantComponents.add(typeComboBox);
        saveRelevantComponents.add(minEffortTextBox);
        saveRelevantComponents.add(minTimeUnitComboBox);
        saveRelevantComponents.add(maxEffortTextBox);
        saveRelevantComponents.add(maxTimeUnitComboBox);
        saveRelevantComponents.add(priorityTextBox);
        saveRelevantComponents.add(typeComboBox);

        for (final AbstractField curComponent : saveRelevantComponents) {
            curComponent.addListener(saveRelevantListener);
        }
    }

    private SaveValidationResult validateEffort() {
        final String minEffortAsString = (String) minEffortTextBox.getValue();
        final String maxEffortAsString = (String) maxEffortTextBox.getValue();

        if (StringUtils.isEmpty(minEffortAsString)) {
            return SaveValidationResult.MIN_EFFORT_EMPTY;
        }

        if (StringUtils.isEmpty(maxEffortAsString)) {
            return SaveValidationResult.MAX_EFFORT_EMPTY;
        }

        double minEffort;
        double maxEffort;

        try {
            minEffort = Double.parseDouble(minEffortAsString);
        } catch (final NumberFormatException exception) {
            return SaveValidationResult.MIN_EFFORT_NOT_NUMERIC;
        }

        try {
            maxEffort = Double.parseDouble(maxEffortAsString);
        } catch (final NumberFormatException exception) {
            return SaveValidationResult.MAX_EFFORT_NOT_NUMERIC;
        }

        if (minEffort < 0) {
            return SaveValidationResult.MIN_EFFORT_NEGATIVE;
        }

        if (maxEffort < 0) {
            return SaveValidationResult.MAX_EFFORT_NEGATIVE;
        }

        final double minEffortInMinutes =
                getEffortInMinutes(minEffort, minTimeUnitComboBox);
        final double maxEffortInMinutes =
                getEffortInMinutes(maxEffort, maxTimeUnitComboBox);

        if (minEffortInMinutes > maxEffortInMinutes) {
            return SaveValidationResult.MIN_EFFORT_LARGER_THAN_MAX_EFFORT;
        }

        return SaveValidationResult.EVERYTHING_OK;
    }

    private SaveValidationResult validatePriority() {
        final String priorityAsString = (String) priorityTextBox.getValue();

        if (StringUtils.isEmpty(priorityAsString)) {
            return SaveValidationResult.PRIORITY_EMPTY;
        }

        if (!StringUtils.isNumeric(priorityAsString)) {
            return SaveValidationResult.PRIORITY_NOT_NUMERIC;
        }

        int priority;

        try {
            priority = Integer.parseInt(priorityAsString);
        } catch (final NumberFormatException exception) {
            return SaveValidationResult.PRIORITY_NOT_NUMERIC;
        }

        if (priority < ControlProcess.LOWEST_PRIORITY) {
            return SaveValidationResult.PRIORITY_TOO_SMALL;
        }

        if (priority > ControlProcess.HIGHEST_PRIORITY) {
            return SaveValidationResult.PRIORITY_TOO_LARGE;
        }

        return SaveValidationResult.EVERYTHING_OK;
    }

    private SaveValidationResult validateSaveRelevantInputs() {
        SaveValidationResult result;
        // Validate priority
        result = validatePriority();

        if (SaveValidationResult.EVERYTHING_OK.equals(result)) {
            result = validateEffort();
        }

        return result;
    }

    @Override
    public void workerAdded(final WorkerAddedMessage aMessage) {
        fillWorkerComboBox();
    }
}

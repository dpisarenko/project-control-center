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

package at.silverstrike.pcc.impl.eventeditingpanel;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.inject.Injector;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Button;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import at.silverstrike.pcc.api.debugids.PccDebugIdRegistry;
import at.silverstrike.pcc.api.dependencieseditingpanelcontroller.DependenciesEditingPanelController;
import at.silverstrike.pcc.api.dependencieseditingpanelcontroller.DependenciesEditingPanelControllerFactory;
import at.silverstrike.pcc.api.eventeditingpanel.EventEditingPanel;
import at.silverstrike.pcc.api.eventeditingpanelcontroller.EventEditingPanelController;
import at.silverstrike.pcc.api.pcc.PccFunctionalBlock;
import eu.livotov.tpt.i18n.TM;

class DefaultEventEditingPanel extends Panel implements EventEditingPanel,
        ClickListener {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultEventEditingPanel.class);
    private static final long serialVersionUID = 1L;

    public static final Object PROJECT_PROPERTY_NAME = "name";

    private static final int PROCESS_NAME_TEXT_FIELD_ROWS = 5;

    private static final String SAVE_EVENT_BUTTON = "029.001";
    private static final String DELETE_EVENT_BUTTON = "029.002";
    private transient at.silverstrike.pcc.api.model.Event event;

    private transient Injector injector;
    private transient EventEditingPanelController controller;
    private transient PccDebugIdRegistry debugIdRegistry;
    private TextField eventNameTextField;
    private InlineDateField startDate;
    private InlineDateField finishDate;
    private TextField placeTextField;
    private DependenciesEditingPanelController dependenciesPanelController;

    public TextField getTaskNameTextField() {
        return eventNameTextField;
    }

    @Override
    public void setInjector(final Injector aInjector) {
        if (aInjector != null) {
            injector = aInjector;
            this.controller = this.injector
                    .getInstance(EventEditingPanelController.class);
            this.controller.setInjector(this.injector);
            this.debugIdRegistry = this.injector
                    .getInstance(PccDebugIdRegistry.class);
        }
    }

    @Override
    public void initGui() {
        final Label taskLabel = new Label(
                TM.get("eventeditingpanel.1-label-event"));
        taskLabel.setContentMode(Label.CONTENT_TEXT);
        this.addComponent(taskLabel);

        final HorizontalLayout buttonsTaskLayout = new HorizontalLayout();
        buttonsTaskLayout.setSpacing(true);

        final Button saveButton = new Button(
                TM.get("eventeditingpanel.2-button-save"));
        saveButton.setDebugId(this.debugIdRegistry.getDebugId(
                PccFunctionalBlock.eventeditingpanel, "1-button-save"));
        saveButton.addListener((ClickListener) this); // react to clicks
        buttonsTaskLayout.addComponent(saveButton);

        final Button deleteButton = new Button(
                TM.get("eventeditingpanel.3-button-delete"));
        deleteButton.setDebugId(this.debugIdRegistry.getDebugId(
                PccFunctionalBlock.eventeditingpanel, "2-button-delete"));
        deleteButton.addListener((ClickListener) this); // react to clicks
        buttonsTaskLayout.addComponent(deleteButton);

        this.addComponent(buttonsTaskLayout);

        eventNameTextField = new TextField();
        eventNameTextField.setWidth("100%");
        eventNameTextField.setRows(PROCESS_NAME_TEXT_FIELD_ROWS);
        this.addComponent(eventNameTextField);

        final HorizontalLayout placeLayout = getPlacePanel();
        this.addComponent(placeLayout);

        final HorizontalLayout datesLayout = getIntervalDatesPanel();
        this.addComponent(datesLayout);

        final DependenciesEditingPanelControllerFactory factory =
                this.injector
                        .getInstance(DependenciesEditingPanelControllerFactory.class);
        dependenciesPanelController = factory.create();
        dependenciesPanelController.setInjector(this.injector);
        this.addComponent(dependenciesPanelController.initGui());
    }

    private HorizontalLayout getPlacePanel() {
        final HorizontalLayout placeLayout = new HorizontalLayout();
        placeLayout.setSpacing(true);

        final Label placeLabel = new Label(
                TM.get("eventeditingpanel.6-label-place"));
        placeLabel.setContentMode(Label.CONTENT_TEXT);
        placeLayout.addComponent(placeLabel);

        placeTextField = new TextField();
        placeLayout.addComponent(placeTextField);

        return placeLayout;
    }

    private HorizontalLayout getIntervalDatesPanel() {
        final HorizontalLayout datesLayout = new HorizontalLayout();
        datesLayout.setSpacing(true);

        startDate = new InlineDateField(
                TM.get("eventeditingpanel.7-label-start"));

        // Set the value of the PopupDateField to current date
        startDate.setValue(new java.util.Date());

        // Set the correct resolution
        startDate.setResolution(InlineDateField.RESOLUTION_MIN);
        startDate.setImmediate(true);

        datesLayout.addComponent(startDate);

        finishDate = new InlineDateField(
                TM.get("eventeditingpanel.8-label-finish"));

        // Set the value of the PopupDateField to current date
        finishDate.setValue(new java.util.Date());

        // Set the correct resolution
        finishDate.setResolution(PopupDateField.RESOLUTION_MIN);

        datesLayout.addComponent(finishDate);

        return datesLayout;
    }

    /*
     * Shows a notification when a button is clicked.
     */
    public void buttonClick(final ClickEvent aEvent) {
        final String debugId = aEvent.getButton().getDebugId();

        LOGGER.debug(
                "at.silverstrike.pcc.impl.eventeditingpanel.DefaultEventEditingPanel.buttonClick(ClickEvent), debugId: {}",
                debugId);

        if (SAVE_EVENT_BUTTON.equals(debugId)) {
            if (this.event != null) {
                setThisEventObject();
                this.controller.saveEvent(this.event);
            }
        } else if (DELETE_EVENT_BUTTON.equals(debugId)) {
            controller.deleteEvent(this.event);
        }
    }

    private void setThisEventObject() {
        this.event.setName((String) this.eventNameTextField.getValue());
        this.event.setPlace((String) this.placeTextField.getValue());
        LOGGER.debug("Event Start Date Time is:" + this.startDate.getValue());
        this.event.setStartDateTime((Date) this.startDate.getValue());
        this.event.setEndDateTime((Date) this.finishDate.getValue());
    }

    @Override
    public Panel toPanel() {
        return this;
    }

    @Override
    public void setEvent(at.silverstrike.pcc.api.model.Event aEvent) {
        this.event = aEvent;
        if (this.event != null) {
            this.eventNameTextField.setValue(this.event.getName());
            String place = this.event.getPlace();
            if (place != null) {
                this.placeTextField.setValue(place);
            } else {
                this.placeTextField.setValue("");
            }
            this.startDate.setValue(this.event.getStartDateTime());
            this.finishDate.setValue(this.event.getEndDateTime());
            
            this.dependenciesPanelController.setData(this.event);
        } else {
            clearIntervalDatesPanel();
        }

    }

    private void clearIntervalDatesPanel() {
        this.eventNameTextField.setValue(null);
        this.placeTextField.setValue("");
        this.startDate.setValue(new Date());
        this.finishDate.setValue(new Date());
    }
}
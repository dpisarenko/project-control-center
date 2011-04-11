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

import at.silverstrike.pcc.api.conventions.FunctionalBlock;
import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.debugids.DebugIdRegistry;
import at.silverstrike.pcc.api.eventeditingpanel.EventEditingPanel;
import at.silverstrike.pcc.api.eventeditingpanelcontroller.EventEditingPanelController;
import at.silverstrike.pcc.api.testtablecreator.TestTableCreator;
import eu.livotov.tpt.TPTApplication;
import eu.livotov.tpt.gui.dialogs.OptionDialog;
import eu.livotov.tpt.i18n.TM;

class DefaultEventEditingPanel extends Panel implements EventEditingPanel,
		ClickListener {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(DefaultEventEditingPanel.class);
	private static final long serialVersionUID = 1L;
	
	public static final Object PROJECT_PROPERTY_NAME = "name";

	private static final int PROCESS_NAME_TEXT_FIELD_ROWS = 5;

	private static final String[] TEST_COLUMN_NAMES = new String[] { "�",
			"Project", "Name" };
	private static final List<String[]> TEST_TABLE_DATA = Arrays.asList(
			new String[] { "1.1", "Project 1", "Task 1" }, new String[] {
					"2.1", "Project 4", "Task 5" });

	private static final String SAVE_EVENT_BUTTON = "029.001";
	private static final String DELETE_EVENT_BUTTON = "029.002";
	private static final String DEPENDENCIES_BUTTON = "029.XYZ";
	private transient at.silverstrike.pcc.api.model.Event event = null;

	private transient Injector injector;
	private transient EventEditingPanelController controller;
	private transient DebugIdRegistry debugIdRegistry;
	private TextField eventNameTextField;

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
					.getInstance(DebugIdRegistry.class);
		}
	}

	@Override
	public void initGui() {
		final Panel verticalLayoutRight = new Panel();

		final Label taskLabel = new Label(
				TM.get("eventeditingpanel.1-label-event"));
		taskLabel.setContentMode(Label.CONTENT_TEXT);
		verticalLayoutRight.addComponent(taskLabel);

		final HorizontalLayout buttonsTaskLayout = new HorizontalLayout();
		buttonsTaskLayout.setSpacing(true);

		final Button saveButton = new Button(
				TM.get("eventeditingpanel.2-button-save"));
		saveButton.setDebugId(this.debugIdRegistry.getDebugId(
				FunctionalBlock.eventeditingpanel, "1-button-save"));
		saveButton.addListener(this); // react to clicks
		buttonsTaskLayout.addComponent(saveButton);

		final Button deleteButton = new Button(
				TM.get("eventeditingpanel.3-button-delete"));
		deleteButton.setDebugId(this.debugIdRegistry.getDebugId(
				FunctionalBlock.eventeditingpanel, "2-button-delete"));
		deleteButton.addListener(this); // react to clicks
		buttonsTaskLayout.addComponent(deleteButton);

		verticalLayoutRight.addComponent(buttonsTaskLayout);

		eventNameTextField = new TextField();
		eventNameTextField.setWidth("100%");
		eventNameTextField.setRows(PROCESS_NAME_TEXT_FIELD_ROWS);
		verticalLayoutRight.addComponent(eventNameTextField);

		final HorizontalLayout placeLayout = getPlacePanel();
		verticalLayoutRight.addComponent(placeLayout);

		final HorizontalLayout datesLayout = getIntervalDatesPanel();
		verticalLayoutRight.addComponent(datesLayout);

		final HorizontalLayout dependLayout = new HorizontalLayout();
		dependLayout.setSpacing(true);

		final Label dependLabel = new Label(
				TM.get("eventeditingpanel.4-label-dependencies"));
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

		final Label placeLabel = new Label(
				TM.get("eventeditingpanel.6-label-place"));
		placeLabel.setContentMode(Label.CONTENT_TEXT);
		placeLayout.addComponent(placeLabel);

		final TextField placeTextField = new TextField();
		placeLayout.addComponent(placeTextField);

		return placeLayout;
	}

	private HorizontalLayout getIntervalDatesPanel() {
		final HorizontalLayout datesLayout = new HorizontalLayout();
		datesLayout.setSpacing(true);

		final InlineDateField startDate = new InlineDateField(
				TM.get("eventeditingpanel.7-label-start"));

		// Set the value of the PopupDateField to current date
		startDate.setValue(new java.util.Date());

		// Set the correct resolution
		startDate.setResolution(InlineDateField.RESOLUTION_MIN);

		datesLayout.addComponent(startDate);

		final InlineDateField finishDate = new InlineDateField(
				TM.get("eventeditingpanel.8-label-finish"));

		// Set the value of the PopupDateField to current date
		finishDate.setValue(new java.util.Date());

		// Set the correct resolution
		finishDate.setResolution(InlineDateField.RESOLUTION_MIN);

		datesLayout.addComponent(finishDate);

		return datesLayout;
	}

	private Button createDependEditButton() {
		final Button dependEditButton = new Button(
				TM.get("eventeditingpanel.5-button-edit"));
		dependEditButton.addListener(new DependenciesButtonClickListener(
				controller));
		return dependEditButton;
	}

	private Table createTestTable() {
		final TestTableCreator creator = this.injector
				.getInstance(TestTableCreator.class);
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
                "at.silverstrike.pcc.impl.eventeditingpanel.DefaultEventEditingPanel.buttonClick(ClickEvent), debugId: {}",
                debugId);

        if (SAVE_EVENT_BUTTON.equals(debugId)) {
            final OptionDialog dialog =
                    new OptionDialog(TPTApplication.getCurrentApplication());

            dialog.showConfirmationDialog("Event", "Debug ID: " + debugId
                    + ", this.event: " + this.event, null);

            if (this.event != null) {
                this.event.setName((String) this.eventNameTextField.getValue());
                this.controller.saveEvent(this.event);
            }
        } else if (DELETE_EVENT_BUTTON.equals(debugId)) {
            controller.deleteEvent(this.event);
        } else if (DEPENDENCIES_BUTTON.equals(debugId)) {
            //letUserEnterDependencies();
        }
	}

	@Override
	public Panel toPanel() {
		return this;
	}


	@Override
	public void setEvent(at.silverstrike.pcc.api.model.Event aEvent) {
        this.event = aEvent;
        if (this.event != null) {
            eventNameTextField.setValue(this.event.getName());
        } else {
            eventNameTextField.setValue("");
        }
	
	}

}
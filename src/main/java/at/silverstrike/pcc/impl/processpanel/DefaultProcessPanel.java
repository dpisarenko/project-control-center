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

package at.silverstrike.pcc.impl.processpanel;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import at.silverstrike.pcc.api.editingprocesspanel.EditingProcessPanel;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.processpanel.ProcessPanel;
import at.silverstrike.pcc.api.processpanel.ProcessPanelListener;

import com.google.inject.Injector;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window.Notification;

import eu.livotov.tpt.i18n.TM;

class DefaultProcessPanel extends Panel implements ProcessPanel {
    private static final int PROCESS_NAME_TEXT_FIELD_ROWS = 5;
    private static final int PROCESS_NAME_TEXT_FIELD_COLUMNS = 30;
    private static final int MAX_PROCESS_NAME_LENGTH = 50;
    private static final String STRING_3 =
            TM.get("processpanel.3-processNameTextField-prompt");
    private static final String STRING_4 =
            TM.get("processpanel.4-addProcessButtonClick");
    private static final String STRING_2 =
            TM.get("processpanel.2-name-caption");
    private static final String STRING_1 = TM.get("processpanel.1-id-caption");
    private static final String STRING_5 =
            TM.get("processpanel.5-parent-process-label-caption");
    private static final String PROCESS_NAME_PLACE_HOLDER = "${process-name}";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_ID = "id";
    private static final long serialVersionUID = 1L;
    private Table table = new Table();
    private transient Persistence persistence = null;
    private TextField processNameTextField = new TextField();
    private Button addProcessButton = new Button();
    private EditingProcessPanel editingProcessPanel = null;
    private Long parentProcessId;
    private ProcessPanelListener listener;
    private Label parentProcessLabel = null;

    public DefaultProcessPanel() {
        super();

    }

    protected void tableSelectionChanged() {
        final Object selectedItemId = this.table.getValue();
        this.editingProcessPanel.setData(selectedItemId);
    }

    protected void addProcessButtonClick() {
        final String processName =
                (String) this.processNameTextField.getValue();

        if (StringUtils.isBlank(processName)) {

            getWindow().showNotification(STRING_4,
                    Notification.TYPE_WARNING_MESSAGE);
            return;
        }

        this.persistence.createSubTask(processName, this.parentProcessId);
        this.updateTable();
        this.processNameTextField.setValue("");

        if (this.listener != null) {
            this.listener.taskAdded();
        }
    }

    private void updateTable() {
        setProcessesToShow(this.persistence.getChildTasks(this.parentProcessId));

    }

    @Override
    public void setProcessesToShow(final List<Task> aProcesses) {
        this.table.removeAllItems();

        for (Task process : aProcesses) {
            this.table.addItem(new Object[] { process.getId(),
                    process.getName() }, process.getId());
        }
    }

    @Override
    public Panel toPanel() {
        return this;
    }

    @Override
    public void setInjector(final Injector aInjector) {
        if (aInjector != null) {
            this.persistence = aInjector.getInstance(Persistence.class);
        }
    }

    @Override
    public void setEditingProcessPanel(final EditingProcessPanel aPanel) {
        this.editingProcessPanel = aPanel;
    }

    @Override
    public void initGui() {
//        this.setWidth("500px");
//        this.setHeight("400px");

        this.setSizeFull();
        
        parentProcessLabel = new Label("");

        this.addComponent(parentProcessLabel);

        final HorizontalLayout processPanel = new HorizontalLayout();

        processNameTextField.setInputPrompt(STRING_3);
        processNameTextField.setColumns(PROCESS_NAME_TEXT_FIELD_COLUMNS);
        processNameTextField.setRows(PROCESS_NAME_TEXT_FIELD_ROWS);

        processPanel.addComponent(processNameTextField);

        this.addProcessButton.setCaption(" <- ");

        this.addProcessButton.setClickShortcut(KeyCode.ENTER);

        this.addProcessButton.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final ClickEvent aEvent) {
                addProcessButtonClick();
            }
        });

        processPanel.addComponent(this.addProcessButton);

        addComponent(processPanel);

        table.addContainerProperty(COLUMN_ID, Long.class, null);
        table.addContainerProperty(COLUMN_NAME, String.class, "");

        table.setVisibleColumns(new String[] { COLUMN_ID, COLUMN_NAME });

        table.setColumnHeader(COLUMN_ID, STRING_1);
        table.setColumnHeader(COLUMN_NAME, STRING_2);

        table.setSizeFull();

        table.setSelectable(true);
        table.setMultiSelect(false);
        table.setImmediate(true); // react at once when something is selected

        table.addListener(new Property.ValueChangeListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void valueChange(final ValueChangeEvent aEvent) {
                tableSelectionChanged();
            }
        });

        addComponent(table);
    }

    @Override
    public void setParentProcessId(final Long aId) {
        this.parentProcessId = aId;

        if (this.parentProcessId != null) {
            final Task process =
                    this.persistence.getTask(this.parentProcessId);

            if (process != null) {
                final String abbreviatedName =
                        StringUtils.abbreviate(process.getName(),
                                MAX_PROCESS_NAME_LENGTH);
                this.parentProcessLabel.setCaption(STRING_5.replace(
                        PROCESS_NAME_PLACE_HOLDER, abbreviatedName));
            } else {
                this.parentProcessLabel.setCaption("");
            }
        } else {
            this.parentProcessLabel.setCaption("");
        }
    }

    @Override
    public void setProcessPanelListener(final ProcessPanelListener aListener) {
        this.listener = aListener;
    }
}

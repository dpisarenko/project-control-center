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

package at.silverstrike.pcc.impl.estimatedcompletiontimespanel;

import java.util.Date;
import java.util.List;

import com.google.inject.Injector;
import com.vaadin.ui.Button;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import eu.livotov.tpt.i18n.TM;

import at.silverstrike.pcc.api.estimatedcompletiontimespanel.EstimatedCompletionTimesPanel;
import at.silverstrike.pcc.api.model.ControlProcess;
import at.silverstrike.pcc.api.persistence.Persistence;

/**
 * @author Dmitri Pisarenko
 * 
 */
class DefaultEstimatedCompletionTimesPanel extends Panel implements
        EstimatedCompletionTimesPanel {
    private static final int COLUMN_INDEX_WORST_CASE = 5;

    private static final int COLUMN_INDEX_AVERAGE_CASE = 4;

    private static final int COLUMN_INDEX_BEST_CASE = 3;

    private static final int COLUMN_INDEX_NAME = 2;

    private static final int COLUMN_INDEX_ID = 1;

    private static final int COLUMN_INDEX_PARENT = 0;

    private static final int COLUMNS_COUNT = 6;

    private static final long serialVersionUID = 1L;

    private static final String COLUMN_PARENT = "COLUMN_PARENT";

    private static final String COLUMN_ID = "COLUMN_ID";

    private static final String COLUMN_PROCESS = "COLUMN_PROCESS";

    private static final String COLUMN_BEST_CASE = "COLUMN_BEST_CASE";

    private static final String COLUMN_AVERAGE_CASE = "COLUMN_AVERAGE_CASE";

    private static final String COLUMN_WORST_CASE = "COLUMN_WORST_CASE";

    private final Table processesTable = new Table();

    private transient Injector injector = null;

    public DefaultEstimatedCompletionTimesPanel() {
        super();
    }

    @Override
    public Panel toPanel() {
        return this;
    }

    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;
    }

    @Override
    public void initGui() {
        final Button refreshButton =
                new Button(TM.get("estimatedcompletiontimespanel.7-refresh"));

        refreshButton.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final ClickEvent aEvent) {
                refreshButtonClicked();
            }
        });

        addComponent(refreshButton);

        createProcessesTable();

        addComponent(processesTable);

        fillTable();
    }

    protected void refreshButtonClicked() {
        this.fillTable();
    }

    private void createProcessesTable() {
        processesTable.addContainerProperty(COLUMN_PARENT, String.class, null);
        processesTable.addContainerProperty(COLUMN_ID, Long.class, null);
        processesTable.addContainerProperty(COLUMN_PROCESS, String.class, null);
        processesTable.addContainerProperty(COLUMN_BEST_CASE, Date.class, null);
        processesTable.addContainerProperty(COLUMN_AVERAGE_CASE, Date.class,
                null);
        processesTable
                .addContainerProperty(COLUMN_WORST_CASE, Date.class, null);

        processesTable.setColumnHeader(COLUMN_PARENT, TM
                .get("estimatedcompletiontimespanel.1-parent"));
        processesTable.setColumnHeader(COLUMN_ID, TM
                .get("estimatedcompletiontimespanel.2-id"));
        processesTable.setColumnHeader(COLUMN_PROCESS, TM
                .get("estimatedcompletiontimespanel.3-process"));
        processesTable.setColumnHeader(COLUMN_BEST_CASE, TM
                .get("estimatedcompletiontimespanel.4-best-case"));
        processesTable.setColumnHeader(COLUMN_AVERAGE_CASE, TM
                .get("estimatedcompletiontimespanel.5-average-case"));
        processesTable.setColumnHeader(COLUMN_WORST_CASE, TM
                .get("estimatedcompletiontimespanel.6-worst-case"));

        processesTable.setWidth("100%");
        processesTable.setHeight(null);

        processesTable.setSelectable(true);
        processesTable.setMultiSelect(false);
        processesTable.setImmediate(true);
    }

    private void fillTable() {
        final Persistence persistence =
                this.injector.getInstance(Persistence.class);
        final List<ControlProcess> tasksToShow =
                persistence.getUncompletedTasksWithEstimatedEndTime();

        this.processesTable.removeAllItems();

        for (ControlProcess task : tasksToShow) {
            this.processesTable.addItem(getTaskCells(task), task.getId());
        }
    }

    private Object[] getTaskCells(final ControlProcess aTask) {
        final Object[] cells = new Object[COLUMNS_COUNT];

        if (aTask.getParent() == null) {
            cells[COLUMN_INDEX_PARENT] = "";
        } else {
            cells[COLUMN_INDEX_PARENT] = aTask.getParent().getName();
        }

        cells[COLUMN_INDEX_ID] = aTask.getId();
        cells[COLUMN_INDEX_NAME] = aTask.getName();
        cells[COLUMN_INDEX_BEST_CASE] = aTask.getBestEstimatedEndDateTime();
        cells[COLUMN_INDEX_AVERAGE_CASE] =
                aTask.getAverageEstimatedEndDateTime();
        cells[COLUMN_INDEX_WORST_CASE] = aTask.getWorstEstimatedEndDateTime();

        return cells;
    }
}

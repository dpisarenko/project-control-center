/**
 * This file is part of Project Control Center (PCC).
 * 
 * Project Control Center (PCC) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Project Control Center (PCC) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Project Control Center (PCC).  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2010 Dmitri Anatol'evich Pisarenko
 **/

package at.silverstrike.pcc.impl.dailyplanpanel;

import static com.vaadin.ui.SplitPanel.ORIENTATION_HORIZONTAL;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Injector;
import com.vaadin.Application;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.SplitPanel;
import com.vaadin.ui.Table;

import eu.livotov.tpt.i18n.TM;

import at.silverstrike.pcc.api.dailyplanpanel.DailyPlanPanel;
import at.silverstrike.pcc.api.model.Booking;
import at.silverstrike.pcc.api.model.ControlProcess;
import at.silverstrike.pcc.api.model.DailyPlan;
import at.silverstrike.pcc.api.model.ProcessState;
import at.silverstrike.pcc.api.persistence.Persistence;

/**
 * @author Dmitri Pisarenko
 * 
 */
class DefaultDailyPlanPanel extends Panel implements DailyPlanPanel {
    private final static Logger LOGGER =
        LoggerFactory.getLogger(DefaultDailyPlanPanel.class);

    private static final int COLUMN_INDEX_SCHEDULE_PROCESS = 3;
    private static final int COLUMN_INDEX_SCHEDULE_TO = 2;
    private static final int COLUMN_INDEX_SCHEDULE_FROM = 1;
    private static final int COLUMN_INDEX_SCHEDULE_ID = 0;
    private static final int SCHEDULE_COLUMNS_COUNT = 4;
    private static final int TODO_COLUMNS_COUNT = 4;
    private static final int COLUMN_INDEX_TODO_PRIORITY = 3;
    private static final int COLUMN_INDEX_TODO_PROCESS_NAME = 2;
    private static final int COLUMN_INDEX_TODO_PARENT = 1;
    private static final int COLUMN_INDEX_TODO_IS_ATTAINED = 0;
    private static final String DATE_LABEL_TEMPLATE =
            "<h1>#{dayInMonth}</h1><h2>#{dayOfWeek}</h2><p>#{date}</p>";
    private static final DateFormat FULL_DATE_FORMAT =
            DateFormat.getDateInstance();
    private static final SimpleDateFormat DAY_OF_WEEK_FORMAT =
            new SimpleDateFormat("EEEE");
    private static final SimpleDateFormat DAY_IN_MONTH_FORMAT =
            new SimpleDateFormat("d");
    private static final long serialVersionUID = 1L;
    private static final String COLUMN_TODO_COMPLETED = "COLUMN_TODO_COMPLETED";
    private static final String COLUMN_TODO_PARENT = "COLUMN_TODO_PARENT";
    private static final String COLUMN_TODO_PROCESS = "COLUMN_TODO_PROCESS";
    private static final String COLUMN_TODO_PRIORITY = "COLUMN_TODO_PRIORITY";
    private static final String COLUMN_SCHEDULE_NUMBER =
            "COLUMN_SCHEDULE_NUMBER";
    private static final String COLUMN_SCHEDULE_FROM = "COLUMN_SCHEDULE_FROM";
    private static final String COLUMN_SCHEDULE_TO = "COLUMN_SCHEDULE_TO";
    private static final String COLUMN_SCHEDULE_PROCESS =
            "COLUMN_SCHEDULE_PROCESS";

    private Injector injector;
    private Table scheduleTable;
    private Table todoTable;

    public DefaultDailyPlanPanel() {
    }

    private Component getDatePanel() {
        final HorizontalLayout layout = new HorizontalLayout();
        final Date now = new Date();
        final Label dateLabel =
                new Label(DATE_LABEL_TEMPLATE.replace("#{dayInMonth}",
                        getDayInMonth(now)).replace("#{dayOfWeek}",
                        getDayOfWeek(now)).replace("#{date}", getFullDate(now)));
        dateLabel.setContentMode(Label.CONTENT_XHTML);

        selectedDayDateField = new InlineDateField();

        // Set the value of the PopupDateField to current date
        selectedDayDateField.setValue(now);

        // Set the correct resolution
        selectedDayDateField.setResolution(InlineDateField.RESOLUTION_DAY);
        selectedDayDateField.setImmediate(true);
        selectedDayDateField.setShowISOWeekNumbers(true);

        selectedDayDateField.addListener(new Listener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void componentEvent(final Event event) {
                if (event instanceof ValueChangeEvent) {
                    selectedDayChanged((ValueChangeEvent) event);
                }
            }
        });

        layout.addComponent(dateLabel);
        layout.addComponent(selectedDayDateField);
        return layout;
    }

    protected void selectedDayChanged(final ValueChangeEvent anEvent) {
        final Date newDate = (Date) anEvent.getProperty().getValue();

        Application app = getApplication();
        LOGGER.debug("getApplication(): " + app);

        final String resource;
        if (app != null) {
            resource = (String) app.getUser();
        } else {
            resource = null;
        }
        LOGGER.debug("resource: " + app);
        LOGGER.debug("newDate: " + newDate);
        final Persistence persistence =
                this.injector.getInstance(Persistence.class);

        final DailyPlan dailyPlan = persistence.getDailyPlan(newDate, resource);

        LOGGER.debug("dailyPlan: " + dailyPlan);
        
        updateToDoTable(dailyPlan);
        updateScheduleTable(dailyPlan);
    }

    private void updateToDoTable(final DailyPlan aDailyPlan) {
        this.todoTable.removeAllItems();

        if (aDailyPlan != null) {
            for (ControlProcess task : aDailyPlan.getToDoList()
                    .getTasksToCompleteToday()) {
                this.todoTable.addItem(getTaskCells(task), task.getId());
            }
        }
    }

    private Object[] getTaskCells(final ControlProcess aTask) {
        final Object[] cells = new Object[TODO_COLUMNS_COUNT];

        cells[COLUMN_INDEX_TODO_IS_ATTAINED] =
                aTask.getState() == ProcessState.ATTAINED;

        if (aTask.getParent() != null) {
            cells[COLUMN_INDEX_TODO_PARENT] = aTask.getParent().getName();
        } else {
            cells[COLUMN_INDEX_TODO_PARENT] = "";
        }

        cells[COLUMN_INDEX_TODO_PROCESS_NAME] = aTask.getName();

        cells[COLUMN_INDEX_TODO_PRIORITY] = "";

        return cells;
    }

    private void updateScheduleTable(final DailyPlan aDailyPlan) {
        this.scheduleTable.removeAllItems();

        if (aDailyPlan != null) {
            for (final Booking booking : aDailyPlan.getSchedule().getBookings()) {
                this.scheduleTable.addItem(getBookingCells(booking), booking
                        .getId());
            }
        }

    }

    private final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm");
    private InlineDateField selectedDayDateField;

    private Object[] getBookingCells(final Booking aBooking) {
        final Object[] cells = new Object[SCHEDULE_COLUMNS_COUNT];

        cells[COLUMN_INDEX_SCHEDULE_ID] = aBooking.getId();
        cells[COLUMN_INDEX_SCHEDULE_FROM] =
                TIME_FORMAT.format(aBooking.getStartDateTime());
        cells[COLUMN_INDEX_SCHEDULE_TO] =
                TIME_FORMAT.format(aBooking.getEndDateTime());
        cells[COLUMN_INDEX_SCHEDULE_PROCESS] = aBooking.getProcess().getName();

        return cells;
    }

    private String getDayInMonth(final Date aNow) {
        return DAY_IN_MONTH_FORMAT.format(aNow);
    }

    private String getDayOfWeek(final Date aNow) {
        return DAY_OF_WEEK_FORMAT.format(aNow);
    }

    private String getFullDate(final Date aNow) {
        return FULL_DATE_FORMAT.format(aNow);
    }

    private Component getToDoAndSchedulePanel() {
        final SplitPanel horizontalPanel = new SplitPanel();
        horizontalPanel.setOrientation(ORIENTATION_HORIZONTAL);
        horizontalPanel.setWidth("100%");

        horizontalPanel.addComponent(getToDoPanel());
        horizontalPanel.addComponent(getSchedulePanel());

        return horizontalPanel;
    }

    private Component getSchedulePanel() {
        scheduleTable =
                new Table(TM.get("dailyplanpanel.6-schedule-table-caption"));

        scheduleTable.setWidth("100%");

        scheduleTable.setSelectable(true);
        scheduleTable.setMultiSelect(false);
        scheduleTable.setImmediate(true);
        scheduleTable.setEditable(false);

        scheduleTable.addContainerProperty(COLUMN_SCHEDULE_NUMBER, Long.class,
                null);
        scheduleTable.addContainerProperty(COLUMN_SCHEDULE_FROM, String.class,
                null);
        scheduleTable.addContainerProperty(COLUMN_SCHEDULE_TO, String.class,
                null);
        scheduleTable.addContainerProperty(COLUMN_SCHEDULE_PROCESS,
                String.class, null);

        scheduleTable.setColumnHeader(COLUMN_SCHEDULE_NUMBER, TM
                .get("dailyplanpanel.7-schedule-table-COLUMN_SCHEDULE_NUMBER"));
        scheduleTable.setColumnHeader(COLUMN_SCHEDULE_FROM, TM
                .get("dailyplanpanel.8-schedule-table-COLUMN_SCHEDULE_FROM"));
        scheduleTable.setColumnHeader(COLUMN_SCHEDULE_TO, TM
                .get("dailyplanpanel.9-schedule-table-COLUMN_SCHEDULE_TO"));
        scheduleTable
                .setColumnHeader(
                        COLUMN_SCHEDULE_PROCESS,
                        TM
                                .get("dailyplanpanel.10-schedule-table-COLUMN_SCHEDULE_PROCESS"));

        return wrapLayout(scheduleTable);
    }

    private Component getToDoPanel() {
        todoTable = new Table(TM.get("dailyplanpanel.1-to-do-panel-caption"));

        todoTable.setWidth("100%");

        todoTable.setSelectable(true);
        todoTable.setMultiSelect(false);
        todoTable.setImmediate(true);

        todoTable.addContainerProperty(COLUMN_TODO_COMPLETED, Boolean.class,
                null);
        todoTable.addContainerProperty(COLUMN_TODO_PARENT, String.class, null);
        todoTable.addContainerProperty(COLUMN_TODO_PROCESS, String.class, null);
        todoTable
                .addContainerProperty(COLUMN_TODO_PRIORITY, String.class, null);

        todoTable.setColumnHeader(COLUMN_TODO_COMPLETED, TM
                .get("dailyplanpanel.2-todo-table-COLUMN_COMPLETED"));
        todoTable.setColumnHeader(COLUMN_TODO_PARENT, TM
                .get("dailyplanpanel.3-todo-table-COLUMN_PARENT"));
        todoTable.setColumnHeader(COLUMN_TODO_PROCESS, TM
                .get("dailyplanpanel.4-todo-table-COLUMN_PROCESS"));
        todoTable.setColumnHeader(COLUMN_TODO_PRIORITY, TM
                .get("dailyplanpanel.5-todo-table-COLUMN_PRIORITY"));

        todoTable.setColumnIcon(COLUMN_TODO_COMPLETED, new ThemeResource(
                "../runo/icons/16/ok.png"));
        return wrapLayout(todoTable);
    }

    private Component wrapLayout(final Table todoTable) {
        HorizontalLayout layout = new HorizontalLayout();
        layout.addComponent(todoTable);

        return layout;
    }

    /**
     * @see at.silverstrike.pcc.api.conventions.AbstractedPanel#toPanel()
     */
    @Override
    public Panel toPanel() {
        return this;
    }

    @Override
    public void setInjector(final Injector anInjector) {
        this.injector = anInjector;
    }

    @Override
    public void initGui() {
        SplitPanel verticalSplitPanel = new SplitPanel();

        verticalSplitPanel.setHeight("450px");
        verticalSplitPanel.setWidth("100%");

        final Component datePanel = getDatePanel();
        verticalSplitPanel.addComponent(datePanel);
        verticalSplitPanel.addComponent(getToDoAndSchedulePanel());

        verticalSplitPanel.setSplitPosition(40);

        addComponent(verticalSplitPanel);

        this.selectedDayDateField.setValue(new Date());
    }
}

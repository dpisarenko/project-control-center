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

package at.silverstrike.pcc.impl.dailyplanpanel;

import static com.vaadin.ui.SplitPanel.ORIENTATION_HORIZONTAL;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
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

import eu.livotov.tpt.TPTApplication;
import eu.livotov.tpt.i18n.TM;

import at.silverstrike.pcc.api.dailyplanpanel.DailyPlanPanel;
import at.silverstrike.pcc.api.model.Booking;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.model.DailyPlan;
import at.silverstrike.pcc.api.model.DailySchedule;
import at.silverstrike.pcc.api.model.ProcessState;
import at.silverstrike.pcc.api.model.UserData;
import at.silverstrike.pcc.api.persistence.Persistence;

/**
 * @author Dmitri Pisarenko
 * 
 */
class DefaultDailyPlanPanel extends Panel implements DailyPlanPanel {
    private static final String TIME_FORMAT = "HH:mm";

    private static final int SPLIT_POSITION = 40;

    private static final String DAY_OF_WEEK_FORMAT = "EEEE";

    private static final String DAY_IN_MONTH_FORMAT = "d";

    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultDailyPlanPanel.class);

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
    private InlineDateField selectedDayDateField;

    private transient Injector injector;
    private Table scheduleTable;
    private Table todoTable;

    private Label dateLabel;

    public DefaultDailyPlanPanel() {
    }

    private Component getDatePanel() {
        final HorizontalLayout layout = new HorizontalLayout();
        final Date now = new Date();
        dateLabel = new Label(getDateLabelText(now));
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
            public void componentEvent(final Event aEvent) {
                if (aEvent instanceof ValueChangeEvent) {
                    selectedDayChanged((ValueChangeEvent) aEvent);
                }
            }
        });

        layout.addComponent(dateLabel);
        layout.addComponent(selectedDayDateField);
        return layout;
    }

    private String getDateLabelText(final Date now) {
        return DATE_LABEL_TEMPLATE
                .replace("#{dayInMonth}", getDayInMonth(now))
                .replace("#{dayOfWeek}", getDayOfWeek(now))
                .replace("#{date}", getFullDate(now));
    }

    private Date removeHoursMinutesSeconds(final Date aDate) {
        Date returnValue = DateUtils.setHours(aDate, 0);

        returnValue = DateUtils.setMinutes(aDate, 0);
        returnValue = DateUtils.setSeconds(aDate, 0);
        returnValue = DateUtils.setMilliseconds(aDate, 0);

        return returnValue;
    }

    protected void selectedDayChanged(final ValueChangeEvent aEvent) {
        final Date newDate =
                removeHoursMinutesSeconds((Date) aEvent.getProperty()
                        .getValue());

        final Application app = getApplication();
        final String resource;
        if (app != null) {
            final UserData user = (UserData)TPTApplication.getCurrentApplication().getUser();
            resource = user.getResourceName();
        } else {
            resource = null;
        }
        LOGGER.debug("{}: Application: '{}', resource: '{}', newDate: '{}' ",
                new Object[] { ErrorCodes.M_001_SELECTED_DAY_CHANGED, app,
                        resource, newDate });

        final Persistence persistence = this.injector
                .getInstance(Persistence.class);

        LOGGER.debug("{}: newDate: {}, resource: {}", new Object[] {});
        
        final DailyPlan dailyPlan = persistence.getDailyPlan(newDate, resource);

        LOGGER.debug("{}: dailyPlan: {}", new Object[] {
                ErrorCodes.M_002_SELECTED_DAY_CHANGED2, dailyPlan });

        updateToDoTable(dailyPlan);
        updateScheduleTable(dailyPlan);
        dateLabel.setValue(getDateLabelText(newDate));
    }

    private void updateToDoTable(final DailyPlan aDailyPlan) {
        this.todoTable.removeAllItems();

        if (aDailyPlan != null) {
            for (Task task : aDailyPlan.getToDoList()
                    .getTasksToCompleteToday()) {
                this.todoTable.addItem(getTaskCells(task), task.getId());
            }
        }
    }

    private Object[] getTaskCells(final Task aTask) {
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
            final DailySchedule schedule = aDailyPlan.getSchedule();

            if (schedule != null) {
                final List<Booking> bookings = schedule.getBookings();

                if (bookings != null) {
                    for (final Booking booking : bookings) {
                        this.scheduleTable.addItem(getBookingCells(booking),
                                booking.getId());
                    }
                }
            }
        }
    }

    private Object[] getBookingCells(final Booking aBooking) {
        final Object[] cells = new Object[SCHEDULE_COLUMNS_COUNT];

        cells[COLUMN_INDEX_SCHEDULE_ID] = aBooking.getId();
        cells[COLUMN_INDEX_SCHEDULE_FROM] = getTimeFormat().format(aBooking
                .getStartDateTime());
        cells[COLUMN_INDEX_SCHEDULE_TO] = getTimeFormat().format(aBooking
                .getEndDateTime());
        cells[COLUMN_INDEX_SCHEDULE_PROCESS] = aBooking.getProcess().getName();

        return cells;
    }

    private SimpleDateFormat getTimeFormat() {
        return new SimpleDateFormat(TIME_FORMAT);
    }

    private String getDayInMonth(final Date aNow) {
        return getDayInMonthFormat().format(aNow);
    }

    /**
     * I introduced this method to fix FindBugs error:
     * 
     * <pre>
     * Call to method of static
     * java.text.DateFormat in
     * at.silverstrike.pcc.impl.dailyplanpanel.DefaultDailyPlanPanel
     * .getDayInMonth(Date)
     * </pre>
     * 
     * MT_CORRECTNESS STCAL_INVOKE_ON_STATIC_DATE_FORMAT_INSTANCE
     */
    private static DateFormat getDayInMonthFormat() {
        return new SimpleDateFormat(DAY_IN_MONTH_FORMAT);
    }

    private String getDayOfWeek(final Date aNow) {
        return getDayOfWeekFormat().format(aNow);
    }

    private String getFullDate(final Date aNow) {
        return getFullDateFormat().format(aNow);
    }

    private static DateFormat getFullDateFormat() {
        return DateFormat.getDateInstance();
    }

    private static DateFormat getDayOfWeekFormat() {
        return new SimpleDateFormat(DAY_OF_WEEK_FORMAT);
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
        scheduleTable = new Table(
                TM.get("dailyplanpanel.6-schedule-table-caption"));

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
        scheduleTable.setColumnHeader(COLUMN_SCHEDULE_FROM,
                TM.get("dailyplanpanel.8-schedule-table-COLUMN_SCHEDULE_FROM"));
        scheduleTable.setColumnHeader(COLUMN_SCHEDULE_TO,
                TM.get("dailyplanpanel.9-schedule-table-COLUMN_SCHEDULE_TO"));
        scheduleTable
                .setColumnHeader(
                        COLUMN_SCHEDULE_PROCESS,
                        TM.get("dailyplanpanel.10-schedule-table-COLUMN_SCHEDULE_PROCESS"));

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

        todoTable.setColumnHeader(COLUMN_TODO_COMPLETED,
                TM.get("dailyplanpanel.2-todo-table-COLUMN_COMPLETED"));
        todoTable.setColumnHeader(COLUMN_TODO_PARENT,
                TM.get("dailyplanpanel.3-todo-table-COLUMN_PARENT"));
        todoTable.setColumnHeader(COLUMN_TODO_PROCESS,
                TM.get("dailyplanpanel.4-todo-table-COLUMN_PROCESS"));
        todoTable.setColumnHeader(COLUMN_TODO_PRIORITY,
                TM.get("dailyplanpanel.5-todo-table-COLUMN_PRIORITY"));

        todoTable.setColumnIcon(COLUMN_TODO_COMPLETED, new ThemeResource(
                "../runo/icons/16/ok.png"));
        return wrapLayout(todoTable);
    }

    private Component wrapLayout(final Table aTodoTable) {
        final HorizontalLayout layout = new HorizontalLayout();
        layout.addComponent(aTodoTable);

        return layout;
    }

    /**
     * @see ru.altruix.commons.api.vaadin.AbstractedPanel#toPanel()
     */
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
        final SplitPanel verticalSplitPanel = new SplitPanel();

        verticalSplitPanel.setHeight("450px");
        verticalSplitPanel.setWidth("100%");

        final Component datePanel = getDatePanel();
        verticalSplitPanel.addComponent(datePanel);
        verticalSplitPanel.addComponent(getToDoAndSchedulePanel());

        verticalSplitPanel.setSplitPosition(SPLIT_POSITION);

        addComponent(verticalSplitPanel);

        this.selectedDayDateField.setValue(new Date());
    }
}

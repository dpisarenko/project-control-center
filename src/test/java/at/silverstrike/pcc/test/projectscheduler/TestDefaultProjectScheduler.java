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

package at.silverstrike.pcc.test.projectscheduler;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.injectorfactory.InjectorFactory;
import at.silverstrike.pcc.api.model.Booking;
import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.model.DailyPlan;
import at.silverstrike.pcc.api.model.DailySchedule;
import at.silverstrike.pcc.api.model.DailyToDoList;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.projectscheduler.ProjectExportInfo;
import at.silverstrike.pcc.api.projectscheduler.ProjectScheduler;
import at.silverstrike.pcc.impl.jruby.RubyDateTimeUtils;
import at.silverstrike.pcc.impl.persistence.DefaultPersistence;
import at.silverstrike.pcc.test.conventions.TestConventions;
import at.silverstrike.pcc.test.testutils.MockInjectorFactory;

import com.google.inject.Injector;

public class TestDefaultProjectScheduler {
    private static final String DIR = "src/test/resources/at/silverstrike/"
                            + "pcc/test/projectscheduler/";
    private final static Logger LOGGER =
            LoggerFactory.getLogger(TestDefaultProjectScheduler.class);
    private Helper helper = new Helper();

    @Test
    public void testTaskJugglerIIIInvokation01() {
        /**
         * Create persistence
         */
        final Persistence persistence = new MockPersistence01();

        /**
         * Create the injector
         */
        final InjectorFactory injectorFactory =
                new MockInjectorFactory(new MockInjectorModule(persistence));
        final Injector injector = injectorFactory.createInjector();

        /**
         * Get object under test
         */
        final ProjectScheduler objectUnderTest =
                injector.getInstance(ProjectScheduler.class);

        assertNotNull(objectUnderTest);

        final ProjectExportInfo projectInfo = objectUnderTest
                .getProjectExportInfo();

        assertNotNull(projectInfo);

        this.helper.fillProjectInfo01(projectInfo);
        objectUnderTest.setInjector(injector);

        /**
         * Set input data
         */
        objectUnderTest
                .setDirectory(DIR);

        /**
         * Run the method under test
         */
        try {
            objectUnderTest.run();
        } catch (final PccException exception) {
            LOGGER.error("", exception);
            fail(exception.getMessage());
        }

        /**
         * Verify that TJ3 was successfully invoked and created an output file
         */
        final File bookingsFile =
                new File(DIR + ProjectScheduler.BOOKINGS_FILE);
        final File deadlinesFile =
                new File(DIR + ProjectScheduler.DEADLINE_CSV_FILE);

        assertTrue("Bookings file doesn't exist.", bookingsFile.exists());
        assertTrue("Deadlines file doesn't exist.", deadlinesFile.exists());
    }

    @Test
    public void testRun01() {
        LOGGER.info("");
        LOGGER.info("Starting test case testRun01");
        LOGGER.info("");

        /**
         * Create persistence
         */
        final Persistence persistence = new DefaultPersistence();

        /**
         * Init persistence
         */
        try {
            persistence.openSession();
        } catch (final RuntimeException exception) {
            LOGGER.error("", exception);
            Assert.fail(exception.getMessage());
        } catch (final Exception exception) {
            LOGGER.error("", exception);
            Assert.fail(exception.getMessage());
        }

        /**
         * Empty the database
         */
        persistence.clearDatabase();

        /**
         * Create the injector
         */
        final InjectorFactory injectorFactory =
                new MockInjectorFactory(new MockInjectorModule(persistence));
        final Injector injector = injectorFactory.createInjector();

        /**
         * Get object under test
         */
        final ProjectScheduler objectUnderTest =
                injector.getInstance(ProjectScheduler.class);

        assertNotNull(objectUnderTest);

        final ProjectExportInfo projectInfo = objectUnderTest
                .getProjectExportInfo();

        assertNotNull(projectInfo);

        this.helper.fillProjectInfo02(projectInfo, persistence);
        objectUnderTest.setInjector(injector);

        /**
         * Save all task and resource data in the database
         */

        /**
         * Set input data
         */
        objectUnderTest
                .setDirectory(DIR);
        objectUnderTest.setNow(projectInfo.getNow());

        /**
         * Verify that our only tasks exists in the database before invokation
         * of the method under test
         */

        /**
         * Run the method under test
         */
        try {
            objectUnderTest.run();
        } catch (final PccException exception) {
            LOGGER.error("", exception);
            fail(exception.getMessage());
        }

        /**
         * Verify that now the result of getUncompletedTasksWithEstimatedEndTime
         * contains exactly one task.
         */
        final List<Task> processes =
                persistence.getUncompletedTasksWithEstimatedEndTime();

        Assert.assertNotNull(processes);
        Assert.assertEquals(1, processes.size());

        final Task process = processes.get(0);

        final Date date201010251130 =
                RubyDateTimeUtils.getDate(2010, Calendar.OCTOBER, 25, 11, 30);

        Assert.assertEquals(date201010251130,
                process.getBestEstimatedEndDateTime());
        Assert.assertEquals(date201010251130,
                process.getAverageEstimatedEndDateTime());
        Assert.assertEquals(date201010251130,
                process.getWorstEstimatedEndDateTime());

        /**
         * Verify that daily plan exists
         */
        final String resource =
                projectInfo.getResourcesToExport().get(0).getAbbreviation();

        Assert.assertNotNull(resource);
        Assert.assertFalse(StringUtils.isEmpty(resource));

        final Date date201010250000 =
                RubyDateTimeUtils.getDate(2010, Calendar.OCTOBER, 25, 0, 0);

        final DailyPlan dailyPlan =
                persistence.getDailyPlan(date201010250000, resource);

        Assert.assertNotNull(dailyPlan);

        final DailySchedule schedule = dailyPlan.getSchedule();

        Assert.assertNotNull(schedule);

        final List<Booking> bookings = schedule.getBookings();

        Assert.assertNotNull(bookings);
        Assert.assertEquals(1, bookings.size());

        final Booking booking = bookings.get(0);

        Assert.assertEquals(2.5, booking.getDuration(), TestConventions.DELTA);
        Assert.assertNotNull(booking.getProcess());
        Assert.assertNotNull(booking.getResource());

        final SchedulingObject expectedTask =
                projectInfo.getSchedulingObjectsToExport().get(0);

        Assert.assertEquals(((Task) expectedTask).getName(), booking
                .getProcess().getName());
        Assert.assertEquals(projectInfo.getResourcesToExport().get(0)
                .getAbbreviation(), booking.getResource().getAbbreviation());

        final Date date201010250900 =
                RubyDateTimeUtils.getDate(2010, Calendar.OCTOBER, 25, 9, 0);

        Assert.assertEquals(date201010250900, booking.getStartDateTime());

        Assert.assertEquals(date201010251130, booking.getEndDateTime());

        final DailyToDoList toDoList = dailyPlan.getToDoList();

        Assert.assertNotNull(toDoList);

        final List<Task> tasks = toDoList.getTasksToCompleteToday();

        Assert.assertNotNull(tasks);

        Assert.assertEquals(1, tasks.size());

        final Task task = tasks.get(0);

        Assert.assertEquals(expectedTask.getId(), task.getId());
        Assert.assertEquals(((Task) expectedTask).getName(), task.getName());
    }

    @Test
    public void testElementaryScenarioScheduling() {
    }
}

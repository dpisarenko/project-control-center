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

package at.silverstrike.pcc.test.export2tj3;

import static at.silverstrike.pcc.test.testutils.LineComparer.assertLinesEqual;

import java.io.File;
import java.util.List;

import junit.framework.Assert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Injector;

import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.export2tj3.NoProcessesException;
import at.silverstrike.pcc.api.export2tj3.TaskJuggler3Exporter;
import at.silverstrike.pcc.api.injectorfactory.InjectorFactory;
import at.silverstrike.pcc.api.projectscheduler.ProjectExportInfo;
import at.silverstrike.pcc.test.mockpersistence.MockProjectExportInfo;
import at.silverstrike.pcc.test.testutils.LineReader;
import at.silverstrike.pcc.test.testutils.MockInjectorFactory;

public class TestDefaultTaskJuggler3Exporter {
    private static final String EURO = "€";
    private static final File ACTUAL_RESULTS_FILE =
            new File(
                    "src/test/resources/at/silverstrike/pcc/test/export2tj3/"
                            + "testRun03.actual.txt");
    private static final File EXPECTED_RESULTS_FILE =
            new File(
                    "src/test/resources/at/silverstrike/pcc/test/export2tj3/"
                            + "testRun03.expected.txt");

    private static final int ONE_MONTH = 1;
    private final Helper helper = new Helper();
    private final static Logger LOGGER =
        LoggerFactory.getLogger(TestDefaultTaskJuggler3Exporter.class);

    @Before
    public void setupLogger() {
    }

    @Test
    public void testRun01() {
        /**
         * Create the injector
         */
        final InjectorFactory injectorFactory =
                new MockInjectorFactory(new MockInjectorModule());
        final Injector injector = injectorFactory.createInjector();

        /**
         * Get object under test
         */
        final TaskJuggler3Exporter objectUnderTest =
                injector.getInstance(TaskJuggler3Exporter.class);

        Assert.assertNotNull(objectUnderTest);

        objectUnderTest.setInjector(injector);

        /**
         * Set input data
         */
        final MockProjectExportInfo projectExportInfo =
                new MockProjectExportInfo();

        objectUnderTest.setProjectExportInfo(projectExportInfo);

        projectExportInfo.setControlProcessesToExport(this.helper
                .getTestRun01Processes());
        projectExportInfo.setCopyright("Dmitri Pisarenko");
        projectExportInfo.setCurrency(EURO);
        projectExportInfo.setNow(helper.getDate18October2010());
        projectExportInfo.setProjectName("MyProject");
        projectExportInfo.setResourcesToExport(null);
        projectExportInfo.setSchedulingHorizonMonths(ONE_MONTH);

        /**
         * Run the method under test
         */
        try {
            objectUnderTest.run();
            Assert
                    .fail("TaskJuggler3Exporter didn't throw an exception, even though resourcesToExport is null.");
        } catch (final PccException exception) {
        }
    }

    @Test
    public void testRun02() {
        /**
         * Create the injector
         */
        final InjectorFactory injectorFactory =
                new MockInjectorFactory(new MockInjectorModule());
        final Injector injector = injectorFactory.createInjector();

        /**
         * Get object under test
         */
        final TaskJuggler3Exporter objectUnderTest =
                injector.getInstance(TaskJuggler3Exporter.class);

        Assert.assertNotNull(objectUnderTest);

        objectUnderTest.setInjector(injector);

        /**
         * Set input data
         */
        final MockProjectExportInfo projectExportInfo =
                new MockProjectExportInfo();

        objectUnderTest.setProjectExportInfo(projectExportInfo);

        projectExportInfo
                .setControlProcessesToExport(null);
        projectExportInfo.setCopyright("Dmitri Pisarenko");
        projectExportInfo.setCurrency(EURO);
        projectExportInfo.setNow(
                helper.getDate18October2010());
        projectExportInfo.setProjectName("MyProject");
        projectExportInfo.setResourcesToExport(this.helper
                .getTestRun01Resources());
        projectExportInfo.setSchedulingHorizonMonths(
                ONE_MONTH);

        /**
         * Run the method under test
         */
        try {
            objectUnderTest.run();
            Assert
                    .fail("TaskJuggler3Exporter didn't throw an exception, even though controlProcessesToExport is null.");
        } catch (final NoProcessesException exception) {
            /**
             * We expect this exception
             */
        } catch (final PccException exception) {
            LOGGER.error("", exception);
            Assert.fail(exception.getMessage());
        }
    }

    @Test
    public void testRun03() {
        /**
         * Create the injector
         */
        final InjectorFactory injectorFactory =
                new MockInjectorFactory(new MockInjectorModule());
        final Injector injector = injectorFactory.createInjector();

        /**
         * Get object under test
         */
        final TaskJuggler3Exporter objectUnderTest =
                injector.getInstance(TaskJuggler3Exporter.class);

        Assert.assertNotNull(objectUnderTest);

        objectUnderTest.setInjector(injector);

        /**
         * Set input data
         */
        final ProjectExportInfo projectExportInfo =
                new MockProjectExportInfo();

        objectUnderTest.setProjectExportInfo(projectExportInfo);

        projectExportInfo.setControlProcessesToExport(
                this.helper
                        .getTestRun03Processes());
        projectExportInfo.setCopyright("Dmitri Pisarenko");
        projectExportInfo.setCurrency(EURO);
        projectExportInfo.setNow(
                helper.getDate18October2010());
        projectExportInfo.setProjectName("MyProject");
        projectExportInfo.setResourcesToExport(this.helper
                .getTestRun03Resources());
        projectExportInfo.setSchedulingHorizonMonths(
                ONE_MONTH);

        /**
         * Run the method under test
         */
        try {
            objectUnderTest.run();
        } catch (final PccException exception) {
            LOGGER.error("", exception);
            Assert.fail(exception.getMessage());
        }

        /**
         * Get actual results
         */
        final String actualResult =
                objectUnderTest.getTaskJugglerIIIProjectFileContents();

        helper.writeStringToFile(actualResult, ACTUAL_RESULTS_FILE);

        final List<String> actualLines =
                LineReader.readTrimmedLines(ACTUAL_RESULTS_FILE);

        /**
         * Get expected results
         */
        final List<String> expectedLines =
                LineReader.readTrimmedLines(EXPECTED_RESULTS_FILE);

        /**
         * Compare actual and expected result
         */
        assertLinesEqual(expectedLines, actualLines);
    }

}

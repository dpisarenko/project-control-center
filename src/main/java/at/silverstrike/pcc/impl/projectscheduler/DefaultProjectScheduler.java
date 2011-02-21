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

package at.silverstrike.pcc.impl.projectscheduler;

import static org.apache.commons.io.IOUtils.closeQuietly;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Injector;

import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.export2tj3.TaskJuggler3Exporter;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.projectscheduler.ProjectExportInfo;
import at.silverstrike.pcc.api.projectscheduler.ProjectScheduler;
import at.silverstrike.pcc.api.tj3bookingsparser.BookingTuple;
import at.silverstrike.pcc.api.tj3bookingsparser.Tj3BookingsParser;
import at.silverstrike.pcc.api.tj3bookingsparser.Tj3BookingsParserFactory;
import at.silverstrike.pcc.api.tj3deadlinesparser.ProcessEndTimeTuple;
import at.silverstrike.pcc.api.tj3deadlinesparser.Tj3DeadlinesFileParser;
import at.silverstrike.pcc.api.tj3deadlinesparser.Tj3DeadlinesFileParserFactory;

/**
 * @author Dmitri Pisarenko
 * 
 */
class DefaultProjectScheduler implements ProjectScheduler {
    private static final String RUBY_PATH = "C:\\Ruby191\\bin\\tj3.bat ";
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultProjectScheduler.class);

    private List<ProcessEndTimeTuple> endTimeTuples;
    private List<BookingTuple> bookingTuples;
    private Injector injector;
    private String directory;
    private ProjectExportInfo projectExportInfo;
    private Date now;

    public DefaultProjectScheduler() {
        this.projectExportInfo = new DefaultProjectExportInfo();
    }

    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;
    }

    @Override
    public void run() throws PccException {
        // Write data into some file
        writeOutProjectPlan(this.directory);

        // Invoke taskjuggler
        final Runtime rt = Runtime.getRuntime();

        runTaskJuggler(this.directory, rt);

        resetTupleCollections();

        // Parse deadlines.csv
        parseDeadlinesFile(this.directory);
        final Persistence persistence = this.injector
                .getInstance(Persistence.class);
        persistence.updateTaskEndTimes(this.endTimeTuples);

        // Parse pccBookings.tji
        parseBookingsFile(this.directory);
        persistence.updateBookings(this.bookingTuples);

        // Update daily plans
        persistence.generateDailyPlans(this.now);
    }

    private void parseDeadlinesFile(final String aParentDir)
            throws PccException {
        final Tj3DeadlinesFileParserFactory parserFactory = this.injector
                .getInstance(Tj3DeadlinesFileParserFactory.class);

        parserFactory.setInjector(this.injector);

        final Tj3DeadlinesFileParser parser = parserFactory.create();
        final File deadlineCsvFile = new File(aParentDir + "/"
                + DEADLINE_CSV_FILE);

        try {
            parser.setInputFileName(deadlineCsvFile.getAbsolutePath());
            parser.run();

            this.endTimeTuples = parser.getProcessEndTimes();
        } catch (final Exception exception) {
            throw new PccException(exception);
        }
    }

    private void parseBookingsFile(final String aParentDir) throws PccException {
        final Tj3BookingsParserFactory factory = this.injector
                .getInstance(Tj3BookingsParserFactory.class);
        final Tj3BookingsParser parser = factory.create();
        final File bookingsFile = new File(aParentDir + BOOKINGS_FILE);
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(bookingsFile);

            parser.setInjector(this.injector);
            parser.setInputStream(fileInputStream);
            parser.run();
            this.bookingTuples = parser.getBookings();
        } catch (final FileNotFoundException exception) {
            throw new PccException(exception);
        } catch (final Exception exception) {
            throw new PccException(exception);
        } finally {
            IOUtils.closeQuietly(fileInputStream);
        }
    }

    private void writeOutProjectPlan(final String aParentDir)
            throws PccException {
        final TaskJuggler3Exporter exporter = this.injector
                .getInstance(TaskJuggler3Exporter.class);

        exporter.setProjectExportInfo(this.projectExportInfo);
        exporter.setInjector(this.injector);

        exporter.run();

        final String projectFileContents = exporter
                .getTaskJugglerIIIProjectFileContents();
        FileOutputStream outputStream = null;
        try {
            final File path = new File(aParentDir + "/" + TJ3_INPUT_FILE);

            LOGGER.debug("path: " + path.getAbsolutePath());

            outputStream = new FileOutputStream(path);

            IOUtils.write(projectFileContents, outputStream);
        } catch (final IOException exception) {
            LOGGER.debug("", exception);
        } finally {
            IOUtils.closeQuietly(outputStream);
        }
    }

    private void resetTupleCollections() {
        this.endTimeTuples = new LinkedList<ProcessEndTimeTuple>();
        this.bookingTuples = new LinkedList<BookingTuple>();
    }

    private void
            runTaskJuggler(final String aParentDir, final Runtime aRuntime)
                    throws PccException {
        BufferedReader input = null;
        BufferedReader error = null;
        try {
            final String command = RUBY_PATH + TJ3_INPUT_FILE;

            LOGGER.info("command: " + command);

            final File parent = new File(aParentDir);
            Process proc;

            proc = aRuntime.exec(command, null, parent);

            LOGGER.info("parent: " + parent.getAbsolutePath());

            input = new BufferedReader(new InputStreamReader(
                    proc.getInputStream()));

            String line = null;

            while ((line = input.readLine()) != null) {
                LOGGER.debug("proc: " + line);
            }

            error = new BufferedReader(
                    new InputStreamReader(proc.getErrorStream()));

            String errorLine = null;

            while ((errorLine = error.readLine()) != null) {
                LOGGER.debug("proc: " + errorLine);
            }

            final int result = proc.waitFor();

            LOGGER.info("result: " + result);
        } catch (final IOException exception) {
            throw new PccException(exception);
        } catch (final InterruptedException exception) {
            throw new PccException(exception);
        } finally {
            closeQuietly(input);
            closeQuietly(error);
        }
    }

    public void setDirectory(final String aDirectory) {
        this.directory = aDirectory;
    }

    public void
            setProjectExportInfo(final ProjectExportInfo aProjectExportInfo) {
        this.projectExportInfo = aProjectExportInfo;
    }

    public ProjectExportInfo getProjectExportInfo() {
        return projectExportInfo;
    }

    public void setNow(final Date aNow) {
        this.now = aNow;
    }

}

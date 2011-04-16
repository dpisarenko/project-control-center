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

package at.silverstrike.pcc.test.debugids;

import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import ru.altruix.commons.api.debugids.DebugIdKeyNotFoundException;
import ru.altruix.commons.api.debugids.DebugIdUniquenessViolation;

import at.silverstrike.pcc.api.debugids.PccDebugIdKey;
import at.silverstrike.pcc.api.debugids.PccDebugIdRegistry;
import at.silverstrike.pcc.api.debugids.PccDebugIdRegistryFactory;
import at.silverstrike.pcc.api.pcc.PccFunctionalBlock;
import at.silverstrike.pcc.impl.debugids.DefaultDebugIdRegistryFactory;

/**
 * Verifies the uniqueness of debug IDs
 * 
 * @author dp118m
 * 
 */
public class TestDebugIdRegistry {
    private static final String DEBUG_ID_NOT_UNIQUE_TEMPLATE =
            "Debug ID not unique: ${debugId}";
    private static final String DEBUG_ID = "${debugId}";

    @Test
    public final void testUniqueness01() {
        final PccDebugIdRegistryFactory factory =
                new DefaultDebugIdRegistryFactory();
        final PccDebugIdRegistry objectUnderTest = factory.create();

        /**
         * Verify that all debug IDs are unique
         */
        final List<PccDebugIdKey> keys = objectUnderTest.getAllKeys();
        final List<Object> debugIds = new LinkedList<Object>();

        for (final PccDebugIdKey debugIdKey : keys) {
            Assert.assertNotNull(debugIdKey);
            Assert.assertNotNull(debugIdKey.getModule());
            Assert.assertNotNull(debugIdKey.getKey());

            final String debugId = objectUnderTest.getDebugId(debugIdKey);

            Assert.assertFalse(getMessage(debugId), debugIds.contains(debugId));
            debugIds.add(debugId);
        }
    }

    private String getMessage(final Object aDebugId) {
        return DEBUG_ID_NOT_UNIQUE_TEMPLATE.replace(
                DEBUG_ID, aDebugId.toString());
    }

    @Test
    public final void testUniqueness02() {
        final PccDebugIdRegistryFactory factory =
                new DefaultDebugIdRegistryFactory();
        final PccDebugIdRegistry objectUnderTest = factory.create();

        /**
         * First time we fetch a certain debug ID, no exception should be thrown
         */
        try {
            objectUnderTest.getDebugId(
                    PccFunctionalBlock.mainwindow, "1");
        } catch (final DebugIdUniquenessViolation exception) {
            Assert.fail(exception.getMessage());
        }

        /**
         * If the same debug ID is fetched a second time, this is an error,
         * since every debug ID can be used only once in the entire code.
         */
        try {
            objectUnderTest.getDebugId(
                    PccFunctionalBlock.mainwindow, "1");
            Assert.fail("No DebugIdUniquenessViolation thrown");
        } catch (final DebugIdUniquenessViolation exception) {
            ;
        }

        /**
         * If we fetch another debug ID, no exception should be thrown
         */
        try {
            objectUnderTest.getDebugId(
                    PccFunctionalBlock.mainprocesseditingpanel,
                    "3");
        } catch (final DebugIdUniquenessViolation exception) {
            Assert.fail(exception.getMessage());
        }
    }

    @Test
    public final void testUnknownKeys() {
        final PccDebugIdRegistryFactory factory =
                new DefaultDebugIdRegistryFactory();
        final PccDebugIdRegistry objectUnderTest = factory.create();

        try {
            objectUnderTest.getDebugId(
                    PccFunctionalBlock.debugids,
                    "An impossible key");
            Assert.fail("No DebugIdUniquenessViolation thrown");
        } catch (final DebugIdKeyNotFoundException exception) {
            // We expect this exception to be thrown
            ;
        }

        try {
            objectUnderTest.getDebugId(
                    PccFunctionalBlock.mainprocesseditingpanel,
                    "3");
        } catch (final DebugIdKeyNotFoundException exception) {
            Assert.fail(exception.getMessage());
        }
    }

    @Test
    public final void testKeyFetchingViaModulesAndKeys() {
        final PccDebugIdRegistryFactory factory =
                new DefaultDebugIdRegistryFactory();
        final PccDebugIdRegistry debugIdRegistry = factory.create();

        Assert.assertEquals("011.001", debugIdRegistry
                .getDebugId(PccFunctionalBlock.mainwindow, "1"));
        Assert.assertEquals("011.002", debugIdRegistry
                .getDebugId(PccFunctionalBlock.mainwindow,
                        "2-tab-sheet"));
        Assert.assertEquals(
                "010.001",
                debugIdRegistry
                        .getDebugId(
                                PccFunctionalBlock.mainprocesseditingpanel,
                                "1"));
        Assert.assertEquals(
                "010.002",
                debugIdRegistry
                        .getDebugId(
                                PccFunctionalBlock.mainprocesseditingpanel,
                                "2"));
        Assert.assertEquals(
                "010.003",
                debugIdRegistry
                        .getDebugId(
                                PccFunctionalBlock.mainprocesseditingpanel,
                                "3"));
        Assert.assertEquals("004.001", debugIdRegistry
                .getDebugId(
                        PccFunctionalBlock.editingprocesspanel,
                        "1"));
        Assert.assertEquals("004.002", debugIdRegistry
                .getDebugId(
                        PccFunctionalBlock.editingprocesspanel,
                        "2-buttonPanel"));
        Assert.assertEquals("004.003", debugIdRegistry
                .getDebugId(
                        PccFunctionalBlock.editingprocesspanel,
                        "3-processNameTextArea"));
        Assert.assertEquals("004.004", debugIdRegistry
                .getDebugId(
                        PccFunctionalBlock.editingprocesspanel,
                        "4-controlSubjectLabel"));
        Assert.assertEquals("004.005", debugIdRegistry
                .getDebugId(
                        PccFunctionalBlock.editingprocesspanel,
                        "5-controlSubjectComboBox"));
        Assert.assertEquals("004.006", debugIdRegistry
                .getDebugId(
                        PccFunctionalBlock.editingprocesspanel,
                        "6-handoffButton"));
        Assert.assertEquals("004.007", debugIdRegistry
                .getDebugId(
                        PccFunctionalBlock.editingprocesspanel,
                        "7-priorityTextBox"));
        Assert.assertEquals("004.008", debugIdRegistry
                .getDebugId(
                        PccFunctionalBlock.editingprocesspanel,
                        "8-typeComboBox"));
        Assert.assertEquals("004.009", debugIdRegistry
                .getDebugId(
                        PccFunctionalBlock.editingprocesspanel,
                        "9-startButton"));
        Assert.assertEquals("004.010", debugIdRegistry
                .getDebugId(
                        PccFunctionalBlock.editingprocesspanel,
                        "10-stopButton"));
        Assert.assertEquals("004.011", debugIdRegistry
                .getDebugId(
                        PccFunctionalBlock.editingprocesspanel,
                        "11-markAsCompleted"));
        Assert.assertEquals("004.012", debugIdRegistry
                .getDebugId(
                        PccFunctionalBlock.editingprocesspanel,
                        "12-cancelButton"));
        Assert.assertEquals("004.013", debugIdRegistry
                .getDebugId(
                        PccFunctionalBlock.editingprocesspanel,
                        "13-reactivateButton"));
        Assert.assertEquals("004.014", debugIdRegistry
                .getDebugId(
                        PccFunctionalBlock.editingprocesspanel,
                        "14-deleteButton"));
        Assert.assertEquals("004.015", debugIdRegistry
                .getDebugId(
                        PccFunctionalBlock.editingprocesspanel,
                        "15-saveButton"));
        Assert.assertEquals("004.016", debugIdRegistry
                .getDebugId(
                        PccFunctionalBlock.editingprocesspanel,
                        "16-dependencyDeleteButton"));
        Assert.assertEquals("004.017", debugIdRegistry
                .getDebugId(
                        PccFunctionalBlock.editingprocesspanel,
                        "17-dependencyAddButton"));
        Assert.assertEquals("004.018", debugIdRegistry
                .getDebugId(
                        PccFunctionalBlock.editingprocesspanel,
                        "18-dependencyComboBox"));
        Assert.assertEquals("004.019", debugIdRegistry
                .getDebugId(
                        PccFunctionalBlock.editingprocesspanel,
                        "19-dependencyList"));
        Assert.assertEquals("004.020", debugIdRegistry
                .getDebugId(
                        PccFunctionalBlock.editingprocesspanel,
                        "20-minEffortTextBox"));
        Assert.assertEquals("004.021", debugIdRegistry
                .getDebugId(
                        PccFunctionalBlock.editingprocesspanel,
                        "21-minTimeUnitComboBox"));
        Assert.assertEquals("004.022", debugIdRegistry
                .getDebugId(
                        PccFunctionalBlock.editingprocesspanel,
                        "22-maxEffortTextBox"));
        Assert.assertEquals("004.023", debugIdRegistry
                .getDebugId(
                        PccFunctionalBlock.editingprocesspanel,
                        "23-maxTimeUnitComboBox"));
        Assert.assertEquals("019.001", debugIdRegistry
                .getDebugId(PccFunctionalBlock.workerpanel, "1"));
        Assert.assertEquals("019.002", debugIdRegistry
                .getDebugId(PccFunctionalBlock.workerpanel,
                        "2-grid"));
        Assert.assertEquals("019.003", debugIdRegistry
                .getDebugId(PccFunctionalBlock.workerpanel,
                        "3-abbreviationTextField"));
        Assert.assertEquals("019.004", debugIdRegistry
                .getDebugId(PccFunctionalBlock.workerpanel,
                        "4-firstNameTextField"));
        Assert.assertEquals("019.005", debugIdRegistry
                .getDebugId(PccFunctionalBlock.workerpanel,
                        "5-middleNameTextField"));
        Assert.assertEquals("019.006", debugIdRegistry
                .getDebugId(PccFunctionalBlock.workerpanel,
                        "6-surnameTextField"));
        Assert.assertEquals("019.007", debugIdRegistry
                .getDebugId(PccFunctionalBlock.workerpanel,
                        "7-dailyMaxTextField"));
        Assert.assertEquals("019.008", debugIdRegistry
                .getDebugId(PccFunctionalBlock.workerpanel,
                        "8-buttonPanel"));
        Assert.assertEquals("019.009", debugIdRegistry
                .getDebugId(PccFunctionalBlock.workerpanel,
                        "9-okButton"));
        Assert.assertEquals("019.010", debugIdRegistry
                .getDebugId(PccFunctionalBlock.workerpanel,
                        "10-cancelButton"));
    }
}

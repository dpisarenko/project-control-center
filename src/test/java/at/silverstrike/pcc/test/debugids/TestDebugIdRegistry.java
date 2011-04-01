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

import at.silverstrike.pcc.api.conventions.Module;
import at.silverstrike.pcc.api.debugids.DebugIdKey;
import at.silverstrike.pcc.api.debugids.DebugIdKeyNotFoundException;
import at.silverstrike.pcc.api.debugids.DebugIdRegistry;
import at.silverstrike.pcc.api.debugids.DebugIdRegistryFactory;
import at.silverstrike.pcc.api.debugids.DebugIdUniquenessViolation;
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
        final DebugIdRegistryFactory factory =
                new DefaultDebugIdRegistryFactory();
        final DebugIdRegistry objectUnderTest = factory.create();

        /**
         * Verify that all debug IDs are unique
         */
        final List<DebugIdKey> keys = objectUnderTest.getAllKeys();
        final List<Object> debugIds = new LinkedList<Object>();

        for (final DebugIdKey debugIdKey : keys) {
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
        final DebugIdRegistryFactory factory =
                new DefaultDebugIdRegistryFactory();
        final DebugIdRegistry objectUnderTest = factory.create();

        /**
         * First time we fetch a certain debug ID, no exception should be thrown
         */
        try {
            objectUnderTest.getDebugId(
                    Module.mainwindow, "1");
        } catch (final DebugIdUniquenessViolation exception) {
            Assert.fail(exception.getMessage());
        }

        /**
         * If the same debug ID is fetched a second time, this is an error,
         * since every debug ID can be used only once in the entire code.
         */
        try {
            objectUnderTest.getDebugId(
                    Module.mainwindow, "1");
            Assert.fail("No DebugIdUniquenessViolation thrown");
        } catch (final DebugIdUniquenessViolation exception) {
            ;
        }

        /**
         * If we fetch another debug ID, no exception should be thrown
         */
        try {
            objectUnderTest.getDebugId(
                    Module.mainprocesseditingpanel,
                    "3");
        } catch (final DebugIdUniquenessViolation exception) {
            Assert.fail(exception.getMessage());
        }
    }

    @Test
    public final void testUnknownKeys() {
        final DebugIdRegistryFactory factory =
                new DefaultDebugIdRegistryFactory();
        final DebugIdRegistry objectUnderTest = factory.create();

        try {
            objectUnderTest.getDebugId(
                    Module.debugids,
                    "An impossible key");
            Assert.fail("No DebugIdUniquenessViolation thrown");
        } catch (final DebugIdKeyNotFoundException exception) {
            // We expect this exception to be thrown
            ;
        }

        try {
            objectUnderTest.getDebugId(
                    Module.mainprocesseditingpanel,
                    "3");
        } catch (final DebugIdKeyNotFoundException exception) {
            Assert.fail(exception.getMessage());
        }
    }

    @Test
    public final void testKeyFetchingViaModulesAndKeys() {
        final DebugIdRegistryFactory factory =
                new DefaultDebugIdRegistryFactory();
        final DebugIdRegistry debugIdRegistry = factory.create();

        Assert.assertEquals("011.001", debugIdRegistry
                .getDebugId(Module.mainwindow, "1"));
        Assert.assertEquals("011.002", debugIdRegistry
                .getDebugId(Module.mainwindow,
                        "2-tab-sheet"));
        Assert.assertEquals(
                "010.001",
                debugIdRegistry
                        .getDebugId(
                                Module.mainprocesseditingpanel,
                                "1"));
        Assert.assertEquals(
                "010.002",
                debugIdRegistry
                        .getDebugId(
                                Module.mainprocesseditingpanel,
                                "2"));
        Assert.assertEquals(
                "010.003",
                debugIdRegistry
                        .getDebugId(
                                Module.mainprocesseditingpanel,
                                "3"));
        Assert.assertEquals("004.001", debugIdRegistry
                .getDebugId(
                        Module.editingprocesspanel,
                        "1"));
        Assert.assertEquals("004.002", debugIdRegistry
                .getDebugId(
                        Module.editingprocesspanel,
                        "2-buttonPanel"));
        Assert.assertEquals("004.003", debugIdRegistry
                .getDebugId(
                        Module.editingprocesspanel,
                        "3-processNameTextArea"));
        Assert.assertEquals("004.004", debugIdRegistry
                .getDebugId(
                        Module.editingprocesspanel,
                        "4-controlSubjectLabel"));
        Assert.assertEquals("004.005", debugIdRegistry
                .getDebugId(
                        Module.editingprocesspanel,
                        "5-controlSubjectComboBox"));
        Assert.assertEquals("004.006", debugIdRegistry
                .getDebugId(
                        Module.editingprocesspanel,
                        "6-handoffButton"));
        Assert.assertEquals("004.007", debugIdRegistry
                .getDebugId(
                        Module.editingprocesspanel,
                        "7-priorityTextBox"));
        Assert.assertEquals("004.008", debugIdRegistry
                .getDebugId(
                        Module.editingprocesspanel,
                        "8-typeComboBox"));
        Assert.assertEquals("004.009", debugIdRegistry
                .getDebugId(
                        Module.editingprocesspanel,
                        "9-startButton"));
        Assert.assertEquals("004.010", debugIdRegistry
                .getDebugId(
                        Module.editingprocesspanel,
                        "10-stopButton"));
        Assert.assertEquals("004.011", debugIdRegistry
                .getDebugId(
                        Module.editingprocesspanel,
                        "11-markAsCompleted"));
        Assert.assertEquals("004.012", debugIdRegistry
                .getDebugId(
                        Module.editingprocesspanel,
                        "12-cancelButton"));
        Assert.assertEquals("004.013", debugIdRegistry
                .getDebugId(
                        Module.editingprocesspanel,
                        "13-reactivateButton"));
        Assert.assertEquals("004.014", debugIdRegistry
                .getDebugId(
                        Module.editingprocesspanel,
                        "14-deleteButton"));
        Assert.assertEquals("004.015", debugIdRegistry
                .getDebugId(
                        Module.editingprocesspanel,
                        "15-saveButton"));
        Assert.assertEquals("004.016", debugIdRegistry
                .getDebugId(
                        Module.editingprocesspanel,
                        "16-dependencyDeleteButton"));
        Assert.assertEquals("004.017", debugIdRegistry
                .getDebugId(
                        Module.editingprocesspanel,
                        "17-dependencyAddButton"));
        Assert.assertEquals("004.018", debugIdRegistry
                .getDebugId(
                        Module.editingprocesspanel,
                        "18-dependencyComboBox"));
        Assert.assertEquals("004.019", debugIdRegistry
                .getDebugId(
                        Module.editingprocesspanel,
                        "19-dependencyList"));
        Assert.assertEquals("004.020", debugIdRegistry
                .getDebugId(
                        Module.editingprocesspanel,
                        "20-minEffortTextBox"));
        Assert.assertEquals("004.021", debugIdRegistry
                .getDebugId(
                        Module.editingprocesspanel,
                        "21-minTimeUnitComboBox"));
        Assert.assertEquals("004.022", debugIdRegistry
                .getDebugId(
                        Module.editingprocesspanel,
                        "22-maxEffortTextBox"));
        Assert.assertEquals("004.023", debugIdRegistry
                .getDebugId(
                        Module.editingprocesspanel,
                        "23-maxTimeUnitComboBox"));
        Assert.assertEquals("019.001", debugIdRegistry
                .getDebugId(Module.workerpanel, "1"));
        Assert.assertEquals("019.002", debugIdRegistry
                .getDebugId(Module.workerpanel,
                        "2-grid"));
        Assert.assertEquals("019.003", debugIdRegistry
                .getDebugId(Module.workerpanel,
                        "3-abbreviationTextField"));
        Assert.assertEquals("019.004", debugIdRegistry
                .getDebugId(Module.workerpanel,
                        "4-firstNameTextField"));
        Assert.assertEquals("019.005", debugIdRegistry
                .getDebugId(Module.workerpanel,
                        "5-middleNameTextField"));
        Assert.assertEquals("019.006", debugIdRegistry
                .getDebugId(Module.workerpanel,
                        "6-surnameTextField"));
        Assert.assertEquals("019.007", debugIdRegistry
                .getDebugId(Module.workerpanel,
                        "7-dailyMaxTextField"));
        Assert.assertEquals("019.008", debugIdRegistry
                .getDebugId(Module.workerpanel,
                        "8-buttonPanel"));
        Assert.assertEquals("019.009", debugIdRegistry
                .getDebugId(Module.workerpanel,
                        "9-okButton"));
        Assert.assertEquals("019.010", debugIdRegistry
                .getDebugId(Module.workerpanel,
                        "10-cancelButton"));
    }
}

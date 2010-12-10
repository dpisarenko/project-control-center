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

package at.silverstrike.pcc.test.debugids;

import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

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
    private static final String MAINPROCESSEDITINGPANEL_3 =
            "mainprocesseditingpanel.3";
    private static final String MAINWINDOW_1 = "mainwindow.1";

    @Test
    public void testUniqueness01() {
        final DebugIdRegistryFactory factory =
                new DefaultDebugIdRegistryFactory();
        final DebugIdRegistry objectUnderTest = factory.create();

        /**
         * Verify that all debug IDs are unique
         */
        final List<DebugIdKey> keys = objectUnderTest.getAllKeys();
        final List<Object> debugIds = new LinkedList<Object>();

        for (final DebugIdKey debugIdKey : keys)
        {
            Assert.assertNotNull(debugIdKey);
            Assert.assertNotNull(debugIdKey.getModule());
            Assert.assertNotNull(debugIdKey.getKey());
            
            final String debugId = objectUnderTest.getDebugId(debugIdKey);
            
            Assert.assertFalse(getMessage(debugId), debugIds
                    .contains(debugId));
            debugIds.add(debugId);   
        }
    }

    private String getMessage(final Object debugId) {
        return DEBUG_ID_NOT_UNIQUE_TEMPLATE.replace(
                DEBUG_ID, debugId.toString());
    }

    @Test
    public void testUniqueness02() {
        final DebugIdRegistryFactory factory =
                new DefaultDebugIdRegistryFactory();
        final DebugIdRegistry objectUnderTest = factory.create();

        /**
         * First time we fetch a certain debug ID, no exception should be thrown
         */
        try {
            objectUnderTest.getDebugId(MAINWINDOW_1);
        } catch (final DebugIdUniquenessViolation exception) {
            Assert.fail(exception.getMessage());
        }

        /**
         * If the same debug ID is fetched a second time, this is an error,
         * since every debug ID can be used only once in the entire code.
         */
        try {
            objectUnderTest.getDebugId(MAINWINDOW_1);
            Assert.fail("No DebugIdUniquenessViolation thrown");
        } catch (final DebugIdUniquenessViolation exception) {
        }

        /**
         * If we fetch another debug ID, no exception should be thrown
         */
        try {
            objectUnderTest.getDebugId(MAINPROCESSEDITINGPANEL_3);
        } catch (final DebugIdUniquenessViolation exception) {
            Assert.fail(exception.getMessage());
        }
    }

    @Test
    public void testUnknownKeys() {
        final DebugIdRegistryFactory factory =
                new DefaultDebugIdRegistryFactory();
        final DebugIdRegistry objectUnderTest = factory.create();

        try {
            objectUnderTest.getDebugId("An impossible key");
            Assert.fail("No DebugIdUniquenessViolation thrown");
        } catch (final DebugIdKeyNotFoundException exception) {
            // We expect this exception to be thrown
        }

        try {
            objectUnderTest.getDebugId(MAINPROCESSEDITINGPANEL_3);
        } catch (final DebugIdKeyNotFoundException exception) {
            Assert.fail(exception.getMessage());
        }
    }
}

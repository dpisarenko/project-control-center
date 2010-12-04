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

import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.silverstrike.pcc.api.debugids.DebugIdKeyNotFoundException;
import at.silverstrike.pcc.api.debugids.DebugIdRegistry;
import at.silverstrike.pcc.api.debugids.DebugIdRegistryFactory;
import at.silverstrike.pcc.api.debugids.DebugIdUniquenessViolation;
import at.silverstrike.pcc.impl.debugids.DefaultDebugIdRegistryFactory;

/**
 * Verifies the uniqueness of debug IDs
 * @author dp118m
 *
 */
public class TestDebugIdRegistry {
    
    private static final String MAINPROCESSEDITINGPANEL_3 = "mainprocesseditingpanel.3";
    private static final String MAINWINDOW_1 = "mainwindow.1";

    private final Logger LOGGER =
        LoggerFactory.getLogger(TestDebugIdRegistry.class);
    
    @Test
    public void testUniqueness01()
    {
        final Properties properties = new Properties();
        
        /**
         * Load properties
         */
        try
        {
            properties.load(new FileInputStream("src/main/resources/debugids/debugids.properties"));
        }
        catch (final Exception exception)
        {
            LOGGER.error("", exception);
            Assert.fail(exception.getMessage());
        }

        /**
         * Verify that all debug IDs are unique
         */
        final List<Object> debugIds = new LinkedList<Object>();
        
        for (final Object debugId : properties.values())
        {
            Assert.assertFalse(debugIds.contains(debugId));
            
            debugIds.add(debugId);
        }
        
    }
    
    @Test
    public void testUniqueness02()
    {
        final DebugIdRegistryFactory factory = new DefaultDebugIdRegistryFactory();
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
         * If the same debug ID is fetched a second time, this is an error, since every
         * debug ID can be used only once in the entire code.
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
    public void testUnknownKeys()
    {
        final DebugIdRegistryFactory factory = new DefaultDebugIdRegistryFactory();
        final DebugIdRegistry objectUnderTest = factory.create();

        try
        {
            objectUnderTest.getDebugId("An impossible key");
            Assert.fail("No DebugIdUniquenessViolation thrown");
        }
        catch (final DebugIdKeyNotFoundException exception)
        {
            // We expect this exception to be thrown
        }
        
        try
        {
            objectUnderTest.getDebugId(MAINPROCESSEDITINGPANEL_3);
        }
        catch (final DebugIdKeyNotFoundException exception)
        {
            Assert.fail(exception.getMessage());
        }
    }
}

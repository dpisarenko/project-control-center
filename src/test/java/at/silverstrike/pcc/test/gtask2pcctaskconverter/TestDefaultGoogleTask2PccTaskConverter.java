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

package at.silverstrike.pcc.test.gtask2pcctaskconverter;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.altruix.commons.api.di.InjectorFactory;
import ru.altruix.commons.api.di.PccException;

import com.google.inject.Injector;

import at.silverstrike.pcc.api.gtask2pcctaskconverter.GoogleTask2PccTaskConverter;
import at.silverstrike.pcc.api.gtask2pcctaskconverter.GoogleTask2PccTaskConverterFactory;
import at.silverstrike.pcc.api.model.UserData;
import at.silverstrike.pcc.impl.gtask2pcctaskconverter.DefaultGoogleTask2PccTaskConverterFactory;
import at.silverstrike.pcc.test.model.MockObjectFactory;
import at.silverstrike.pcc.test.testutils.MockInjectorFactory;

/**
 * @author DP118M
 * 
 */
public class TestDefaultGoogleTask2PccTaskConverter {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(TestDefaultGoogleTask2PccTaskConverter.class);

    @Test
    public void testOnNullNotes() {
        final GoogleTask2PccTaskConverter objectUnderTest =
                getObjectUnderTest();

        /**
         * Run the first time, with non-null notes
         */
        final com.google.api.services.tasks.v1.model.Task task1 =
                new com.google.api.services.tasks.v1.model.Task();
        task1.set("notes", "1h");
        objectUnderTest.setGoogleTask(task1);
        objectUnderTest.setUser(getUserData());
        try {
            objectUnderTest.run();
        } catch (final PccException exception) {
            LOGGER.error("", exception);
            Assert.fail(exception.getMessage());
        }

        final at.silverstrike.pcc.api.model.Task pccTask =
                objectUnderTest.getPccTask();

        Assert.assertEquals(1.0, pccTask.getBestCaseEffort());
        Assert.assertEquals(1.0, pccTask.getWorstCaseEffort());

        /**
         * Run the second time, with null notes
         */
        final com.google.api.services.tasks.v1.model.Task task2 =
                new com.google.api.services.tasks.v1.model.Task();
        task2.set("notes", "");

        objectUnderTest.setGoogleTask(task2);
        try {
            objectUnderTest.run();
        } catch (final PccException exception) {
            LOGGER.error("", exception);
            Assert.fail(exception.getMessage());
        }

        final at.silverstrike.pcc.api.model.Task pccTask2 =
                objectUnderTest.getPccTask();

        /**
         * Make sure that the second time the effort is equal to null.
         */
        Assert.assertNull(pccTask2.getBestCaseEffort());
        Assert.assertNull(pccTask2.getWorstCaseEffort());
    }

    @Test
    public void testReadingLabelsPositive() {
        final GoogleTask2PccTaskConverter objectUnderTest =
                getObjectUnderTest();

        final com.google.api.services.tasks.v1.model.Task task1 =
                new com.google.api.services.tasks.v1.model.Task();
        task1.set("title", "T1: bla-bla-bla");

        objectUnderTest.setGoogleTask(task1);
        objectUnderTest.setUser(getUserData());
        try {
            objectUnderTest.run();
        } catch (final PccException exception) {
            LOGGER.error("", exception);
            Assert.fail(exception.getMessage());
        }

        final at.silverstrike.pcc.api.model.Task pccTask =
                objectUnderTest.getPccTask();

        Assert.assertNotNull(pccTask.getLabel());
        Assert.assertEquals("T1", pccTask.getLabel());
    }

    @Test
    public void testReadingLabelsPositiveMultipleSemicola() {
        final GoogleTask2PccTaskConverter objectUnderTest =
                getObjectUnderTest();

        final com.google.api.services.tasks.v1.model.Task task1 =
                new com.google.api.services.tasks.v1.model.Task();
        task1.set("title", "T2: T1: bla-bla-bla");

        objectUnderTest.setGoogleTask(task1);
        objectUnderTest.setUser(getUserData());
        try {
            objectUnderTest.run();
        } catch (final PccException exception) {
            LOGGER.error("", exception);
            Assert.fail(exception.getMessage());
        }

        final at.silverstrike.pcc.api.model.Task pccTask =
                objectUnderTest.getPccTask();

        Assert.assertNotNull(pccTask.getLabel());
        Assert.assertEquals("T2", pccTask.getLabel());
    }

    @Test
    public void testReadingLabelsNegative() {
        final GoogleTask2PccTaskConverter objectUnderTest =
                getObjectUnderTest();

        final com.google.api.services.tasks.v1.model.Task task1 =
                new com.google.api.services.tasks.v1.model.Task();
        task1.set("title", "T1 bla-bla-bla");

        objectUnderTest.setGoogleTask(task1);
        objectUnderTest.setUser(getUserData());
        try {
            objectUnderTest.run();
        } catch (final PccException exception) {
            LOGGER.error("", exception);
            Assert.fail(exception.getMessage());
        }

        final at.silverstrike.pcc.api.model.Task pccTask =
                objectUnderTest.getPccTask();

        Assert.assertNull(pccTask.getLabel());
    }

    private GoogleTask2PccTaskConverter getObjectUnderTest() {
        final InjectorFactory injectorFactory = new MockInjectorFactory(
                new MockInjectorModule(new MockPersistence()));
        final Injector injector = injectorFactory.createInjector();
        final GoogleTask2PccTaskConverterFactory factory =
                new DefaultGoogleTask2PccTaskConverterFactory();
        final GoogleTask2PccTaskConverter objectUnderTest = factory.create();
        objectUnderTest.setInjector(injector);

        return objectUnderTest;
    }

    private UserData getUserData() {
        final MockObjectFactory mockObjectFactory = new MockObjectFactory();
        return mockObjectFactory.createUserData();
    }
}

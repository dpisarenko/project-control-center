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

package at.silverstrike.pcc.test.gcaltasks2pccimporter;

import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.altruix.commons.api.di.InjectorFactory;
import ru.altruix.commons.api.di.PccException;

import com.google.api.services.tasks.v1.model.Task;
import com.google.inject.Injector;

import at.silverstrike.pcc.api.gcaltasks2pccimporter.GoogleCalendarTasks2PccImporter2;
import at.silverstrike.pcc.api.gcaltasks2pccimporter.GoogleCalendarTasks2PccImporter2Factory;
import at.silverstrike.pcc.api.model.UserData;
import at.silverstrike.pcc.test.model.MockObjectFactory;
import at.silverstrike.pcc.test.testutils.MockInjectorFactory;

/**
 * @author DP118M
 * 
 */
public final class TestDefaultGoogleCalendarTasks2PccImporter2 {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(TestDefaultGoogleCalendarTasks2PccImporter2.class);

    private static final MockObjectFactory MOCK_OBJECT_FACTORY =
            new MockObjectFactory();

    @Test
    public void testDependenciesReadingPrefix() {
        final Injector injector = getInjector();
        final GoogleCalendarTasks2PccImporter2 objectUnderTest =
                getObjectUnderTest(injector);
        final UserData user = MOCK_OBJECT_FACTORY.createUserData();

        // Prepare test data (START)
        final List<Task> googleTasks = new LinkedList<Task>();

        // Task T1, depends on nothing
        final Task t1 = new Task();
        t1.set("title", "T1: Task 1");

        // Task T2, depends on T1
        final Task t2 = new Task();
        t2.set("title", "T2: Task 2");
        t2.set("notes", "Depends on T1");

        // Task T3, depends on T1 and T2
        final Task t3 = new Task();
        t3.set("title", "T3: Task 3");
        t3.set("notes", "Depends on T1, T2");

        googleTasks.add(t1);
        googleTasks.add(t2);
        googleTasks.add(t3);

        // Prepare test data (END)

        objectUnderTest.setGoogleTasks(googleTasks);
        objectUnderTest.setInjector(injector);
        objectUnderTest.setUser(user);
        try {
            objectUnderTest.run();
        } catch (final PccException exception) {
            LOGGER.error("", exception);
            Assert.fail(exception.getMessage());
        }

        final List<at.silverstrike.pcc.api.model.Task> createdPccTasks =
                objectUnderTest.getCreatedPccTasks();

        Assert.assertNotNull(createdPccTasks);
        Assert.assertEquals(3, createdPccTasks.size());
        
        // Check T1

    }

    @Test
    public void testDependenciesReadingHashtags() {
        final Injector injector = getInjector();
        final GoogleCalendarTasks2PccImporter2 objectUnderTest =
                getObjectUnderTest(injector);

    }

    private GoogleCalendarTasks2PccImporter2 getObjectUnderTest(
            final Injector aInjector) {
        final GoogleCalendarTasks2PccImporter2Factory factory =
                aInjector
                        .getInstance(GoogleCalendarTasks2PccImporter2Factory.class);
        final GoogleCalendarTasks2PccImporter2 objectUnderTest =
                factory.create();
        objectUnderTest.setInjector(aInjector);

        return objectUnderTest;
    }

    private Injector getInjector() {
        final InjectorFactory injectorFactory = new MockInjectorFactory(
                new MockInjectorModule(new MockPersistence()));
        final Injector injector = injectorFactory.createInjector();
        return injector;
    }
}

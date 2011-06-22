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

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
import at.silverstrike.pcc.api.model.SchedulingObject;
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

        testDependencyCreation(googleTasks);
    }

    @Test
    public void testDependenciesReadingHashtags() {
        // Prepare test data (START)
        final List<Task> googleTasks = new LinkedList<Task>();

        // Task T1, depends on nothing
        final Task t1 = new Task();
        t1.set("title", "T1: Task 1");

        // Task T2, depends on T1
        final Task t2 = new Task();
        t2.set("title", "T2: Task 2");
        t2.set("notes", "#T1");

        // Task T3, depends on T1 and T2
        final Task t3 = new Task();
        t3.set("title", "T3: Task 3");
        t3.set("notes", "#T1 #T2");

        googleTasks.add(t1);
        googleTasks.add(t2);
        googleTasks.add(t3);

        testDependencyCreation(googleTasks);
    }

    
    private void testDependencyCreation(final List<Task> aGoogleTasks) {
        final Injector injector = getInjector();
        final GoogleCalendarTasks2PccImporter2 objectUnderTest =
                getObjectUnderTest(injector);
        final UserData user = MOCK_OBJECT_FACTORY.createUserData();


        // Prepare test data (END)
        objectUnderTest.setGoogleTasks(aGoogleTasks);
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

        final Map<String, at.silverstrike.pcc.api.model.Task> pccTasksByLabels =
                getPccTasksByLabels(createdPccTasks);

        // Check T1
        final at.silverstrike.pcc.api.model.Task t1pcc =
                pccTasksByLabels.get("T1");
        Assert.assertEquals(0, t1pcc.getPredecessors().size());

        // Check T2
        final at.silverstrike.pcc.api.model.Task t2pcc =
                pccTasksByLabels.get("T2");
        Assert.assertEquals(1, t2pcc.getPredecessors().size());

        final at.silverstrike.pcc.api.model.SchedulingObject t2predecessor =
                t2pcc.getPredecessors().iterator().next();
        Assert.assertEquals("T1", t2predecessor.getLabel());

        // Check T3
        final at.silverstrike.pcc.api.model.Task t3pcc =
                pccTasksByLabels.get("T3");
        Assert.assertEquals(2, t3pcc.getPredecessors().size());

        final Iterator<SchedulingObject> t3predecessorsIterator =
                t3pcc.getPredecessors().iterator();

        final at.silverstrike.pcc.api.model.SchedulingObject t3predecessor1 =
                t3predecessorsIterator.next();
        Assert.assertEquals("T1", t3predecessor1.getLabel());

        final at.silverstrike.pcc.api.model.SchedulingObject t3predecessor2 =
                t3predecessorsIterator.next();
        Assert.assertEquals("T2", t3predecessor2.getLabel());
    }

    private Map<String, at.silverstrike.pcc.api.model.Task>
            getPccTasksByLabels(
                    final List<at.silverstrike.pcc.api.model.Task> aPccTasks) {
        final Map<String, at.silverstrike.pcc.api.model.Task> returnValue =
                new HashMap<String, at.silverstrike.pcc.api.model.Task>();

        for (final at.silverstrike.pcc.api.model.Task curTask : aPccTasks) {
            returnValue.put(curTask.getLabel(), curTask);
        }

        return returnValue;
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

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

package at.silverstrike.pcc.test.projectnetworkgraphcreator;

import static junit.framework.Assert.assertNotNull;

import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.injectorfactory.InjectorFactory;
import at.silverstrike.pcc.api.projectnetworkgraphcreator.ProjectNetworkGraph;
import at.silverstrike.pcc.api.projectnetworkgraphcreator.ProjectNetworkGraphCreator;
import at.silverstrike.pcc.api.projectnetworkgraphcreator.SchedulingObjectDependencyTuple;
import at.silverstrike.pcc.test.testutils.MockInjectorFactory;

import com.google.inject.Injector;

import eu.livotov.tpt.i18n.TM;

/**
 * @author DP118M
 * 
 */
public class TestDefaultProjectNetworkGraphCreator {
    private static final String P3 = "P3";
    private static final String P2 = "P2";
    private static final String P1 = "P1";
    private final static Logger LOGGER =
            LoggerFactory
                    .getLogger(TestDefaultProjectNetworkGraphCreator.class);

    @Test
    public void test01() {
        /**
         * Init the i18n mechanism
         */
        TM.getDictionary().setDefaultLanguage("ru");
        
        /**
         * Create the injector
         */
        final InjectorFactory injectorFactory = new MockInjectorFactory(
                        new MockInjectorModule());
        final Injector injector = injectorFactory.createInjector();

        /**
         * Get the object under test
         */
        final ProjectNetworkGraphCreator objectUnderTest =
                injector.getInstance(ProjectNetworkGraphCreator.class);
        assertNotNull(objectUnderTest);

        /**
         * Create input data
         * 
         * The graph looks like this:
         * 
         * <pre>
         * P1 -> P2 
         * P3
         * </pre>
         * 
         * P1..3 - tasks (processes)
         */
        final List<SchedulingObjectDependencyTuple> tuples =
                getDependencyTuples();

        /**
         * Invoke the method under test
         */
        objectUnderTest.setDependencies(tuples);
        try {
            objectUnderTest.run();
        } catch (final PccException exception) {
            LOGGER.error("", exception);
            Assert.fail(exception.getMessage());
        }

        /**
         * Verify the results
         * 
         * <pre>
         * IE -> P1 -> P2 -> FE 
         *    -> P3       -> FE
         * 
         * P1..3 - tasks (processes)
         * IE - initial event
         * FE - final event
         * </pre>
         */
        final ProjectNetworkGraph graph = objectUnderTest.getGraph();

        Assert.assertNotNull(graph);

        final String initialEventVertex = graph.getInitialEventVertex();
        final String finalEventVertex = graph.getFinalEventVertex();

        Assert.assertNotNull(initialEventVertex);
        Assert.assertNotNull(finalEventVertex);

        Assert.assertNotSame(P1, initialEventVertex);
        Assert.assertNotSame(P2, initialEventVertex);
        Assert.assertNotSame(P3, initialEventVertex);

        Assert.assertNotSame(P1, finalEventVertex);
        Assert.assertNotSame(P2, finalEventVertex);
        Assert.assertNotSame(P3, finalEventVertex);

        Assert.assertTrue(edgeExists(graph, initialEventVertex, P1));
        Assert.assertFalse(edgeExists(graph, initialEventVertex, P2));
        Assert.assertTrue(edgeExists(graph, initialEventVertex, P3));

        Assert.assertTrue(edgeExists(graph, P1, P2));

        Assert.assertFalse(edgeExists(graph, P1, finalEventVertex));
        Assert.assertTrue(edgeExists(graph, P2, finalEventVertex));
        Assert.assertTrue(edgeExists(graph, P3, finalEventVertex));
        
        Assert.assertEquals(5, graph.getEdgeCount());
    }

    private boolean edgeExists(final ProjectNetworkGraph aGraph,
            final String aSource, final String aTarget) {
        return aGraph.findEdge(aSource, aTarget) != null;
    }

    private List<SchedulingObjectDependencyTuple> getDependencyTuples() {
        final List<SchedulingObjectDependencyTuple> tuples =
                new LinkedList<SchedulingObjectDependencyTuple>();

        final SchedulingObjectDependencyTuple tuple1 =
                new MockSchedulingObjectDependencyTuple();
        tuple1.setLabel(P1);

        final List<String> p1Dependencies = new LinkedList<String>();
        p1Dependencies.add(P2);

        final SchedulingObjectDependencyTuple tuple2 =
                new MockSchedulingObjectDependencyTuple();
        tuple2.setLabel(P2);
        tuple2.setDependencies(p1Dependencies);

        final SchedulingObjectDependencyTuple tuple3 =
                new MockSchedulingObjectDependencyTuple();
        tuple3.setLabel(P3);

        tuples.add(tuple1);
        tuples.add(tuple2);
        tuples.add(tuple3);

        return tuples;
    }
}

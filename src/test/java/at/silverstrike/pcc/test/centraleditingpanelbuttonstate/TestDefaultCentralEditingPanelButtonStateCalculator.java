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

package at.silverstrike.pcc.test.centraleditingpanelbuttonstate;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.silverstrike.pcc.api.centraleditingpanelbuttonstate.CentralEditingPanelButtonStateCalculator;
import at.silverstrike.pcc.api.centraleditingpanelbuttonstate.CentralEditingPanelButtonStateCalculatorFactory;
import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.injectorfactory.InjectorFactory;
import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.test.model.MockObjectFactory;
import at.silverstrike.pcc.test.testutils.MockInjectorFactory;
import com.google.inject.Injector;

/**
 * @author DP118M
 * 
 */
public class TestDefaultCentralEditingPanelButtonStateCalculator {
    private static final Logger LOGGER =
            LoggerFactory
                    .getLogger(TestDefaultCentralEditingPanelButtonStateCalculator.class);
    private static final MockObjectFactory MOCK_OBJECT_FACTORY =
            new MockObjectFactory();

    @Test
    public final void testOnEmptySelection() {
        final CentralEditingPanelButtonStateCalculator objectUnderTest =
                getObjectUnderTest(new MockPersistenceEmptyDb());

        final boolean expectedDecreasePriorityButtonState = false;
        final boolean expectedIncreasePriorityButtonState = false;
        final boolean expectedNewTaskButtonEnabled = true;
        final boolean expectedNewEventButtonEnabled = true;
        final boolean expectedNewMilestoneButtonEnabled = true;
        final SchedulingObject selectedSchedulingObject = null;

        executeTest(objectUnderTest, selectedSchedulingObject,
                expectedDecreasePriorityButtonState,
                expectedIncreasePriorityButtonState,
                expectedNewTaskButtonEnabled,
                expectedNewEventButtonEnabled,
                expectedNewMilestoneButtonEnabled);
    }

    @Test
    public final void testOnTaskSelection() {
        final CentralEditingPanelButtonStateCalculator objectUnderTest =
                getObjectUnderTest(new MockPersistenceEmptyDb());

        final boolean expectedDecreasePriorityButtonState = false;
        final boolean expectedIncreasePriorityButtonState = false;
        final boolean expectedNewTaskButtonEnabled = true;
        final boolean expectedNewEventButtonEnabled = true;
        final boolean expectedNewMilestoneButtonEnabled = true;
        final SchedulingObject selectedSchedulingObject =
                MOCK_OBJECT_FACTORY.createControlProcess(1L);

        executeTest(objectUnderTest, selectedSchedulingObject,
                expectedDecreasePriorityButtonState,
                expectedIncreasePriorityButtonState,
                expectedNewTaskButtonEnabled,
                expectedNewEventButtonEnabled,
                expectedNewMilestoneButtonEnabled);
    }

    @Test
    public final void testOnMilestoneSelection() {
        final CentralEditingPanelButtonStateCalculator objectUnderTest =
                getObjectUnderTest(new MockPersistenceEmptyDb());

        boolean expectedDecreasePriorityButtonState = false;
        boolean expectedIncreasePriorityButtonState = false;
        boolean expectedNewTaskButtonEnabled = false;
        boolean expectedNewEventButtonEnabled = false;
        boolean expectedNewMilestoneButtonEnabled = false;
        final SchedulingObject selectedSchedulingObject =
                MOCK_OBJECT_FACTORY.createMilestone();

        executeTest(objectUnderTest, selectedSchedulingObject,
                expectedDecreasePriorityButtonState,
                expectedIncreasePriorityButtonState,
                expectedNewTaskButtonEnabled,
                expectedNewEventButtonEnabled,
                expectedNewMilestoneButtonEnabled);
    }

    @Test
    public final void testOnEventSelection() {
        final CentralEditingPanelButtonStateCalculator objectUnderTest =
                getObjectUnderTest(new MockPersistenceEmptyDb());

        boolean expectedDecreasePriorityButtonState = false;
        boolean expectedIncreasePriorityButtonState = false;
        boolean expectedNewTaskButtonEnabled = false;
        boolean expectedNewEventButtonEnabled = false;
        boolean expectedNewMilestoneButtonEnabled = false;
        final SchedulingObject selectedSchedulingObject =
                MOCK_OBJECT_FACTORY.createEvent();

        executeTest(objectUnderTest, selectedSchedulingObject,
                expectedDecreasePriorityButtonState,
                expectedIncreasePriorityButtonState,
                expectedNewTaskButtonEnabled,
                expectedNewEventButtonEnabled,
                expectedNewMilestoneButtonEnabled);
    }

    private void executeTest(
            final CentralEditingPanelButtonStateCalculator aObjectUnderTest,
            final SchedulingObject aCurrentSelection,
            final boolean aExpectedDecreasePriorityButtonState,
            final boolean aExpectedIncreasePriorityButtonState,
            boolean aExpectedNewTaskButtonEnabled,
            boolean aExpectedNewEventButtonEnabled,
            boolean aExpectedNewMilestoneButtonEnabled) {
        aObjectUnderTest.setCurrentSelection(aCurrentSelection);
        try {
            aObjectUnderTest.run();
        } catch (final PccException exception) {
            LOGGER.error("", exception);
            Assert.fail(exception.getMessage());
        }
        Assert.assertEquals("Decrease priority button state",
                aExpectedDecreasePriorityButtonState,
                aObjectUnderTest.isDecreasePriorityButtonEnabled());
        Assert.assertEquals("Increase priority button state",
                aExpectedIncreasePriorityButtonState,
                aObjectUnderTest.isIncreasePriorityButtonEnabled());
    }

    private CentralEditingPanelButtonStateCalculator getObjectUnderTest(
            final MockPersistenceEmptyDb aPersistence) {
        final InjectorFactory injectorFactory =
                new MockInjectorFactory(new MockInjectorModule(aPersistence));
        final Injector injector = injectorFactory.createInjector();
        final CentralEditingPanelButtonStateCalculatorFactory factory =
                injector.getInstance(CentralEditingPanelButtonStateCalculatorFactory.class);
        final CentralEditingPanelButtonStateCalculator objectUnderTest =
                factory.create();
        return objectUnderTest;
    }
}

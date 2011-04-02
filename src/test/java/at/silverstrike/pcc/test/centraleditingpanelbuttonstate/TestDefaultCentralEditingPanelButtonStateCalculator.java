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
import at.silverstrike.pcc.test.testutils.MockInjectorFactory;
import com.google.inject.Injector;

/**
 * @author DP118M
 * 
 */
public class TestDefaultCentralEditingPanelButtonStateCalculator {
    private final static Logger LOGGER =
            LoggerFactory
                    .getLogger(TestDefaultCentralEditingPanelButtonStateCalculator.class);

    @Test
    public void testOnEmptyDatabase() {
        final CentralEditingPanelButtonStateCalculator objectUnderTest =
                getObjectUnderTest(new MockPersistenceEmptyDb());

        executeTest(objectUnderTest, null, true, true);

    }
    
    @Test
    public void testOnTopLevelProcesses() {
    }

    private void executeTest(
            final CentralEditingPanelButtonStateCalculator objectUnderTest,
            final SchedulingObject aCurrentSelection,
            final boolean aExpectedDecreasePriorityButtonState,
            final boolean aExpectedIncreasePriorityButtonState) {
        objectUnderTest.setCurrentSelection(aCurrentSelection);
        try {
            objectUnderTest.run();
        } catch (final PccException exception) {
            LOGGER.error("", exception);
            Assert.fail(exception.getMessage());
        }
        Assert.assertEquals("Decrease priority button state",
                aExpectedDecreasePriorityButtonState,
                objectUnderTest.isDecreasePriorityButtonEnabled());
        Assert.assertEquals("Increase priority button state",
                aExpectedIncreasePriorityButtonState,
                objectUnderTest.isIncreasePriorityButtonEnabled());
    }

    private CentralEditingPanelButtonStateCalculator getObjectUnderTest(MockPersistenceEmptyDb aPersistence) {
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

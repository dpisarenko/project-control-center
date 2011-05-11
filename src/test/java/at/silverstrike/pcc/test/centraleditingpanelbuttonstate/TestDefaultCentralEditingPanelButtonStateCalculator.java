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

import ru.altruix.commons.api.di.InjectorFactory;
import ru.altruix.commons.api.di.PccException;

import at.silverstrike.pcc.api.centraleditingpanelbuttonstate.CentralEditingPanelButtonStateCalculator;
import at.silverstrike.pcc.api.centraleditingpanelbuttonstate.CentralEditingPanelButtonStateCalculatorFactory;
import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.test.model.MockObjectFactory;
import at.silverstrike.pcc.test.testutils.MockInjectorFactory;
import com.google.inject.Injector;

/**
 * @author DP118M
 * 
 */
public class TestDefaultCentralEditingPanelButtonStateCalculator {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(TestDefaultCentralEditingPanelButtonStateCalculator.class);
	private static final MockObjectFactory MOCK_OBJECT_FACTORY = new MockObjectFactory();

	@Test
	public final void testOnEmptySelection() {
		final CentralEditingPanelButtonStateCalculator objectUnderTest = getObjectUnderTest(new MockPersistenceEmptyDb());

		final boolean expectedDecreasePriorityButtonState = false;
		final boolean expectedIncreasePriorityButtonState = false;
		final boolean expectedNewTaskButtonEnabled = true;
		final boolean expectedNewEventButtonEnabled = true;
		final boolean expectedNewMilestoneButtonEnabled = true;
		final SchedulingObject selectedSchedulingObject = null;

		executeTest(objectUnderTest, selectedSchedulingObject,
				expectedDecreasePriorityButtonState,
				expectedIncreasePriorityButtonState,
				expectedNewTaskButtonEnabled, expectedNewEventButtonEnabled,
				expectedNewMilestoneButtonEnabled);
	}

	@Test
	public final void testOnTaskSelection() {
		final CentralEditingPanelButtonStateCalculator objectUnderTest = getObjectUnderTest(new MockPersistenceEmptyDb());

		final Boolean expectedDecreasePriorityButtonState = null;
		final Boolean expectedIncreasePriorityButtonState = null;
		final boolean expectedNewTaskButtonEnabled = true;
		final boolean expectedNewEventButtonEnabled = true;
		final boolean expectedNewMilestoneButtonEnabled = true;
		final SchedulingObject selectedSchedulingObject = MOCK_OBJECT_FACTORY
				.createControlProcess(1L);

		executeTest(objectUnderTest, selectedSchedulingObject,
				expectedDecreasePriorityButtonState,
				expectedIncreasePriorityButtonState,
				expectedNewTaskButtonEnabled, expectedNewEventButtonEnabled,
				expectedNewMilestoneButtonEnabled);
	}

	@Test
	public final void testOnMilestoneSelection() {
		final CentralEditingPanelButtonStateCalculator objectUnderTest = getObjectUnderTest(new MockPersistenceEmptyDb());

		Boolean expectedDecreasePriorityButtonState = null;
		Boolean expectedIncreasePriorityButtonState = null;
		boolean expectedNewTaskButtonEnabled = false;
		boolean expectedNewEventButtonEnabled = false;
		boolean expectedNewMilestoneButtonEnabled = false;
		final SchedulingObject selectedSchedulingObject = MOCK_OBJECT_FACTORY
				.createMilestone();

		executeTest(objectUnderTest, selectedSchedulingObject,
				expectedDecreasePriorityButtonState,
				expectedIncreasePriorityButtonState,
				expectedNewTaskButtonEnabled, expectedNewEventButtonEnabled,
				expectedNewMilestoneButtonEnabled);
	}

	@Test
	public final void testOnEventSelection() {
		final CentralEditingPanelButtonStateCalculator objectUnderTest = getObjectUnderTest(new MockPersistenceEmptyDb());

		Boolean expectedDecreasePriorityButtonState = null;
		Boolean expectedIncreasePriorityButtonState = null;
		boolean expectedNewTaskButtonEnabled = false;
		boolean expectedNewEventButtonEnabled = false;
		boolean expectedNewMilestoneButtonEnabled = false;
		final SchedulingObject selectedSchedulingObject = MOCK_OBJECT_FACTORY
				.createEvent();

		executeTest(objectUnderTest, selectedSchedulingObject,
				expectedDecreasePriorityButtonState,
				expectedIncreasePriorityButtonState,
				expectedNewTaskButtonEnabled, expectedNewEventButtonEnabled,
				expectedNewMilestoneButtonEnabled);
	}

	@Test
	public final void testOnHighestPriorityTask() {
		final CentralEditingPanelButtonStateCalculator objectUnderTest = getObjectUnderTest(new MockPersistenceHighestLevelTask());

		boolean expectedDecreasePriorityButtonState = true;
		boolean expectedIncreasePriorityButtonState = false;
		boolean expectedNewTaskButtonEnabled = true;
		boolean expectedNewEventButtonEnabled = true;
		boolean expectedNewMilestoneButtonEnabled = true;
		final SchedulingObject selectedSchedulingObject = MOCK_OBJECT_FACTORY
				.createTask();

		executeTest(objectUnderTest, selectedSchedulingObject,
				expectedDecreasePriorityButtonState,
				expectedIncreasePriorityButtonState,
				expectedNewTaskButtonEnabled, expectedNewEventButtonEnabled,
				expectedNewMilestoneButtonEnabled);
	}

	@Test
	public final void testOnMediumPriorityTask() {
		final CentralEditingPanelButtonStateCalculator objectUnderTest = getObjectUnderTest(new MockPersistenceMediumPriorityTask());

		boolean expectedDecreasePriorityButtonState = true;
		boolean expectedIncreasePriorityButtonState = true;
		boolean expectedNewTaskButtonEnabled = true;
		boolean expectedNewEventButtonEnabled = true;
		boolean expectedNewMilestoneButtonEnabled = true;
		final SchedulingObject selectedSchedulingObject = MOCK_OBJECT_FACTORY
				.createTask();

		executeTest(objectUnderTest, selectedSchedulingObject,
				expectedDecreasePriorityButtonState,
				expectedIncreasePriorityButtonState,
				expectedNewTaskButtonEnabled, expectedNewEventButtonEnabled,
				expectedNewMilestoneButtonEnabled);
	}

	@Test
	public final void testOnLowestPriorityTask() {
		final CentralEditingPanelButtonStateCalculator objectUnderTest = getObjectUnderTest(new MockPersistenceLowestPriorityTask());

		boolean expectedDecreasePriorityButtonState = false;
		boolean expectedIncreasePriorityButtonState = true;
		boolean expectedNewTaskButtonEnabled = true;
		boolean expectedNewEventButtonEnabled = true;
		boolean expectedNewMilestoneButtonEnabled = true;
		final SchedulingObject selectedSchedulingObject = MOCK_OBJECT_FACTORY
				.createTask();

		executeTest(objectUnderTest, selectedSchedulingObject,
				expectedDecreasePriorityButtonState,
				expectedIncreasePriorityButtonState,
				expectedNewTaskButtonEnabled, expectedNewEventButtonEnabled,
				expectedNewMilestoneButtonEnabled);
	}

	private void executeTest(
			final CentralEditingPanelButtonStateCalculator aObjectUnderTest,
			final SchedulingObject aCurrentSelection,
			final Boolean aExpectedDecreasePriorityButtonState,
			final Boolean aExpectedIncreasePriorityButtonState,
			final boolean aExpectedNewTaskButtonEnabled,
			final boolean aExpectedNewEventButtonEnabled,
			final boolean aExpectedNewMilestoneButtonEnabled) {
		aObjectUnderTest.setCurrentSelection(aCurrentSelection);
		try {
			aObjectUnderTest.run();
		} catch (final PccException exception) {
			LOGGER.error("", exception);
			Assert.fail(exception.getMessage());
		}
		if (aExpectedDecreasePriorityButtonState != null) {
			Assert.assertEquals("Decrease priority button state",
					aExpectedDecreasePriorityButtonState,
					aObjectUnderTest.isDecreasePriorityButtonEnabled());
		}
		if (aExpectedIncreasePriorityButtonState != null) {
			Assert.assertEquals("Increase priority button state",
					aExpectedIncreasePriorityButtonState,
					aObjectUnderTest.isIncreasePriorityButtonEnabled());
		}
		Assert.assertEquals("New task button state",
				aExpectedNewTaskButtonEnabled,
				aObjectUnderTest.isNewTaskButtonEnabled());
		Assert.assertEquals("New milestone button state",
				aExpectedNewMilestoneButtonEnabled,
				aObjectUnderTest.isNewMilestoneButtonEnabled());
		Assert.assertEquals("New event button state",
				aExpectedNewEventButtonEnabled,
				aObjectUnderTest.isNewEventButtonEnabled());

	}

	private CentralEditingPanelButtonStateCalculator getObjectUnderTest(
			final Persistence aPersistence) {
		final InjectorFactory injectorFactory = new MockInjectorFactory(
				new MockInjectorModule(aPersistence));
		final Injector injector = injectorFactory.createInjector();
		final CentralEditingPanelButtonStateCalculatorFactory factory = injector
				.getInstance(CentralEditingPanelButtonStateCalculatorFactory.class);
		final CentralEditingPanelButtonStateCalculator objectUnderTest = factory
				.create();
		objectUnderTest.setInjector(injector);
		return objectUnderTest;
	}
}

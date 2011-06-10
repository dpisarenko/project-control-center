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

package at.silverstrike.pcc.test.gtaskrelevance;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.altruix.commons.api.di.PccException;

import com.google.api.services.tasks.v1.model.Task;

import at.silverstrike.pcc.api.gtaskrelevance.IsGoogleTaskRelevantCalculator;
import at.silverstrike.pcc.api.gtaskrelevance.IsGoogleTaskRelevantCalculatorFactory;
import at.silverstrike.pcc.impl.gtaskrelevance.DefaultIsGoogleTaskRelevantCalculatorFactory;

/**
 * @author DP118M
 * 
 */
public class TestDefaultIsGoogleTaskRelevantCalculator {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(TestDefaultIsGoogleTaskRelevantCalculator.class);

    @Test
    public void testCompleted() {
        final IsGoogleTaskRelevantCalculatorFactory factory =
                new DefaultIsGoogleTaskRelevantCalculatorFactory();
        final IsGoogleTaskRelevantCalculator objectUnderTest = factory.create();

        final com.google.api.services.tasks.v1.model.Task task = new Task();

        task.set(IsGoogleTaskRelevantCalculator.COMPLETED,
                "2011-06-10T11:44:22.300Z");
        task.set(IsGoogleTaskRelevantCalculator.PARENT,
                "MTE5OTY3NjA1Mjc5NDc1OTc1NjI6MDo5");

        Assert.assertFalse(getActualRelevance(objectUnderTest, task));
    }

    @Test
    public void testNotCompletedNullNote() {
        final IsGoogleTaskRelevantCalculatorFactory factory =
                new DefaultIsGoogleTaskRelevantCalculatorFactory();
        final IsGoogleTaskRelevantCalculator objectUnderTest = factory.create();

        final com.google.api.services.tasks.v1.model.Task task = new Task();

        task.set(IsGoogleTaskRelevantCalculator.COMPLETED, null);
        task.set(IsGoogleTaskRelevantCalculator.NOTES, null);
        task.set(IsGoogleTaskRelevantCalculator.PARENT,
                "MTE5OTY3NjA1Mjc5NDc1OTc1NjI6MDo5");

        Assert.assertFalse(getActualRelevance(objectUnderTest, task));
    }

    @Test
    public void testNotCompletedEmptyNote() {
        final IsGoogleTaskRelevantCalculatorFactory factory =
                new DefaultIsGoogleTaskRelevantCalculatorFactory();
        final IsGoogleTaskRelevantCalculator objectUnderTest = factory.create();

        final com.google.api.services.tasks.v1.model.Task task = new Task();

        task.set(IsGoogleTaskRelevantCalculator.COMPLETED, null);
        task.set(IsGoogleTaskRelevantCalculator.NOTES, "   ");
        task.set(IsGoogleTaskRelevantCalculator.PARENT,
                "MTE5OTY3NjA1Mjc5NDc1OTc1NjI6MDo5");

        Assert.assertFalse(getActualRelevance(objectUnderTest, task));
    }

    @Test
    public void testNotCompletedEmptyNoteTopLevelTask() {
        final IsGoogleTaskRelevantCalculatorFactory factory =
                new DefaultIsGoogleTaskRelevantCalculatorFactory();
        final IsGoogleTaskRelevantCalculator objectUnderTest = factory.create();

        final com.google.api.services.tasks.v1.model.Task task = new Task();

        task.set(IsGoogleTaskRelevantCalculator.COMPLETED, null);
        task.set(IsGoogleTaskRelevantCalculator.NOTES, "   ");
        task.set(IsGoogleTaskRelevantCalculator.PARENT,
                null);

        Assert.assertTrue(getActualRelevance(objectUnderTest, task));
    }

    private boolean getActualRelevance(
            final IsGoogleTaskRelevantCalculator objectUnderTest,
            final com.google.api.services.tasks.v1.model.Task task) {
        boolean actualRelevance = false;
        try {
            objectUnderTest.setGoogleTask(task);
            objectUnderTest.run();
            actualRelevance = objectUnderTest.isRelevant();
        } catch (final PccException exception) {
            LOGGER.error("", exception);
            Assert.fail(exception.getMessage());
        }
        return actualRelevance;
    }
}
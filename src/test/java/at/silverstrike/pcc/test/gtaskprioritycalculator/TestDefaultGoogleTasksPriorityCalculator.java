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

package at.silverstrike.pcc.test.gtaskprioritycalculator;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.altruix.commons.api.di.PccException;

import com.google.api.services.tasks.v1.model.Task;

import at.silverstrike.pcc.api.gtaskprioritycalculator.GoogleTasksPriorityCalculator;
import at.silverstrike.pcc.api.gtaskprioritycalculator.GoogleTasksPriorityCalculatorFactory;
import at.silverstrike.pcc.impl.gtaskprioritycalculator.DefaultGoogleTasksPriorityCalculatorFactory;

/**
 * @author DP118M
 * 
 */
public class TestDefaultGoogleTasksPriorityCalculator {
    private static final String ID_2 = "MTE5OTY3NjA1Mjc5NDc1OTc1NjI6MDoxMg";
    private static final String ID_1 = "MTE5OTY3NjA1Mjc5NDc1OTc1NjI6MDo5";
    private static final Logger LOGGER = LoggerFactory
            .getLogger(TestDefaultGoogleTasksPriorityCalculator.class);

    @Test
    public void testTwoTasks() {
        final GoogleTasksPriorityCalculatorFactory factory =
                new DefaultGoogleTasksPriorityCalculatorFactory();
        final GoogleTasksPriorityCalculator objectUnderTest = factory.create();

        final Map<String, com.google.api.services.tasks.v1.model.Task> inputData =
                new HashMap<String, com.google.api.services.tasks.v1.model.Task>();

        final com.google.api.services.tasks.v1.model.Task task1 = new Task();
        task1.set("title", "Project 1");
        task1.set("id", ID_1);
        task1.set("position", "00000000001073741823");

        final com.google.api.services.tasks.v1.model.Task task2 = new Task();
        task2.set("title", "Project 2");
        task2.set("id", ID_2);
        task2.set("position", "00000000001610612735");

        inputData.put(ID_1, task1);
        inputData.put(ID_2, task2);

        try {
            objectUnderTest.setTasks(inputData);
            objectUnderTest.run();
            final Map<String, Long> prioritiesByTaskIds =
                    objectUnderTest.getPrioritiesByTaskIds();

            Assert.assertEquals(new Long(1000L), prioritiesByTaskIds.get(ID_1));
            Assert.assertEquals(new Long(900L), prioritiesByTaskIds.get(ID_2));
        } catch (final PccException exception) {
            LOGGER.error("", exception);
            Assert.fail(exception.getMessage());
        }
    }
}

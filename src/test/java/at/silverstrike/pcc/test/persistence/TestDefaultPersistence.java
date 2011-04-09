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

package at.silverstrike.pcc.test.persistence;

import static junit.framework.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.model.ProcessState;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.impl.persistence.DefaultPersistence;

public class TestDefaultPersistence {
    @Test
    public final void test01() {
        final Persistence persistence = new DefaultPersistence();

        try {
            persistence.openSession();
        } catch (final RuntimeException exception) {
            Assert.fail(exception.getMessage());
        } catch (final Exception exception) {
            Assert.fail(exception.getMessage());
        }

        try {
            persistence.getDailyPlan(new Date(), "DP");
        } catch (final RuntimeException exception) {
            Assert.fail(exception.getMessage());
        } catch (final Exception exception) {
            Assert.fail(exception.getMessage());
        }

    }

    @Test
    public final void testDefect57() {
        // Get object under test (persistence)
        final Persistence persistence = new DefaultPersistence();

        try {
            persistence.openSession();
        } catch (final RuntimeException exception) {
            Assert.fail(exception.getMessage());
        } catch (final Exception exception) {
            Assert.fail(exception.getMessage());
        }

        // Clear database
        persistence.clearDatabase();

        // Add one task
        final Long taskId = persistence.createTask("Bug #57");

        // Verify it's present in the list of not attained tasks
        final List<SchedulingObject> processList1 =
                persistence.getAllNotDeletedTasks();

        Assert.assertEquals(1, processList1.size());

        // Mark the task as attained
        final Task process = persistence.getTask(taskId);
        Assert.assertNotNull(process);

        process.setState(ProcessState.ATTAINED);
        persistence.updateTask(process);

        // Verify it's not present in the list of not attained tasks
        final List<SchedulingObject> processList2 =
                persistence.getAllNotDeletedTasks();

        Assert.assertEquals(0, processList2.size());

        Assert.assertEquals(0, persistence.getChildTasks((Long) null).size());
        Assert.assertNotNull(persistence.getSubProcessesWithChildren((Long) null));
        Assert.assertEquals(0, persistence.getSubProcessesWithChildren((Long) null).size());
    }
    
    @Test
    public final void testEventCreation()
    {
        // Get object under test (persistence)
        final Persistence persistence = new DefaultPersistence();

        try {
            persistence.openSession();
        } catch (final RuntimeException exception) {
            Assert.fail(exception.getMessage());
        } catch (final Exception exception) {
            Assert.fail(exception.getMessage());
        }

        // Clear database
        persistence.clearDatabase();

        // Количество объектов должно быть нулевым
        assertEquals(0, persistence.getSubProcessesWithChildren(null).size());
        
        // Создаём событие
        persistence.createSubEvent("test event", null);
        
        // Проверяем - создалось ли событие
        assertEquals(1, persistence.getSubProcessesWithChildren(null).size());
    }
}

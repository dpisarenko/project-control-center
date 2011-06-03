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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.silverstrike.pcc.api.model.Event;
import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.model.ProcessState;
import at.silverstrike.pcc.api.model.UserData;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.impl.persistence.DefaultPersistence;

public class TestDefaultPersistence {
    private static final Logger LOGGER =
            LoggerFactory
                    .getLogger(TestDefaultPersistence.class);

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

        // Fetch super user
        persistence.createSuperUser();
        final UserData user = persistence.getUser(Persistence.SUPER_USER_NAME,
                Persistence.SUPER_USER_PASSWORD);

        // Add one task
        final Long taskId = persistence.createTask("Bug #57");

        // Verify it's present in the list of not attained tasks
        final List<SchedulingObject> processList1 =
                persistence.getAllNotDeletedTasks(null);

        Assert.assertEquals(1, processList1.size());

        // Mark the task as attained
        final Task process = persistence.getTask(taskId);
        Assert.assertNotNull(process);

        process.setState(ProcessState.ATTAINED);
        persistence.updateTask(process);

        // Verify it's not present in the list of not attained tasks
        final List<SchedulingObject> processList2 =
                persistence.getAllNotDeletedTasks(user);

        Assert.assertEquals(0, processList2.size());
        Assert.assertEquals(0, persistence.getChildTasks((Long) null).size());
        final List<SchedulingObject> topLevelProcesses =
                persistence.getSubProcessesWithChildren(
                        (Long) null, user);
        Assert.assertNotNull(topLevelProcesses);
        Assert.assertEquals(0,
                topLevelProcesses.size());
    }

    @Test
    public final void testEventCreation() {
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

        // Create/fetch super user
        persistence.createSuperUser();
        final UserData user =
                persistence.getUser(Persistence.SUPER_USER_NAME,
                        Persistence.SUPER_USER_PASSWORD);

        // Количество объектов должно быть нулевым
        assertEquals(0, persistence.getSubProcessesWithChildren(null, user)
                .size());

        // Создаём событие
        persistence.createSubEvent("test event", null);

        // Проверяем - создалось ли событие
        assertEquals(1, persistence.getSubProcessesWithChildren(null, user)
                .size());
    }

    /**
     * Эта проверочная программа проверяет наличие следующей ошибки при попытке
     * удалить событие.
     * 
     * <pre>
     * Caused by: java.sql.SQLIntegrityConstraintViolationException: DELETE on table 'T
     * BL_SCHEDULING_OBJECT' caused a violation of foreign key constraint 'FK1F4FD5E95B
     * 8C45B3' for key (38).  The statement has been rolled back.
     *         at org.apache.derby.impl.jdbc.SQLExceptionFactory40.getSQLException(Unkn
     * own Source) ~[derby-10.6.2.1.jar:na]
     *         at org.apache.derby.impl.jdbc.Util.generateCsSQLException(Unknown Source
     * ) ~[derby-10.6.2.1.jar:na]
     *         at org.apache.derby.impl.jdbc.TransactionResourceImpl.wrapInSQLException
     * (Unknown Source) ~[derby-10.6.2.1.jar:na]
     *         at org.apache.derby.impl.jdbc.TransactionResourceImpl.handleException(Un
     * known Source) ~[derby-10.6.2.1.jar:na]
     *         at org.apache.derby.impl.jdbc.EmbedConnection.handleException(Unknown So
     * urce) ~[derby-10.6.2.1.jar:na]
     *         at org.apache.derby.impl.jdbc.ConnectionChild.handleException(Unknown So
     * urce) ~[derby-10.6.2.1.jar:na]
     *         at org.apache.derby.impl.jdbc.EmbedStatement.executeStatement(Unknown So
     * urce) ~[derby-10.6.2.1.jar:na]
     *         at org.apache.derby.impl.jdbc.EmbedPreparedStatement.executeStatement(Un
     * known Source) ~[derby-10.6.2.1.jar:na]
     *         at org.apache.derby.impl.jdbc.EmbedPreparedStatement.executeUpdate(Unkno
     * wn Source) ~[derby-10.6.2.1.jar:na]
     *         at org.hibernate.jdbc.NonBatchingBatcher.addToBatch(NonBatchingBatcher.j
     * ava:46) ~[hibernate-core-3.6.0.Final.jar:3.6.0.Final]
     *         at org.hibernate.persister.entity.AbstractEntityPersister.delete(Abstrac
     * tEntityPersister.java:2689) ~[hibernate-core-3.6.0.Final.jar:3.6.0.Final]
     *         ... 45 common frames omitted
     * Caused by: org.apache.derby.impl.jdbc.EmbedSQLException: DELETE on table 'TBL_SC
     * HEDULING_OBJECT' caused a violation of foreign key constraint 'FK1F4FD5E95B8C45B
     * 3' for key (38).  The statement has been rolled back.
     *         at org.apache.derby.impl.jdbc.SQLExceptionFactory.getSQLException(Unknow
     * n Source) ~[derby-10.6.2.1.jar:na]
     *         at org.apache.derby.impl.jdbc.SQLExceptionFactory40.wrapArgsForTransport
     * AcrossDRDA(Unknown Source) ~[derby-10.6.2.1.jar:na]
     *         ... 56 common frames omitted
     * Caused by: org.apache.derby.iapi.error.StandardException: DELETE on table 'TBL_S
     * CHEDULING_OBJECT' caused a violation of foreign key constraint 'FK1F4FD5E95B8C45
     * B3' for key (38).  The statement has been rolled back.
     *         at org.apache.derby.iapi.error.StandardException.newException(Unknown So
     * urce) ~[derby-10.6.2.1.jar:na]
     *         at org.apache.derby.impl.sql.execute.ReferencedKeyRIChecker.doCheck(Unkn
     * own Source) ~[derby-10.6.2.1.jar:na]
     *         at org.apache.derby.impl.sql.execute.RISetChecker.doPKCheck(Unknown Sour
     * ce) ~[derby-10.6.2.1.jar:na]
     *         at org.apache.derby.impl.sql.execute.DeleteResultSet.runFkChecker(Unknow
     * n Source) ~[derby-10.6.2.1.jar:na]
     *         at org.apache.derby.impl.sql.execute.DeleteResultSet.open(Unknown Source
     * ) ~[derby-10.6.2.1.jar:na]
     *         at org.apache.derby.impl.sql.GenericPreparedStatement.executeStmt(Unknow
     * n Source) ~[derby-10.6.2.1.jar:na]
     *         at org.apache.derby.impl.sql.GenericPreparedStatement.execute(Unknown So
     * urce) ~[derby-10.6.2.1.jar:na]
     *         ... 50 common frames omitted
     * </pre>
     */
    @Test
    public void testEventDeletion() {
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

        // Создаём событие
        final Task project = persistence.createSubTask("project", null, null);
        final Event event = persistence.createSubEvent("test event", null);

        Assert.assertEquals(2,
                persistence.getSubProcessesWithChildren(null, null).size());

        // Удаляем и смотрим - вылетает ли исключение
        try {
            persistence.deleteEvent(event);
        } catch (final Exception exception) {
            LOGGER.error("", exception);
            Assert.fail(exception.getMessage());
        }

        Assert.assertEquals(1,
                persistence.getSubProcessesWithChildren(null, null).size());
    }
}

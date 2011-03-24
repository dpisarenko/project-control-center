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

package at.silverstrike.pcc.impl.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Application Lifecycle Listener implementation class
 * DatabaseStartStopServletContextListener
 * 
 */
public class DatabaseStartStopServletContextListener implements
        ServletContextListener {
    private static final String JDBC_CONN_STRING_SHUTDOWN = "jdbc:derby:"
            + DefaultPersistence.DB_NAME + ";shutdown=true";

    private static final Logger LOGGER = LoggerFactory
            .getLogger(DatabaseStartStopServletContextListener.class);

    /**
     * Default constructor.
     */
    public DatabaseStartStopServletContextListener() {
    }

    /**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(final ServletContextEvent aEvent) {
    }

    /**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public final void contextDestroyed(final ServletContextEvent aEvent) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(JDBC_CONN_STRING_SHUTDOWN);
        } catch (final SQLException exception) {
            LOGGER.error("An error occured while trying to shutdown Derby.",
                    exception);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (final SQLException exception) {
                    LOGGER.error(
                            "An error occured while trying to shutdown Derby.",
                            exception);
                }
            }

        }
    }

}

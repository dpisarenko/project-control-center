/**
 * This file is part of Project Control Center (PCC).
 * 
 * Project Control Center (PCC) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Project Control Center (PCC) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Project Control Center (PCC).  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2010 Dmitri Anatol'evich Pisarenko
 **/

package at.silverstrike.pcc.impl.persistence;

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
    private static final String JDBC_CONN_STRING_SHUTDOWN =
            "jdbc:derby:" + DefaultPersistence.DB_NAME + ";shutdown=true";

    private Logger LOGGER =
        LoggerFactory.getLogger(DatabaseStartStopServletContextListener.class);

    /**
     * Default constructor.
     */
    public DatabaseStartStopServletContextListener() {
    }

    /**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(final ServletContextEvent anEvent) {
    }

    /**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(final ServletContextEvent anEvent) {
        try {
            DriverManager.getConnection(JDBC_CONN_STRING_SHUTDOWN);

        } catch (final SQLException exception) {
            LOGGER.error("An error occured while trying to shutdown Derby.",
                    exception);
        }
    }

}

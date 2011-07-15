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

package at.silverstrike.pcc;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.altruix.commons.api.di.PccException;

import com.google.inject.Injector;

import at.silverstrike.pcc.api.automaticexport.AutomaticExporter;
import at.silverstrike.pcc.api.automaticexport.AutomaticExporterFactory;
import at.silverstrike.pcc.api.persistence.Persistence;

/**
 * Application Lifecycle Listener implementation class
 * DatabaseStartStopServletContextListener
 * 
 */
public class DatabaseStartStopServletContextListener implements
        ServletContextListener {
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
        final Injector injector =
                (Injector) aEvent.getServletContext().getAttribute(
                        ProjectControlCenterApplication.PARAM_INJECTOR);

        LOGGER.debug("injector: {}", injector);

        final AutomaticExporterFactory factory =
                injector.getInstance(AutomaticExporterFactory.class);
        final AutomaticExporter exporter = factory.create();

        exporter.setInjector(injector);
        try {
            exporter.run();
        } catch (final PccException exception) {
            LOGGER.error("", exception);
        }

        shutdownDatabase(injector);
    }

    private void shutdownDatabase(final Injector injector) {
        final Persistence persistence = injector.getInstance(Persistence.class);
        persistence.closeSession();
    }
}

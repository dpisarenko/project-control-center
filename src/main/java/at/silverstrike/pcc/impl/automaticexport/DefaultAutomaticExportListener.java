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

package at.silverstrike.pcc.impl.automaticexport;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.altruix.commons.api.di.PccException;

import at.silverstrike.pcc.ProjectControlCenterApplication;
import at.silverstrike.pcc.api.automaticexport.AutomaticExporter;
import at.silverstrike.pcc.api.automaticexport.AutomaticExporterFactory;

import com.google.inject.Injector;

/**
 * @author DP118M
 * 
 */
public final class DefaultAutomaticExportListener implements
        HttpSessionListener {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultAutomaticExportListener.class);

    @Override
    public void sessionCreated(final HttpSessionEvent aEvent) {
    }

    @Override
    public void sessionDestroyed(final HttpSessionEvent aEvent) {
        final Injector injector =
                (Injector) aEvent.getSession().getAttribute(
                        ProjectControlCenterApplication.PARAM_INJECTOR);
        final AutomaticExporterFactory factory =
                injector.getInstance(AutomaticExporterFactory.class);
        final AutomaticExporter exporter = factory.create();

        exporter.setInjector(injector);
        try {
            exporter.run();
        } catch (final PccException exception) {
            LOGGER.error(ErrorCodes.M_001_EXPORT_FAILURE, exception);
        }
    }

}

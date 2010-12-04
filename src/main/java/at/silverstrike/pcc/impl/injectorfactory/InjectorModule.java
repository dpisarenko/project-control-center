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

package at.silverstrike.pcc.impl.injectorfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.dailyplanpanel.DailyPlanPanelFactory;
import at.silverstrike.pcc.api.debugids.DebugIdRegistry;
import at.silverstrike.pcc.api.editingprocesspanel.EditingProcessPanelFactory;
import at.silverstrike.pcc.api.embeddedfilereading.EmbeddedFileReader;
import at.silverstrike.pcc.api.estimatedcompletiontimespanel.EstimatedCompletionTimesPanelFactory;
import at.silverstrike.pcc.api.export2tj3.TaskJuggler3Exporter;
import at.silverstrike.pcc.api.mainprocesseditingpanel.MainProcessEditingPanelFactory;
import at.silverstrike.pcc.api.mainwindow.MainWindowFactory;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.processpanel.ProcessPanelFactory;
import at.silverstrike.pcc.api.projectscheduler.ProjectScheduler;
import at.silverstrike.pcc.api.projectscheduler.ProjectSchedulerFactory;
import at.silverstrike.pcc.api.schedulingpanel.SchedulingPanelFactory;
import at.silverstrike.pcc.api.tj3bookingsparser.BookingsFile2BookingsFactory;
import at.silverstrike.pcc.api.tj3bookingsparser.Tj3BookingsParserFactory;
import at.silverstrike.pcc.api.tj3deadlinesparser.Tj3DeadlinesFileParserFactory;
import at.silverstrike.pcc.api.version.PccVersionReader;
import at.silverstrike.pcc.api.version.PccVersionReaderFactory;
import at.silverstrike.pcc.api.webguibus.WebGuiBus;
import at.silverstrike.pcc.api.webguibus.WebGuiBusMessageFactory;
import at.silverstrike.pcc.api.workerpanel.WorkerPanelFactory;
import at.silverstrike.pcc.impl.dailyplanpanel.DefaultDailyPlanPanelFactory;
import at.silverstrike.pcc.impl.debugids.DefaultDebugIdRegistryFactory;
import at.silverstrike.pcc.impl.editingprocesspanel.DefaultEditingProcessPanelFactory;
import at.silverstrike.pcc.impl.embeddedfilereading.DefaultEmbeddedFileReaderFactory;
import at.silverstrike.pcc.impl.estimatedcompletiontimespanel.DefaultEstimatedCompletionTimesPanelFactory;
import at.silverstrike.pcc.impl.export2tj3.DefaultTaskJuggler3ExporterFactory;
import at.silverstrike.pcc.impl.jruby.DefaultJRubySandBoxFactory;
import at.silverstrike.pcc.impl.mainprocesseditingpanel.DefaultMainProcessEditingPanelFactory;
import at.silverstrike.pcc.impl.mainwindow.DefaultMainWindowFactory;
import at.silverstrike.pcc.impl.persistence.DefaultPersistence;
import at.silverstrike.pcc.impl.processpanel.DefaultProcessPanelFactory;
import at.silverstrike.pcc.impl.projectscheduler.DefaultProjectSchedulerFactory;
import at.silverstrike.pcc.impl.schedulingpanel.DefaultSchedulingPanelFactory;
import at.silverstrike.pcc.impl.tj3bookingsparser.DefaultBookingsFile2BookingsFactory;
import at.silverstrike.pcc.impl.tj3bookingsparser.DefaultTj3BookingsParserFactory;
import at.silverstrike.pcc.impl.tj3deadlinesparser.DefaultTj3DeadlinesFileParserFactory;
import at.silverstrike.pcc.impl.version.DefaultPccVersionReaderFactory;
import at.silverstrike.pcc.impl.webguibus.DefaultWebGuiBusFactory;
import at.silverstrike.pcc.impl.webguibus.DefaultWebGuiBusMessageFactory;
import at.silverstrike.pcc.impl.workerpanel.DefaultWorkerPanelFactory;

import com.google.inject.AbstractModule;

class InjectorModule extends AbstractModule {
    private final Logger LOGGER =
        LoggerFactory.getLogger(InjectorModule.class);
    
    @Override
    protected void configure() {
        bind(ProcessPanelFactory.class).toInstance(
                new DefaultProcessPanelFactory());
        bind(Persistence.class).toInstance(new DefaultPersistence());
        bind(MainWindowFactory.class)
                .toInstance(new DefaultMainWindowFactory());
        bind(EditingProcessPanelFactory.class).toInstance(
                new DefaultEditingProcessPanelFactory());
        bind(WorkerPanelFactory.class).toInstance(
                new DefaultWorkerPanelFactory());
        bind(SchedulingPanelFactory.class).toInstance(
                new DefaultSchedulingPanelFactory());
        bind(DefaultJRubySandBoxFactory.class).toInstance(
                new DefaultJRubySandBoxFactory());
        bind(EmbeddedFileReader.class).toInstance(
                new DefaultEmbeddedFileReaderFactory().create());
        bind(BookingsFile2BookingsFactory.class).toInstance(
                new DefaultBookingsFile2BookingsFactory());
        bind(TaskJuggler3Exporter.class).toInstance(
                new DefaultTaskJuggler3ExporterFactory().create());
        bind(Tj3DeadlinesFileParserFactory.class).toInstance(
                new DefaultTj3DeadlinesFileParserFactory());
        bind(Tj3BookingsParserFactory.class).toInstance(
                new DefaultTj3BookingsParserFactory());
        bind(ProjectScheduler.class).toInstance(
                new DefaultProjectSchedulerFactory().create());
        bind(EstimatedCompletionTimesPanelFactory.class).toInstance(
                new DefaultEstimatedCompletionTimesPanelFactory());
        bind(DailyPlanPanelFactory.class).toInstance(
                new DefaultDailyPlanPanelFactory());
        bind(MainProcessEditingPanelFactory.class).toInstance(
                new DefaultMainProcessEditingPanelFactory());
        bind(ProjectSchedulerFactory.class).toInstance(
                new DefaultProjectSchedulerFactory());
        bind(DebugIdRegistry.class).toInstance(
                new DefaultDebugIdRegistryFactory().create());
        bind(WebGuiBus.class)
                .toInstance(new DefaultWebGuiBusFactory().create());
        bind(WebGuiBusMessageFactory.class).toInstance(
                new DefaultWebGuiBusMessageFactory());        
        bind(PccVersionReader.class).toInstance(getVersionReader());
        
    }

    private PccVersionReader getVersionReader() {
        final PccVersionReaderFactory factory = new DefaultPccVersionReaderFactory();
        final PccVersionReader versionReader = factory.create();
        try {
            versionReader.run();
        } catch (final PccException exception) {
            LOGGER.error(ErrorCodes.M_001_VERSION_READER, exception);
        }
        return versionReader;
    }
}

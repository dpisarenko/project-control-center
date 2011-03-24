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

package at.silverstrike.pcc.impl.injectorfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.silverstrike.pcc.api.centraleditingpanel.CentralEditingPanelFactory;
import at.silverstrike.pcc.api.centraleditingpanelcontroller.CentralEditingPanelControllerFactory;
import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.culture2lang.CultureToLanguageMapper;
import at.silverstrike.pcc.api.culture2lang.CultureToLanguageMapperFactory;
import at.silverstrike.pcc.api.dailyplanpanel.DailyPlanPanelFactory;
import at.silverstrike.pcc.api.debugids.DebugIdRegistry;
import at.silverstrike.pcc.api.dependencieseditingwindow.DependenciesEditingPanelFactory;
import at.silverstrike.pcc.api.editingprocesspanel.EditingProcessPanelFactory;
import at.silverstrike.pcc.api.embeddedfilereading.EmbeddedFileReader;
import at.silverstrike.pcc.api.entrywindow.EntryWindowFactory;
import at.silverstrike.pcc.api.estimatedcompletiontimespanel.EstimatedCompletionTimesPanelFactory;
import at.silverstrike.pcc.api.export2tj3.TaskJuggler3Exporter;
import at.silverstrike.pcc.api.graphdemopanel.GraphDemoPanelFactory;
import at.silverstrike.pcc.api.mainwindow.MainWindowFactory;
import at.silverstrike.pcc.api.meetingeditingpanel.MeetingEditingPanelFactory;
import at.silverstrike.pcc.api.milestoneeditingpanel.MilestoneEditingPanelFactory;
import at.silverstrike.pcc.api.milestoneeditingpanelcontroller.MilestoneEditingPanelController;
import at.silverstrike.pcc.api.openid.Deauthenticator;
import at.silverstrike.pcc.api.openid.DeauthenticatorFactory;
import at.silverstrike.pcc.api.openid.OpenIdAuthenticationInitiator;
import at.silverstrike.pcc.api.openid.OpenIdAuthenticationInitiatorFactory;
import at.silverstrike.pcc.api.openid.OpenIdAuthenticationResponder;
import at.silverstrike.pcc.api.openid.OpenIdAuthenticationResponderFactory;
import at.silverstrike.pcc.api.parameterdatareader.ParameterDataReader;
import at.silverstrike.pcc.api.parameterdatareader.ParameterDataReaderFactory;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.processpanel.ProcessPanelFactory;
import at.silverstrike.pcc.api.projectscheduler.ProjectScheduler;
import at.silverstrike.pcc.api.projectscheduler.ProjectSchedulerFactory;
import at.silverstrike.pcc.api.schedulingpanel.SchedulingPanelFactory;
import at.silverstrike.pcc.api.taskeditingpanel.TaskEditingPanelFactory;
import at.silverstrike.pcc.api.testtablecreator.TestTableCreator;
import at.silverstrike.pcc.api.tj3bookingsparser.BookingsFile2BookingsFactory;
import at.silverstrike.pcc.api.tj3bookingsparser.Tj3BookingsParserFactory;
import at.silverstrike.pcc.api.tj3deadlinesparser.Tj3DeadlinesFileParserFactory;
import at.silverstrike.pcc.api.version.PccVersionReader;
import at.silverstrike.pcc.api.version.PccVersionReaderFactory;
import at.silverstrike.pcc.api.webguibus.WebGuiBus;
import at.silverstrike.pcc.api.webguibus.WebGuiBusMessageFactory;
import at.silverstrike.pcc.api.workerpanel.WorkerPanelFactory;
import at.silverstrike.pcc.api.xmlserialization.XmlDeserializerFactory;
import at.silverstrike.pcc.api.xmlserialization.XmlSerializerFactory;
import at.silverstrike.pcc.impl.centraleditingpanel.DefaultCentralEditingPanelFactory;
import at.silverstrike.pcc.impl.centraleditingpanelcontroller.
    DefaultCentralEditingPanelControllerFactory;
import at.silverstrike.pcc.impl.culture2lang.DefaultCultureToLanguageMapperFactory;
import at.silverstrike.pcc.impl.dailyplanpanel.DefaultDailyPlanPanelFactory;
import at.silverstrike.pcc.impl.debugids.DefaultDebugIdRegistryFactory;
import at.silverstrike.pcc.impl.dependencieseditingwindow.DefaultDependenciesEditingPanelFactory;
import at.silverstrike.pcc.impl.editingprocesspanel.DefaultEditingProcessPanelFactory;
import at.silverstrike.pcc.impl.embeddedfilereading.
    DefaultEmbeddedFileReaderFactory;
import at.silverstrike.pcc.impl.entrywindow.DefaultEntryWindowFactory;
import at.silverstrike.pcc.impl.estimatedcompletiontimespanel.
    DefaultEstimatedCompletionTimesPanelFactory;
import at.silverstrike.pcc.impl.export2tj3.DefaultTaskJuggler3ExporterFactory;
import at.silverstrike.pcc.impl.graphdemopanel.DefaultGraphDemoPanelFactory;
import at.silverstrike.pcc.impl.jruby.DefaultJRubySandBoxFactory;
import at.silverstrike.pcc.impl.mainwindow.DefaultMainWindowFactory;
import at.silverstrike.pcc.impl.meetingeditingpanel.DefaultMeetingEditingPanelFactory;
import at.silverstrike.pcc.impl.milestoneeditingpanel.DefaultMilestoneEditingPanelFactory;
import at.silverstrike.pcc.impl.milestoneeditingpanelcontroller.DefaultMilestoneEditingPanelControllerFactory;
import at.silverstrike.pcc.impl.openid.DefaultDeauthenticatorFactory;
import at.silverstrike.pcc.impl.openid.DefaultOpenIdAuthenticationInitiatorFactory;
import at.silverstrike.pcc.impl.openid.DefaultOpenIdAuthenticationResponderFactory;
import at.silverstrike.pcc.impl.parameterdatareader.DefaultParameterDataReaderFactory;
import at.silverstrike.pcc.impl.persistence.DefaultPersistence;
import at.silverstrike.pcc.impl.processpanel.DefaultProcessPanelFactory;
import at.silverstrike.pcc.impl.projectscheduler.DefaultProjectSchedulerFactory;
import at.silverstrike.pcc.impl.schedulingpanel.DefaultSchedulingPanelFactory;
import at.silverstrike.pcc.impl.taskeditingpanel.DefaultTaskEditingPanelFactory;
import at.silverstrike.pcc.impl.testtablecreator.DefaultTestTableCreatorFactory;
import at.silverstrike.pcc.impl.tj3bookingsparser.DefaultBookingsFile2BookingsFactory;
import at.silverstrike.pcc.impl.tj3bookingsparser.DefaultTj3BookingsParserFactory;
import at.silverstrike.pcc.impl.tj3deadlinesparser.DefaultTj3DeadlinesFileParserFactory;
import at.silverstrike.pcc.impl.version.DefaultPccVersionReaderFactory;
import at.silverstrike.pcc.impl.webguibus.DefaultWebGuiBusFactory;
import at.silverstrike.pcc.impl.webguibus.DefaultWebGuiBusMessageFactory;
import at.silverstrike.pcc.impl.workerpanel.DefaultWorkerPanelFactory;
import at.silverstrike.pcc.impl.xmlserialization.DefaultXmlDeserializerFactory;
import at.silverstrike.pcc.impl.xmlserialization.DefaultXmlSerializerFactory;

import com.google.inject.AbstractModule;

class InjectorModule extends AbstractModule {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(InjectorModule.class);

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
        bind(ProjectSchedulerFactory.class).toInstance(
                new DefaultProjectSchedulerFactory());
        bind(DebugIdRegistry.class).toInstance(
                new DefaultDebugIdRegistryFactory().create());
        bind(WebGuiBus.class)
                .toInstance(new DefaultWebGuiBusFactory().create());
        bind(WebGuiBusMessageFactory.class).toInstance(
                new DefaultWebGuiBusMessageFactory());
        bind(PccVersionReader.class).toInstance(getVersionReader());
        bind(XmlSerializerFactory.class).toInstance(
                new DefaultXmlSerializerFactory());
        bind(XmlDeserializerFactory.class).toInstance(
                new DefaultXmlDeserializerFactory());
        bind(EntryWindowFactory.class).toInstance(
                new DefaultEntryWindowFactory());
        bind(ParameterDataReader.class).toInstance(getParameterDataReader());
        bind(CultureToLanguageMapper.class).toInstance(
                getCultureToLanguageMapper());
        bind(OpenIdAuthenticationResponder.class).toInstance(
                getOpenIdAuthenticationResponder());
        bind(OpenIdAuthenticationInitiator.class).toInstance(
                getOpenIdAuthenticationInitiator());
        bind(Deauthenticator.class).toInstance(getDeauthenticator());
        bind(CentralEditingPanelFactory.class).toInstance(
                new DefaultCentralEditingPanelFactory());
        bind(DependenciesEditingPanelFactory.class).toInstance(
                new DefaultDependenciesEditingPanelFactory());
        bind(TestTableCreator.class).toInstance(
                getTestTableCreator());
        bind(GraphDemoPanelFactory.class).toInstance(
                new DefaultGraphDemoPanelFactory());
        bind(TaskEditingPanelFactory.class).toInstance(
                new DefaultTaskEditingPanelFactory());
        bind(MeetingEditingPanelFactory.class).toInstance(
                new DefaultMeetingEditingPanelFactory());
        bind(MilestoneEditingPanelFactory.class).toInstance(
                new DefaultMilestoneEditingPanelFactory());
        bind(CentralEditingPanelControllerFactory.class).toInstance(new 
                DefaultCentralEditingPanelControllerFactory());
        bind(MilestoneEditingPanelController.class).toInstance(new 
                DefaultMilestoneEditingPanelControllerFactory().create());
    }

    private TestTableCreator getTestTableCreator() {
        return new DefaultTestTableCreatorFactory().create();
    }

    private Deauthenticator getDeauthenticator() {
        final DeauthenticatorFactory factory =
                new DefaultDeauthenticatorFactory();
        return factory.create();
    }

    private OpenIdAuthenticationInitiator getOpenIdAuthenticationInitiator() {
        final OpenIdAuthenticationInitiatorFactory factory =
                new DefaultOpenIdAuthenticationInitiatorFactory();
        return factory.create();
    }

    private OpenIdAuthenticationResponder getOpenIdAuthenticationResponder() {
        final OpenIdAuthenticationResponderFactory factory =
                new DefaultOpenIdAuthenticationResponderFactory();
        return factory.create();
    }

    private CultureToLanguageMapper getCultureToLanguageMapper() {
        final CultureToLanguageMapperFactory factory =
                new DefaultCultureToLanguageMapperFactory();
        return factory.create();
    }

    private ParameterDataReader getParameterDataReader() {
        final ParameterDataReaderFactory factory =
                new DefaultParameterDataReaderFactory();
        return factory.create();
    }

    private PccVersionReader getVersionReader() {
        final PccVersionReaderFactory factory =
                new DefaultPccVersionReaderFactory();
        final PccVersionReader versionReader = factory.create();
        try {
            versionReader.run();
        } catch (final PccException exception) {
            LOGGER.error(ErrorCodes.M_001_VERSION_READER, exception);
        }
        return versionReader;
    }
}

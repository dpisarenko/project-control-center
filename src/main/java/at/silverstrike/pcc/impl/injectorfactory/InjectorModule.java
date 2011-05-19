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

import ru.altruix.commons.api.di.PccException;
import ru.altruix.commons.api.version.PccVersionReader;
import ru.altruix.commons.api.version.PccVersionReaderFactory;
import ru.altruix.commons.impl.version.DefaultPccVersionReaderFactory;

import at.silverstrike.pcc.api.automaticexport.AutomaticExporterFactory;
import at.silverstrike.pcc.api.calendarpanel.CalendarPanelFactory;
import at.silverstrike.pcc.api.calendarpanelcontroller.CalendarPanelControllerFactory;
import at.silverstrike.pcc.api.centraleditingpanel.CentralEditingPanelFactory;
import at.silverstrike.pcc.api.centraleditingpanelbuttonstate.CentralEditingPanelButtonStateCalculatorFactory;
import at.silverstrike.pcc.api.centraleditingpanelcontroller.CentralEditingPanelControllerFactory;
import at.silverstrike.pcc.api.culture2lang.CultureToLanguageMapper;
import at.silverstrike.pcc.api.culture2lang.CultureToLanguageMapperFactory;
import at.silverstrike.pcc.api.dailyplanpanel.DailyPlanPanelFactory;
import at.silverstrike.pcc.api.debugids.PccDebugIdRegistry;
import at.silverstrike.pcc.api.dependencieseditingdialog.DependenciesEditingDialogFactory;
import at.silverstrike.pcc.api.dependencieseditingdialogcontroller.DependenciesEditingDialogControllerFactory;
import at.silverstrike.pcc.api.dependencieseditingpanel.DependenciesEditingPanelFactory;
import at.silverstrike.pcc.api.dependencieseditingpanelcontroller.DependenciesEditingPanelControllerFactory;
import at.silverstrike.pcc.api.dependencytablefiller.DependencyTableFillerFactory;
import at.silverstrike.pcc.api.embeddedfilereading.EmbeddedFileReader;
import at.silverstrike.pcc.api.entrywindow.EntryWindowFactory;
import at.silverstrike.pcc.api.estimatedcompletiontimespanel.EstimatedCompletionTimesPanelFactory;
import at.silverstrike.pcc.api.eventeditingpanel.EventEditingPanelFactory;
import at.silverstrike.pcc.api.eventeditingpanelcontroller.EventEditingPanelController;
import at.silverstrike.pcc.api.eventeditingpanelcontroller.EventEditingPanelControllerFactory;
import at.silverstrike.pcc.api.export2tj3.TaskJuggler3Exporter;
import at.silverstrike.pcc.api.graph2resource.Graph2ResourceConverter;
import at.silverstrike.pcc.api.incorrectschedulingobjectsmarker.IncorrectSchedulingObjectsMarkerFactory;
import at.silverstrike.pcc.api.mainwindow.MainWindowFactory;
import at.silverstrike.pcc.api.mainwindowcontroller.MainWindowControllerFactory;
import at.silverstrike.pcc.api.milestoneeditingpanel.MilestoneEditingPanelFactory;
import at.silverstrike.pcc.api.milestoneeditingpanelcontroller.MilestoneEditingPanelController;
import at.silverstrike.pcc.api.milestoneeditingpanelcontroller.MilestoneEditingPanelControllerFactory;
import at.silverstrike.pcc.api.openid.Deauthenticator;
import at.silverstrike.pcc.api.openid.DeauthenticatorFactory;
import at.silverstrike.pcc.api.openid.OpenIdAuthenticationInitiator;
import at.silverstrike.pcc.api.openid.OpenIdAuthenticationInitiatorFactory;
import at.silverstrike.pcc.api.openid.OpenIdAuthenticationResponder;
import at.silverstrike.pcc.api.openid.OpenIdAuthenticationResponderFactory;
import at.silverstrike.pcc.api.parameterdatareader.ParameterDataReader;
import at.silverstrike.pcc.api.parameterdatareader.ParameterDataReaderFactory;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.projectnetworkdatacreator.ProjectNetworkDataCreatorFactory;
import at.silverstrike.pcc.api.projectnetworkgraphcreator.ProjectNetworkGraphCreator;
import at.silverstrike.pcc.api.projectnetworkgraphpanel.ProjectNetworkGraphPanelFactory;
import at.silverstrike.pcc.api.projectscheduler.ProjectScheduler;
import at.silverstrike.pcc.api.projectscheduler.ProjectSchedulerFactory;
import at.silverstrike.pcc.api.projecttreemodel.ProjectTreeContainerFactory;
import at.silverstrike.pcc.api.schedulingguicontroller.SchedulingPanelControllerFactory;
import at.silverstrike.pcc.api.schedulingindicatorpanel.SchedulingIndicatorPanelFactory;
import at.silverstrike.pcc.api.taskeditingpanel.TaskEditingPanelFactory;
import at.silverstrike.pcc.api.taskeditingpanelcontroller.TaskEditingPanelController;
import at.silverstrike.pcc.api.taskeditingpanelcontroller.TaskEditingPanelControllerFactory;
import at.silverstrike.pcc.api.testtablecreator.TestTableCreator;
import at.silverstrike.pcc.api.tj3bookingsparser.BookingsFile2BookingsFactory;
import at.silverstrike.pcc.api.tj3bookingsparser.Tj3BookingsParserFactory;
import at.silverstrike.pcc.api.tj3deadlinesparser.Tj3DeadlinesFileParserFactory;
import at.silverstrike.pcc.api.webguibus.WebGuiBus;
import at.silverstrike.pcc.api.webguibus.WebGuiBusMessageFactory;
import at.silverstrike.pcc.api.workerpanel.WorkerPanelFactory;
import at.silverstrike.pcc.api.xmlserialization.XmlDeserializerFactory;
import at.silverstrike.pcc.api.xmlserialization.XmlSerializerFactory;
import at.silverstrike.pcc.impl.automaticexport.DefaultAutomaticExporterFactory;
import at.silverstrike.pcc.impl.calendarpanel.DefaultCalendarPanelFactory;
import at.silverstrike.pcc.impl.calendarpanelcontroller.DefaultCalendarPanelControllerFactory;
import at.silverstrike.pcc.impl.centraleditingpanel.DefaultCentralEditingPanelFactory;
import at.silverstrike.pcc.impl.centraleditingpanelbuttonstate.DefaultCentralEditingPanelButtonStateCalculatorFactory;
import at.silverstrike.pcc.impl.centraleditingpanelcontroller.DefaultCentralEditingPanelControllerFactory;
import at.silverstrike.pcc.impl.culture2lang.DefaultCultureToLanguageMapperFactory;
import at.silverstrike.pcc.impl.dailyplanpanel.DefaultDailyPlanPanelFactory;
import at.silverstrike.pcc.impl.debugids.DefaultPccDebugIdRegistryFactory;
import at.silverstrike.pcc.impl.dependencieseditingdialog.DefaultDependenciesEditingDialogFactory;
import at.silverstrike.pcc.impl.dependencieseditingpanel.DefaultDependenciesEditingPanelFactory;
import at.silverstrike.pcc.impl.dependencieseditingpanelcontroller.DefaultDependenciesEditingPanelControllerFactory;
import at.silverstrike.pcc.impl.dependencieseditingdialogcontroller.DefaultDependenciesEditingDialogControllerFactory;
import at.silverstrike.pcc.impl.dependencytablefiller.DefaultDependencyTableFillerFactory;
import at.silverstrike.pcc.impl.embeddedfilereading.DefaultEmbeddedFileReaderFactory;
import at.silverstrike.pcc.impl.entrywindow.DefaultEntryWindowFactory;
import at.silverstrike.pcc.impl.estimatedcompletiontimespanel.DefaultEstimatedCompletionTimesPanelFactory;
import at.silverstrike.pcc.impl.eventeditingpanel.DefaultMeetingEditingPanelFactory;
import at.silverstrike.pcc.impl.eventeditingpanelcontroller.DefaultEventEditingPanelControllerFactory;
import at.silverstrike.pcc.impl.export2tj3.DefaultTaskJuggler3ExporterFactory;
import at.silverstrike.pcc.impl.graph2resource.DefaultGraph2ResourceConverterFactory;
import at.silverstrike.pcc.impl.incorrectschedulingobjectsmarker.DefaultIncorrectSchedulingObjectsMarkerFactory;
import at.silverstrike.pcc.impl.jruby.DefaultJRubySandBoxFactory;
import at.silverstrike.pcc.impl.mainwindow.DefaultMainWindowFactory;
import at.silverstrike.pcc.impl.mainwindowcontroller.DefaultMainWindowControllerFactory;
import at.silverstrike.pcc.impl.milestoneeditingpanel.DefaultMilestoneEditingPanelFactory;
import at.silverstrike.pcc.impl.milestoneeditingpanelcontroller.DefaultMilestoneEditingPanelControllerFactory;
import at.silverstrike.pcc.impl.openid.DefaultDeauthenticatorFactory;
import at.silverstrike.pcc.impl.openid.DefaultOpenIdAuthenticationInitiatorFactory;
import at.silverstrike.pcc.impl.openid.DefaultOpenIdAuthenticationResponderFactory;
import at.silverstrike.pcc.impl.parameterdatareader.DefaultParameterDataReaderFactory;
import at.silverstrike.pcc.impl.persistence.DefaultPersistence;
import at.silverstrike.pcc.impl.projectnetworkdatacreator.DefaultProjectNetworkDataCreatorFactory;
import at.silverstrike.pcc.impl.projectnetworkgraphcreator.DefaultProjectNetworkGraphCreatorFactory;
import at.silverstrike.pcc.impl.projectnetworkgraphpanel.DefaultProjectNetworkGraphPanelFactory;
import at.silverstrike.pcc.impl.projectscheduler.DefaultProjectSchedulerFactory;
import at.silverstrike.pcc.impl.projecttreemodel.DefaultProjectTreeContainerFactory;
import at.silverstrike.pcc.impl.schedulingguicontroller.DefaultSchedulingPanelControllerFactory;
import at.silverstrike.pcc.impl.schedulingindicatorpanel.DefautSchedulingIndicatorPanelFactory;
import at.silverstrike.pcc.impl.taskeditingpanel.DefaultTaskEditingPanelFactory;
import at.silverstrike.pcc.impl.taskeditingpanelcontroller.DefaultTaskEditingPanelControllerFactory;
import at.silverstrike.pcc.impl.testtablecreator.DefaultTestTableCreatorFactory;
import at.silverstrike.pcc.impl.tj3bookingsparser.DefaultBookingsFile2BookingsFactory;
import at.silverstrike.pcc.impl.tj3bookingsparser.DefaultTj3BookingsParserFactory;
import at.silverstrike.pcc.impl.tj3deadlinesparser.DefaultTj3DeadlinesFileParserFactory;
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
        bind(Persistence.class).toInstance(new DefaultPersistence());
        bind(MainWindowFactory.class)
                .toInstance(new DefaultMainWindowFactory());
        bind(WorkerPanelFactory.class).toInstance(
                new DefaultWorkerPanelFactory());
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
        bind(PccDebugIdRegistry.class).toInstance(
                new DefaultPccDebugIdRegistryFactory().create());
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
        bind(TaskEditingPanelFactory.class).toInstance(
                new DefaultTaskEditingPanelFactory());
        bind(EventEditingPanelFactory.class).toInstance(
                new DefaultMeetingEditingPanelFactory());
        bind(MilestoneEditingPanelFactory.class).toInstance(
                new DefaultMilestoneEditingPanelFactory());
        bind(CentralEditingPanelControllerFactory.class).toInstance(new
                DefaultCentralEditingPanelControllerFactory());
        bind(MilestoneEditingPanelController.class).toInstance(new
                DefaultMilestoneEditingPanelControllerFactory().create());
        bind(EventEditingPanelController.class).toInstance(
                new DefaultEventEditingPanelControllerFactory().create());
        bind(TaskEditingPanelController.class).toInstance(
                new DefaultTaskEditingPanelControllerFactory().create());
        bind(ProjectNetworkGraphPanelFactory.class).toInstance(
                new DefaultProjectNetworkGraphPanelFactory());
        bind(ProjectNetworkGraphCreator.class).toInstance(
                new DefaultProjectNetworkGraphCreatorFactory().create());
        bind(Graph2ResourceConverter.class).toInstance(
                new DefaultGraph2ResourceConverterFactory().create());
        bind(ProjectTreeContainerFactory.class).toInstance(
                new DefaultProjectTreeContainerFactory());
        bind(CentralEditingPanelButtonStateCalculatorFactory.class).toInstance(
                new DefaultCentralEditingPanelButtonStateCalculatorFactory());
        bind(MainWindowControllerFactory.class).toInstance(
                new DefaultMainWindowControllerFactory());
        bind(TaskEditingPanelControllerFactory.class).toInstance(
                new DefaultTaskEditingPanelControllerFactory());
        bind(EventEditingPanelControllerFactory.class).toInstance(
                new DefaultEventEditingPanelControllerFactory());
        bind(MilestoneEditingPanelControllerFactory.class).toInstance(
                new DefaultMilestoneEditingPanelControllerFactory());
        bind(ProjectNetworkDataCreatorFactory.class).toInstance(
                new DefaultProjectNetworkDataCreatorFactory());
        bind(DependenciesEditingDialogControllerFactory.class).toInstance(
                new DefaultDependenciesEditingDialogControllerFactory());
        bind(DependenciesEditingDialogFactory.class).toInstance(
                new DefaultDependenciesEditingDialogFactory());
        bind(SchedulingPanelControllerFactory.class).toInstance(
                new DefaultSchedulingPanelControllerFactory());
        bind(SchedulingIndicatorPanelFactory.class).toInstance(
                new DefautSchedulingIndicatorPanelFactory());
        bind(DependenciesEditingPanelControllerFactory.class).toInstance(
                new DefaultDependenciesEditingPanelControllerFactory());
        bind(DependencyTableFillerFactory.class).toInstance(
                new DefaultDependencyTableFillerFactory());
        bind(IncorrectSchedulingObjectsMarkerFactory.class).toInstance(
                new DefaultIncorrectSchedulingObjectsMarkerFactory());
        bind(CalendarPanelFactory.class).toInstance(
                new DefaultCalendarPanelFactory());
        bind(CalendarPanelControllerFactory.class).toInstance(
                new DefaultCalendarPanelControllerFactory());
        bind(AutomaticExporterFactory.class).toInstance(
                new DefaultAutomaticExporterFactory());
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

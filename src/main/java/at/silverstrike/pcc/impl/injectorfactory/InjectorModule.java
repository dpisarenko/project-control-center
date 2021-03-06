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
import at.silverstrike.pcc.api.culture2lang.CultureToLanguageMapper;
import at.silverstrike.pcc.api.culture2lang.CultureToLanguageMapperFactory;
import at.silverstrike.pcc.api.debugids.PccDebugIdRegistry;
import at.silverstrike.pcc.api.embeddedfilereading.EmbeddedFileReader;
import at.silverstrike.pcc.api.entrywindow.EntryWindowFactory;
import at.silverstrike.pcc.api.gcalservicecreator.GoogleCalendarServiceCreatorFactory;
import at.silverstrike.pcc.api.gcaltasks2pcc.GoogleCalendarTasks2PccImporterFactory;
import at.silverstrike.pcc.api.gcaltasks2pccimporter.GoogleCalendarTasks2PccImporter2Factory;
import at.silverstrike.pcc.api.googletasksservicecreator.GoogleTasksServiceCreatorFactory;
import at.silverstrike.pcc.api.gtask2pcctaskconverter.GoogleTask2PccTaskConverterFactory;
import at.silverstrike.pcc.api.gtasknoteparser.GoogleTaskNotesParserFactory;
import at.silverstrike.pcc.api.gtaskrelevance.IsGoogleTaskRelevantCalculatorFactory;
import at.silverstrike.pcc.api.incorrectschedulingobjectsmarker.IncorrectSchedulingObjectsMarkerFactory;
import at.silverstrike.pcc.api.invitationrequestadminpanel.InvitationRequestAdminPanelFactory;
import at.silverstrike.pcc.api.invitationrequestadminpanelcontroller.InvitationRequestAdminPanelControllerFactory;
import at.silverstrike.pcc.api.invitationrequestadminpanelvisibility.InvitationRequestAdminPanelVisibilityCalculator;
import at.silverstrike.pcc.api.invitationrequestadminpanelvisibility.InvitationRequestAdminPanelVisibilityCalculatorFactory;
import at.silverstrike.pcc.api.mainwindow.MainWindowFactory;
import at.silverstrike.pcc.api.mainwindowcontroller.MainWindowControllerFactory;
import at.silverstrike.pcc.api.parameterdatareader.ParameterDataReader;
import at.silverstrike.pcc.api.parameterdatareader.ParameterDataReaderFactory;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.privatekeyreader.PrivateKeyReaderFactory;
import at.silverstrike.pcc.api.usersettingspanel.UserSettingsPanelFactory;
import at.silverstrike.pcc.api.usersettingspanelcontroller.UserSettingsPanelControllerFactory;
import at.silverstrike.pcc.api.xmlserialization.XmlDeserializerFactory;
import at.silverstrike.pcc.api.xmlserialization.XmlSerializerFactory;
import at.silverstrike.pcc.impl.automaticexport.DefaultAutomaticExporterFactory;
import at.silverstrike.pcc.impl.culture2lang.DefaultCultureToLanguageMapperFactory;
import at.silverstrike.pcc.impl.debugids.DefaultPccDebugIdRegistryFactory;
import at.silverstrike.pcc.impl.embeddedfilereading.DefaultEmbeddedFileReaderFactory;
import at.silverstrike.pcc.impl.entrywindow.DefaultEntryWindowFactory;
import at.silverstrike.pcc.impl.gcalservicecreator.DefaultGoogleCalendarServiceCreatorFactory;
import at.silverstrike.pcc.impl.gcaltasks2pcc.DefaultGoogleCalendarTasks2PccImporterFactory;
import at.silverstrike.pcc.impl.gcaltasks2pccimporter.DefaultGoogleCalendarTasks2PccImporter2Factory;
import at.silverstrike.pcc.impl.googletasksservicecreator.DefaultGoogleTasksServiceCreatorFactory;
import at.silverstrike.pcc.impl.gtask2pcctaskconverter.DefaultGoogleTask2PccTaskConverterFactory;
import at.silverstrike.pcc.impl.gtasknoteparser.DefaultGoogleTaskNotesParserFactory;
import at.silverstrike.pcc.impl.gtaskrelevance.DefaultIsGoogleTaskRelevantCalculatorFactory;
import at.silverstrike.pcc.impl.incorrectschedulingobjectsmarker.DefaultIncorrectSchedulingObjectsMarkerFactory;
import at.silverstrike.pcc.impl.invitationrequestadminpanel.DefaultInvitationRequestAdminPanelFactory;
import at.silverstrike.pcc.impl.invitationrequestadminpanelcontroller.DefaultInvitationRequestAdminPanelControllerFactory;
import at.silverstrike.pcc.impl.invitationrequestadminpanelvisibility.DefaultInvitationRequestAdminPanelVisibilityCalculatorFactory;
import at.silverstrike.pcc.impl.jruby.DefaultJRubySandBoxFactory;
import at.silverstrike.pcc.impl.mainwindow.DefaultMainWindowFactory;
import at.silverstrike.pcc.impl.mainwindowcontroller.DefaultMainWindowControllerFactory;
import at.silverstrike.pcc.impl.parameterdatareader.DefaultParameterDataReaderFactory;
import at.silverstrike.pcc.impl.persistence.DefaultPersistence;
import at.silverstrike.pcc.impl.privatekeyreader.DefaultPrivateKeyReaderFactory;
import at.silverstrike.pcc.impl.usersettingspanel.DefaultUserSettingsPanelFactory;
import at.silverstrike.pcc.impl.usersettingspanelcontroller.DefaultUserSettingsPanelControllerFactory;
import at.silverstrike.pcc.impl.xmlserialization.DefaultXmlDeserializerFactory;
import at.silverstrike.pcc.impl.xmlserialization.DefaultXmlSerializerFactory;

import com.google.inject.AbstractModule;

class InjectorModule extends AbstractModule {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(InjectorModule.class);

    private String oauthRedirectUri;
    private String invitationAdmins;

    @Override
    protected void configure() {
        bind(Persistence.class).toInstance(new DefaultPersistence());
        bind(MainWindowFactory.class)
                .toInstance(new DefaultMainWindowFactory());
        bind(DefaultJRubySandBoxFactory.class).toInstance(
                new DefaultJRubySandBoxFactory());
        bind(EmbeddedFileReader.class).toInstance(
                new DefaultEmbeddedFileReaderFactory().create());
        bind(PccDebugIdRegistry.class).toInstance(
                new DefaultPccDebugIdRegistryFactory().create());
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
        bind(MainWindowControllerFactory.class).toInstance(
                new DefaultMainWindowControllerFactory());
        bind(IncorrectSchedulingObjectsMarkerFactory.class).toInstance(
                new DefaultIncorrectSchedulingObjectsMarkerFactory());
        bind(AutomaticExporterFactory.class).toInstance(
                new DefaultAutomaticExporterFactory());
        bind(InvitationRequestAdminPanelControllerFactory.class).toInstance(
                new DefaultInvitationRequestAdminPanelControllerFactory());
        bind(InvitationRequestAdminPanelFactory.class).toInstance(
                new DefaultInvitationRequestAdminPanelFactory());
        bind(UserSettingsPanelFactory.class).toInstance(
                new DefaultUserSettingsPanelFactory());
        bind(UserSettingsPanelControllerFactory.class).toInstance(
                getUserSettingsPanelControllerFactory());
        bind(GoogleCalendarServiceCreatorFactory.class).toInstance(
                new DefaultGoogleCalendarServiceCreatorFactory());
        bind(GoogleCalendarTasks2PccImporterFactory.class).toInstance(
                new DefaultGoogleCalendarTasks2PccImporterFactory());
        bind(GoogleTasksServiceCreatorFactory.class).toInstance(
                new DefaultGoogleTasksServiceCreatorFactory());
        bind(GoogleTask2PccTaskConverterFactory.class).toInstance(
                new DefaultGoogleTask2PccTaskConverterFactory());
        bind(IsGoogleTaskRelevantCalculatorFactory.class).toInstance(
                new DefaultIsGoogleTaskRelevantCalculatorFactory());
        bind(GoogleTaskNotesParserFactory.class).toInstance(
                new DefaultGoogleTaskNotesParserFactory());
        bind(GoogleCalendarTasks2PccImporter2Factory.class).toInstance(new
                DefaultGoogleCalendarTasks2PccImporter2Factory());
        bind(PrivateKeyReaderFactory.class).toInstance(
                new DefaultPrivateKeyReaderFactory());

        bind(InvitationRequestAdminPanelVisibilityCalculator.class).toInstance(
                getInvitationRequestAdminPanelVisibilityCalculator());
    }

    private InvitationRequestAdminPanelVisibilityCalculator
            getInvitationRequestAdminPanelVisibilityCalculator() {
        final InvitationRequestAdminPanelVisibilityCalculatorFactory factory =
                new DefaultInvitationRequestAdminPanelVisibilityCalculatorFactory();
        final InvitationRequestAdminPanelVisibilityCalculator calculator =
                factory.create();

        calculator.setInvitationAdmins(this.invitationAdmins);

        return calculator;
    }

    private DefaultUserSettingsPanelControllerFactory
            getUserSettingsPanelControllerFactory() {
        final DefaultUserSettingsPanelControllerFactory retVal =
                new DefaultUserSettingsPanelControllerFactory();
        retVal.setOauthRedirectUri(this.oauthRedirectUri);
        return retVal;
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

    public void setOauthRedirectUri(final String aOauthRedirectUri) {
        oauthRedirectUri = aOauthRedirectUri;
    }

    public void setInvitationAdmins(final String aInvitationAdmins) {
        this.invitationAdmins = aInvitationAdmins;
    }
}

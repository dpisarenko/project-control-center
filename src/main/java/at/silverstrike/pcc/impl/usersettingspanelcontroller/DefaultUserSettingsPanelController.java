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

package at.silverstrike.pcc.impl.usersettingspanelcontroller;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.altruix.commons.api.di.PccException;

import com.google.api.client.auth.oauth2.draft10.AccessTokenResponse;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAuthorizationRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessTokenRequest.GoogleAuthorizationCodeGrant;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.tasks.v1.Tasks;
import com.google.gdata.client.authn.oauth.GoogleOAuthHelper;
import com.google.gdata.client.authn.oauth.GoogleOAuthParameters;
import com.google.gdata.client.authn.oauth.OAuthException;
import com.google.gdata.client.authn.oauth.OAuthHmacSha1Signer;
import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.Link;
import com.google.gdata.data.calendar.CalendarEntry;
import com.google.gdata.data.calendar.CalendarFeed;
import com.google.gdata.util.ServiceException;
import com.google.inject.Injector;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Panel;

import eu.livotov.tpt.TPTApplication;

import at.silverstrike.pcc.api.entrywindow.EntryWindow;
import at.silverstrike.pcc.api.entrywindow.EntryWindowFactory;
import at.silverstrike.pcc.api.export2tj3.InvalidDurationException;
import at.silverstrike.pcc.api.gcaltasks2pcc.GoogleCalendarTasks2PccImporter;
import at.silverstrike.pcc.api.gcaltasks2pcc.GoogleCalendarTasks2PccImporterFactory;
import at.silverstrike.pcc.api.googletasksservicecreator.GoogleTasksServiceCreator;
import at.silverstrike.pcc.api.googletasksservicecreator.GoogleTasksServiceCreatorFactory;
import at.silverstrike.pcc.api.model.Resource;
import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.model.UserData;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.projectscheduler.ProjectScheduler;
import at.silverstrike.pcc.api.usersettingspanel.UserSettingsPanel;
import at.silverstrike.pcc.api.usersettingspanel.UserSettingsPanelFactory;
import at.silverstrike.pcc.api.usersettingspanelcontroller.UserSettingsPanelController;
import at.silverstrike.pcc.api.webguibus.WebGuiBus;

/**
 * @author DP118M
 * 
 */
class DefaultUserSettingsPanelController implements UserSettingsPanelController {
    private static final String PCCHQ_COM = "pcchq.com";
    private static final String REDIRECT_URL =
            "http://localhost:8080/pcc/oauth2callback";
    private static final String CLIENT_SECRET = "J1JRmoTA-EmOjTwKkW-eLHLY";
    private static final String APPLICATION_NAME = PCCHQ_COM;
    private static final String SCOPE_CALENDAR =
            "https://www.google.com/calendar/feeds/";
    private static final String SCOPE_TASKS =
            "https://www.googleapis.com/auth/tasks";
    private static final String CLIENT_ID =
            "294496059397.apps.googleusercontent.com";
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultUserSettingsPanelController.class);
    private static final int ONE_MONTH = 1;
    private Injector injector;
    private Persistence persistence;
    private WebGuiBus webGuiBus;
    private String oauthQueryString;

    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;

        if (this.injector != null) {
            this.persistence = this.injector.getInstance(Persistence.class);
            this.webGuiBus = this.injector.getInstance(WebGuiBus.class);
        }
    }

    @Override
    public Panel initGui() {
        final UserSettingsPanelFactory factory =
                this.injector.getInstance(UserSettingsPanelFactory.class);
        final UserSettingsPanel panel = factory.create();
        panel.initGui();
        panel.setGuiController(this);
        return panel.toPanel();
    }

    @Override
    public void calculateAndSyncData(final String aAuthorizationCode) {
        try {
            LOGGER.debug("Before serviceCreator");

            final GoogleTasksServiceCreatorFactory factory =
                    this.injector
                            .getInstance(GoogleTasksServiceCreatorFactory.class);
            final GoogleTasksServiceCreator serviceCreator = factory.create();

            serviceCreator.setApplicationName(APPLICATION_NAME);
            serviceCreator.setAuthorizationCode(aAuthorizationCode);
            serviceCreator
                    .setClientId(CLIENT_ID);
            serviceCreator.setClientSecret(CLIENT_SECRET);
            serviceCreator.setRedirectUrl(REDIRECT_URL);
            serviceCreator.run();

            LOGGER.debug("Before tasksService = serviceCreator.getService()");

            final Tasks tasksService = serviceCreator.getService();

            final GoogleCalendarTasks2PccImporterFactory importerFactory =
                    this.injector
                            .getInstance(GoogleCalendarTasks2PccImporterFactory.class);
            final GoogleCalendarTasks2PccImporter importer =
                    importerFactory.create();

            importer.setInjector(this.injector);
            importer.setService(tasksService);
            importer.setUser((UserData) TPTApplication.getCurrentApplication()
                    .getUser());

            importer.run();

            LOGGER.debug("Before webGuiBus.broadcastTasksImportedFromGoogleMessage");

            this.webGuiBus.broadcastTasksImportedFromGoogleMessage();

            LOGGER.debug("Before calculatePlan");

            calculatePlan();
            LOGGER.debug("Calculated the plan");
        } catch (final PccException exception) {
            LOGGER.error("", exception);
        }
    }

    private void
            exportBookingsToGoogleCalendar(final String aAuthorizationCode,
                    final GoogleOAuthParameters aOauthParams) {
        try {
            final OAuthHmacSha1Signer signer = new OAuthHmacSha1Signer();

            final CalendarService calendarService =
                    new CalendarService(APPLICATION_NAME);

            calendarService
                    .setOAuthCredentials(aOauthParams, signer);

            LOGGER.debug("calendarService: {}", calendarService);

            final URL feedUrl =
                    new URL(
                            "http://www.google.com/calendar/feeds/default/allcalendars/full");
            final CalendarFeed resultFeed =
                    calendarService.getFeed(feedUrl, CalendarFeed.class);

            LOGGER.debug("resultFeed: {}", resultFeed);

            final List<CalendarEntry> entries = resultFeed.getEntries();

            LOGGER.debug("Entries (START)");

            for (final CalendarEntry entry : entries) {
                LOGGER.debug("---------------------");
                LOGGER.debug("Title: {}", entry.getTitle().getPlainText());
                LOGGER.debug("Edit Link: {}", entry.getEditLink().getHref());
                LOGGER.debug("Self Link: {}", entry.getSelfLink().getHref());
                LOGGER.debug("ID: {}", entry.getId());
                LOGGER.debug("Etag: {}", entry.getEtag());

                for (final Link link : entry.getLinks()) {
                    // link.getTitle()
                    LOGGER.debug("Link: Title: '{}', HREF: '{}'", new Object[] {
                            link.getTitle(), link.getHref() });
                }
            }

            LOGGER.debug("Entries (END)");

            // Read bookings
            // final UserData user =
            // (UserData) TPTApplication.getCurrentApplication().getUser();
            // final List<Booking> bookings =
            // this.persistence.getBookings(user);

        } catch (final OAuthException exception) {
            LOGGER.error("", exception);
        } catch (final IOException exception) {
            LOGGER.error("", exception);
        } catch (final ServiceException exception) {
            LOGGER.error("", exception);
        }
    }

    @Override
    public void requestGoogleAuthorizationCode() {
        // The clientId and clientSecret are copied from the API Access tab
        // on
        // the Google APIs Console
        String clientId = CLIENT_ID;

        // Or your redirect URL for web based applications.
        String redirectUrl = REDIRECT_URL;
        String scope = SCOPE_TASKS;

        // Step 1: Authorize -->
        String authorizationUrl =
                new GoogleAuthorizationRequestUrl(clientId, redirectUrl,
                        scope)
                        .build();

        TPTApplication.getCurrentApplication().getMainWindow()
                .open(new ExternalResource(authorizationUrl), "_top");
    }

    private void calculatePlan() {
        final ProjectScheduler scheduler =
                injector.getInstance(ProjectScheduler.class);

        final Persistence persistence =
                this.injector.getInstance(Persistence.class);
        final UserData user = (UserData) TPTApplication
                .getCurrentApplication().getUser();

        LOGGER.debug("calculatePlan, user: {}", user.getId());

        final List<SchedulingObject> schedulingObjectsToExport =
                persistence.getTopLevelTasks(user);

        LOGGER.debug("SCHEDULING OBJECTS TO EXPORT (START)");
        for (final SchedulingObject curSchedulingObject : schedulingObjectsToExport) {
            LOGGER.debug("Name: {}, ID: {}",
                    new Object[] { curSchedulingObject.getName(),
                            curSchedulingObject.getId() });
        }
        LOGGER.debug("SCHEDULING OBJECTS TO EXPORT (END)");

        scheduler.getProjectExportInfo().setSchedulingObjectsToExport(
                schedulingObjectsToExport);

        final List<Resource> resources = new LinkedList<Resource>();
        resources.add(persistence.getCurrentWorker(user));

        scheduler.getProjectExportInfo().setResourcesToExport(resources);

        scheduler.getProjectExportInfo().setProjectName("pcc");

        final Date now = new Date();

        scheduler.getProjectExportInfo().setNow(now);
        scheduler.getProjectExportInfo().setCopyright("Dmitri Pisarenko");
        scheduler.getProjectExportInfo().setCurrency("EUR");
        scheduler.getProjectExportInfo().setSchedulingHorizonMonths(ONE_MONTH);

        scheduler.setDirectory(System.getProperty("user.dir") + "/");
        scheduler.setInjector(injector);
        scheduler.setNow(now);

        try {
            scheduler.run();
        } catch (final InvalidDurationException exception) {
            LOGGER.error("", exception);
        } catch (final PccException exception) {
            LOGGER.error("", exception);
        }
    }

    @Override
    public void logout() {
        TPTApplication.getCurrentApplication().setUser(null);

        final EntryWindowFactory factory =
                this.injector.getInstance(EntryWindowFactory.class);
        final EntryWindow mainWindow = factory.create();

        mainWindow.setInjector(this.injector);
        mainWindow.initGui();

        TPTApplication.getCurrentApplication().removeWindow(
                TPTApplication.getCurrentApplication().getMainWindow());
        TPTApplication.getCurrentApplication().setMainWindow(
                mainWindow.toWindow());
    }

    @Override
    public void writeBookingsToCalendar() {
        final String CONSUMER_KEY = CLIENT_ID;
        final String CONSUMER_SECRET = CLIENT_SECRET;

        final GoogleOAuthParameters oauthParameters =
                new GoogleOAuthParameters();
        oauthParameters.setOAuthConsumerKey(CONSUMER_KEY);
        oauthParameters.setOAuthConsumerSecret(CONSUMER_SECRET);
        oauthParameters.setScope(SCOPE_CALENDAR);
        oauthParameters.setOAuthCallback(REDIRECT_URL);

        GoogleOAuthHelper oauthHelper =
                new GoogleOAuthHelper(new OAuthHmacSha1Signer());
        try {
            oauthHelper.getUnauthorizedRequestToken(oauthParameters);

            final String approvalPageUrl =
                    oauthHelper.createUserAuthorizationUrl(oauthParameters);

            TPTApplication.getCurrentApplication().getMainWindow()
                    .open(new ExternalResource(approvalPageUrl), "_top");

        } catch (final OAuthException exception) {
            LOGGER.error("", exception);
        }

        // // The clientId and clientSecret are copied from the API Access tab
        // // on
        // // the Google APIs Console
        // String clientId = CLIENT_ID;
        //
        // // Or your redirect URL for web based applications.
        // String redirectUrl = REDIRECT_URL;
        // String scope = SCOPE_CALENDAR;
        //
        // // Step 1: Authorize -->
        // String authorizationUrl =
        // new GoogleAuthorizationRequestUrl(clientId, redirectUrl,
        // scope)
        // .build();
        //
        // TPTApplication.getCurrentApplication().getMainWindow()
        // .open(new ExternalResource(authorizationUrl), "_top");
    }

    @Override
    public void writeBookingsToCalendar2(final String aAuthorizationCode) {
        GoogleOAuthParameters oauthParameters = new GoogleOAuthParameters();
        oauthParameters.setOAuthConsumerKey(CLIENT_ID);
        oauthParameters.setOAuthConsumerSecret(CLIENT_SECRET);

        LOGGER.debug("writeBookingsToCalendar2: {}", this.oauthQueryString);

        GoogleOAuthHelper oauthHelper =
                new GoogleOAuthHelper(new OAuthHmacSha1Signer());
        oauthHelper.getOAuthParametersFromCallback(this.oauthQueryString,
                oauthParameters);
        exportBookingsToGoogleCalendar(aAuthorizationCode, oauthParameters);

        
        // final HttpTransport httpTransport = new NetHttpTransport();
        // final JacksonFactory jsonFactory = new JacksonFactory();
        // try {
        // final String clientId = CLIENT_ID;
        //
        // // Step 2: Exchange -->
        //
        // LOGGER.debug(
        // "Calendar: clientId='{}', clientSecret='{}', authorizationCode='{}', redirectUrl='{}'",
        // new Object[] { clientId, CLIENT_SECRET, aAuthorizationCode,
        // REDIRECT_URL });
        //
        // final AccessTokenResponse response =
        // new GoogleAuthorizationCodeGrant(httpTransport,
        // jsonFactory,
        // clientId, CLIENT_SECRET, aAuthorizationCode,
        // REDIRECT_URL).execute();
        // // End of Step 2 <--
        //
        // LOGGER.debug("response.accessToken: {}", response.accessToken);
        //
        // final GoogleOAuthParameters oauth = new GoogleOAuthParameters();
        //
        // oauth.setOAuthConsumerKey(clientId);
        // oauth.setOAuthConsumerSecret(CLIENT_SECRET);
        // oauth.setOAuthToken(response.accessToken);
        // oauth.setOAuthTokenSecret(aAuthorizationCode);
        // oauth.setScope(SCOPE_CALENDAR);
        //
        // exportBookingsToGoogleCalendar(aAuthorizationCode, oauth);
        // } catch (IOException exception) {
        // LOGGER.error("", exception);
        // }
    }

    @Override
    public void setOauthQueryString(final String aQueryString) {
        LOGGER.debug("aQueryString={}", aQueryString);
        this.oauthQueryString = aQueryString;
    }

}

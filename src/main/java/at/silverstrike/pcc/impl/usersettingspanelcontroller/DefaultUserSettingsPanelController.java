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
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessProtectedResource;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAuthorizationRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessTokenRequest.GoogleAuthorizationCodeGrant;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.tasks.v1.Tasks;
import com.google.gdata.client.authn.oauth.OAuthException;
import com.google.gdata.client.authn.oauth.OAuthParameters;
import com.google.gdata.client.authn.oauth.OAuthRsaSha1Signer;
import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.Link;
import com.google.gdata.data.calendar.CalendarEntry;
import com.google.gdata.data.calendar.CalendarFeed;
import com.google.gdata.util.ServiceException;
import com.google.inject.Injector;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Panel;

import eu.livotov.tpt.TPTApplication;

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
    private static final String REDIRECT_URL = "urn:ietf:wg:oauth:2.0:oob";
    private static final String CLIENT_SECRET = "6KqjOMZ90rc7j252rn1L9nG2";
    private static final String APPLICATION_NAME = "PCC";
    private static final String SCOPE_CALENDAR =
            "https://www.google.com/calendar/feeds/";
    private static final String SCOPE_TASKS =
            "https://www.googleapis.com/auth/tasks";
    private static final String CLIENT_ID =
            "482402692152.apps.googleusercontent.com";
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultUserSettingsPanelController.class);
    private static final int ONE_MONTH = 1;
    private Injector injector;
    private Persistence persistence;
    private WebGuiBus webGuiBus;

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
    public void saveGoogleAccessData(final String aUsername,
            final String aPassword) {
        final UserData user =
                (UserData) TPTApplication.getCurrentApplication().getUser();

        user.setGoogleUsername(aUsername);
        user.setGooglePassword(aPassword);

        this.persistence.updateUser(user);
    }

    @Override
    public void fetchData(final String aAuthorizationCode) {
        try {

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

            this.webGuiBus.broadcastTasksImportedFromGoogleMessage();
        } catch (final PccException exception) {
            LOGGER.error("", exception);
        }
    }

    @Override
    public void
            writeDataToGoogle(final String aAuthorizationCode) {
        calculatePlan();
        LOGGER.debug("Calculated the plan");
        exportBookingsToGoogleCalendar(aAuthorizationCode);

    }

    private void
            exportBookingsToGoogleCalendar(final String aAuthorizationCode) {
        final HttpTransport httpTransport = new NetHttpTransport();
        final JacksonFactory jsonFactory = new JacksonFactory();

        try {
            // Step 2: Exchange -->
            final AccessTokenResponse response =
                    new GoogleAuthorizationCodeGrant(httpTransport,
                            jsonFactory,
                            CLIENT_ID, CLIENT_SECRET, aAuthorizationCode,
                            REDIRECT_URL).execute();
            LOGGER.debug("response: {}", response);
            // End of Step 2 <--

            final GoogleAccessProtectedResource accessProtectedResource =
                    new GoogleAccessProtectedResource(
                            response.accessToken, httpTransport, jsonFactory,
                            CLIENT_ID, CLIENT_SECRET,
                            response.refreshToken);

            LOGGER.debug("accessProtectedResource: {}", accessProtectedResource);
            
            final OAuthParameters oauth = new OAuthParameters();
            oauth.setOAuthConsumerKey("pcchq.com");
            oauth.setOAuthConsumerSecret(CLIENT_SECRET);
            oauth.setOAuthToken(accessProtectedResource.getAccessToken());

            LOGGER.debug("oauth: {}", oauth);
            
            final CalendarService calendarService =
                    new CalendarService(APPLICATION_NAME);
            calendarService.setOAuthCredentials(oauth, new OAuthRsaSha1Signer());
                        
            
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

        // Point or redirect your user to the authorizationUrl.
        System.out.println("Go to the following link in your browser:");
        System.out.println(authorizationUrl);

        TPTApplication.getCurrentApplication().getMainWindow()
                .open(new ExternalResource(authorizationUrl), "_blank");
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

}

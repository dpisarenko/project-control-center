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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.altruix.commons.api.di.PccException;

import com.google.api.client.auth.oauth2.draft10.AccessTokenResponse;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessProtectedResource;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessTokenRequest.GoogleAuthorizationCodeGrant;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAuthorizationRequestUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.tasks.v1.Tasks;
import com.google.gdata.client.calendar.CalendarService;
import com.google.inject.Injector;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Panel;

import eu.livotov.tpt.TPTApplication;

import at.silverstrike.pcc.api.gcalservicecreator.GoogleCalendarServiceCreator;
import at.silverstrike.pcc.api.gcalservicecreator.GoogleCalendarServiceCreatorFactory;
import at.silverstrike.pcc.api.gcaltasks2pcc.GoogleCalendarTasks2PccImporter;
import at.silverstrike.pcc.api.gcaltasks2pcc.GoogleCalendarTasks2PccImporterFactory;
import at.silverstrike.pcc.api.model.UserData;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.usersettingspanel.UserSettingsPanel;
import at.silverstrike.pcc.api.usersettingspanel.UserSettingsPanelFactory;
import at.silverstrike.pcc.api.usersettingspanelcontroller.UserSettingsPanelController;
import at.silverstrike.pcc.api.webguibus.WebGuiBus;

/**
 * @author DP118M
 * 
 */
class DefaultUserSettingsPanelController implements UserSettingsPanelController {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultUserSettingsPanelController.class);

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
    public void fetchData(final String aUsername, final String aPassword) {
        LOGGER.debug("at.silverstrike.pcc.impl.usersettingspanelcontroller.DefaultUserSettingsPanelController.fetchData(String, String)");
        try {
            HttpTransport httpTransport = new NetHttpTransport();
            JacksonFactory jsonFactory = new JacksonFactory();

            // The clientId and clientSecret are copied from the API Access tab
            // on
            // the Google APIs Console
            String clientId = "482402692152.apps.googleusercontent.com";
            String clientSecret = "8dKZsmt4W2YwQwcw3WyFZy6x";

            // Or your redirect URL for web based applications.
            String redirectUrl = "urn:ietf:wg:oauth:2.0:oob";
            String scope = "https://www.googleapis.com/auth/tasks";

            // Step 1: Authorize -->
            String authorizationUrl =
                    new GoogleAuthorizationRequestUrl(clientId, redirectUrl,
                            scope)
                            .build();

            // Point or redirect your user to the authorizationUrl.
            System.out.println("Go to the following link in your browser:");
            System.out.println(authorizationUrl);

            LOGGER.debug("authorizationUrl: {}", authorizationUrl);
            
            TPTApplication.getCurrentApplication().getMainWindow()
                    .open(new ExternalResource(authorizationUrl), "_blank");

            // Read the authorization code from the standard input stream.
//            BufferedReader in =
//                    new BufferedReader(new InputStreamReader(System.in));
//            System.out.println("What is the authorization code?");
//            String code = in.readLine();
            String code = "AIzaSyCGzdZ4Nti5yoP8b4riePwd6ozMAFbmufU";
            // End of Step 1 <--

            // Step 2: Exchange -->
            AccessTokenResponse response =
                    new GoogleAuthorizationCodeGrant(httpTransport,
                            jsonFactory,
                            clientId, clientSecret, code, redirectUrl)
                            .execute();
            // End of Step 2 <--

            GoogleAccessProtectedResource accessProtectedResource =
                    new GoogleAccessProtectedResource(
                            response.accessToken, httpTransport, jsonFactory,
                            clientId, clientSecret,
                            response.refreshToken);

            Tasks service =
                    new Tasks(httpTransport, accessProtectedResource,
                            jsonFactory);
            service.setApplicationName("YOUR_APPLICATION_NAME");
            
            
        } catch (final IOException exception) {
            LOGGER.error("", exception);
        }
        // try {
        // final GoogleCalendarTasks2PccImporterFactory importerFactory =
        // this.injector
        // .getInstance(GoogleCalendarTasks2PccImporterFactory.class);
        // final GoogleCalendarTasks2PccImporter importer =
        // importerFactory.create();
        //
        // importer.setInjector(this.injector);
        // importer.setUser((UserData)TPTApplication.getCurrentApplication().getUser());
        // importer.run();
        //
        // this.webGuiBus.broadcastTasksImportedFromGoogleMessage();
        // } catch (final PccException exception) {
        // LOGGER.error("", exception);
        // }
    }

    @Override
    public void
            writeDataToGoogle(final String aUsername, final String aPassword) {
        // TODO Auto-generated method stub
        
    }
}

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
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.client.auth.oauth2.draft10.AccessTokenResponse;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessProtectedResource;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessTokenRequest.GoogleAuthorizationCodeGrant;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAuthorizationRequestUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.tasks.v1.Tasks;
import com.google.api.services.tasks.v1.model.TaskList;
import com.google.api.services.tasks.v1.model.TaskLists;
import com.google.inject.Injector;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Panel;

import eu.livotov.tpt.TPTApplication;

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
    public void fetchData(final String aAuthorizationCode) {
        HttpTransport httpTransport = new NetHttpTransport();
        JacksonFactory jsonFactory = new JacksonFactory();
        String clientId = "482402692152.apps.googleusercontent.com";
        String clientSecret = "8dKZsmt4W2YwQwcw3WyFZy6x";
        String redirectUrl = "urn:ietf:wg:oauth:2.0:oob";

        try {
            // Step 2: Exchange -->
            AccessTokenResponse response =
                    new GoogleAuthorizationCodeGrant(httpTransport,
                            jsonFactory,
                            clientId, clientSecret, aAuthorizationCode,
                            redirectUrl).execute();
            // End of Step 2 <--

            GoogleAccessProtectedResource accessProtectedResource =
                    new GoogleAccessProtectedResource(
                            response.accessToken, httpTransport, jsonFactory,
                            clientId, clientSecret,
                            response.refreshToken);

            Tasks service =
                    new Tasks(httpTransport, accessProtectedResource,
                            jsonFactory);
            service.setApplicationName("PCC");

            TaskLists taskLists = service.tasklists.list().execute();

            LOGGER.debug("TASK LISTS (START)");

            for (final TaskList curTaskList : taskLists.items) {
                LOGGER.debug("Task list: {}", curTaskList.title);
            }
            LOGGER.debug("TASK LISTS (END)");

            LOGGER.debug("TASKS (START)");

            final com.google.api.services.tasks.v1.model.Tasks tasks =
                    service.tasks.list("@default").execute();

            for (final com.google.api.services.tasks.v1.model.Task curTask : tasks.items) {
                LOGGER.debug("Task list: title='{}', completed='{}', id='{}'",
                        new Object[] { curTask.title, curTask.completed,
                                curTask.id });

            }
            LOGGER.debug("TASKS (END)");

        } catch (IOException exception) {
            LOGGER.error("", exception);
        }

    }

    @Override
    public void
            writeDataToGoogle() {
        // TODO Auto-generated method stub

    }

    @Override
    public void requestGoogleAuthorizationCode() {
        // The clientId and clientSecret are copied from the API Access tab
        // on
        // the Google APIs Console
        String clientId = "482402692152.apps.googleusercontent.com";

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

        TPTApplication.getCurrentApplication().getMainWindow()
                .open(new ExternalResource(authorizationUrl), "_blank");
    }
}

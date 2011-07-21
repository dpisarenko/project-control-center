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
import java.security.PrivateKey;
import java.util.Map;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.altruix.commons.api.di.PccException;

import co.altruix.pcc.impl.cdm.DefaultImmediateSchedulingRequest;

import com.google.api.client.auth.oauth2.draft10.AccessTokenResponse;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAuthorizationRequestUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.gdata.client.authn.oauth.GoogleOAuthHelper;
import com.google.gdata.client.authn.oauth.GoogleOAuthParameters;
import com.google.gdata.client.authn.oauth.OAuthException;
import com.google.gdata.client.authn.oauth.OAuthRsaSha1Signer;
import com.google.inject.Injector;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Panel;

import eu.livotov.tpt.TPTApplication;

import at.silverstrike.pcc.api.entrywindow.EntryWindow;
import at.silverstrike.pcc.api.entrywindow.EntryWindowFactory;
import at.silverstrike.pcc.api.model.UserData;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.privatekeyreader.PrivateKeyReader;
import at.silverstrike.pcc.api.privatekeyreader.PrivateKeyReaderFactory;
import at.silverstrike.pcc.api.usersettingspanel.UserSettingsPanel;
import at.silverstrike.pcc.api.usersettingspanel.UserSettingsPanelFactory;
import at.silverstrike.pcc.api.usersettingspanelcontroller.UserSettingsPanelController;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessTokenRequest.GoogleAuthorizationCodeGrant;

/**
 * @author DP118M
 * 
 */
class DefaultUserSettingsPanelController implements UserSettingsPanelController {
    private static final String CLIENT_SECRET = "J1JRmoTA-EmOjTwKkW-eLHLY";
    private static final String GOOGLE_CALENDAR_OAUTH_TOKEN_PARAMETER =
            "oauth_token";
    private static final String GOOGLE_CALENDAR_OAUTH_VERIFIER_PARAMETER =
            "oauth_verifier";
    private static final String GOOGLE_TASKS_REFRESH_TOKEN_PARAMETER = "code";
    private static final String REDIRECT_URL =
            "http://localhost:8080/pcc/oauth2callback";
    private static final String SCOPE_CALENDAR =
            "https://www.google.com/calendar/feeds";
    private static final String SCOPE_TASKS =
            "https://www.googleapis.com/auth/tasks";
    private static final String CLIENT_ID =
            "294496059397.apps.googleusercontent.com";
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultUserSettingsPanelController.class);
    private Injector injector;
    private Persistence persistence;
    private GoogleOAuthParameters oauthParameters;
    private GoogleOAuthHelper oauthHelper;
    private PrivateKey privKey;

    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;

        if (this.injector != null) {
            this.persistence = this.injector.getInstance(Persistence.class);
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

    public PrivateKey getPrivateKey() {
        final PrivateKeyReaderFactory factory =
                this.injector.getInstance(PrivateKeyReaderFactory.class);
        final PrivateKeyReader reader = factory.create();

        reader.setInputStream(getClass().getClassLoader()
                        .getResourceAsStream("privatekey"));

        try {
            reader.run();

            return reader.getPrivateKey();
        } catch (final PccException exception) {
            LOGGER.error("", exception);
            return null;
        }
    }

    @Override
    public void requestImmediateRecalculation() {
        Session session = null;
        Connection connection = null;
        try {
            final ActiveMQConnectionFactory connectionFactory =
                    new ActiveMQConnectionFactory("", "",
                            "failover://tcp://localhost:61616");

            // Create a Connection
            connection = connectionFactory.createConnection();
            connection.start();

            // Create a Session
            session =
                    connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            final Destination destination =
                    session.createQueue("PCC.WEB.WORKER");

            // Create a MessageProducer from the Session to the Topic or Queue
            final MessageProducer producer =
                    session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);

            final UserData user =
                    (UserData) TPTApplication.getCurrentApplication()
                            .getUser();

            LOGGER.debug("Message with user ID {}", user.getId());

            final DefaultImmediateSchedulingRequest message2 =
                    new DefaultImmediateSchedulingRequest();

            message2.setUserId(user.getId());
            final ObjectMessage objectMessage =
                    session.createObjectMessage(message2);
            producer.send(objectMessage);

            // Clean up
            session.close();
            connection.close();

        } catch (final JMSException exception) {
            LOGGER.error("", exception);
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (final JMSException exception) {
                    LOGGER.error("", exception);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (final JMSException exception) {
                    LOGGER.error("", exception);
                }
            }
        }

    }

    @Override
    public void initiateGoogleCalendarAuthorization() {
        try {
            oauthParameters = new GoogleOAuthParameters();
            oauthParameters.setOAuthConsumerKey("pcchq.com");
            oauthParameters.setScope(SCOPE_CALENDAR);
            oauthParameters.setOAuthCallback(REDIRECT_URL);

            privKey = getPrivateKey();

            oauthHelper =
                    new GoogleOAuthHelper(new OAuthRsaSha1Signer(privKey));
            oauthHelper.getUnauthorizedRequestToken(oauthParameters);

            TPTApplication
                    .getCurrentApplication()
                    .getMainWindow()
                    .open(new ExternalResource(oauthHelper
                            .createUserAuthorizationUrl(oauthParameters)),
                            "_top");

        } catch (final OAuthException exception) {
            LOGGER.error("", exception);
        }
    }

    @Override
    public void initiateGoogleTasksAuthorization() {
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

    @Override
    public void processGoogleResponse(final Map<String, String[]> aParameters) {
        LOGGER.debug("processGoogleResponse, 1");
        if (isWaitingForGoogleTasks(aParameters)) {
            LOGGER.debug("processGoogleResponse, 2");
            final String refreshToken = aParameters
                    .get(GOOGLE_TASKS_REFRESH_TOKEN_PARAMETER)[0];
            finalizeGoogleTasksAuthorization(refreshToken);
        } else if (isWaitingForGoogleCalendar(aParameters)) {
            LOGGER.debug("processGoogleResponse, 3");
            finalizeGoogleCalendarAuthorization(aParameters);
        }
    }

    private void finalizeGoogleTasksAuthorization(final String aCode) {
        final HttpTransport httpTransport = new NetHttpTransport();
        final JacksonFactory jsonFactory = new JacksonFactory();

        try {
            final UserData user =
                    (UserData) TPTApplication.getCurrentApplication()
                            .getUser();
            
            final AccessTokenResponse response =
                    new GoogleAuthorizationCodeGrant(httpTransport,
                            jsonFactory,
                            CLIENT_ID, CLIENT_SECRET, aCode,
                            REDIRECT_URL).execute();
            LOGGER.debug("response.refreshToken: {}", response.refreshToken);

            user.setGoogleTasksRefreshToken(response.refreshToken);
            this.persistence.updateUser(user);
        } catch (IOException exception) {
            LOGGER.error("", exception);
        }
    }

    private boolean isWaitingForGoogleCalendar(
            final Map<String, String[]> aParameters) {
        return (aParameters != null)
                && (aParameters.keySet().size() == 2)
                && aParameters.keySet().contains(
                        GOOGLE_CALENDAR_OAUTH_VERIFIER_PARAMETER)
                && aParameters.keySet().contains(
                        GOOGLE_CALENDAR_OAUTH_TOKEN_PARAMETER)
                && (aParameters.get(GOOGLE_CALENDAR_OAUTH_VERIFIER_PARAMETER).length == 1)
                && (aParameters.get(GOOGLE_CALENDAR_OAUTH_TOKEN_PARAMETER).length == 1);
    }

    private void finalizeGoogleCalendarAuthorization(
            final Map<String, String[]> aParameters) {

        final StringBuilder queryString = new StringBuilder();
        boolean firstValue = true;

        for (final String curKey : aParameters.keySet()) {
            if (!firstValue) {
                queryString.append("&");
            } else {
                firstValue = false;
            }
            queryString.append(curKey);
            queryString.append("=");

            final String[] values = aParameters.get(curKey);

            for (final String curValue : values) {
                queryString.append(curValue);
            }
        }

        final String verifier =
                aParameters.get(GOOGLE_CALENDAR_OAUTH_VERIFIER_PARAMETER)[0];

        oauthHelper.getOAuthParametersFromCallback(queryString.toString(),
                oauthParameters);
        final String tokenSecret = oauthParameters.getOAuthTokenSecret();
        LOGGER.debug("Token secret: '{}'",
                tokenSecret);

        try {
            final String accessToken =
                    oauthHelper.getAccessToken(oauthParameters);

            LOGGER.debug("Access token: {}", accessToken);

            final UserData user =
                    (UserData) TPTApplication.getCurrentApplication()
                            .getUser();
            user.setGoogleCalendarOAuthTokenSecret(tokenSecret);
            user.setGoogleCalendarOAuthToken(accessToken);
            user.setGoogleCalendarOAuthVerifier(verifier);
            this.persistence.updateUser(user);
        } catch (final OAuthException exception) {
            LOGGER.error("", exception);
        }
    }

    private boolean isWaitingForGoogleTasks(
            final Map<String, String[]> aParameters) {
        return (aParameters != null)
                && (aParameters.keySet().size() == 1)
                && (aParameters
                        .containsKey(GOOGLE_TASKS_REFRESH_TOKEN_PARAMETER))
                && (aParameters.get(GOOGLE_TASKS_REFRESH_TOKEN_PARAMETER).length == 1);
    }
}

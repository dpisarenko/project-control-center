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

package at.silverstrike.pcc.test.gcaltasks2pcc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.altruix.commons.api.di.InjectorFactory;

import com.google.api.client.auth.oauth2.draft10.AccessTokenResponse;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessProtectedResource;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessTokenRequest.GoogleAuthorizationCodeGrant;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAuthorizationRequestUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.tasks.v1.Tasks;
import com.google.inject.Injector;

import at.silverstrike.pcc.api.gcaltasks2pcc.GoogleCalendarTasks2PccImporter;
import at.silverstrike.pcc.api.gcaltasks2pcc.GoogleCalendarTasks2PccImporterFactory;
import at.silverstrike.pcc.api.model.UserData;
import at.silverstrike.pcc.impl.gcaltasks2pcc.DefaultGoogleCalendarTasks2PccImporterFactory;
import at.silverstrike.pcc.test.model.MockObjectFactory;
import at.silverstrike.pcc.test.testutils.MockInjectorFactory;

public class TestDefaultGoogleCalendarTasks2PccImporter {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(TestDefaultGoogleCalendarTasks2PccImporter.class);

    @Test
    public final void testRun1() {
//        final InjectorFactory injectorFactory =
//                new MockInjectorFactory(new MockInjectorModule());
//        final Injector injector = injectorFactory.createInjector();
//
//        final GoogleCalendarTasks2PccImporterFactory factory =
//                new DefaultGoogleCalendarTasks2PccImporterFactory();
//        final GoogleCalendarTasks2PccImporter objectUnderTest = factory.create();
//        
//        objectUnderTest.setInjector(injector);


        
        try {
            testTaskReading();
        } catch (final IOException exception) {
            LOGGER.error("", exception);
        }
    }

    private void testTaskReading() throws IOException {
        HttpTransport httpTransport = new NetHttpTransport();
        JacksonFactory jsonFactory = new JacksonFactory();

        // The clientId and clientSecret are copied from the API Access tab on
        // the Google APIs Console
        String clientId = "YOUR_CLIENT_ID";
        String clientSecret = "YOUR_CLIENT_SECRET";

        // Or your redirect URL for web based applications.
        String redirectUrl = "urn:ietf:wg:oauth:2.0:oob";
        String scope = "https://www.googleapis.com/auth/tasks";

        // Step 1: Authorize -->
        String authorizationUrl = new GoogleAuthorizationRequestUrl(clientId, redirectUrl, scope)
            .build();

        // Point or redirect your user to the authorizationUrl.
        System.out.println("Go to the following link in your browser:");
        System.out.println(authorizationUrl);

        // Read the authorization code from the standard input stream.
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("What is the authorization code?");
        String code = in.readLine();
        // End of Step 1 <--

        // Step 2: Exchange -->
        AccessTokenResponse response = new GoogleAuthorizationCodeGrant(httpTransport, jsonFactory,
            clientId, clientSecret, code, redirectUrl).execute();
        // End of Step 2 <--

        GoogleAccessProtectedResource accessProtectedResource = new GoogleAccessProtectedResource(
            response.accessToken, httpTransport, jsonFactory, clientId, clientSecret,
            response.refreshToken);

        Tasks service = new Tasks(httpTransport, accessProtectedResource, jsonFactory);
        service.setApplicationName("YOUR_APPLICATION_NAME");
    }
    
    private UserData getUserData() {
        final MockObjectFactory factory = new MockObjectFactory();
        final UserData user = factory.createUserData();
        
        
        return null;
    }
}

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

package at.silverstrike.pcc.test.twoleggedoauth;

import java.io.IOException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.client.auth.oauth2.draft10.AccessTokenResponse;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessProtectedResource;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessTokenRequest;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.tasks.v1.Tasks;

/**
 * @author DP118M
 * 
 */
public class Test2LeggedOAuth {
    private static final Logger LOGGER =
            LoggerFactory
                    .getLogger(Test2LeggedOAuth.class);
    private static final String CLIENT_ID = "294496059397.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "J1JRmoTA-EmOjTwKkW-eLHLY";
    private static final String REFRESH_TOKEN = "1/SNHvbndl5mYgJ_tW8GjwRQjY0FvNaMxR8VRPzuYhqmg";

    @Test
    public void testTasks() {
        final HttpTransport httpTransport = new NetHttpTransport();
        final JacksonFactory jsonFactory = new JacksonFactory();

        try {
            // Step 2: Exchange -->
            final AccessTokenResponse response =
                    new GoogleAccessTokenRequest.GoogleRefreshTokenGrant(
                            httpTransport,
                            jsonFactory,
                            CLIENT_ID, CLIENT_SECRET, REFRESH_TOKEN).execute();
            // End of Step 2 <--

            final GoogleAccessProtectedResource accessProtectedResource =
                    new GoogleAccessProtectedResource(
                            response.accessToken, httpTransport, jsonFactory,
                            CLIENT_ID, CLIENT_SECRET,
                            REFRESH_TOKEN);

            Tasks service =
                    new Tasks(httpTransport, accessProtectedResource,
                            jsonFactory);
            service.setApplicationName("pcchq.com");

        } catch (final IOException exception) {
            LOGGER.error("", exception);
        }
    }
}

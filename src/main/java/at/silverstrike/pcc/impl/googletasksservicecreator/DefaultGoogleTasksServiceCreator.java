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

package at.silverstrike.pcc.impl.googletasksservicecreator;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.altruix.commons.api.di.PccException;

import com.google.api.client.auth.oauth2.draft10.AccessTokenResponse;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessProtectedResource;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessTokenRequest.GoogleAuthorizationCodeGrant;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.tasks.v1.Tasks;

import at.silverstrike.pcc.api.googletasksservicecreator.GoogleTasksServiceCreator;

/**
 * @author DP118M
 * 
 */
class DefaultGoogleTasksServiceCreator implements GoogleTasksServiceCreator {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultGoogleTasksServiceCreator.class);
    private String clientId;
    private String clientSecret;
    private String redirectUrl;
    private String applicationName;
    private Tasks service;
    private String authorizationCode;
    private String oauthAccessToken;

    @Override
    public void run() throws PccException {
        final HttpTransport httpTransport = new NetHttpTransport();
        final JacksonFactory jsonFactory = new JacksonFactory();
        try {
            LOGGER.debug("Tasks: clientId='{}', clientSecret='{}', authorizationCode='{}', redirectUrl='{}'",
                    new Object[]{clientId, clientSecret, authorizationCode, redirectUrl});
            
            // Step 2: Exchange -->
            final AccessTokenResponse response =
                    new GoogleAuthorizationCodeGrant(httpTransport,
                            jsonFactory,
                            clientId, clientSecret, authorizationCode,
                            redirectUrl).execute();
            // End of Step 2 <--

            final GoogleAccessProtectedResource accessProtectedResource =
                    new GoogleAccessProtectedResource(
                            response.accessToken, httpTransport, jsonFactory,
                            clientId, clientSecret,
                            response.refreshToken);
            
            LOGGER.debug("response.accessToken: {}", response.accessToken);
            
            this.oauthAccessToken = response.accessToken;
            
            LOGGER.debug("response.refreshToken: {}", response.refreshToken);
            
            this.service =
                    new Tasks(httpTransport, accessProtectedResource,
                            jsonFactory);
            this.service.setApplicationName(this.applicationName);
        } catch (IOException exception) {
            LOGGER.error("", exception);
        }
    }

    @Override
    public void setClientId(final String aClientId) {
        this.clientId = aClientId;
    }

    @Override
    public void setClientSecret(final String aClientSecret) {
        this.clientSecret = aClientSecret;
    }

    @Override
    public void setRedirectUrl(final String aUrl) {
        this.redirectUrl = aUrl;
    }

    @Override
    public void setApplicationName(final String aAppName) {
        this.applicationName = aAppName;
    }

    @Override
    public Tasks getService() {
        return this.service;
    }

    @Override
    public void setAuthorizationCode(final String aAuthorizationCode) {
        this.authorizationCode = aAuthorizationCode;
    }

    @Override
    public String getOAuthAccessToken() {
        return oauthAccessToken;
    }
}

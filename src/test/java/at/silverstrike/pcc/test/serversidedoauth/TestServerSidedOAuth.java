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

package at.silverstrike.pcc.test.serversidedoauth;

import java.io.IOException;
import java.net.URL;
import java.security.PrivateKey;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.altruix.commons.api.di.PccException;

import at.silverstrike.pcc.api.privatekeyreader.PrivateKeyReader;
import at.silverstrike.pcc.api.privatekeyreader.PrivateKeyReaderFactory;
import at.silverstrike.pcc.impl.privatekeyreader.DefaultPrivateKeyReaderFactory;

import com.google.api.client.auth.oauth2.draft10.AccessTokenResponse;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessProtectedResource;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessTokenRequest;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.tasks.v1.Tasks;
import com.google.api.services.tasks.v1.model.TaskList;
import com.google.gdata.client.authn.oauth.GoogleOAuthParameters;
import com.google.gdata.client.authn.oauth.OAuthException;
import com.google.gdata.client.authn.oauth.OAuthRsaSha1Signer;
import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.calendar.CalendarEntry;
import com.google.gdata.data.calendar.CalendarFeed;
import com.google.gdata.util.ServiceException;

/**
 * @author DP118M
 * 
 */
public class TestServerSidedOAuth {
    private static final Logger LOGGER =
            LoggerFactory
                    .getLogger(TestServerSidedOAuth.class);
    private static final String CLIENT_ID =
            "294496059397.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "J1JRmoTA-EmOjTwKkW-eLHLY";
    private static final String REFRESH_TOKEN_TASKS =
            "1/SNHvbndl5mYgJ_tW8GjwRQjY0FvNaMxR8VRPzuYhqmg";
    private static final String CONSUMER_KEY = "pcchq.com";

    @Test
    public void testTasks() {
        final HttpTransport httpTransport = new NetHttpTransport();
        final JacksonFactory jsonFactory = new JacksonFactory();

        try {
            final AccessTokenResponse response =
                    new GoogleAccessTokenRequest.GoogleRefreshTokenGrant(
                            httpTransport,
                            jsonFactory,
                            CLIENT_ID, CLIENT_SECRET, REFRESH_TOKEN_TASKS)
                            .execute();

            final GoogleAccessProtectedResource accessProtectedResource =
                    new GoogleAccessProtectedResource(
                            response.accessToken, httpTransport, jsonFactory,
                            CLIENT_ID, CLIENT_SECRET,
                            REFRESH_TOKEN_TASKS);

            Tasks service =
                    new Tasks(httpTransport, accessProtectedResource,
                            jsonFactory);
            service.setApplicationName("pcchq.com");

            LOGGER.debug("Task lists (START)");

            for (final TaskList curTaskList : service.tasklists.list()
                    .execute().items) {
                LOGGER.debug(
                        "Task list: etag='{}', id='{}', kind='{}', title='{}'",
                        new Object[] { curTaskList.etag, curTaskList.id,
                                curTaskList.kind, curTaskList.title });
            }

            LOGGER.debug("Task lists (END)");

        } catch (final IOException exception) {
            LOGGER.error("", exception);
        }
    }
    
    @Test
    public void testCalendar() {
        try {
            final PrivateKey privKey = getPrivateKey();
            final OAuthRsaSha1Signer signer = new OAuthRsaSha1Signer(privKey);

            
            GoogleOAuthParameters oauthParameters = new GoogleOAuthParameters();
            oauthParameters.setOAuthConsumerKey(CONSUMER_KEY);

            oauthParameters.setScope("https://www.google.com/calendar/feeds");

            oauthParameters.setOAuthVerifier("Mbu7axn2kpiVsvoQmh18Q21f"); // Verifier from the interactive part
            
            oauthParameters.setOAuthToken("1/Pw10CRNNfiHMdk9n4U3FHP8pccHuF17VsicKYK8xt4Y"); // Access token from the interactive part
            oauthParameters.setOAuthTokenSecret("ftepxc4olAgTGVmXJhjS7HLo"); // Token secret from the interactive part

            final CalendarService calendarService =
                    new CalendarService("pcchq.com");

            calendarService
                    .setOAuthCredentials(oauthParameters, signer);
            
            LOGGER.debug("calendarService: {}", calendarService);

            final URL feedUrl =
                    new URL(
                            "https://www.google.com/calendar/feeds/default/allcalendars/full");
            final CalendarFeed resultFeed =
                    calendarService.getFeed(feedUrl, CalendarFeed.class);

            LOGGER.debug("resultFeed: {}", resultFeed);

            LOGGER.debug("Your calendars:");

            CalendarEntry pccCalendar = null;
            for (int i = 0; (i < resultFeed.getEntries().size())
                    && (pccCalendar == null); i++) {
                final CalendarEntry entry = resultFeed.getEntries().get(i);
                
                LOGGER.debug(entry.getTitle().getPlainText());
            }

        } catch (final IOException exception) {
            LOGGER.error("", exception);
            Assert.fail(exception.getMessage());
        } catch (final OAuthException exception) {
            LOGGER.error("", exception);
            Assert.fail(exception.getMessage());
        } catch (final ServiceException exception) {
            LOGGER.error("", exception);
            Assert.fail(exception.getMessage());
        }
    }

    public PrivateKey getPrivateKey() {
        final PrivateKeyReaderFactory factory =
                new DefaultPrivateKeyReaderFactory();
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

}

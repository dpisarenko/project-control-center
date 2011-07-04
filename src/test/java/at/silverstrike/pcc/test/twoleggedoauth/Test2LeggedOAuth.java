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

import com.google.api.client.auth.oauth.OAuthHmacSigner;
import com.google.api.client.auth.oauth.OAuthParameters;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.tasks.v1.Tasks;
import com.google.api.services.tasks.v1.Tasks.Tasklists.List;
import com.google.api.services.tasks.v1.model.TaskList;
import com.google.api.services.tasks.v1.model.TaskLists;
import com.google.gdata.client.GoogleService;
import com.google.gdata.client.authn.oauth.GoogleOAuthHelper;
import com.google.gdata.client.authn.oauth.GoogleOAuthParameters;
import com.google.gdata.client.authn.oauth.OAuthHmacSha1Signer;
import com.google.gdata.client.authn.oauth.OAuthRsaSha1Signer;
import com.google.gdata.client.authn.oauth.OAuthParameters.OAuthType;
import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.BaseEntry;
import com.google.gdata.data.BaseFeed;
import com.google.gdata.data.Feed;
import com.google.gdata.data.calendar.CalendarEntry;
import com.google.gdata.data.calendar.CalendarFeed;

/**
 * @author DP118M
 * 
 */
public class Test2LeggedOAuth {
    private static final Logger LOGGER =
            LoggerFactory
                    .getLogger(Test2LeggedOAuth.class);

    @Test
    public void test() {
        try {
            String CONSUMER_KEY = "pcchq.com";
            String CONSUMER_SECRET = "6KqjOMZ90rc7j252rn1L9nG2";

            HttpTransport httpTransport = new NetHttpTransport();
            JacksonFactory jsonFactory = new JacksonFactory();

            // The 2-LO authorization section
            OAuthHmacSigner signer = new OAuthHmacSigner();

            String OAUTH_CONSUMER_SECRET = "6KqjOMZ90rc7j252rn1L9nG2";
            signer.clientSharedSecret = OAUTH_CONSUMER_SECRET;

            OAuthParameters oauthParameters = new OAuthParameters();
            oauthParameters.version = "1";
            String OAUTH_CONSUMER_KEY = "pcchq.com";
            oauthParameters.consumerKey = OAUTH_CONSUMER_KEY;
            oauthParameters.signer = signer;
            oauthParameters.token = null;
            oauthParameters.signRequestsUsingAuthorizationHeader(httpTransport);

            // Initializing the Tasks API service
            Tasks service =
                    new Tasks("pcchq.com", httpTransport, jsonFactory);
            String API_KEY_FROM_APIS_CONSOLE =
                    "AIzaSyCip62Ao6a56UaV3ZUMhW7YaG3fn4Azcms";
            service.accessKey = API_KEY_FROM_APIS_CONSOLE;

            // Performing first request: Getting the tasks lists
            List getTaskListsOperation = service.tasklists.list();
            Object ACCOUNT_EMAIL = "dmitri.pissarenko@gmail.com";
            getTaskListsOperation.unknownFields.add("xoauth_requestor_id",
                    ACCOUNT_EMAIL);
            TaskLists taskLists = getTaskListsOperation.execute();

            // Simply printing the title of each tasks lists
            for (TaskList taskList : taskLists.items) {
                System.out.println(taskList.title);
            }
        } catch (final Exception exception) {
            LOGGER.error("", exception);
            Assert.fail(exception.getMessage());
        }
    }

    @Test
    public void test2() {
        try {
            String CONSUMER_KEY = "pcchq.com";
            String CONSUMER_SECRET = "6KqjOMZ90rc7j252rn1L9nG2";

            GoogleOAuthParameters oauthParameters = new GoogleOAuthParameters();
            
            final com.google.gdata.client.authn.oauth.OAuthSigner signer =
                new OAuthHmacSha1Signer();

            GoogleOAuthHelper helper = new GoogleOAuthHelper(signer);

            
            oauthParameters.setOAuthConsumerKey(CONSUMER_KEY);
            oauthParameters.setOAuthConsumerSecret(CONSUMER_SECRET);
            oauthParameters.setOAuthSignatureMethod("HMAC-SHA");
            
            //            oauthParameters.setScope("http://www.google.com/calendar/feeds/");
//            oauthParameters.setOAuthType(OAuthType.TWO_LEGGED_OAUTH);

            
            
            final CalendarService calendarService =
                    new CalendarService("pcchq.com");

            calendarService
                    .setOAuthCredentials(oauthParameters, signer);

            LOGGER.debug("calendarService: {}", calendarService);

            final URL feedUrl =
                    new URL(
                            "https://www.google.com/calendar/feeds/default/allcalendars/full"
                                    +
                                    "?xoauth_requestor_id=dmitri.pissarenko@gmail.com");
            final CalendarFeed resultFeed =
                    calendarService.getFeed(feedUrl, CalendarFeed.class);

            LOGGER.debug("resultFeed: {}", resultFeed);

            LOGGER.debug("Your calendars:");

            CalendarEntry pccCalendar = null;
            for (int i = 0; (i < resultFeed.getEntries().size())
                    && (pccCalendar == null); i++) {
                final CalendarEntry entry = resultFeed.getEntries().get(i);

                if ("PCC".equals(entry.getTitle().getPlainText())) {
                    pccCalendar = entry;
                }
            }

        } catch (Exception exception) {
            LOGGER.error("", exception);
            Assert.fail(exception.getMessage());
        }
    }

    @Test
    public void test3() {
        try {
            String CONSUMER_KEY = "pcchq.com";
            String CONSUMER_SECRET = "6KqjOMZ90rc7j252rn1L9nG2";

            GoogleOAuthParameters oauthParameters = new GoogleOAuthParameters();
            oauthParameters.setOAuthConsumerKey(CONSUMER_KEY);
            oauthParameters.setOAuthConsumerSecret(CONSUMER_SECRET);
            oauthParameters.setScope("http://www.google.com/calendar/feeds/");
            oauthParameters.setOAuthType(OAuthType.TWO_LEGGED_OAUTH);

            final URL feedUrl =
                    new URL(
                            "https://www.google.com/calendar/feeds/default/allcalendars/full"
                                    +
                                    "?xoauth_requestor_id=dmitri.pissarenko@gmail.com");

            GoogleService googleService =
                    new GoogleService("cl",
                            "2-legged-oauth-sample-app");

            // Set the OAuth credentials which were obtained from the steps
            // above.
            googleService.setOAuthCredentials(oauthParameters,
                    new OAuthHmacSha1Signer());

            // Make the request to Google
            BaseFeed resultFeed = googleService.getFeed(feedUrl, Feed.class);
            System.out.println("Response Data:");
            System.out
                    .println("=====================================================");
            System.out.println("| TITLE: "
                    + resultFeed.getTitle().getPlainText());
            if (resultFeed.getEntries().size() == 0) {
                System.out.println("|\tNo entries found.");
            } else {
                for (int i = 0; i < resultFeed.getEntries().size(); i++) {
                    BaseEntry entry =
                            (BaseEntry) resultFeed.getEntries().get(i);
                    System.out.println("|\t" + (i + 1) + ": "
                            + entry.getTitle().getPlainText());
                }
            }

        } catch (Exception exception) {
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

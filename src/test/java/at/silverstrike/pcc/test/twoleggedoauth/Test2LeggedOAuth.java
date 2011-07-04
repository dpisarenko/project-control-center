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
import java.util.Date;

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
import com.google.gdata.data.DateTime;
import com.google.gdata.data.Feed;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.calendar.CalendarEntry;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.calendar.CalendarFeed;
import com.google.gdata.data.extensions.When;

/**
 * @author DP118M
 * 
 */
public class Test2LeggedOAuth {
    private static final Logger LOGGER =
            LoggerFactory
                    .getLogger(Test2LeggedOAuth.class);

    @Test
    public void test2() {
        try {
            final com.google.gdata.client.authn.oauth.OAuthSigner signer =
                new OAuthHmacSha1Signer();

            GoogleOAuthParameters oauthParameters = new GoogleOAuthParameters();
            
            String CONSUMER_KEY = "pcchq.com";
            String CONSUMER_SECRET = "6KqjOMZ90rc7j252rn1L9nG2";

            oauthParameters.setOAuthConsumerKey(CONSUMER_KEY);
            oauthParameters.setOAuthConsumerSecret(CONSUMER_SECRET);
            oauthParameters.setScope("https://www.google.com/calendar/feeds");

            final CalendarService calendarService =
                new CalendarService("pcchq.com");

            calendarService
            .setOAuthCredentials(oauthParameters, signer);
            
            
            new GoogleOAuthHelper(signer);

//            URL feedUrl = new URL("https://www.google.com/calendar/feeds"
//                    + "/private/full?xoauth_requestor_id=" + "dmitri.pissarenko@gmail.com");

          URL feedUrl = new URL("https://www.google.com/calendar/feeds/dmitri.pissarenko@gmail.com/private/full?xoauth_requestor_id="
          + "dmitri.pissarenko@gmail.com");
            
            LOGGER.debug("feedUrl: {}", feedUrl);
            LOGGER.debug("calendarService: {}", calendarService);

            // Prepare entry
            CalendarEventEntry entry = new CalendarEventEntry();

            entry.setTitle(new PlainTextConstruct("abc"));
            entry.setContent(new PlainTextConstruct("def"));

            DateTime start = new DateTime(new Date());
            DateTime end = new DateTime(new Date());
            When eventTimes = new When();
            eventTimes.setStartTime(start);
            eventTimes.setEndTime(end);
            entry.addTime(eventTimes);

            // Insert entry
            CalendarEventEntry insertedEntry = calendarService.insert(feedUrl, entry); 

        } catch (Exception exception) {
            LOGGER.error("", exception);
            Assert.fail(exception.getMessage());
        }
    }

}

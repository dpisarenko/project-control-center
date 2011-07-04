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

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.silverstrike.pcc.test.tj3deadlinesparser.TestDefaultTj3DeadlinesFileParserFactory;

import com.google.api.services.tasks.v1.Tasks;
import com.google.gdata.client.GoogleService;
import com.google.gdata.client.authn.oauth.GoogleOAuthHelper;
import com.google.gdata.client.authn.oauth.GoogleOAuthParameters;
import com.google.gdata.client.authn.oauth.OAuthHmacSha1Signer;
import com.google.gdata.client.authn.oauth.OAuthSigner;
import com.google.gdata.client.calendar.CalendarService;
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

            GoogleOAuthParameters oauthParameters = new GoogleOAuthParameters();
            oauthParameters.setOAuthConsumerKey(CONSUMER_KEY);
            oauthParameters.setOAuthConsumerSecret(CONSUMER_SECRET);

            OAuthSigner signer = new OAuthHmacSha1Signer();

            GoogleOAuthHelper oauthHelper = new GoogleOAuthHelper(signer);

            oauthParameters.setScope("http://www.google.com/calendar/feeds/");
            
            String feedUrlString = "http://www.google.com/calendar/feeds/default/allcalendars/full";

            feedUrlString += "?xoauth_requestor_id="
                + "pcctest31331@gmail.com";
            URL feedUrl = new URL(feedUrlString);

            System.out.println("Sending request to " + feedUrl.toString());
            System.out.println();
            GoogleService googleService =
                new GoogleService("cl",
                    "2-legged-oauth-sample-app");

            // Set the OAuth credentials which were obtained from the steps above.
            googleService.setOAuthCredentials(oauthParameters, signer);

            // Make the request to Google
            BaseFeed resultFeed = googleService.getFeed(feedUrl, Feed.class);

        } catch (final Exception exception) {
            LOGGER.error("", exception);
            Assert.fail(exception.getMessage());
        }
    }
}

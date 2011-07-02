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

package at.silverstrike.pcc.test.oauth;

import junit.framework.Assert;

import com.google.gdata.client.authn.oauth.GoogleOAuthHelper;
import com.google.gdata.client.authn.oauth.GoogleOAuthParameters;
import com.google.gdata.client.authn.oauth.OAuthHmacSha1Signer;

/**
 * @author DP118M
 * 
 */
public class TestOAuthHelper {
    public void testAccessTokenSecretParsing() {
        final GoogleOAuthParameters oauthParameters =
                new GoogleOAuthParameters();

        final GoogleOAuthHelper oauthHelper =
                new GoogleOAuthHelper(new OAuthHmacSha1Signer());
        oauthHelper
                .getOAuthParametersFromCallback(
                        "writeBookingsToCalendar2: oauth_verifier=bG_JiMC1QyJkBbkqaDdeFNhe&oauth_token=4/sESfTRqBIuH_2OZEFTzLjd9P5hS6",
                        oauthParameters);

        Assert.assertEquals("bG_JiMC1QyJkBbkqaDdeFNhe", oauthParameters
                .getOAuthTokenSecret());
    }
}

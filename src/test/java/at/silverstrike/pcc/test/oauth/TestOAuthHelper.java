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

import java.util.Map;

import org.junit.Test;

import junit.framework.Assert;

import com.google.gdata.client.authn.oauth.GoogleOAuthHelper;
import com.google.gdata.client.authn.oauth.GoogleOAuthParameters;
import com.google.gdata.client.authn.oauth.OAuthHmacSha1Signer;
import com.google.gdata.client.authn.oauth.OAuthParameters;
import com.google.gdata.client.authn.oauth.OAuthUtil;

/**
 * @author DP118M
 * 
 */
public class TestOAuthHelper {
    private static final String TOKEN_SECRET = "bG_JiMC1QyJkBbkqaDdeFNhe";
    private static final String TOKEN = "4/sESfTRqBIuH_2OZEFTzLjd9P5hS6";
    private static final String QUERY_STRING =
            "oauth_verifier=bG_JiMC1QyJkBbkqaDdeFNhe&oauth_token=4/sESfTRqBIuH_2OZEFTzLjd9P5hS6";

    @Test
    public void testAccessTokenSecretParsing() {
        final GoogleOAuthParameters oauthParameters =
                new GoogleOAuthParameters();

        final GoogleOAuthHelper objectUnderTest =
                new GoogleOAuthHelper(new OAuthHmacSha1Signer());
        objectUnderTest
                .getOAuthParametersFromCallback(
                        QUERY_STRING,
                        oauthParameters);

        Assert.assertEquals(TOKEN_SECRET, oauthParameters
                .getOAuthTokenSecret());
    }

    @Test
    public void testOAuthUtil() {
        final Map<String, String> params =
                OAuthUtil.parseQuerystring(QUERY_STRING);

        Assert.assertEquals(TOKEN,
                params.get(OAuthParameters.OAUTH_TOKEN_KEY));
    }
}

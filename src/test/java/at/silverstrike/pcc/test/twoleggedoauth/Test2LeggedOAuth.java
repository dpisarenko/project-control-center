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
import com.google.api.client.auth.oauth.OAuthRsaSigner;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessProtectedResource;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.tasks.v1.Tasks;
import com.google.api.services.tasks.v1.Tasks.Tasklists.List;
import com.google.api.services.tasks.v1.model.TaskList;
import com.google.api.services.tasks.v1.model.TaskLists;
import com.google.gdata.client.authn.oauth.OAuthRsaSha1Signer;

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
            signer.clientSharedSecret = OAUTH_CONSUMER_SECRET ;

            OAuthParameters oauthParameters = new OAuthParameters();
            oauthParameters.version = "1";
            String OAUTH_CONSUMER_KEY = "pcchq.com";
            oauthParameters.consumerKey = OAUTH_CONSUMER_KEY ;
            oauthParameters.signer = signer;
            oauthParameters.token = null;
//            oauthParameters.signRequestsUsingAuthorizationHeader(httpTransport);

            // Initializing the Tasks API service
            Tasks service =
                    new Tasks("pcchq.com", httpTransport, jsonFactory);
            String API_KEY_FROM_APIS_CONSOLE = "AIzaSyCip62Ao6a56UaV3ZUMhW7YaG3fn4Azcms";
            service.accessKey = API_KEY_FROM_APIS_CONSOLE;

            // Performing first request: Getting the tasks lists
            List getTaskListsOperation = service.tasklists.list();
            Object ACCOUNT_EMAIL = "dmitri.pissarenko@gmail.com";
            getTaskListsOperation.unknownFields.add("xoauth_requestor_id",
                    ACCOUNT_EMAIL );
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

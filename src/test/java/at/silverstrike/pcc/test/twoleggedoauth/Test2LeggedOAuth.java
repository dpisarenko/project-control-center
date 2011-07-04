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

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.client.auth.oauth.OAuthHmacSigner;
import com.google.api.client.auth.oauth.OAuthParameters;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.tasks.v1.Tasks;
import com.google.api.services.tasks.v1.Tasks.Tasklists.List;
import com.google.api.services.tasks.v1.model.TaskList;
import com.google.api.services.tasks.v1.model.TaskLists;

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
            oauthParameters.signRequestsUsingAuthorizationHeader(httpTransport);

            // Initializing the Tasks API service
            Tasks service =
                    new Tasks("2-lo-tasks-test/1.0", httpTransport, jsonFactory);
            String API_KEY_FROM_APIS_CONSOLE = "AIzaSyCip62Ao6a56UaV3ZUMhW7YaG3fn4Azcms";
            service.accessKey = API_KEY_FROM_APIS_CONSOLE;

            // Performing first request: Getting the tasks lists
            List getTaskListsOperation = service.tasklists.list();
            Object ACCOUNT_EMAIL = "pcctest31331@gmail.com";
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
}

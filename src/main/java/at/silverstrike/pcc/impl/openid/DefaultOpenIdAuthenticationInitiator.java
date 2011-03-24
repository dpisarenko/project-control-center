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

package at.silverstrike.pcc.impl.openid;

import javax.servlet.http.HttpServletRequest;

import org.openid4java.consumer.ConsumerException;
import org.openid4java.discovery.DiscoveryException;
import org.openid4java.message.AuthRequest;
import org.openid4java.message.MessageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.Application;

import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.openid.OpenIdAuthenticationInitiator;

class DefaultOpenIdAuthenticationInitiator implements
        OpenIdAuthenticationInitiator {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultOpenIdAuthenticationInitiator.class);

    private String identity;
    private HttpServletRequest request;
    private Application application;
    private boolean authenticationRequestSentSuccessfully;

    @Override
    public void run() throws PccException {
        final OpenidService openidService = new OpenidService();

        LOGGER.debug("{}: this.request={}", new Object[] {
                ErrorCodes.M_005_REQUEST_MESSAGE, this.request });

        final HttpProcessor httpProcessor = new HttpProcessor(this.request);

        try {
            openidService
                    .performDiscoveryOnUserSuppliedIdentifier(this.identity);

            httpProcessor.saveService(openidService);

            final AuthRequest authRequest = openidService
                    .createOpenIdAuthRequest(httpProcessor.getReturnUrl());

            this.application.setLogoutURL(authRequest.getDestinationUrl(true));
            this.application.close();

            this.authenticationRequestSentSuccessfully = true;
        } catch (final ConsumerException exception) {
            LOGGER.error(ErrorCodes.M_002_AUTH_REQUEST_SENDING_FAILURE,
                    exception);
            this.authenticationRequestSentSuccessfully = false;
        } catch (final DiscoveryException exception) {
            LOGGER.error(ErrorCodes.M_002_AUTH_REQUEST_SENDING_FAILURE,
                    exception);
            this.authenticationRequestSentSuccessfully = false;
        } catch (final MessageException exception) {
            LOGGER.error(ErrorCodes.M_002_AUTH_REQUEST_SENDING_FAILURE,
                    exception);
            this.authenticationRequestSentSuccessfully = false;

        }
    }

    @Override
    public void setIdentity(final String aIdentity) {
        this.identity = aIdentity;
    }

    @Override
    public void setRequest(final HttpServletRequest aRequest) {
        this.request = aRequest;
    }

    @Override
    public void setApplication(final Application aApplication) {
        this.application = aApplication;
    }

    @Override
    public boolean isAuthenticationRequestSentSuccessfully() {
        return authenticationRequestSentSuccessfully;
    }

}

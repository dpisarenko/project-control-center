/**
 * This file is part of Project Control Center (PCC).
 * 
 * PCC (Project Control Center) project is intellectual property of 
 * Dmitri Anatol'evich Pisarenko.
 * 
 * Copyright 2010 Dmitri Anatol'evich Pisarenko
 * All rights reserved
 *
 **/

package at.silverstrike.pcc.impl.openid;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openid4java.message.AuthSuccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.livotov.tpt.TPTApplication;

import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.openid.OpenIdAuthenticationResponder;

class DefaultOpenIdAuthenticationResponder implements
        OpenIdAuthenticationResponder {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultOpenIdAuthenticationResponder.class);

    private boolean validationSuccessful;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String identity;

    @Override
    public void run() throws PccException {
        final HttpProcessor httpProcessor = new HttpProcessor(this.request);
        OpenidService openidService;

        if (!httpProcessor.isRepaint()) {
            try {
                openidService = httpProcessor.restoreService();

                if (httpProcessor.isReturned()) {
                    final Map<String, String> parametersMap =
                            httpProcessor.getRequestParamsMap();
                    final AuthSuccess success =
                            openidService.processReturn(parametersMap,
                                    httpProcessor.getReturnUrl());

                    final OpenidModel model =
                            openidService.extractOpenidData(success);
                    httpProcessor.setModel(model);
                    httpProcessor.saveService(openidService);
//                    this.response.sendRedirect(httpProcessor.getRootUrl());
                    this.validationSuccessful = true;
                }
            } catch (final Exception exception) {
                this.validationSuccessful = false;
                this.identity = null;
                LOGGER.error(ErrorCodes.M_001_AUTH_EXCEPTION, exception);

                httpProcessor.cleanIdentity();
            }
        }

    }

    @Override
    public boolean isValidationSuccessful() {
        return this.validationSuccessful;
    }

    @Override
    public void setRequest(final HttpServletRequest aRequest) {
        this.request = aRequest;
    }

    @Override
    public void setResponse(final HttpServletResponse aResponse) {
        this.response = aResponse;
    }

    @Override
    public String getIdentity() {
        return identity;
    }

}

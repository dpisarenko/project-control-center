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

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openid4java.message.AuthSuccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

        LOGGER.debug("{}", new Object[] { ErrorCodes.M_006_DIAGNOSTIC_MESSAGE });

        if (!httpProcessor.isRepaint()) {
            try {
                LOGGER.debug("{}", new Object[] { ErrorCodes.M_007_DIAGNOSTIC_MESSAGE });
                
                openidService = httpProcessor.restoreService();

                LOGGER.debug("{}", new Object[] { ErrorCodes.M_008_DIAGNOSTIC_MESSAGE });
                
                if (httpProcessor.isReturned()) {
                    
                    LOGGER.debug("{}", new Object[] { ErrorCodes.M_009_DIAGNOSTIC_MESSAGE });
                    
                    final Map<String, String> parametersMap =
                            httpProcessor.getRequestParamsMap();
                    final AuthSuccess success =
                            openidService.processReturn(parametersMap,
                                    httpProcessor.getReturnUrl());

                    LOGGER.debug("{}", new Object[] { ErrorCodes.M_010_DIAGNOSTIC_MESSAGE });
                    
                    final OpenidModel model =
                            openidService.extractOpenidData(success);
                    
                    LOGGER.debug("{}", new Object[] { ErrorCodes.M_011_DIAGNOSTIC_MESSAGE });
                    
                    httpProcessor.setModel(model);
                    LOGGER.debug("{}", new Object[] { ErrorCodes.M_012_DIAGNOSTIC_MESSAGE });
                    httpProcessor.saveService(openidService);
                    
                    LOGGER.debug("{}", new Object[] { ErrorCodes.M_013_DIAGNOSTIC_MESSAGE });
                    
                    this.response.sendRedirect(httpProcessor.getRootUrl());
                    
                    LOGGER.debug("{}", new Object[] { ErrorCodes.M_014_DIAGNOSTIC_MESSAGE });
                    
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

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

package at.silverstrike.pcc.api.openid;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.altruix.commons.api.conventions.SingleActivityModule;


public interface OpenIdAuthenticationResponder extends SingleActivityModule {
    void setRequest(final HttpServletRequest aRequest);

    void setResponse(final HttpServletResponse aResponse);

    boolean isValidationSuccessful();

    String getIdentity();
}

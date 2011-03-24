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

import com.vaadin.Application;

import at.silverstrike.pcc.api.conventions.SingleActivityModule;

public interface OpenIdAuthenticationInitiator extends SingleActivityModule {
    void setIdentity(final String aIdentity);

    void setRequest(final HttpServletRequest aRequest);

    void setApplication(final Application aApplication);

    boolean isAuthenticationRequestSentSuccessfully();
}

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

import at.silverstrike.pcc.api.openid.OpenIdAuthenticationInitiator;
import at.silverstrike.pcc.api.openid.OpenIdAuthenticationInitiatorFactory;

public class DefaultOpenIdAuthenticationInitiatorFactory implements
        OpenIdAuthenticationInitiatorFactory {

    @Override
    public final OpenIdAuthenticationInitiator create() {
        return new DefaultOpenIdAuthenticationInitiator();
    }

}

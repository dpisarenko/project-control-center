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

import at.silverstrike.pcc.api.openid.Deauthenticator;
import at.silverstrike.pcc.api.openid.DeauthenticatorFactory;

public class DefaultDeauthenticatorFactory implements DeauthenticatorFactory {

    @Override
    public final Deauthenticator create() {
        return new DefaultDeauthenticator();
    }

}

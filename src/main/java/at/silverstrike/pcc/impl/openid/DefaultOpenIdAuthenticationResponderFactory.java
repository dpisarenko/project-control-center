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

import at.silverstrike.pcc.api.openid.OpenIdAuthenticationResponder;
import at.silverstrike.pcc.api.openid.OpenIdAuthenticationResponderFactory;

/**
 * @author DP118M
 * 
 */
public class DefaultOpenIdAuthenticationResponderFactory implements
        OpenIdAuthenticationResponderFactory {

    @Override
    public final OpenIdAuthenticationResponder create() {
        return new DefaultOpenIdAuthenticationResponder();
    }

}

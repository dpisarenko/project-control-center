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

import at.silverstrike.pcc.api.conventions.MessageCodePrefixRegistry;

final class ErrorCodes {
    private static final String PREFIX = MessageCodePrefixRegistry
            .getInstance().getPrefix(MessageCodePrefixRegistry.Module.openid);

    public static final String M_001_AUTH_EXCEPTION = PREFIX + "001";

    public static final String M_002_AUTH_REQUEST_SENDING_FAILURE = PREFIX
            + "002";
    public static final String M_003_CONSUMER_MANAGER = PREFIX
            + "003";
    public static final String M_004_UNSUPPORTED_ENCODING = PREFIX
            + "004";
    public static final String M_005_REQUEST_MESSAGE = PREFIX + "005";
    
    private ErrorCodes() {

    }
}

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

import at.silverstrike.pcc.api.conventions.MessageCodePrefixRegistry;
import at.silverstrike.pcc.api.conventions.Module;

final class ErrorCodes {
    private static final String PREFIX = MessageCodePrefixRegistry
            .getInstance().getPrefix(Module.openid);

    public static final String M_001_AUTH_EXCEPTION = PREFIX + "001";

    public static final String M_002_AUTH_REQUEST_SENDING_FAILURE = PREFIX
            + "002";
    public static final String M_003_CONSUMER_MANAGER = PREFIX
            + "003";
    public static final String M_004_UNSUPPORTED_ENCODING = PREFIX
            + "004";
    public static final String M_005_REQUEST_MESSAGE = PREFIX + "005";
    
    public static final String M_006_DIAGNOSTIC_MESSAGE = PREFIX + "006";
    public static final String M_007_DIAGNOSTIC_MESSAGE = PREFIX + "007";
    public static final String M_008_DIAGNOSTIC_MESSAGE = PREFIX + "008";
    public static final String M_009_DIAGNOSTIC_MESSAGE = PREFIX + "009";
    public static final String M_010_DIAGNOSTIC_MESSAGE = PREFIX + "010";
    public static final String M_011_DIAGNOSTIC_MESSAGE = PREFIX + "011";
    
    public static final String M_012_DIAGNOSTIC_MESSAGE = PREFIX + "012";
    public static final String M_013_DIAGNOSTIC_MESSAGE = PREFIX + "013";
    public static final String M_014_DIAGNOSTIC_MESSAGE = PREFIX + "014";
    
    public static final String M_015_QUERY_STRING = PREFIX + "015";
    
    private ErrorCodes() {

    }
}

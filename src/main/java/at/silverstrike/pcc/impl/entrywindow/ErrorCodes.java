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

package at.silverstrike.pcc.impl.entrywindow;

import at.silverstrike.pcc.api.conventions.MessageCodePrefixRegistry;
import at.silverstrike.pcc.api.conventions.FunctionalBlock;

final class ErrorCodes {
    private static final String PREFIX = MessageCodePrefixRegistry
            .getInstance().getPrefix(FunctionalBlock.entrywindow);
    public static final String M_001_HANDLE_PARAMETERS_1 = PREFIX + "001";
    public static final String M_002_HANDLE_PARAMETERS_2 = PREFIX + "002";
    public static final String M_003_HANDLE_PARAMETERS_3 = PREFIX + "003";
    public static final String M_004_REQUEST_MESSAGE = PREFIX + "004";
    
    private ErrorCodes() {

    }
}

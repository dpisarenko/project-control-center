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

package at.silverstrike.pcc.impl.entrywindow;

import at.silverstrike.pcc.api.conventions.MessageCodePrefixRegistry;
import at.silverstrike.pcc.api.conventions.MessageCodePrefixRegistry.Module;

final class ErrorCodes {
    private static final String PREFIX = MessageCodePrefixRegistry
            .getInstance().getPrefix(Module.entrywindow);
    public static final String M_001_HANDLE_PARAMETERS_1 = PREFIX + "001";
    public static final String M_002_HANDLE_PARAMETERS_2 = PREFIX + "002";
    public static final String M_003_HANDLE_PARAMETERS_3 = PREFIX + "003";
    
    private ErrorCodes() {

    }
}

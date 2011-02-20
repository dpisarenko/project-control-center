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
    private final static String PREFIX = MessageCodePrefixRegistry.getInstance().getPrefix(Module.entrywindow);
    
    public final static String M_001_HANDLE_PARAMETERS_1 = PREFIX + "001";
    public final static String M_001_HANDLE_PARAMETERS_2 = PREFIX + "002";
    public final static String M_001_HANDLE_PARAMETERS_3 = PREFIX + "003";

}

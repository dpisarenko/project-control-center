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

package at.silverstrike.pcc.impl.dailyplanpanel;

import at.silverstrike.pcc.api.conventions.MessageCodePrefixRegistry;

final class ErrorCodes {
    private final static String PREFIX = MessageCodePrefixRegistry.getInstance().getPrefix(MessageCodePrefixRegistry.Module.dailyplanpanel);
    
    public final static String M_001_SELECTED_DAY_CHANGED = PREFIX + "001";
    
    public final static String M_002_SELECTED_DAY_CHANGED2 = PREFIX + "002";
}

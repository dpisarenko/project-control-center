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

package at.silverstrike.pcc.impl.dailyplanpanel;

import at.silverstrike.pcc.api.conventions.MessageCodePrefixRegistry;
import at.silverstrike.pcc.api.conventions.FunctionalBlock;

final class ErrorCodes {
    private static final String PREFIX = MessageCodePrefixRegistry
            .getInstance().getPrefix(
                    FunctionalBlock.dailyplanpanel);

    public static final String M_001_SELECTED_DAY_CHANGED = PREFIX + "001";

    public static final String M_002_SELECTED_DAY_CHANGED2 = PREFIX + "002";

    private ErrorCodes() {
    }
}

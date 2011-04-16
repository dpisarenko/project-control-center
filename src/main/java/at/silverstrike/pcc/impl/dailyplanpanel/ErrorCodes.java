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

import at.silverstrike.pcc.api.pcc.PccFunctionalBlock;
import at.silverstrike.pcc.api.pcc.PccMessageCodePrefixRegistry;

final class ErrorCodes {
    private static final String PREFIX = PccMessageCodePrefixRegistry
            .getInstance().getPrefix(
                    PccFunctionalBlock.dailyplanpanel);

    public static final String M_001_SELECTED_DAY_CHANGED = PREFIX + "001";

    public static final String M_002_SELECTED_DAY_CHANGED2 = PREFIX + "002";

    private ErrorCodes() {
    }
}

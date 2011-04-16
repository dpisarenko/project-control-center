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

package ru.altruix.commons.api.debugids;

import ru.altruix.commons.api.commons.CommonsFunctionalBlock;
import ru.altruix.commons.api.commons.CommonsMessageCodePrefixRegistry;

/**
 * @author DP118M
 * 
 */
final class ErrorCodes {
    private static final String PREFIX = CommonsMessageCodePrefixRegistry
            .getInstance().getPrefix(CommonsFunctionalBlock.debugids);

    public static final String M_001_LOAD_DATA = PREFIX + "001";

    private ErrorCodes() {
    }
}

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

package at.silverstrike.pcc.impl.injectorfactory;

import static at.silverstrike.pcc.api.pcc.FunctionalBlock.injectorfactory;
import at.silverstrike.pcc.api.conventions.PccMessageCodePrefixRegistry;

final class ErrorCodes {
    private static final String PREFIX = PccMessageCodePrefixRegistry
            .getInstance().getPrefix(injectorfactory);

    public static final String M_001_VERSION_READER = PREFIX + "001";

    private ErrorCodes() {

    }
}

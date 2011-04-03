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

package at.silverstrike.pcc.impl.version;

import static at.silverstrike.pcc.api.conventions.FunctionalBlock.version;
import at.silverstrike.pcc.api.conventions.MessageCodePrefixRegistry;

final class ErrorCodes {
    private static final String PREFIX = MessageCodePrefixRegistry
            .getInstance().getPrefix(version);

    public static final String M_001_RUN = PREFIX + "001";
    public static final String M_002_RUN2 = PREFIX + "002";

    private ErrorCodes() {

    }
}

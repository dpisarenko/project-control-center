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

package at.silverstrike.pcc.impl.injectorfactory;

import static at.silverstrike.pcc.api.conventions.MessageCodePrefixRegistry.Module.injectorfactory;
import at.silverstrike.pcc.api.conventions.MessageCodePrefixRegistry;

final class ErrorCodes {
    private final static String PREFIX = MessageCodePrefixRegistry
            .getInstance().getPrefix(injectorfactory);

    public final static String M_001_VERSION_READER = PREFIX + "001";

}

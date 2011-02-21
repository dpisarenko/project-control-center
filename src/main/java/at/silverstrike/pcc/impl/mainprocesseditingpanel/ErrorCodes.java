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

package at.silverstrike.pcc.impl.mainprocesseditingpanel;

import static at.silverstrike.pcc.api.conventions.MessageCodePrefixRegistry.Module.mainprocesseditingpanel;
import at.silverstrike.pcc.api.conventions.MessageCodePrefixRegistry;

final class ErrorCodes {
    private static final String PREFIX = MessageCodePrefixRegistry
            .getInstance().getPrefix(mainprocesseditingpanel);

    public static final String M_001_EXPORT_FAILURE = PREFIX + "001";

    private ErrorCodes() {

    }
}

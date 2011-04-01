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

package at.silverstrike.pcc.impl.mainwindowcontroller;

import at.silverstrike.pcc.api.conventions.MessageCodePrefixRegistry;

final class ErrorCodes {
    private static final String PREFIX = MessageCodePrefixRegistry
            .getInstance().getPrefix(
                    MessageCodePrefixRegistry.Module.mainwindowcontroller);
    public static final String M_001_FILE_NOT_FOUND = PREFIX + "001";
    public static final String M_002_SERIALIZATION_FAULT = PREFIX + "002";
    public static final String M_003_FILE_NOT_FOUND = PREFIX + "003";

    private ErrorCodes() {
    }
}

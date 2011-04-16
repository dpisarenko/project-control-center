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

package at.silverstrike.pcc.impl.taskeditingpanel;

import static at.silverstrike.pcc.api.pcc.FunctionalBlock.taskeditingpanel;
import at.silverstrike.pcc.api.conventions.PccMessageCodePrefixRegistry;

final class ErrorCodes {
    private static final String PREFIX = PccMessageCodePrefixRegistry
            .getInstance().getPrefix(taskeditingpanel);

    public static final String M_001_TEST_TABLE_CREATION = PREFIX + "001";

    private ErrorCodes() {

    }
}
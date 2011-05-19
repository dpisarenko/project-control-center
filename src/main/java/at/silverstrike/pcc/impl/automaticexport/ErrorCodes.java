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

package at.silverstrike.pcc.impl.automaticexport;

import static at.silverstrike.pcc.api.pcc.PccFunctionalBlock.persistence;
import at.silverstrike.pcc.api.pcc.PccMessageCodePrefixRegistry;

/**
 * @author DP118M
 * 
 */
final class ErrorCodes {
    private static final String PREFIX = PccMessageCodePrefixRegistry
            .getInstance().getPrefix(persistence);

    public static final String M_001_EXPORT_FAILURE = PREFIX + "001";
}

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

package at.silverstrike.pcc.impl.graphdemopanel;

import static at.silverstrike.pcc.api.conventions.MessageCodePrefixRegistry.Module.graphdemopanel;
import at.silverstrike.pcc.api.conventions.MessageCodePrefixRegistry;

/**
 * @author DP118M
 * 
 */
final class ErrorCodes {
    private static final String PREFIX = MessageCodePrefixRegistry
            .getInstance().getPrefix(graphdemopanel);

    public static final String M_001_UNSUPPORTED_ENCONDING = PREFIX + "001";
    public static final String M_002_SVG_GRAPHICS_2D_IO = PREFIX + "002";
    public static final String M_003_PARSER_CONFIGURATION = PREFIX + "003";

    private ErrorCodes() {

    }
}

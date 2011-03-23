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

package at.silverstrike.pcc.impl.graph2resource;

import at.silverstrike.pcc.api.conventions.MessageCodePrefixRegistry;

/**
 * @author DP118M
 * 
 */
final class ErrorCodes {
    private static final String PREFIX = MessageCodePrefixRegistry
            .getInstance().getPrefix(
                    MessageCodePrefixRegistry.Module.graph2resource);

    public static final String M_001_BUFFER_SIZE_IO_EX = PREFIX + "001";
    public static final String M_002_UNSUPPORTED_ENCONDING = PREFIX + "002";
    public static final String M_003_SVG_GRAPHICS_2D_IO = PREFIX + "003";
    public static final String M_004_PARSER_CONFIGURATION = PREFIX + "004";
    
    private ErrorCodes() {
    }
}

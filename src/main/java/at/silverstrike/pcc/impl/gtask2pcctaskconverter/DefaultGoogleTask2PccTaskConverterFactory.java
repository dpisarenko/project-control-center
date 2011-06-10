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

package at.silverstrike.pcc.impl.gtask2pcctaskconverter;

import at.silverstrike.pcc.api.gtask2pcctaskconverter.GoogleTask2PccTaskConverter;
import at.silverstrike.pcc.api.gtask2pcctaskconverter.GoogleTask2PccTaskConverterFactory;

/**
 * @author DP118M
 *
 */
public final class DefaultGoogleTask2PccTaskConverterFactory implements
        GoogleTask2PccTaskConverterFactory {

    @Override
    public GoogleTask2PccTaskConverter create() {
        return new DefaultGoogleTask2PccTaskConverter();
    }

}

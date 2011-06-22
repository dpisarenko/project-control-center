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

package at.silverstrike.pcc.impl.gtasktitleparser;

import at.silverstrike.pcc.api.gtasktitleparser.GoogleTaskTitleParser;
import at.silverstrike.pcc.api.gtasktitleparser.GoogleTaskTitleParserFactory;

/**
 * @author DP118M
 * 
 */
public final class DefaultGoogleTaskTitleParserFactory implements
        GoogleTaskTitleParserFactory {

    @Override
    public GoogleTaskTitleParser create() {
        return new DefaultGoogleTaskTitleParser();
    }

}

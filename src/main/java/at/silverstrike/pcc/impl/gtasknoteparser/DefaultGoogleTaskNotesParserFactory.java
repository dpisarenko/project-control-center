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

package at.silverstrike.pcc.impl.gtasknoteparser;

import at.silverstrike.pcc.api.gtasknoteparser.GoogleTaskNotesParser;
import at.silverstrike.pcc.api.gtasknoteparser.GoogleTaskNotesParserFactory;

/**
 * @author DP118M
 *
 */
public class DefaultGoogleTaskNotesParserFactory implements
        GoogleTaskNotesParserFactory {

    @Override
    public GoogleTaskNotesParser create() {
        return new DefaultGoogleTaskNotesParser();
    }

}

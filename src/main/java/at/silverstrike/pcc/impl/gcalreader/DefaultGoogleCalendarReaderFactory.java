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

package at.silverstrike.pcc.impl.gcalreader;

import at.silverstrike.pcc.api.gcalreader.GoogleCalendarReader;
import at.silverstrike.pcc.api.gcalreader.GoogleCalendarReaderFactory;

/**
 * @author DP118M
 *
 */
public final class DefaultGoogleCalendarReaderFactory implements
        GoogleCalendarReaderFactory {

    @Override
    public GoogleCalendarReader create() {
        return new DefaultGoogleCalendarReader();
    }
}

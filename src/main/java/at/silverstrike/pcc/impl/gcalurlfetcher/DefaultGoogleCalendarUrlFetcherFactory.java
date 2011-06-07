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

package at.silverstrike.pcc.impl.gcalurlfetcher;

import at.silverstrike.pcc.api.gcalurlfetcher.GoogleCalendarUrlFetcher;
import at.silverstrike.pcc.api.gcalurlfetcher.GoogleCalendarUrlFetcherFactory;

/**
 * @author DP118M
 *
 */
public final class DefaultGoogleCalendarUrlFetcherFactory implements
        GoogleCalendarUrlFetcherFactory {

    @Override
    public GoogleCalendarUrlFetcher create() {
        return new DefaultGoogleCalendarUrlFetcher();
    }

}

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

import ru.altruix.commons.api.di.PccException;

import com.google.gdata.client.calendar.CalendarService;
import com.google.inject.Injector;

import at.silverstrike.pcc.api.gcalurlfetcher.GoogleCalendarUrlFetcher;

/**
 * @author DP118M
 *
 */
public class DefaultGoogleCalendarUrlFetcher implements
        GoogleCalendarUrlFetcher {
    private Injector injector;
    private CalendarService service;
    private String calendarName;
    private String feedUrl;
    
    @Override
    public void run() throws PccException {
        // TODO Auto-generated method stub

    }
    
    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;
    }
    
    @Override
    public void setService(final CalendarService aService) {
        this.service = aService;
    }
    
    @Override
    public void setCalendarName(String aName) {
        this.calendarName = aName;
    }
    
    @Override
    public String getFeedUrl() {
        return feedUrl;
    }

}

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

package at.silverstrike.pcc.api.gcalurlfetcher;

import com.google.gdata.client.calendar.CalendarService;

import ru.altruix.commons.api.conventions.SingleActivityModule;
import ru.altruix.commons.api.di.ModuleWithInjectableDependencies;

/**
 * @author DP118M
 * 
 */
public interface GoogleCalendarUrlFetcher extends SingleActivityModule,
        ModuleWithInjectableDependencies {
    void setService(final CalendarService aService);
    void setCalendarName(final String aName);
    
    String getFeedUrl();
}

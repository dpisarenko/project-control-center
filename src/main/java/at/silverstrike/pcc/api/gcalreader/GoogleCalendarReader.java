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

package at.silverstrike.pcc.api.gcalreader;

import java.util.List;

import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.calendar.CalendarEntry;

import ru.altruix.commons.api.conventions.SingleActivityModule;

/**
 * Returns all calendars (like default user's calendar, holidays, contacts' birthdays.
 * @author DP118M
 *
 */
public interface GoogleCalendarReader extends SingleActivityModule {
    void setCalendarService(final CalendarService aService);
    
    List<CalendarEntry> getCalendarEntries();
}

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

import java.io.IOException;
import java.net.URL;
import java.util.List;

import at.silverstrike.pcc.api.gcalreader.GoogleCalendarReader;

import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.calendar.CalendarEntry;
import com.google.gdata.data.calendar.CalendarFeed;
import com.google.gdata.util.ServiceException;

import ru.altruix.commons.api.di.PccException;

/**
 * @author DP118M
 * 
 */
class DefaultGoogleCalendarReader implements GoogleCalendarReader {
    private static final String CALENDAR_URL =
            "http://www.google.com/calendar/feeds/default/allcalendars/full";
    private List<CalendarEntry> calendarEntries;
    private CalendarService service;

    @Override
    public void run() throws PccException {
        try {
            final URL feedUrl = new URL(CALENDAR_URL);
            final CalendarFeed resultFeed =
                    service.getFeed(feedUrl, CalendarFeed.class);

            this.calendarEntries = resultFeed.getEntries();
        } catch (final IOException exception) {
            throw new PccException(exception);
        } catch (final ServiceException exception) {
            throw new PccException(exception);
        }
    }

    @Override
    public List<CalendarEntry> getCalendarEntries() {
        return this.calendarEntries;
    }

    @Override
    public void setCalendarService(final CalendarService aService) {
        this.service = aService;
    }
}

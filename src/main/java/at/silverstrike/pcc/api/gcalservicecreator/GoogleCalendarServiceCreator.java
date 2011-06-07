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

package at.silverstrike.pcc.api.gcalservicecreator;

import com.google.gdata.client.calendar.CalendarService;

import ru.altruix.commons.api.conventions.SingleActivityModule;

/**
 * @author DP118M
 *
 */
public interface GoogleCalendarServiceCreator extends SingleActivityModule {
    void setUsername(final String aUserName);
    void setPassword(final String aPassword);

    CalendarService getService();
}

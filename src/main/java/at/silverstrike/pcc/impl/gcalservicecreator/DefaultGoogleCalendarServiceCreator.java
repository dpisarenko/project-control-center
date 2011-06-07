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

package at.silverstrike.pcc.impl.gcalservicecreator;

import at.silverstrike.pcc.api.gcalservicecreator.GoogleCalendarServiceCreator;

import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.util.AuthenticationException;

import ru.altruix.commons.api.di.PccException;

/**
 * @author DP118M
 *
 */
class DefaultGoogleCalendarServiceCreator implements
        GoogleCalendarServiceCreator {
    private static final String APPLICATION_NAME = "pcccaldav";
    private CalendarService service;
    private String username;
    private String password;
    
    @Override
    public void run() throws PccException {
        this.service = new CalendarService(APPLICATION_NAME);

        try {
            service.setUserCredentials(this.username, this.password);
        } catch (final AuthenticationException exception) {
            throw new PccException(exception);
        }
    }

    @Override
    public void setUsername(final String aUserName) {
        this.username = aUserName;
    }

    @Override
    public void setPassword(final String aPassword) {
        this.password = aPassword;
    }

    @Override
    public CalendarService getService() {
        return this.service;
    }
}

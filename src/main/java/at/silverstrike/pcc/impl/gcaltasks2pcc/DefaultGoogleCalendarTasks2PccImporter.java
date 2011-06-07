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

package at.silverstrike.pcc.impl.gcaltasks2pcc;

import ru.altruix.commons.api.di.PccException;

import com.google.gdata.client.calendar.CalendarService;
import com.google.inject.Injector;

import at.silverstrike.pcc.api.gcaltasks2pcc.GoogleCalendarTasks2PccImporter;
import at.silverstrike.pcc.api.model.UserData;

/**
 * @author DP118M
 *
 */
class DefaultGoogleCalendarTasks2PccImporter implements
        GoogleCalendarTasks2PccImporter {
    private CalendarService service;
    private Injector injector;
    private UserData user;
    
    @Override
    public void run() throws PccException {
        // TODO Auto-generated method stub
//        this.service.ta
    }

    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;
    }
    
    @Override
    public void setUser(final UserData aUser) {
        this.user = aUser;
    }
}

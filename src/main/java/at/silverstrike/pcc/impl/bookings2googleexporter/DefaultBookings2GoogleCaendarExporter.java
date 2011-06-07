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

package at.silverstrike.pcc.impl.bookings2googleexporter;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.altruix.commons.api.di.PccException;

import com.google.gdata.client.calendar.CalendarService;
import com.google.inject.Injector;

import at.silverstrike.pcc.api.bookings2googleexporter.Bookings2GoogleCaendarExporter;
import at.silverstrike.pcc.api.gcalservicecreator.GoogleCalendarServiceCreator;
import at.silverstrike.pcc.api.gcalservicecreator.GoogleCalendarServiceCreatorFactory;
import at.silverstrike.pcc.api.model.Booking;
import at.silverstrike.pcc.api.model.UserData;
import at.silverstrike.pcc.api.persistence.Persistence;

/**
 * @author DP118M
 * 
 */
class DefaultBookings2GoogleCaendarExporter implements
        Bookings2GoogleCaendarExporter {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultBookings2GoogleCaendarExporter.class);

    private UserData userData;
    private Injector injector;

    @Override
    public void run() throws PccException {
        final List<Booking> bookings = this.getBookings();

        final CalendarService service = createCalendarService();

        this.removeEvents(service);
        this.addEvents(bookings, service);
    }

    private CalendarService createCalendarService() {
        final GoogleCalendarServiceCreatorFactory factory =
                this.injector
                        .getInstance(GoogleCalendarServiceCreatorFactory.class);
        final GoogleCalendarServiceCreator serviceCreator = factory.create();
        CalendarService service = null;

        serviceCreator.setUsername(this.userData.getGoogleUsername());
        serviceCreator.setPassword(this.userData.getGooglePassword());
        try {
            serviceCreator.run();
            service = serviceCreator.getService();
        } catch (final PccException exception) {
            LOGGER.error("", exception);
        }

        return service;
    }

    private void addEvents(final List<Booking> aBookings,
            final CalendarService aService) {
        // TODO Auto-generated method stub

    }

    private void removeEvents(CalendarService aService) {
        // TODO Auto-generated method stub

    }

    private List<Booking> getBookings() {
        final Persistence persistence =
                this.injector.getInstance(Persistence.class);
        return persistence.getBookings(this.userData);
    }

    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;
    }

    @Override
    public void setUserData(final UserData aUserData) {
        this.userData = aUserData;
    }

}

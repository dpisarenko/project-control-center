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

package at.silverstrike.pcc.impl.persistence;

import java.util.List;

import at.silverstrike.pcc.api.model.Booking;
import at.silverstrike.pcc.api.model.DailySchedule;
import at.silverstrike.pcc.api.model.UserData;

/**
 * @author Dmitri Pisarenko
 * 
 */
class DefaultDailySchedule implements DailySchedule {
    private List<Booking> bookings;
    private Long id;
    private UserData user;

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(final List<Booking> aBookings) {
        this.bookings = aBookings;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long aId) {
        this.id = aId;
    }

    public UserData getUser() {
        return user;
    }

    public void setUser(final UserData aUser) {
        this.user = aUser;
    }
}

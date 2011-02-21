/**
 * This file is part of Project Control Center (PCC).
 * 
 * PCC (Project Control Center) project is intellectual property of 
 * Dmitri Anatol'evich Pisarenko.
 * 
 * Copyright 2010 Dmitri Anatol'evich Pisarenko
 * All rights reserved
 *
 **/

package at.silverstrike.pcc.impl.tj3bookingsparser.grammar;

import java.util.LinkedList;
import java.util.List;

import at.silverstrike.pcc.api.tj3bookingsparser.BookingStatement;
import at.silverstrike.pcc.api.tj3bookingsparser.IndBooking;

public class DefaultBookingStatement implements BookingStatement {
    private String resource;
    private String scenario;
    private List<IndBooking> indBookings;

    public DefaultBookingStatement() {
        this.indBookings = new LinkedList<IndBooking>();
    }

    final void setResource(final String aResource) {
        this.resource = aResource;
    }

    final void setScenario(final String aScenrio) {
        this.scenario = aScenrio;
    }

    final void addIndBooking(final IndBooking aIndBooking) {
        this.indBookings.add(aIndBooking);
    }

    public final String getResource() {
        return resource;
    }

    public final String getScenario() {
        return scenario;
    }

    public final List<IndBooking> getIndBookings() {
        return indBookings;
    }
}

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
    public String resource;
    public String scenario;
    public List<IndBooking> indBookings;

    public DefaultBookingStatement() {
        this.indBookings = new LinkedList<IndBooking>();
    }

    void setResource(final String aResource) {
        this.resource = aResource;
    }

    void setScenario(final String aScenrio) {
        this.scenario = aScenrio;
    }

    void addIndBooking(final IndBooking anIndBooking) {
        this.indBookings.add(anIndBooking);
    }

    public String getResource() {
        return resource;
    }

    public String getScenario() {
        return scenario;
    }

    public List<IndBooking> getIndBookings() {
        return indBookings;
    }
}

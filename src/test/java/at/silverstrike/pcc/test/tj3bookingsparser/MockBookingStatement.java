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

package at.silverstrike.pcc.test.tj3bookingsparser;

import java.util.LinkedList;
import java.util.List;

import at.silverstrike.pcc.api.tj3bookingsparser.BookingStatement;
import at.silverstrike.pcc.api.tj3bookingsparser.IndBooking;

public class MockBookingStatement implements BookingStatement {

    private String resource;

    private String scenario;

    private List<IndBooking> indBookings = new LinkedList<IndBooking>();

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public List<IndBooking> getIndBookings() {
        return indBookings;
    }

    public void setIndBookings(List<IndBooking> indBookings) {
        this.indBookings = indBookings;
    }
}

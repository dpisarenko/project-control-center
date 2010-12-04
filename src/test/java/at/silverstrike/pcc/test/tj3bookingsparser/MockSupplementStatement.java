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

package at.silverstrike.pcc.test.tj3bookingsparser;

import java.util.LinkedList;
import java.util.List;

import at.silverstrike.pcc.api.tj3bookingsparser.BookingStatement;
import at.silverstrike.pcc.api.tj3bookingsparser.SupplementStatement;

public class MockSupplementStatement implements SupplementStatement{
    private String taskId;

    private List<BookingStatement> bookingStatements = new LinkedList<BookingStatement>();

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public List<BookingStatement> getBookingStatements() {
        return bookingStatements;
    }

    public void setBookingStatements(List<BookingStatement> bookingStatements) {
        this.bookingStatements = bookingStatements;
    }

}

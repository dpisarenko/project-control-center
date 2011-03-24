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

import at.silverstrike.pcc.api.tj3bookingsparser.BookingsFile;
import at.silverstrike.pcc.api.tj3bookingsparser.SupplementStatement;

public class MockBookingsFile implements BookingsFile {
    private List<SupplementStatement> supplementStatements = new LinkedList<SupplementStatement>();

    public List<SupplementStatement> getSupplementStatements() {
        return supplementStatements;
    }

    public void setSupplementStatements(
            List<SupplementStatement> supplementStatements) {
        this.supplementStatements = supplementStatements;
    }
}

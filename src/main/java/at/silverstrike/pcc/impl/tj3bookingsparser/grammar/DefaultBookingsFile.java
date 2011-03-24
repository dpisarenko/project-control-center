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

package at.silverstrike.pcc.impl.tj3bookingsparser.grammar;

import java.util.LinkedList;
import java.util.List;

import at.silverstrike.pcc.api.tj3bookingsparser.BookingsFile;
import at.silverstrike.pcc.api.tj3bookingsparser.SupplementStatement;

/**
 * @author Dmitri Pisarenko
 * 
 */
class DefaultBookingsFile implements BookingsFile {
    private List<SupplementStatement> supplementStatements;

    public DefaultBookingsFile() {
        this.supplementStatements = new LinkedList<SupplementStatement>();
    }

    public void addSupplementStatement(
            final DefaultSupplementStatement aSuppStmt) {
        this.supplementStatements.add(aSuppStmt);
    }

    public List<SupplementStatement> getSupplementStatements() {
        return supplementStatements;
    }
}

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
import at.silverstrike.pcc.api.tj3bookingsparser.SupplementStatement;

/**
 * @author Dmitri Pisarenko
 * 
 */
class DefaultSupplementStatement implements SupplementStatement {
	private String taskId;
	private List<BookingStatement> bookingStatements;

	public DefaultSupplementStatement() {
		this.bookingStatements = new LinkedList<BookingStatement>();
	}

	void setTaskId(final String aTaskId) {
		this.taskId = aTaskId;
	}

	void addBookingStatement(final BookingStatement aBookingStatement) {
		this.bookingStatements.add(aBookingStatement);
	}

	public String getTaskId() {
		return taskId;
	}

	public List<BookingStatement> getBookingStatements() {
		return bookingStatements;
	}
}

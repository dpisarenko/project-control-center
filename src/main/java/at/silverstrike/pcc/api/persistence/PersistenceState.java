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

package at.silverstrike.pcc.api.persistence;

public enum PersistenceState {
	/**
	 * Initial state of the persistence mechanism.
	 */
	INITIAL,
	/**
	 * Connection is open and persistence mechanism is ready for work.
	 */
	CONNECTION_OPEN,
	
	/**
	 * Connection was closed without errors.
	 */
	CONNECTION_CLOSED,
	/**
	 * Connection could not be opened.
	 */
	CONNECTION_OPENINING_FAILURE,
	
	/**
	 * There were errors while closing the connection.
	 */
	CONNECTION_CLOSING_FAILURE,
	/**
	 * Process of opening the connection is running.
	 */
	OPENING_CONNECTION,
	/**
	 * Process of closing connection is running.
	 */
	CLOSING_CONNECTION
}

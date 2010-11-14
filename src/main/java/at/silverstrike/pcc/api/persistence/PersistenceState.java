/**
 * This file is part of Project Control Center (PCC).
 * 
 * Project Control Center (PCC) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Project Control Center (PCC) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Project Control Center (PCC).  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2010 Dmitri Anatol'evich Pisarenko
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

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

package at.silverstrike.pcc.api.model;

import java.util.Date;

import at.silverstrike.pcc.api.conventions.UniquelyIdentifiableObject;

/**
 * Instances of this class represent times, when a certain resource is required
 * for attaining a certain goal (performing certain process/task).
 * 
 * E. g. when a worker needs to work on a certain task on 06.06.2010 from 10:00
 * to 12:00, then this information is represented by an instance of this interface.
 * 
 * @author Dmitri Pisarenko
 *
 */
public interface Booking extends UniquelyIdentifiableObject {
	void setProcess(final ControlProcess aProcess);
	ControlProcess getProcess();
	
	void setResource(final Resource aResource);
	Resource getResource();
	
	void setStartDateTime(final Date aDate);
	Date getStartDateTime();
	
	void setDuration(final double aDurationInHours);
	double getDuration();
	
	Date getEndDateTime();
}

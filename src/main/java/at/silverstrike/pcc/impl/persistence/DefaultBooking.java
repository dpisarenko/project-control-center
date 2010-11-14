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

package at.silverstrike.pcc.impl.persistence;

import java.util.Date;
import at.silverstrike.pcc.api.model.Booking;
import at.silverstrike.pcc.api.model.ControlProcess;
import at.silverstrike.pcc.api.model.Resource;
import at.silverstrike.pcc.impl.util.Utils;

/**
 * @author Dmitri Pisarenko
 *
 */
class DefaultBooking implements Booking {

	private Long id;
	private ControlProcess process;
	private Resource resource;	
	private Date startDateTime;	
	private double duration;
	public ControlProcess getProcess() {
		return process;
	}
	public void setProcess(final ControlProcess process) {
		this.process = process;
	}
	public Resource getResource() {
		return resource;
	}
	public void setResource(final Resource resource) {
		this.resource = resource;
	}
	public Date getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(final Date startDateTime) {
		this.startDateTime = startDateTime;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(final double duration) {
		this.duration = duration;
	}
	@Override
	public Date getEndDateTime() {
	    return Utils.addHours(this.startDateTime, this.duration);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(final Long id) {
		this.id = id;
	}

}

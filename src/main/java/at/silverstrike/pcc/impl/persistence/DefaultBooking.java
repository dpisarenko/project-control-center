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

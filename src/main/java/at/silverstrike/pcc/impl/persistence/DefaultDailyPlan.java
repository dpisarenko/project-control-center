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

import at.silverstrike.pcc.api.model.DailyPlan;
import at.silverstrike.pcc.api.model.DailySchedule;
import at.silverstrike.pcc.api.model.DailyToDoList;
import at.silverstrike.pcc.api.model.Resource;

/**
 * @author Dmitri Pisarenko
 *
 */
class DefaultDailyPlan implements DailyPlan {
	private Resource resource;
	
	private Date date;
	
	private DailyToDoList toDoList;

	private DailySchedule schedule;
	
	private Long id;

	public Resource getResource() {
		return resource;
	}

	public void setResource(final Resource resource) {
		this.resource = resource;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	public DailyToDoList getToDoList() {
		return toDoList;
	}

	public void setToDoList(final DailyToDoList toDoList) {
		this.toDoList = toDoList;
	}

	public DailySchedule getSchedule() {
		return schedule;
	}

	public void setSchedule(final DailySchedule schedule) {
		this.schedule = schedule;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}
}

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

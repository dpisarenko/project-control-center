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

import java.util.LinkedList;
import java.util.List;

import at.silverstrike.pcc.api.model.ControlProcess;
import at.silverstrike.pcc.api.model.DailyToDoList;

/**
 * @author Dmitri Pisarenko
 *
 */
class DefaultDailyToDoList implements DailyToDoList {
	private List<ControlProcess> tasksToCompleteToday;
	private Long id;
	
	public DefaultDailyToDoList()
	{
		this.tasksToCompleteToday = new LinkedList<ControlProcess>();
	}
	
	public List<ControlProcess> getTasksToCompleteToday() {
		return tasksToCompleteToday;
	}
	public void setTasksToCompleteToday(final List<ControlProcess> tasksToCompleteToday) {
		this.tasksToCompleteToday = tasksToCompleteToday;
	}
	public Long getId() {
		return id;
	}
	public void setId(final Long id) {
		this.id = id;
	}
}

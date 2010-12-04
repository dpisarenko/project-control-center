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

package at.silverstrike.pcc.api.model;

import java.util.List;

import at.silverstrike.pcc.api.conventions.UniquelyIdentifiableObject;

/**
 * @author Dmitri Pisarenko
 *
 */
public interface DailyToDoList extends UniquelyIdentifiableObject {	
	void setTasksToCompleteToday(final List<ControlProcess> aTasksToCompleteToday);
	List<ControlProcess> getTasksToCompleteToday();
}

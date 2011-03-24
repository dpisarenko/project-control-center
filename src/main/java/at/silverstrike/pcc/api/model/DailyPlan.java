/**
 * This file is part of Project Control Center (PCC).
 * 
 * PCC (Project Control Center) project is intellectual property of 
 * Dmitri Anatol'evich Pisarenko.
 * 
 * Copyright 2010, 2011 Dmitri Anatol'evich Pisarenko
 * All rights reserved
 *
 **/

package at.silverstrike.pcc.api.model;

import java.util.Date;

import at.silverstrike.pcc.api.conventions.UniquelyIdentifiableObject;

/**
 * @author Dmitri Pisarenko
 *
 */
public interface DailyPlan extends UniquelyIdentifiableObject {
	void setResource(final Resource aResource);
	Resource getResource();
	
	void setDate(final Date aDate);
	Date getDate();
	
	void setToDoList(final DailyToDoList aToDoList);
	DailyToDoList getToDoList();
	
	void setSchedule(final DailySchedule aSchedule);
	DailySchedule getSchedule();
}

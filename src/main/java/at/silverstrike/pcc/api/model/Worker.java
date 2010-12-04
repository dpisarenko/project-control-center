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

public interface Worker extends Resource {
	void setFirstName(final String aFirstName);
	String getFirstName();
	
	void setMiddleName(final String aMiddleName);
	String getMiddleName();
	
	void setSurname(final String aSurname);
	String getSurname();
}

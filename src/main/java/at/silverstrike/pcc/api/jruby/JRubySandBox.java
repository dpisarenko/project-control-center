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
package at.silverstrike.pcc.api.jruby;

import ru.altruix.commons.api.conventions.SingleActivityModule;

/**
 * Interface for testing whether we can implement Java interfaces in
 * Ruby.
 * 
 * @author Dmitri Pisarenko
 *
 */
public interface JRubySandBox extends SingleActivityModule {
	void setStringInput(final String aString);
	void setIntInput(final int aInteger);
	String getTextOutput();
	int getIntOutput();
}

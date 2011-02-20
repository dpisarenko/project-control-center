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

package at.silverstrike.pcc.api.culture2lang;

import at.silverstrike.pcc.api.conventions.SingleActivityModule;

public interface CultureToLanguageMapper extends SingleActivityModule {
	void setCulture(final String aCulture);
	String getLanguage();
}

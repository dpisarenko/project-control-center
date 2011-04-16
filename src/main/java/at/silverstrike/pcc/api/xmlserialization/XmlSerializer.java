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

package at.silverstrike.pcc.api.xmlserialization;

import java.io.OutputStream;

import ru.altruix.commons.api.conventions.SingleActivityModule;

import at.silverstrike.pcc.api.model.UserData;

public interface XmlSerializer extends SingleActivityModule {
	void setUserData(final UserData aUserData);
	void setOutputStream(final OutputStream aOutputStream);
}

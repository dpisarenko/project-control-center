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

package at.silverstrike.pcc.api.xmlserialization;

import java.io.InputStream;

import at.silverstrike.pcc.api.conventions.SingleActivityModule;
import at.silverstrike.pcc.api.model.UserData;

/**
 * @author DP118M
 *
 */
public interface XmlDeserializer extends SingleActivityModule {
	void setInputStream(final InputStream aInputStream);
	UserData getUserData();
}

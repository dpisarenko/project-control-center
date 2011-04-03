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

package at.silverstrike.pcc.api.debugids;

import java.util.List;

import at.silverstrike.pcc.api.conventions.FunctionalBlock;

public interface DebugIdRegistry {
	void setDebugIdsFile(final String aFileName);

	String getDebugId(final FunctionalBlock aModule, final String aKey);

	List<DebugIdKey> getAllKeys();

	String getDebugId(final DebugIdKey aDebugIdKey);
}

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

package at.silverstrike.pcc.api.debugids;

import java.util.List;

import at.silverstrike.pcc.api.conventions.MessageCodePrefixRegistry.Module;

public interface DebugIdRegistry {
    void setDebugIdsFile(final String aFileName);
    String getDebugId(final Module aModule, final String aKey) throws DebugIdUniquenessViolation;
    List<DebugIdKey> getAllKeys();
    String getDebugId(DebugIdKey debugIdKey);
}

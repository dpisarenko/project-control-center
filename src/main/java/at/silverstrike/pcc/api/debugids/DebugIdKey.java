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

import at.silverstrike.pcc.api.conventions.MessageCodePrefixRegistry.Module;

public interface DebugIdKey {
    void setModule(final Module aModule);

    Module getModule();

    void setKey(final String aKey);

    String getKey();
}

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

package ru.altruix.commons.api.debugids;

import java.util.List;


/**
 * @author DP118M
 * 
 */
public interface DebugIdRegistry<F extends Enum<F>, C extends DebugIdKey<F>> {
    void setDebugIdsFile(final String aFileName);

    String getDebugId(final F aModule, final String aKey);

    List<C> getAllKeys();

    String getDebugId(final C aDebugIdKey);

}

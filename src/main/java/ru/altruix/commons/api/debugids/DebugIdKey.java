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

/**
 * @author DP118M
 *
 */
public interface DebugIdKey<C extends Enum<C>> {
    void setModule(final C aModule);

    C getModule();

    void setKey(final String aKey);

    String getKey();

}

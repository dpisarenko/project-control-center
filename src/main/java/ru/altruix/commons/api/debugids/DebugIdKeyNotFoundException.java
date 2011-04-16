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

public class DebugIdKeyNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DebugIdKeyNotFoundException(final String aKey) {
        super("Debug ID key not found: " + aKey);
    }
}

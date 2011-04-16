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

public class DebugIdUniquenessViolation extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DebugIdUniquenessViolation(final String aKey) {
        super("DebugIdUniquenessViolation: " + aKey);
    }
}

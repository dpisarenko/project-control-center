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

package ru.altruix.commons.impl.version;

import ru.altruix.commons.api.version.PccVersionReader;
import ru.altruix.commons.api.version.PccVersionReaderFactory;

public class DefaultPccVersionReaderFactory implements PccVersionReaderFactory {

    @Override
    public final PccVersionReader create() {
        return new DefaultPccVersionReader();
    }

}

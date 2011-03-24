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

package at.silverstrike.pcc.impl.version;

import at.silverstrike.pcc.api.version.PccVersionReader;
import at.silverstrike.pcc.api.version.PccVersionReaderFactory;

public class DefaultPccVersionReaderFactory implements PccVersionReaderFactory {

    @Override
    public final PccVersionReader create() {
        return new DefaultPccVersionReader();
    }

}

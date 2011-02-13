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

package at.silverstrike.pcc.impl.debugids;

import at.silverstrike.pcc.api.debugids.DebugIdKey;
import at.silverstrike.pcc.api.debugids.DebugIdKeyFactory;

public class DefaultDebugIdKeyFactory implements DebugIdKeyFactory {

    @Override
    public DebugIdKey create() {
        return new DefaultDebugIdKey();
    }
}

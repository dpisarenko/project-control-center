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

import at.silverstrike.pcc.api.debugids.DebugIdRegistry;
import at.silverstrike.pcc.api.debugids.DebugIdRegistryFactory;

public class DefaultDebugIdRegistryFactory implements DebugIdRegistryFactory {

    @Override
    public DebugIdRegistry create() {
        return new DefaultDebugIdRegistry();
    }
}

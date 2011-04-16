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

package at.silverstrike.pcc.impl.debugids;

import at.silverstrike.pcc.api.debugids.PccDebugIdRegistry;
import at.silverstrike.pcc.api.debugids.PccDebugIdRegistryFactory;

public class DefaultPccDebugIdRegistryFactory implements PccDebugIdRegistryFactory {

    @Override
    public final PccDebugIdRegistry create() {
        return new DefaultPccDebugIdRegistry();
    }
}

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

import ru.altruix.commons.api.debugids.DebugIdKeyFactory;
import at.silverstrike.pcc.api.debugids.PccDebugIdKey;
import at.silverstrike.pcc.api.pcc.PccFunctionalBlock;

class DefaultPccDebugIdKeyFactory implements DebugIdKeyFactory<PccFunctionalBlock, PccDebugIdKey> {

    @Override
    public PccDebugIdKey create() {
        return new DefaultPccDebugIdKey();
    }
}

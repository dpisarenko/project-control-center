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

import java.util.List;
import java.util.ArrayList;

import ru.altruix.commons.api.debugids.AbstractDebugIdRegistry;
import ru.altruix.commons.api.debugids.DebugIdKeyFactory;

import at.silverstrike.pcc.api.debugids.PccDebugIdKey;
import at.silverstrike.pcc.api.debugids.PccDebugIdRegistry;
import at.silverstrike.pcc.api.pcc.PccFunctionalBlock;
import at.silverstrike.pcc.api.pcc.PccMessageCodePrefixRegistry;

class DefaultPccDebugIdRegistry extends
        AbstractDebugIdRegistry<PccFunctionalBlock, PccDebugIdKey, PccMessageCodePrefixRegistry> implements
        PccDebugIdRegistry {
    private List<PccDebugIdKey> debugKeys = new ArrayList<PccDebugIdKey>();

    public DefaultPccDebugIdRegistry() {
        super(PccMessageCodePrefixRegistry.getInstance());
    }

    @Override
    public List<PccDebugIdKey> getAllKeys() {
        return debugKeys;
    }

    @Override
    protected DebugIdKeyFactory<PccFunctionalBlock, PccDebugIdKey>
            getDebugIdKeyFactory() {
        return new DefaultPccDebugIdKeyFactory();
    }

    @Override
    protected PccFunctionalBlock stringToEnum(final String aString) {
        return PccFunctionalBlock.valueOf(aString);
    }

}

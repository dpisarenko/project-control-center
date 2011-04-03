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

import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.silverstrike.pcc.api.conventions.MessageCodePrefixRegistry;
import at.silverstrike.pcc.api.conventions.FunctionalBlock;
import at.silverstrike.pcc.api.debugids.DebugIdKey;
import at.silverstrike.pcc.api.debugids.DebugIdKeyNotFoundException;
import at.silverstrike.pcc.api.debugids.DebugIdRegistry;
import at.silverstrike.pcc.api.debugids.DebugIdUniquenessViolation;

class DefaultDebugIdRegistry implements DebugIdRegistry {
    private static final String DEBUGIDS_FILE = "debugids/debugids.properties";
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultDebugIdRegistry.class);
    private Set<String> alreadyFetchedKeys = new HashSet<String>();
    private Properties properties = new Properties();
    private List<DebugIdKey> debugKeys = new ArrayList<DebugIdKey>();

    public DefaultDebugIdRegistry() {
        loadData(DEBUGIDS_FILE);
    }

    private void loadData(final String aFileName) {
        try {
            this.properties.load(getClass().getClassLoader()
                    .getResourceAsStream(aFileName));
        } catch (final Exception exception) {
            LOGGER.error(ErrorCodes.M_001_LOAD_DATA, exception);
        }

        final DefaultDebugIdKeyFactory factory = new DefaultDebugIdKeyFactory();
        for (final Enumeration<?> names = this.properties.propertyNames(); names
                .hasMoreElements();) {
            final String name = (String) names.nextElement();
            final int index = name.indexOf(MessageCodePrefixRegistry
                    .getMessageNumberSeparator());

            if (index != -1) {
                final DebugIdKey debugIdKey = factory.create();
                debugIdKey.setModule(FunctionalBlock.valueOf(name.substring(0, index)));
                debugIdKey.setKey(name.substring(index + 1));

                this.debugKeys.add(debugIdKey);
            }
        }
    }

    @Override
    public String getDebugId(final FunctionalBlock aModule, final String aKey) {
        final DefaultDebugIdKeyFactory factory = new DefaultDebugIdKeyFactory();
        final DebugIdKey debugIdKey = factory.create();
        debugIdKey.setModule(aModule);
        debugIdKey.setKey(aKey);

        return this.getDebugId(debugIdKey);
    }

    @Override
    public void setDebugIdsFile(final String aFileName) {
        loadData(aFileName);
    }

    @Override
    public List<DebugIdKey> getAllKeys() {
        return debugKeys;
    }

    @Override
    public String getDebugId(final DebugIdKey aDebugIdKey) {
        final String aModuleWithKey = aDebugIdKey.getModule().toString()
                + MessageCodePrefixRegistry.getMessageNumberSeparator()
                + aDebugIdKey.getKey();

        if (!this.debugKeys.contains(aDebugIdKey)) {
            throw new DebugIdKeyNotFoundException(aModuleWithKey);
        }
        if (this.alreadyFetchedKeys.contains(aModuleWithKey)) {
            throw new DebugIdUniquenessViolation(aModuleWithKey);
        }

        this.alreadyFetchedKeys.add(aModuleWithKey);

        return (MessageCodePrefixRegistry.getInstance().getPrefix(
                aDebugIdKey.getModule()) + this.properties
                .getProperty(aModuleWithKey));
    }

}

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

import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.silverstrike.pcc.api.conventions.MessageCodePrefixRegistry.Module;
import at.silverstrike.pcc.api.debugids.DebugIdKey;
import at.silverstrike.pcc.api.debugids.DebugIdKeyNotFoundException;
import at.silverstrike.pcc.api.debugids.DebugIdRegistry;
import at.silverstrike.pcc.api.debugids.DebugIdUniquenessViolation;

class DefaultDebugIdRegistry implements DebugIdRegistry {
    private static final String DEBUGIDS_FILE = "debugids/debugids.properties";
    private final Logger LOGGER =
        LoggerFactory.getLogger(DefaultDebugIdRegistry.class);
    private Set<String> alreadyFetchedKeys = new HashSet<String>();
    private Properties properties = new Properties();
    
    public DefaultDebugIdRegistry()
    {
        loadData(DEBUGIDS_FILE);
    }

    private void loadData(final String aFileName) {
        try
        {
            properties.load(getClass().getClassLoader().getResourceAsStream(aFileName));
        }
        catch (final Exception exception)
        {
            LOGGER.error(ErrorCodes.M_001_LOAD_DATA, exception);
        }
    }
    
    @Override
    public String getDebugId(final String aKey) throws DebugIdUniquenessViolation, DebugIdKeyNotFoundException {
        if (this.alreadyFetchedKeys.contains(aKey))
        {
            throw new DebugIdUniquenessViolation(aKey);
        }
        
        if (!this.properties.containsKey(aKey))
        {
            throw new DebugIdKeyNotFoundException(aKey);
        }
        
        this.alreadyFetchedKeys.add(aKey);
        
        return this.properties.getProperty(aKey);
    }

    @Override
    public String getDebugId(final Module aModule, final String aKey)
            throws DebugIdUniquenessViolation {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setDebugIdsFile(final String aFileName) {
        loadData(aFileName);
    }

    @Override
    public List<DebugIdKey> getAllKeys() {
        throw new NotImplementedException();
    }

    @Override
    public String getDebugId(DebugIdKey debugIdKey) {
        throw new NotImplementedException();
    }

}

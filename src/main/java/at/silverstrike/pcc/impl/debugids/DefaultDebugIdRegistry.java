package at.silverstrike.pcc.impl.debugids;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        try
        {
            properties.load(getClass().getClassLoader().getResourceAsStream(DEBUGIDS_FILE));
        }
        catch (final Exception exception)
        {
            LOGGER.error(ErrorCodes.M_001_CTOR, exception);
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

}

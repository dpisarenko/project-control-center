package at.silverstrike.pcc.impl.version;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import junit.framework.Assert;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.version.PccVersionReader;

class DefaultPccVersionReader implements PccVersionReader {
    private static final String PROPERTY_FILE_PATH = "META-INF/maven/at.swdev/pcc/pom.properties";
    private static final String VERSION_PROPERTY_NAME = "version";
    private final Logger LOGGER =
        LoggerFactory.getLogger(DefaultPccVersionReader.class);
    private String version = "?";
    
    @Override
    public String getVersion() {
        return this.version;
    }

    @Override
    public void run() throws PccException {
        final Properties properties = new Properties();        
        final InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTY_FILE_PATH);
        try {
            properties.load(inputStream);
            final String versionFromFile = properties.getProperty(VERSION_PROPERTY_NAME);
            
            if (StringUtils.isNotBlank(versionFromFile))
            {
                this.version = versionFromFile;
            }

        } catch (final IOException exception) {
            LOGGER.error(ErrorCodes.M_001_RUN, exception);
        }
        catch (final RuntimeException exception)
        {
            LOGGER.error(ErrorCodes.M_002_RUN2, exception);
        }
        finally
        {
            IOUtils.closeQuietly(inputStream);
        }
    }

}

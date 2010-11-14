/**
 * This file is part of Project Control Center (PCC).
 * 
 * Project Control Center (PCC) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Project Control Center (PCC) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Project Control Center (PCC).  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2010 Dmitri Anatol'evich Pisarenko
 **/

package at.silverstrike.pcc.impl.embeddedfilereading;

import static org.apache.commons.io.FileUtils.openInputStream;

import java.io.File;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.embeddedfilereading.EmbeddedFileReader;

/**
 * @author Dmitri Pisarenko
 * 
 */
class DefaultEmbeddedFileReader implements EmbeddedFileReader {
    private final static Logger LOGGER =
        LoggerFactory.getLogger(DefaultEmbeddedFileReader.class);

    @Override
    public String readEmbeddedFile(final String aFileName) throws PccException {
        final ClassLoader contextClassLoader =
                Thread.currentThread().getContextClassLoader();
        InputStream inputStream = null;
        String projectTemplate = null;
        try {
            inputStream = contextClassLoader.getResourceAsStream(aFileName);

            if (inputStream == null) {
                /**
                 * If we are here, it means that this code is invoked outside of
                 * the web application, e. g. in a unit test.
                 */                
                inputStream = openInputStream(new File(System.getProperty("user.dir") + 
                        "/src/main/webapp/WEB-INF/classes/" + aFileName));
            }

            LOGGER.debug("inputStream: " + inputStream);
            LOGGER.debug("aFileName: " + aFileName);
            LOGGER.debug("pwd: " + System.getProperty("user.dir"));
            projectTemplate = IOUtils.toString(inputStream);
        } catch (final Exception exception) {
            LOGGER.debug("", exception);
            throw new PccException(exception);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
        return projectTemplate;

    }

}

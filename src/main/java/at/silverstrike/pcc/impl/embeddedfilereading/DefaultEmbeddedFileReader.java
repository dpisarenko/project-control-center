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
    private static final Logger LOGGER =
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
                inputStream =
                        openInputStream(new File(System.getProperty("user.dir")
                                +
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

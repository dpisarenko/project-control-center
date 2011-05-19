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

package at.silverstrike.pcc.impl.automaticexport;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.altruix.commons.api.di.PccException;

import com.google.inject.Injector;

import at.silverstrike.pcc.api.automaticexport.AutomaticExporter;
import at.silverstrike.pcc.api.model.UserData;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.xmlserialization.XmlSerializer;
import at.silverstrike.pcc.api.xmlserialization.XmlSerializerFactory;
import at.silverstrike.pcc.impl.xmlserialization.DefaultXmlSerializerFactory;

/**
 * @author DP118M
 * 
 */
class DefaultAutomaticExporter implements AutomaticExporter {
    private static final SimpleDateFormat TIMESTAMP_FORMAT =
            new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultAutomaticExporter.class);
    private Injector injector;
    private File targetFile;

    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;
    }

    @Override
    public void run() throws PccException {
        final Persistence persistence =
                this.injector.getInstance(Persistence.class);

        final UserData writtenData = persistence.getUserData();

        final XmlSerializerFactory serializerFactory =
                new DefaultXmlSerializerFactory();
        final XmlSerializer serializer = serializerFactory.create();

        final String fileName =
                "PCC_exported_data_${datetime}.xml".replace("${datetime}",
                        TIMESTAMP_FORMAT
                                .format(new Date()));

        targetFile = new File(fileName);
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(targetFile);
            serializer.setOutputStream(fileOutputStream);
            serializer.setUserData(writtenData);

            serializer.run();
        } catch (final PccException exception) {
            LOGGER.error("", exception);
        } catch (final FileNotFoundException exception) {
            LOGGER.error("", exception);
        } finally {
            IOUtils.closeQuietly(fileOutputStream);
        }
    }

    @Override
    public File getTargetFile() {
        return this.targetFile;
    }
}

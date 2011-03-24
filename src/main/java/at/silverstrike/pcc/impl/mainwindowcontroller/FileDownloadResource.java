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

package at.silverstrike.pcc.impl.mainwindowcontroller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.Application;
import com.vaadin.terminal.DownloadStream;
import com.vaadin.terminal.FileResource;

/**
 * @author DP118M
 * 
 */
class FileDownloadResource extends FileResource {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory
            .getLogger(FileDownloadResource.class);

    public FileDownloadResource(File sourceFile, Application application) {
        super(sourceFile, application);
    }

    public DownloadStream getStream() {
        try {
            final DownloadStream ds = new DownloadStream(new FileInputStream(
                    getSourceFile()), getMIMEType(), getFilename());
            ds.setParameter("Content-Disposition", "attachment; filename="
                    + getFilename());
            ds.setCacheTime(getCacheTime());
            return ds;
        } catch (final FileNotFoundException exception) {
            LOGGER.error("", exception);
            return null;
        }
    }
}

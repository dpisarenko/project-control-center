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

package at.silverstrike.pcc.impl.graphdemopanel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.Application;
import com.vaadin.terminal.ApplicationResource;
import com.vaadin.terminal.DownloadStream;

import eu.livotov.tpt.TPTApplication;

class JungResource implements ApplicationResource {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory
            .getLogger(JungResource.class);

    private transient ByteArrayInputStream bytestream = null;
    private transient ByteArrayOutputStream originalOutputStream;

    public JungResource(final ByteArrayOutputStream aOutputStream) {
        this.originalOutputStream = aOutputStream;
    }

    @Override
    public String getMIMEType() {
        return "image/svg+xml";
    }

    @Override
    public DownloadStream getStream() {
        DownloadStream downloadStream = new DownloadStream(
                getByteStream(), getMIMEType(), getFilename());
        return downloadStream;

    }

    private InputStream getByteStream() {
        this.bytestream = new ByteArrayInputStream(
                originalOutputStream.toByteArray());
        return this.bytestream;
    }

    @Override
    public Application getApplication() {
        return TPTApplication.getCurrentApplication();
    }

    @Override
    public String getFilename() {
        return "projectnetwork.svg";

    }

    @Override
    public long getCacheTime() {
        return 0;
    }

    @Override
    public int getBufferSize() {
        try {
            return getByteStream().available();
        } catch (final IOException exception) {
            LOGGER.error("", exception);
            return 0;
        }
    }

}

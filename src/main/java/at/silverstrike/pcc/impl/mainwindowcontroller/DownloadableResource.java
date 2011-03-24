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

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.Map;

import com.vaadin.terminal.DownloadStream;
import com.vaadin.terminal.ParameterHandler;
import com.vaadin.terminal.URIHandler;

/**
 * @author DP118M
 * 
 */
class DownloadableResource implements URIHandler,
        ParameterHandler {
    private static final long serialVersionUID = 1L;

    public void handleParameters(@SuppressWarnings("rawtypes") Map parameters) {
    }

    public DownloadStream handleURI(final URL aContext,
            final String aRelativeUri) {
        if (!aRelativeUri.startsWith("exportToXml")) {
            return null;
        }

        return new DownloadStream(
                new ByteArrayInputStream("Hello!".getBytes()), null, null);
    }
}

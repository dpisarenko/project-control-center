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

package at.silverstrike.pcc.impl.xmlserialization;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import ru.altruix.commons.api.di.PccException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import at.silverstrike.pcc.api.model.UserData;
import at.silverstrike.pcc.api.xmlserialization.XmlDeserializer;

class DefaultXmlDeserializer implements XmlDeserializer {
    private InputStream inputStream;
    private UserData userData;

    public void run() throws PccException {
        final XStream xstream = new XStream(new DomDriver());
        String xml = null;

        try {
            xml = IOUtils.toString(this.inputStream);
        } catch (final IOException exception) {
            throw new PccException(exception);
        }

        userData = (UserData) xstream.fromXML(xml);
    }

    public void setInputStream(final InputStream aInputStream) {
        this.inputStream = aInputStream;
    }

    public UserData getUserData() {
        return this.userData;
    }
}

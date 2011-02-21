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

package at.silverstrike.pcc.impl.xmlserialization;

import java.io.IOException;
import java.io.OutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.model.UserData;
import at.silverstrike.pcc.api.xmlserialization.XmlSerializer;

class DefaultXmlSerializer implements XmlSerializer {
    private UserData userData;
    private OutputStream outputStream = null;

    public void run() throws PccException {
        final XStream xstream = new XStream(new DomDriver());
        final String xml = xstream.toXML(userData);
        try {
            outputStream.write(xml.getBytes());
        } catch (final IOException exception) {
            throw new PccException(exception);
        }
    }

    public void setUserData(final UserData aUserData) {
        this.userData = aUserData;
    }

    public void setOutputStream(final OutputStream aOutputStream) {
        this.outputStream = aOutputStream;
    }
}

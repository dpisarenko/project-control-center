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

package at.silverstrike.pcc.impl.xmlimportwindowcontroller;

import at.silverstrike.pcc.api.xmlimportwindowcontroller.XmlImportWindowController;
import at.silverstrike.pcc.api.xmlimportwindowcontroller.XmlImportWindowControllerFactory;

/**
 * @author DP118M
 *
 */
public final class DefaultXmlImportWindowControllerFactory implements
        XmlImportWindowControllerFactory {

    @Override
    public XmlImportWindowController create() {
        return new DefaultXmlImportWindowController();
    }

}

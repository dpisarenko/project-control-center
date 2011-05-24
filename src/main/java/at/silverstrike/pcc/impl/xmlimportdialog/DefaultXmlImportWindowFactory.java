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

package at.silverstrike.pcc.impl.xmlimportdialog;

import at.silverstrike.pcc.api.xmlimportdialog.XmlImportWindow;
import at.silverstrike.pcc.api.xmlimportdialog.XmlImportWindowFactory;

/**
 * @author DP118M
 *
 */
public class DefaultXmlImportWindowFactory implements XmlImportWindowFactory {

    @Override
    public XmlImportWindow create() {
        return new DefaultXmlImportWindow();
    }

}

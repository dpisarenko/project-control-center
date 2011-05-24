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

import com.google.inject.Injector;
import com.vaadin.ui.Window;

import at.silverstrike.pcc.api.xmlimportdialog.XmlImportWindow;
import at.silverstrike.pcc.api.xmlimportdialogcontroller.XmlImportWindowController;

/**
 * @author DP118M
 * 
 */
class DefaultXmlImportWindow implements XmlImportWindow {
    private Injector injector;
    private XmlImportWindowController controller;

    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;
    }

    @Override
    public void setGuiController(final XmlImportWindowController aController) {
        this.controller = aController;
    }

    @Override
    public Window toWindow() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void initGui() {
        // TODO Auto-generated method stub
        
    }

}

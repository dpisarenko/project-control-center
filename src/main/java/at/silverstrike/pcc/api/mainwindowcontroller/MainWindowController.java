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

package at.silverstrike.pcc.api.mainwindowcontroller;

import ru.altruix.commons.api.di.ModuleWithInjectableDependencies;

import com.vaadin.Application;


public interface MainWindowController extends ModuleWithInjectableDependencies {
    void importFromXML();

    void exportToXML();

    void initGui(final Application aApplication);
}

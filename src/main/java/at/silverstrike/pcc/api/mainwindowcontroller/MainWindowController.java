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
import ru.altruix.commons.api.gui.GuiController;

import com.vaadin.ui.Window;

public interface MainWindowController extends ModuleWithInjectableDependencies,
        GuiController<Window> {
    void importFromXML();

    void exportToXML();
}

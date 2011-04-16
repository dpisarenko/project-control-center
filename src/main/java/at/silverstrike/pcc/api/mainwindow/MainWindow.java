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

package at.silverstrike.pcc.api.mainwindow;

import ru.altruix.commons.api.di.ModuleWithInjectableDependencies;
import ru.altruix.commons.api.gui.InitializableGuiComponent;
import ru.altruix.commons.api.vaadin.AbstractedWindow;

import com.vaadin.ui.Panel;


public interface MainWindow extends ModuleWithInjectableDependencies,
		InitializableGuiComponent, AbstractedWindow {

    void setCentralEditingPanel(final Panel aPanel);	
}

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

package at.silverstrike.pcc.api.xmlimportdialog;

import com.vaadin.ui.Window;

import at.silverstrike.pcc.api.xmlimportdialogcontroller.XmlImportWindowController;
import ru.altruix.commons.api.di.ModuleWithInjectableDependencies;
import ru.altruix.commons.api.gui.ExternallyControlledGuiComponent;
import ru.altruix.commons.api.gui.InitializableGuiComponent;
import ru.altruix.commons.api.vaadin.AbstractedWindow;

/**
 * @author DP118M
 * 
 */
public interface XmlImportWindow extends ModuleWithInjectableDependencies,
        ExternallyControlledGuiComponent<XmlImportWindowController, Window>,
        AbstractedWindow, InitializableGuiComponent {

}

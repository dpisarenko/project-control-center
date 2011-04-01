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

package at.silverstrike.pcc.api.centraleditingpanel;

import at.silverstrike.pcc.api.conventions.AbstractedPanel;
import at.silverstrike.pcc.api.conventions.ModuleWithInjectableDependencies;
import at.silverstrike.pcc.api.webguibus.WebGuiBusListener;

public interface CentralEditingPanel extends ModuleWithInjectableDependencies,
        AbstractedPanel, WebGuiBusListener {

}

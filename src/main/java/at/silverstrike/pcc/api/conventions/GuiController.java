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

package at.silverstrike.pcc.api.conventions;

import at.silverstrike.pcc.api.webguibus.WebGuiBusListener;

import com.vaadin.ui.AbstractComponent;

/**
 * @author DP118M
 *
 */
public interface GuiController extends WebGuiBusListener {
    AbstractComponent initGui();
}

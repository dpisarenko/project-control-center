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

package at.silverstrike.pcc.api.schedulingpanelcontroller;

import at.silverstrike.pcc.api.webguibus.WebGuiBusListener;

import com.vaadin.ui.Panel;

import ru.altruix.commons.api.gui.GuiController;

/**
 * @author DP118M
 * 
 */
public interface SchedulingPanelController extends GuiController<Panel>,
        WebGuiBusListener {

}

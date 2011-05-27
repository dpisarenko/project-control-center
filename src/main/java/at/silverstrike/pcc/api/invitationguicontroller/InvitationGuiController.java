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

package at.silverstrike.pcc.api.invitationguicontroller;

import com.vaadin.ui.Window;

import ru.altruix.commons.api.di.ModuleWithInjectableDependencies;
import ru.altruix.commons.api.gui.GuiController;

/**
 * @author DP118M
 * 
 */
public interface InvitationGuiController extends
        ModuleWithInjectableDependencies, GuiController<Window> {

    void nextButtonInStep1Pressed();

    void nextButtonInStep2Pressed();
}

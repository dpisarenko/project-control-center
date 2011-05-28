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

package at.silverstrike.pcc.api.invitationrequestadminpanelcontroller;

import ru.altruix.commons.api.di.ModuleWithInjectableDependencies;
import ru.altruix.commons.api.gui.GuiController;

import at.silverstrike.pcc.api.model.InvitationRequest;

import com.vaadin.ui.Panel;

/**
 * @author DP118M
 * 
 */
public interface InvitationRequestAdminPanelController extends
        ModuleWithInjectableDependencies, GuiController<Panel> {

    void acceptButtonPressed(final String aUserIdentity, final InvitationRequest aRequest);

    void rejectButtonPressed(final InvitationRequest aSelectedRequest);

    void refreshButtonPressed();

}

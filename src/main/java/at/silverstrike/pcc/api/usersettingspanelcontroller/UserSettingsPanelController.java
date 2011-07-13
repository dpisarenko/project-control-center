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

package at.silverstrike.pcc.api.usersettingspanelcontroller;

import ru.altruix.commons.api.di.ModuleWithInjectableDependencies;
import ru.altruix.commons.api.gui.GuiController;

import com.vaadin.ui.Panel;

/**
 * @author DP118M
 * 
 */
public interface UserSettingsPanelController extends
        ModuleWithInjectableDependencies, GuiController<Panel> {

    void calculateAndSyncData(final String aAuthCode);

    void requestGoogleAuthorizationCode();

    void logout();

    void writeBookingsToCalendar();

    void writeBookingsToCalendar2(final String aAuthorizationCode);

    void setOauthQueryString(final String aQueryString);

    void sendMessageToQueue();
}

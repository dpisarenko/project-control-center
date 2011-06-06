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

package at.silverstrike.pcc.impl.usersettingspanelcontroller;

import com.google.inject.Injector;
import com.vaadin.ui.Panel;

import eu.livotov.tpt.TPTApplication;

import at.silverstrike.pcc.api.model.UserData;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.usersettingspanel.UserSettingsPanel;
import at.silverstrike.pcc.api.usersettingspanel.UserSettingsPanelFactory;
import at.silverstrike.pcc.api.usersettingspanelcontroller.UserSettingsPanelController;

/**
 * @author DP118M
 * 
 */
class DefaultUserSettingsPanelController implements UserSettingsPanelController {
    private Injector injector;
    private Persistence persistence;

    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;

        if (this.injector != null) {
            this.persistence = (Persistence) this.injector;
        }
    }

    @Override
    public Panel initGui() {
        final UserSettingsPanelFactory factory =
                this.injector.getInstance(UserSettingsPanelFactory.class);
        final UserSettingsPanel panel = factory.create();
        panel.initGui();
        return panel.toPanel();
    }

    @Override
    public void saveGoogleAccessData(final String aUsername,
            final String aPassword) {
        final UserData user =
                (UserData) TPTApplication.getCurrentApplication().getUser();

        user.setGoogleUsername(aUsername);
        user.setGooglePassword(aPassword);

        this.persistence.updateUser(user);
    }

    @Override
    public void fetchData(final String aUsername, final String aPassword) {
        // TODO Auto-generated method stub

    }

    @Override
    public void
            writeDataToGoogle(final String aUsername, final String aPassword) {
        // TODO Auto-generated method stub

    }
}

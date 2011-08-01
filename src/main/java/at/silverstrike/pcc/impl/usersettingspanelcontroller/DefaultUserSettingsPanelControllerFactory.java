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

import at.silverstrike.pcc.api.usersettingspanelcontroller.UserSettingsPanelController;
import at.silverstrike.pcc.api.usersettingspanelcontroller.UserSettingsPanelControllerFactory;

/**
 * @author DP118M
 * 
 */
public final class DefaultUserSettingsPanelControllerFactory implements
        UserSettingsPanelControllerFactory {

    private String oauthRedirectUri;

    @Override
    public UserSettingsPanelController create() {
        final UserSettingsPanelController returnValue =
                new DefaultUserSettingsPanelController();

        returnValue.setOauthRedirectUri(this.oauthRedirectUri);

        return returnValue;
    }

    public void setOauthRedirectUri(final String aOauthRedirectUri) {
        this.oauthRedirectUri = aOauthRedirectUri;
    }

}

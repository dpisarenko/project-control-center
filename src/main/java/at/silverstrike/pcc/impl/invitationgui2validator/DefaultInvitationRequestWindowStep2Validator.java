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

package at.silverstrike.pcc.impl.invitationgui2validator;

import org.apache.commons.lang.StringUtils;

import ru.altruix.commons.api.di.PccException;
import at.silverstrike.pcc.api.invitationgui2validator.InvitationRequestWindowStep2Validator;
import at.silverstrike.pcc.api.openid.SupportedOpenIdProvider;

/**
 * @author DP118M
 * 
 */
class DefaultInvitationRequestWindowStep2Validator implements
        InvitationRequestWindowStep2Validator {
    private boolean valid;
    private String facebookId;
    private String vkontakteId;
    private String googleUsername;
    private SupportedOpenIdProvider openIdProvider;
    private String userName;

    @Override
    public void run() throws PccException {
        final String trimmedFacebookId = StringUtils.trim(facebookId);
        final String trimmedVkontakteId = StringUtils.trim(vkontakteId);
        final String trimmedGoogleUsername = StringUtils.trim(googleUsername);

        if (!StringUtils.isBlank(trimmedFacebookId)) {
            this.openIdProvider = SupportedOpenIdProvider.FACEBOOK;
            this.userName = trimmedFacebookId;
            this.valid = true;
        } else if (!StringUtils.isBlank(trimmedVkontakteId)) {
            this.openIdProvider = SupportedOpenIdProvider.VKONTAKTE;
            this.userName = trimmedVkontakteId;
            this.valid = true;
        } else if (!StringUtils.isBlank(trimmedGoogleUsername)) {
            this.openIdProvider = SupportedOpenIdProvider.GOOGLE;
            this.userName = trimmedGoogleUsername;
            this.valid = true;
        } else {
            this.valid = false;
        }
    }

    @Override
    public void setEmail(final String aFacebookId) {
        this.facebookId = aFacebookId;
    }

    @Override
    public void setVkontakteId(final String aVkontakteId) {
        this.vkontakteId = aVkontakteId;
    }

    @Override
    public void setGoogleUsername(final String aGoogleUsername) {
        this.googleUsername = aGoogleUsername;
    }

    @Override
    public boolean isValid() {
        return this.valid;
    }

    @Override
    public SupportedOpenIdProvider getOpenIdProvider() {
        return openIdProvider;
    }

    @Override
    public String getUserName() {
        return userName;
    }
}

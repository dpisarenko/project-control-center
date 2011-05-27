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

package at.silverstrike.pcc.api.invitationgui2validator;

import at.silverstrike.pcc.api.openid.SupportedOpenIdProvider;
import ru.altruix.commons.api.conventions.SingleActivityModule;

/**
 * @author DP118M
 *
 */
public interface InvitationRequestWindowStep2Validator extends SingleActivityModule {
    void setFacebookId(final String aFacebookId);
    void setVkontakteId(final String aVkontakteId);
    void setGoogleUsername(final String aGoogleUsername);
    
    boolean isValid();
    SupportedOpenIdProvider getOpenIdProvider();
    String getUserName();
}

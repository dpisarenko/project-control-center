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

package at.silverstrike.pcc.api.googletasksservicecreator;

import ru.altruix.commons.api.conventions.SingleActivityModule;
import com.google.api.services.tasks.v1.Tasks;

/**
 * @author DP118M
 *
 */
public interface GoogleTasksServiceCreator extends SingleActivityModule {
    void setClientId(final String aClientId);
    void setClientSecret(final String aClientSecret);
    void setRedirectUrl(final String aUrl);
    void setApplicationName(final String aAppName);
    void setAuthorizationCode(final String aAuthorizationCode);
    
    Tasks getService();
    String getOAuthAccessToken();
}

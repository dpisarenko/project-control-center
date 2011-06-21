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

package at.silverstrike.pcc.api.gcaltasks2pccimporter;

import java.util.List;

import ru.altruix.commons.api.conventions.SingleActivityModule;
import ru.altruix.commons.api.di.ModuleWithInjectableDependencies;

import at.silverstrike.pcc.api.model.UserData;

/**
 * @author DP118M
 * 
 */
public interface GoogleCalendarTasks2PccImporter2 extends SingleActivityModule,
        ModuleWithInjectableDependencies {
    void setGoogleTasks(final java.util.List<com.google.api.services.tasks.v1.model.Task> aTasks);
    void setUser(final UserData aUser);
    
    List<at.silverstrike.pcc.api.model.Task> getCreatedPccTasks();
}

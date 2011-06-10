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

package at.silverstrike.pcc.api.gcaltasks2pcc;

import at.silverstrike.pcc.api.model.UserData;

import com.google.api.services.tasks.v1.Tasks;
import ru.altruix.commons.api.conventions.SingleActivityModule;
import ru.altruix.commons.api.di.ModuleWithInjectableDependencies;

/**
 * @author DP118M
 * 
 */
public interface GoogleCalendarTasks2PccImporter extends SingleActivityModule,
        ModuleWithInjectableDependencies {
    void setService(final Tasks aService);
    void setUser(final UserData aUser);
}

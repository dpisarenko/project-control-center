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

package at.silverstrike.pcc.api.bookings2googleexporter;

import at.silverstrike.pcc.api.model.UserData;
import ru.altruix.commons.api.conventions.SingleActivityModule;
import ru.altruix.commons.api.di.ModuleWithInjectableDependencies;

/**
 * @author DP118M
 * 
 */
public interface Bookings2GoogleCaendarExporter extends SingleActivityModule,
        ModuleWithInjectableDependencies {
    void setUserData(final UserData aUserData);
}

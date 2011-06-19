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

package at.silverstrike.pcc.impl.gcaltasks2pccimporter;

import at.silverstrike.pcc.api.gcaltasks2pccimporter.GoogleCalendarTasks2PccImporter2;
import at.silverstrike.pcc.api.gcaltasks2pccimporter.GoogleCalendarTasks2PccImporter2Factory;

/**
 * @author DP118M
 * 
 */
public final class DefaultGoogleCalendarTasks2PccImporter2Factory implements
        GoogleCalendarTasks2PccImporter2Factory {

    @Override
    public GoogleCalendarTasks2PccImporter2 create() {
        return new DefaultGoogleCalendarTasks2PccImporter2();
    }

}

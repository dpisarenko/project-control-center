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

package at.silverstrike.pcc.impl.gcaltasks2pcc;

import at.silverstrike.pcc.api.gcaltasks2pcc.GoogleCalendarTasks2PccImporter;
import at.silverstrike.pcc.api.gcaltasks2pcc.GoogleCalendarTasks2PccImporterFactory;

/**
 * @author DP118M
 *
 */
public final class DefaultGoogleCalendarTasks2PccImporterFactory implements
        GoogleCalendarTasks2PccImporterFactory {

    @Override
    public GoogleCalendarTasks2PccImporter create() {
        return new DefaultGoogleCalendarTasks2PccImporter();
    }

}

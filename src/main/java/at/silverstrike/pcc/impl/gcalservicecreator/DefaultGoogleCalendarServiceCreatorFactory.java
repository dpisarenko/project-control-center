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

package at.silverstrike.pcc.impl.gcalservicecreator;

import at.silverstrike.pcc.api.gcalservicecreator.GoogleCalendarServiceCreator;
import at.silverstrike.pcc.api.gcalservicecreator.GoogleCalendarServiceCreatorFactory;

/**
 * @author DP118M
 *
 */
public class DefaultGoogleCalendarServiceCreatorFactory implements
        GoogleCalendarServiceCreatorFactory {

    @Override
    public GoogleCalendarServiceCreator create() {
        return new DefaultGoogleCalendarServiceCreator();
    }

}

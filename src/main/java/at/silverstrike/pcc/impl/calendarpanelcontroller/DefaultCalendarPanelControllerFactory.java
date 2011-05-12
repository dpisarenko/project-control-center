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

package at.silverstrike.pcc.impl.calendarpanelcontroller;

import at.silverstrike.pcc.api.calendarpanelcontroller.CalendarPanelController;
import at.silverstrike.pcc.api.calendarpanelcontroller.CalendarPanelControllerFactory;

/**
 * @author DP118M
 *
 */
public class DefaultCalendarPanelControllerFactory implements
        CalendarPanelControllerFactory {

    @Override
    public CalendarPanelController create() {
        return new DefaultCalendarPanelController();
    }

}

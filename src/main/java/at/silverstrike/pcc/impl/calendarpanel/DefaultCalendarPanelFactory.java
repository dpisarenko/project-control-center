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

package at.silverstrike.pcc.impl.calendarpanel;

import at.silverstrike.pcc.api.calendarpanel.CalendarPanel;
import at.silverstrike.pcc.api.calendarpanel.CalendarPanelFactory;

/**
 * @author DP118M
 *
 */
public final class DefaultCalendarPanelFactory implements CalendarPanelFactory {

    @Override
    public CalendarPanel create() {
        return new DefaultCalendarPanel();
    }

}

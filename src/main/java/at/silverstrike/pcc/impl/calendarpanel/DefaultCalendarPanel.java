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

import com.google.inject.Injector;
import com.vaadin.ui.Panel;

import at.silverstrike.pcc.api.calendarpanel.CalendarPanel;

/**
 * @author DP118M
 *
 */
class DefaultCalendarPanel implements CalendarPanel {
    private Injector injector;
    
    @Override
    public Panel toPanel() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void initGui() {
        // TODO Auto-generated method stub

    }
    
    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;
    }
}

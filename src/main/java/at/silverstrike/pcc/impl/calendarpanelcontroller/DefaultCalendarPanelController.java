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

import com.google.inject.Injector;
import com.vaadin.ui.Panel;

import at.silverstrike.pcc.api.calendarpanel.CalendarPanel;
import at.silverstrike.pcc.api.calendarpanel.CalendarPanelFactory;
import at.silverstrike.pcc.api.calendarpanelcontroller.CalendarPanelController;

/**
 * @author DP118M
 *
 */
class DefaultCalendarPanelController implements CalendarPanelController {
    private Injector injector;
    
    @Override
    public Panel initGui() {
        final CalendarPanelFactory factory = this.injector.getInstance(CalendarPanelFactory.class);
        final CalendarPanel panel = factory.create();
        
        panel.setInjector(this.injector);
        panel.initGui();
        
        return panel.toPanel();
    }

    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;
    }
}

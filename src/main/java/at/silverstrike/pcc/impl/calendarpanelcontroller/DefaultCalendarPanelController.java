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
import at.silverstrike.pcc.api.webguibus.WebGuiBus;
import at.silverstrike.pcc.impl.webguibus.WebGuiBusListenerAdapter;

/**
 * @author DP118M
 * 
 */
class DefaultCalendarPanelController extends WebGuiBusListenerAdapter implements
        CalendarPanelController {
    private Injector injector;
    private CalendarPanel panel;

    @Override
    public Panel initGui() {
        final WebGuiBus webGuiBus = this.injector.getInstance(WebGuiBus.class);
        webGuiBus.addListener(this);

        final CalendarPanelFactory factory =
                this.injector.getInstance(CalendarPanelFactory.class);
        panel = factory.create();

        panel.setInjector(this.injector);
        panel.initGui();

        return panel.toPanel();
    }

    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;
    }

    @Override
    public void planCalculated() {
        /**
         * This method is called whenever a plan has been calculated successfully.
         */
    }
}

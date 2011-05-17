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

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

import com.google.inject.Injector;
import com.vaadin.addon.calendar.ui.Calendar;
import com.vaadin.ui.Panel;

import at.silverstrike.pcc.api.calendarpanel.CalendarPanel;

/**
 * @author DP118M
 *
 */
class DefaultCalendarPanel extends Panel implements CalendarPanel {
    private static final int SEVEN_DAYS = 7;
    private static final long serialVersionUID = 1L;
    
    @Override
    public Panel toPanel() {
        return this;
    }

    @Override
    public void initGui() {
        final Calendar calendar = new Calendar();
        final Date now = new Date();
        final Date inOneWeek = DateUtils.addDays(now, SEVEN_DAYS);
        
        calendar.setStartDate(now);
        calendar.setEndDate(inOneWeek);
        
        calendar.setSizeFull();
        
        this.addComponent(calendar);
    }
    
    @Override
    public void setInjector(final Injector aInjector) {
    }
}

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

package at.silverstrike.pcc.impl.dailyplanpanel;

import at.silverstrike.pcc.api.dailyplanpanel.DailyPlanPanel;
import at.silverstrike.pcc.api.dailyplanpanel.DailyPlanPanelFactory;

/**
 * @author Dmitri Pisarenko
 * 
 */
public class DefaultDailyPlanPanelFactory implements DailyPlanPanelFactory {

    /**
     * @see at.silverstrike.pcc.api.conventions.Factory#create()
     */
    @Override
    public final DailyPlanPanel create() {
        return new DefaultDailyPlanPanel();
    }

}

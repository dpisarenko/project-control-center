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

package at.silverstrike.pcc.impl.schedulingindicatorpanel;

import at.silverstrike.pcc.api.schedulingindicatorpanel.SchedulingIndicatorPanel;
import at.silverstrike.pcc.api.schedulingindicatorpanel.SchedulingIndicatorPanelFactory;

/**
 * @author DP118M
 *
 */
public final class DefautSchedulingIndicatorPanelFactory implements
        SchedulingIndicatorPanelFactory {

    @Override
    public SchedulingIndicatorPanel create() {
        return new DefaultSchedulingIndicatorPanel();
    }

}

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

package at.silverstrike.pcc.impl.milestoneeditingpanelcontroller;

import at.silverstrike.pcc.api.milestoneeditingpanelcontroller.MilestoneEditingPanelController;
import at.silverstrike.pcc.api.milestoneeditingpanelcontroller.MilestoneEditingPanelControllerFactory;

/**
 * @author DP118M
 * 
 */
public final class DefaultMilestoneEditingPanelControllerFactory implements
        MilestoneEditingPanelControllerFactory {

    @Override
    public MilestoneEditingPanelController create() {
        return new DefaultMilestoneEditingPanelController();
    }

}

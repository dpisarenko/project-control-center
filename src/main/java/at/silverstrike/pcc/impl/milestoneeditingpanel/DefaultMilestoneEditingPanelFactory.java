/**
 * This file is part of Project Control Center (PCC).
 * 
 * PCC (Project Control Center) project is intellectual property of 
 * Dmitri Anatol'evich Pisarenko.
 * 
 * Copyright 2010 Dmitri Anatol'evich Pisarenko
 * All rights reserved
 *
 **/

package at.silverstrike.pcc.impl.milestoneeditingpanel;

import at.silverstrike.pcc.api.milestoneeditingpanel.MilestoneEditingPanel;
import at.silverstrike.pcc.api.milestoneeditingpanel.MilestoneEditingPanelFactory;

/**
 * @author Dmitri Pisarenko
 * 
 */
public class DefaultMilestoneEditingPanelFactory implements
        MilestoneEditingPanelFactory {
    /**
     * @see at.silverstrike.pcc.api.conventions.Factory#create()
     */
    @Override
    public final MilestoneEditingPanel create() {
        return new DefaultMilestoneEditingPanel();
    }

}
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

package at.silverstrike.pcc.impl.schedulingguicontroller;

import at.silverstrike.pcc.api.schedulingguicontroller.SchedulingPanelController;
import at.silverstrike.pcc.api.schedulingguicontroller.SchedulingPanelControllerFactory;

public class DefaultSchedulingPanelControllerFactory implements
        SchedulingPanelControllerFactory {

    @Override
    public SchedulingPanelController create() {
        return new DefaultSchedulingPanelController();
    }

}

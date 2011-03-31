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

package at.silverstrike.pcc.impl.taskeditingpanelcontroller;

import at.silverstrike.pcc.api.taskeditingpanelcontroller.TaskEditingPanelController;
import at.silverstrike.pcc.api.taskeditingpanelcontroller.TaskEditingPanelControllerFactory;

/**
 * @author DP118M
 * 
 */
public final class DefaultTaskEditingPanelControllerFactory implements
        TaskEditingPanelControllerFactory {

    @Override
    public TaskEditingPanelController create() {
        return new DefaultTaskEditingPanelController();
    }

}

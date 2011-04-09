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

package at.silverstrike.pcc.impl.eventeditingpanelcontroller;

import at.silverstrike.pcc.api.eventeditingpanelcontroller.EventEditingPanelController;
import at.silverstrike.pcc.api.eventeditingpanelcontroller.EventEditingPanelControllerFactory;

/**
 * @author DP118M
 * 
 */
public final class DefaultEventEditingPanelControllerFactory implements
        EventEditingPanelControllerFactory {

    @Override
    public EventEditingPanelController create() {
        return new DefaultEventEditingPanelController();
    }
}

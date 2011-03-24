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

package at.silverstrike.pcc.impl.centraleditingpanelcontroller;

import at.silverstrike.pcc.api.centraleditingpanelcontroller.CentralEditingPanelController;
import at.silverstrike.pcc.api.centraleditingpanelcontroller.CentralEditingPanelControllerFactory;

/**
 * @author DP118M
 *
 */
public class DefaultCentralEditingPanelControllerFactory implements
        CentralEditingPanelControllerFactory {

    @Override
    public final CentralEditingPanelController create() {
        return new DefaultCentralEditingPanelController();
    }

}

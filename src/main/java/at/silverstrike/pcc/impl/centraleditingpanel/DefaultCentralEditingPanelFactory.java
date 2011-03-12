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

package at.silverstrike.pcc.impl.centraleditingpanel;

import at.silverstrike.pcc.api.centraleditingpanel.CentralEditingPanel;
import at.silverstrike.pcc.api.centraleditingpanel.CentralEditingPanelFactory;

/**
 * @author Dmitri Pisarenko
 * 
 */
public class DefaultCentralEditingPanelFactory implements
		CentralEditingPanelFactory {

    /**
     * @see at.silverstrike.pcc.api.conventions.Factory#create()
     */
    @Override
    public final CentralEditingPanel create() {
        return new DefaultCentralEditingPanel();
    }

}
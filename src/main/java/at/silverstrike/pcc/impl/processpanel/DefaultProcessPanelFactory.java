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

package at.silverstrike.pcc.impl.processpanel;

import at.silverstrike.pcc.api.processpanel.ProcessPanel;
import at.silverstrike.pcc.api.processpanel.ProcessPanelFactory;

public class DefaultProcessPanelFactory implements ProcessPanelFactory {

    @Override
    public final ProcessPanel create() {
        return new DefaultProcessPanel();
    }

}

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

package at.silverstrike.pcc.impl.workerpanel;

import at.silverstrike.pcc.api.workerpanel.WorkerPanel;
import at.silverstrike.pcc.api.workerpanel.WorkerPanelFactory;

public class DefaultWorkerPanelFactory implements WorkerPanelFactory {

    @Override
    public final WorkerPanel create() {
        return new DefaultWorkerPanel();
    }

}

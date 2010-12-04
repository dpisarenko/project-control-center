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

package at.silverstrike.pcc.impl.webguibus;

import at.silverstrike.pcc.api.webguibus.WebGuiBusMessageFactory;
import at.silverstrike.pcc.api.webguibus.WorkerAddedMessage;

public class DefaultWebGuiBusMessageFactory implements WebGuiBusMessageFactory {
    @Override
    public WorkerAddedMessage createWorkerAddedMessage() {
        return new DefaultWorkerAddedMessage();
    }
}
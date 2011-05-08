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

package at.silverstrike.pcc.impl.incorrectschedulingobjectsmarker;

import at.silverstrike.pcc.api.incorrectschedulingobjectsmarker.IncorrectSchedulingObjectsMarker;
import at.silverstrike.pcc.api.incorrectschedulingobjectsmarker.IncorrectSchedulingObjectsMarkerFactory;

/**
 * @author DP118M
 *
 */
public final class DefaultIncorrectSchedulingObjectsMarkerFactory implements
        IncorrectSchedulingObjectsMarkerFactory {
    
    @Override
    public IncorrectSchedulingObjectsMarker create() {
        return new DefaultIncorrectSchedulingObjectsMarker();
    }
}

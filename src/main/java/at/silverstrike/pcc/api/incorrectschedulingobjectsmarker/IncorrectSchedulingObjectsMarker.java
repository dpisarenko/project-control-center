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

package at.silverstrike.pcc.api.incorrectschedulingobjectsmarker;

import java.util.List;

import at.silverstrike.pcc.api.model.SchedulingObject;

import ru.altruix.commons.api.conventions.SingleActivityModule;
import ru.altruix.commons.api.di.ModuleWithInjectableDependencies;

/**
 * @author DP118M
 * 
 */
public interface IncorrectSchedulingObjectsMarker extends SingleActivityModule,
        ModuleWithInjectableDependencies {
    void setSchedulingObjects(final List<SchedulingObject> aObjects);
    
    boolean areErrorsFound();
}

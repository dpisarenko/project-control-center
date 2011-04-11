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

package at.silverstrike.pcc.api.dependencieseditingwindowcontroller;

import java.util.List;

import at.silverstrike.pcc.api.conventions.SingleActivityModule;
import at.silverstrike.pcc.api.model.SchedulingObject;

/**
 * @author DP118M
 *
 */
public interface DependenciesEditingWindowController extends SingleActivityModule {
    /**
     * Задаёт расчётный объект, зависимости которого отображаются в окне при старте.
     */
    void setSchedulingObject(final SchedulingObject aObject);

    List<SchedulingObject> getNewDependencies();
}

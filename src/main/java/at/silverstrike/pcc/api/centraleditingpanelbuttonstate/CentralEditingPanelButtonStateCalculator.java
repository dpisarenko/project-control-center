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

package at.silverstrike.pcc.api.centraleditingpanelbuttonstate;

import ru.altruix.commons.api.conventions.SingleActivityModule;
import ru.altruix.commons.api.di.ModuleWithInjectableDependencies;
import at.silverstrike.pcc.api.model.SchedulingObject;

/**
 * @author DP118M
 * 
 */
public interface CentralEditingPanelButtonStateCalculator extends
        SingleActivityModule, ModuleWithInjectableDependencies {
    void setCurrentSelection(final SchedulingObject aObject);
    Boolean isIncreasePriorityButtonEnabled();
    Boolean isDecreasePriorityButtonEnabled();
    boolean isNewTaskButtonEnabled();
    boolean isNewEventButtonEnabled();
    boolean isNewMilestoneButtonEnabled();
}

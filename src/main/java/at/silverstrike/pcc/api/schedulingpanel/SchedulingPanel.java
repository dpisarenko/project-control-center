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

package at.silverstrike.pcc.api.schedulingpanel;

import at.silverstrike.pcc.api.schedulingpanelcontroller.SchedulingState;
import ru.altruix.commons.api.di.ModuleWithInjectableDependencies;
import ru.altruix.commons.api.vaadin.AbstractedPanel;

public interface SchedulingPanel extends ModuleWithInjectableDependencies,
        AbstractedPanel {

    void displayState(final SchedulingState aState);

}

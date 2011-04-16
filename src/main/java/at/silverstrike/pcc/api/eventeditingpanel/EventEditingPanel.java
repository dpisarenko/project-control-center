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

package at.silverstrike.pcc.api.eventeditingpanel;

import ru.altruix.commons.api.di.ModuleWithInjectableDependencies;
import ru.altruix.commons.api.vaadin.AbstractedPanel;
import at.silverstrike.pcc.api.model.Event;

/**
 * @author DP118M
 * 
 */
public interface EventEditingPanel extends ModuleWithInjectableDependencies,
        AbstractedPanel {

    void setEvent(final Event aEvent);

}

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

package at.silverstrike.pcc.api.dependencieseditingpanel;

import at.silverstrike.pcc.api.model.SchedulingObject;
import ru.altruix.commons.api.di.ModuleWithInjectableDependencies;
import ru.altruix.commons.api.gui.InitializableGuiComponent;
import ru.altruix.commons.api.vaadin.AbstractedPanel;

public interface DependenciesEditingPanel extends ModuleWithInjectableDependencies,
	InitializableGuiComponent, AbstractedPanel {
    void setData(final SchedulingObject aSchedulingObject);
}

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

package at.silverstrike.pcc.api.dependencieseditingdialog;

import java.util.List;
import java.util.Set;

import ru.altruix.commons.api.vaadin.AbstractedWindow;

import com.vaadin.ui.Window;

import at.silverstrike.pcc.api.conventions.InitializableGuiComponent;
import at.silverstrike.pcc.api.conventions.ModalDialogResult;
import at.silverstrike.pcc.api.conventions.ModuleWithInjectableDependencies;
import at.silverstrike.pcc.api.model.SchedulingObject;

/**
 * @author DP118M
 * 
 */
public interface DependenciesEditingDialog extends InitializableGuiComponent,
        AbstractedWindow, ModuleWithInjectableDependencies {

    void setExistingDependencies(final Set<SchedulingObject> predecessors);

    void setAvailableDependencies(
            final List<SchedulingObject> availableDependencies);

    void setParentWindow(final Window aWindow);

    ModalDialogResult getDialogResult();

    Set<SchedulingObject> getSelectedDependencies();
}

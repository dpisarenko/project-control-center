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

package at.silverstrike.pcc.api.taskeditingpanelcontroller;

import at.silverstrike.pcc.api.conventions.ModuleWithInjectableDependencies;
import at.silverstrike.pcc.api.model.Task;

public interface TaskEditingPanelController extends
        ModuleWithInjectableDependencies {
    void dependEditButtonClicked();

    void saveButtonClicked();

    void saveTask(final Task aTask);

    void markTaskAsCompleted(final Task aTask);

    void deleteTask(final Task aTask);

    void newTaskCreated();
}
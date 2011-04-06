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

import com.vaadin.ui.Panel;

import at.silverstrike.pcc.api.conventions.GuiController;
import at.silverstrike.pcc.api.conventions.ModuleWithInjectableDependencies;
import at.silverstrike.pcc.api.model.Task;

public interface TaskEditingPanelController extends
        ModuleWithInjectableDependencies, GuiController<Panel> {
    void dependEditButtonClicked();

    void saveButtonClicked();

    void saveTask(final Task aTask);

    void markTaskAsCompleted(final Task aTask);

    void deleteTask(final Task aTask);

    void newTaskCreated();
    
    /**
     * Этот метод вызывается центральной панелью и приводит к тому,
     * что все поля панели дела заполняются данными переданного объекта.
     */
    void setData(final Task aTask);
}
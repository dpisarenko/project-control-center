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

import ru.altruix.commons.api.di.ModuleWithInjectableDependencies;
import ru.altruix.commons.api.gui.GuiController;

import com.vaadin.ui.Panel;

import at.silverstrike.pcc.api.model.Task;

public interface TaskEditingPanelController extends
        ModuleWithInjectableDependencies, GuiController<Panel> {
    void saveTask(final Task aTask);

    void markTaskAsCompleted(final Task aTask);

    void deleteTask(final Task aTask);
    
    /**
     * Этот метод вызывается центральной панелью и приводит к тому,
     * что все поля панели дела заполняются данными переданного объекта.
     */
    void setData(final Task aTask);

    /**
     * Этот метод удаляет значения полей панели при удалении Task из базы.
     */
	void clearPanel();
}
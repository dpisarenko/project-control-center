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

package at.silverstrike.pcc.impl.taskeditingpanelcontroller;

import at.silverstrike.pcc.api.dependencieseditingwindow.DependenciesEditingPanel;
import at.silverstrike.pcc.api.dependencieseditingwindow.DependenciesEditingPanelFactory;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.taskeditingpanel.TaskEditingPanel;
import at.silverstrike.pcc.api.taskeditingpanel.TaskEditingPanelFactory;
import at.silverstrike.pcc.api.taskeditingpanelcontroller.TaskEditingPanelController;

import com.google.inject.Injector;
import com.vaadin.ui.Panel;

class DefaultTaskEditingPanelController implements
        TaskEditingPanelController {
    private Injector injector = null;
    private TaskEditingPanel panel;

    @Override
    public void dependEditButtonClicked() {
        final DependenciesEditingPanelFactory factory =
                this.injector
                        .getInstance(DependenciesEditingPanelFactory.class);
        final DependenciesEditingPanel panel = factory.create();
        panel.setInjector(injector);
        panel.initGui();
    }

    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;
    }

    @Override
    public void saveButtonClicked() {
        final Persistence persistence = this.injector
                .getInstance(Persistence.class);

        persistence.createTask("TaskName");

    }

    @Override
    public void saveTask(Task aTask) {
        // TODO Auto-generated method stub

    }

    @Override
    public void markTaskAsCompleted(Task aTask) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteTask(Task aTask) {
        // TODO Auto-generated method stub

    }

    @Override
    public void newTaskCreated() {
        // TODO Auto-generated method stub

    }

    @Override
    public void setData(final Task aTask) {
        this.panel.setTaskName(aTask.getName());
    }

    @Override
    public Panel initGui() {
        final TaskEditingPanelFactory factory =
                this.injector.getInstance(TaskEditingPanelFactory.class);
        this.panel = factory.create();

        panel.setInjector(this.injector);
        panel.initGui();
        
        return panel.toPanel();
    }

    @Override
    public void taskCreated(final Task aNewTask) {
        // TODO Auto-generated method stub

    }

    @Override
    public void taskCreationFailure() {
        // TODO Auto-generated method stub

    }

}

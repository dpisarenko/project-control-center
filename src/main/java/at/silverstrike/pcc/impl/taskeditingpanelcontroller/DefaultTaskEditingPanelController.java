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
import at.silverstrike.pcc.api.taskeditingpanelcontroller.TaskEditingPanelController;


import com.google.inject.Injector;

class DefaultTaskEditingPanelController implements
        TaskEditingPanelController {
    private Injector injector = null;

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


}

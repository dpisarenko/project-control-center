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

package at.silverstrike.pcc.api.projecttreemodel;

import ru.altruix.commons.api.conventions.InitializableComponent;
import ru.altruix.commons.api.di.ModuleWithInjectableDependencies;
import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.model.Task;

import com.vaadin.data.Container;

/**
 * @author DP118M
 * 
 */
public interface ProjectTreeContainer extends Container.Hierarchical,
        ModuleWithInjectableDependencies, InitializableComponent {
    Integer TREE_ROOT_ID = 1;
    Object PROJECT_PROPERTY_NAME = "name";
    
    void setRootLabel(final String aLabel);
    void updateData();
    SchedulingObject getSchedulingObject(final Integer aTreeItemId);
    void updateNodeLettering(final Task aTask);
	void updateNodeLettering(final SchedulingObject aShedulingObject);
}

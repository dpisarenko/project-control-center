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

import at.silverstrike.pcc.api.conventions.InitializableComponent;
import at.silverstrike.pcc.api.conventions.ModuleWithInjectableDependencies;
import at.silverstrike.pcc.api.model.SchedulingObject;

import com.vaadin.data.Container;

/**
 * @author DP118M
 * 
 */
public interface ProjectTreeContainer extends Container.Hierarchical,
        ModuleWithInjectableDependencies, InitializableComponent {
    public static final Object PROJECT_PROPERTY_NAME = "name";
    Integer TREE_ROOT_ID = 0;
    Integer VISIBLE_TREE_ROOT_ID = 1;
    void setRootLabel(final String aLabel);
    void updateData();
    SchedulingObject getSchedulingObject(final Integer aTreeItemId);
}

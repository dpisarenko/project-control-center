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

package at.silverstrike.pcc.impl.projecttreemodel;

import java.util.List;

import com.google.inject.Injector;
import com.vaadin.data.Item;
import com.vaadin.data.util.HierarchicalContainer;

import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.projecttreemodel.ProjectTreeContainer;

/**
 * @author DP118M
 * 
 */
@SuppressWarnings("unchecked")
class DefaultProjectTreeContainer extends HierarchicalContainer implements
        ProjectTreeContainer {
    private static final long serialVersionUID = 1L;
    private static final Object PROJECT_PROPERTY_ID = "id";
    public static final Object PROJECT_PROPERTY_NAME = "name";
    private Persistence persistence;
    private Item root;
    private String rootLabel;

    @Override
    public void setInjector(final Injector aInjector) {
        if (aInjector != null) {
            this.persistence = aInjector.getInstance(Persistence.class);
        }

    }

    public DefaultProjectTreeContainer() {
    }

    @Override
    public void updateData() {
        final List<SchedulingObject> topLevelProcesses =
                persistence.getSubProcessesWithChildren(null);

        this.removeAllContainerFilters();

        addNodes(this, topLevelProcesses, null, persistence, 1);
    }

    private int addNodes(final HierarchicalContainer aContainer,
            final List<SchedulingObject> aProcesses, final Integer aParentId,
            final Persistence aPersistence, final int aTreeItemId) {
        int treeItemId = aTreeItemId;

        if (aProcesses == null) {
            return treeItemId;
        }

        for (final SchedulingObject process : aProcesses) {
            final int processItemId = treeItemId++;

            final Item processItem = aContainer.addItem(processItemId);

            processItem.getItemProperty(PROJECT_PROPERTY_ID).setValue(
                    process.getId());
            processItem.getItemProperty(PROJECT_PROPERTY_NAME).setValue(
                    process.getName());

            aContainer.setChildrenAllowed(processItemId, true);

            if (aParentId != null) {
                aContainer.setParent(processItemId, aParentId);
            } else {
                aContainer.setParent(processItemId, TREE_ROOT_ID);
            }

            final List<SchedulingObject> subProcessesWithChildren =
                    aPersistence.getSubProcessesWithChildren(process.getId());

            treeItemId =
                    addNodes(aContainer, subProcessesWithChildren,
                            processItemId, aPersistence, treeItemId);
        }

        return treeItemId;
    }

    @Override
    public void init() {
        this.addContainerProperty(PROJECT_PROPERTY_ID, Long.class, null);
        this.addContainerProperty(PROJECT_PROPERTY_NAME, String.class, null);

        this.root = this.addItem(TREE_ROOT_ID);
        this.root.getItemProperty(PROJECT_PROPERTY_ID).setValue(null);
        this.root.getItemProperty(PROJECT_PROPERTY_NAME).setValue(
                this.rootLabel);
    }

    @Override
    public void setRootLabel(final String aLabel) {
        this.rootLabel = aLabel;
    }
}
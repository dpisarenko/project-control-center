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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Injector;
import com.vaadin.data.Item;
import com.vaadin.data.util.HierarchicalContainer;

import eu.livotov.tpt.i18n.TM;

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
    private Persistence persistence;
    private Item root;
    private Item visibleRoot;
    private String rootLabel;
    private Map<Integer, SchedulingObject> schedulingObjectsByTreeItemIds;
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultProjectTreeContainer.class);

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

//        this.removeAllItems();
        if (this.schedulingObjectsByTreeItemIds != null)
        {
            for (final Integer taskNodeId : this.schedulingObjectsByTreeItemIds.keySet())
            {
                this.removeItem(taskNodeId);
            }
            
        }
        this.schedulingObjectsByTreeItemIds =
                new HashMap<Integer, SchedulingObject>();
        addNodes(topLevelProcesses, null, persistence, (VISIBLE_TREE_ROOT_ID + 1));
    }

    private int addNodes(final List<SchedulingObject> aProcesses,
            final Integer aParentId,
            final Persistence aPersistence, final int aTreeItemId) {
        int treeItemId = aTreeItemId;

        if (aProcesses == null) {
            return treeItemId;
        }

        for (final SchedulingObject process : aProcesses) {
            final int processItemId = treeItemId++;

            final Item processItem = this.addItem(processItemId);

            this.schedulingObjectsByTreeItemIds.put(processItemId, process);

            LOGGER.debug("process.getId(): {}", process.getId());

            LOGGER.debug("processItem: {}", processItem);
            LOGGER.debug(
                    "processItem.getItemProperty(PROJECT_PROPERTY_NAME): ",
                    processItem.getItemProperty(PROJECT_PROPERTY_NAME));
            LOGGER.debug(
                    "processItem.getItemProperty(PROJECT_PROPERTY_ID): {}",
                    processItem.getItemProperty(PROJECT_PROPERTY_ID).toString());

            processItem.getItemProperty(PROJECT_PROPERTY_ID).setValue(
                    process.getId());

            processItem.getItemProperty(PROJECT_PROPERTY_NAME).setValue(
                    process.getName());

            this.setChildrenAllowed(processItemId, true);

            if (aParentId != null) {
                this.setParent(processItemId, aParentId);
            } else {
                this.setParent(processItemId, VISIBLE_TREE_ROOT_ID);
            }

            final List<SchedulingObject> subProcessesWithChildren =
                    aPersistence.getSubProcessesWithChildren(process.getId());

            treeItemId =
                    addNodes(subProcessesWithChildren,
                            processItemId, aPersistence, treeItemId);
        }

        return treeItemId;
    }

    @Override
    public void init() {
        this.addContainerProperty(PROJECT_PROPERTY_ID, Long.class, null);
        this.addContainerProperty(PROJECT_PROPERTY_NAME, String.class, null);

        this.root = this.addItem(TREE_ROOT_ID);
        this.root.getItemProperty(PROJECT_PROPERTY_NAME).setValue(
                this.rootLabel);
        this.setChildrenAllowed(TREE_ROOT_ID, true);
        
        this.visibleRoot = this.addItem(VISIBLE_TREE_ROOT_ID);
        this.visibleRoot.getItemProperty(PROJECT_PROPERTY_NAME).setValue(
                TM.get("projecttreemodel.1-visibleTreeRootCaption"));
        this.setChildrenAllowed(VISIBLE_TREE_ROOT_ID, true);
        
        setParent(VISIBLE_TREE_ROOT_ID, TREE_ROOT_ID);
        
        
        
    }

    @Override
    public void setRootLabel(final String aLabel) {
        this.rootLabel = aLabel;
    }

    @Override
    public SchedulingObject getSchedulingObject(final Integer aTreeItemId) {
        return this.schedulingObjectsByTreeItemIds.get(aTreeItemId);
    }
}

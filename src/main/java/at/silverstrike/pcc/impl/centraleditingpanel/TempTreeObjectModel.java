/**
 * This file is part of Project Control Center (PCC).
 * 
 * PCC (Project Control Center) project is intellectual property of Dmitri
 * Anatol'evich Pisarenko.
 * 
 * Copyright 2010 Dmitri Anatol'evich Pisarenko All rights reserved
 * 
 **/

package at.silverstrike.pcc.impl.centraleditingpanel;

import com.vaadin.data.util.HierarchicalContainer;

final class TempTreeObjectModel {
    private static final String[] FILTER_IMPACT_TYPE = { "Passive filters",
            "Active filters" };
    private static final String[] FILTER_TYPES =
            { "Sums", "Parities", "Numbers" };

    private TempTreeObjectModel() {
    }

    public static HierarchicalContainer getFilterHierarchicalContainer() {
        final HierarchicalContainer getFilterHierarchicalContainer =
                new HierarchicalContainer();
        getFilterHierarchicalContainer.addContainerProperty("FILTER_NAME",
                String.class, null);

        Integer itemId = 0;
        for (int i = 0; i < FILTER_IMPACT_TYPE.length; i++) {
            // Add new item
            getFilterHierarchicalContainer.addItem(itemId);
            // Add name property for item
            // item.getItemProperty(FILTER_NAME).setValue(filterImpactTypes[ i
            // ]);
            // Allow children
            getFilterHierarchicalContainer.setChildrenAllowed(itemId, true);
            itemId++;
        }

        itemId = 100;
        for (int i = 0; i < FILTER_TYPES.length; i++) {
            // Add new item
            getFilterHierarchicalContainer.addItem(itemId);
            // Add name property for item
            // item.getItemProperty(FILTER_NAME).setValue(filterTypes[ i ]);
            // Set parent
            getFilterHierarchicalContainer.setParent(itemId, 0);
            // Allow children
            getFilterHierarchicalContainer.setChildrenAllowed(itemId, false);
            itemId++;
        }

        return getFilterHierarchicalContainer;
    }
}
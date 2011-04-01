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

import com.google.inject.Injector;
import com.vaadin.data.util.HierarchicalContainer;

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
    private Persistence persistence;

    @Override
    public void setInjector(final Injector aInjector) {
        if (aInjector != null) {
            this.persistence = aInjector.getInstance(Persistence.class);
        }

    }

    @Override
    public void updateData() {
        // TODO Auto-generated method stub

    }

}

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

package at.silverstrike.pcc.impl.centraleditingpanel;

import at.silverstrike.pcc.api.model.SchedulingObject;

import com.vaadin.ui.Tree;

/**
 * @author DP118M
 * 
 */
class ProjectTreeItemStyleGenerator implements Tree.ItemStyleGenerator {
    private static final long serialVersionUID = 1L;

    @Override
    public String getStyle(final Object aItemId) {
        final SchedulingObject schedulingObject = (SchedulingObject) aItemId;

        if (schedulingObject.getValidationError() != null) {
            return "schedulingObjectWithErrors";
        } else {
            return "schedulingObjectWithoutErrors";
        }
    }
}

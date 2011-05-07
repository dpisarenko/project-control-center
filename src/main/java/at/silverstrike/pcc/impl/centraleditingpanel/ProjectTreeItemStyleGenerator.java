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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.ui.Tree;

/**
 * @author DP118M
 * 
 */
class ProjectTreeItemStyleGenerator implements Tree.ItemStyleGenerator {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ProjectTreeItemStyleGenerator.class);

    @Override
    public String getStyle(final Object aItemId) {
        LOGGER.debug("ProjectTreeItemStyleGenerator.getStyle: {}", aItemId);
        return "schedulingObjectWithErrors";
        
//        final SchedulingObject schedulingObject = (SchedulingObject) aItemId;
//
//        if (schedulingObject.getValidationError() != null) {
//            return "schedulingObjectWithErrors";
//        } else {
//            return "schedulingObjectWithoutErrors";
//        }
    }
}

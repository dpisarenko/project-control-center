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

import com.vaadin.data.Property.ValueChangeEvent;

/**
 * @author DP118M
 *
 */
public interface ProjectTreeSelectionListenerParent {
    void treeSelectionChanged(final ValueChangeEvent aEvent);
}

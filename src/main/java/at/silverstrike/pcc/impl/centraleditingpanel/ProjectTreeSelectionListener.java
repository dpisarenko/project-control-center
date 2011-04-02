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

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;

/**
 * @author DP118M
 * 
 */
final class ProjectTreeSelectionListener implements
        Property.ValueChangeListener {
    private static final long serialVersionUID = 1L;
    private ProjectTreeSelectionListenerParent parent;
    
    public ProjectTreeSelectionListener(final ProjectTreeSelectionListenerParent aParent)
    {
        this.parent = aParent;
    }
    
    @Override
    public void valueChange(final ValueChangeEvent aEvent) {
        // Здесь на первых порах будем а) менять текущий идентификатор
        // выбранного объекта и б) показывать сообщение об этом
        this.parent.treeSelectionChanged(aEvent);
    }

}

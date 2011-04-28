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

package at.silverstrike.pcc.impl.dependencieseditingdialog;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;

/**
 * @author DP118M
 *
 */
class TableListener implements ValueChangeListener {
    private static final long serialVersionUID = 1L;
    private DefaultDependenciesEditingDialog dialog;
    
    public TableListener(final DefaultDependenciesEditingDialog aDialog){
        this.dialog = aDialog;
    }
    
    @Override
    public void valueChange(final ValueChangeEvent aEvent) {
        this.dialog.tableSelectionChanged();
    }

}

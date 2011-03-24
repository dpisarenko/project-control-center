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

package at.silverstrike.pcc.impl.taskeditingpanel;

import at.silverstrike.pcc.api.taskeditingpanel.TaskEditingPanel;
import at.silverstrike.pcc.api.taskeditingpanel.TaskEditingPanelFactory;

/**
 * @author Dmitri Pisarenko
 * 
 */
public class DefaultTaskEditingPanelFactory implements
        TaskEditingPanelFactory {
    /**
     * @see at.silverstrike.pcc.api.conventions.Factory#create()
     */
    @Override
    public final TaskEditingPanel create() {
        return new DefaultTaskEditingPanel();
    }

}
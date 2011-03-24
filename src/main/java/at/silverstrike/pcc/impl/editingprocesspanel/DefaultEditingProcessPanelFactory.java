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

package at.silverstrike.pcc.impl.editingprocesspanel;

import at.silverstrike.pcc.api.editingprocesspanel.EditingProcessPanel;
import at.silverstrike.pcc.api.editingprocesspanel.EditingProcessPanelFactory;

/**
 * @author Dmitri Pisarenko
 * 
 */
public class DefaultEditingProcessPanelFactory implements
        EditingProcessPanelFactory {

    /**
     * @see at.silverstrike.pcc.api.editingprocesspanel.EditingProcessPanelFactory#create()
     */
    @Override
    public final EditingProcessPanel create() {
        return new DefaultEditingProcessPanel();
    }

}

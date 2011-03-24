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

package at.silverstrike.pcc.api.processpanel;

import java.util.List;

import at.silverstrike.pcc.api.conventions.AbstractedPanel;
import at.silverstrike.pcc.api.conventions.ModuleWithInjectableDependencies;
import at.silverstrike.pcc.api.editingprocesspanel.EditingProcessPanel;
import at.silverstrike.pcc.api.model.ControlProcess;

public interface ProcessPanel extends ModuleWithInjectableDependencies,
        AbstractedPanel {
    void setProcessesToShow(final List<ControlProcess> aProcesses);

    void setEditingProcessPanel(final EditingProcessPanel aPanel);

    /**
     * Sets the ID of the process, to which a newly created tasks (created in
     * this panel) will be assigned as sub-tasks (children).
     */
    void setParentProcessId(final Long aId);

    void setProcessPanelListener(final ProcessPanelListener aListener);
}

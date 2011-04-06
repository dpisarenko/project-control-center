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

package at.silverstrike.pcc.api.taskeditingpanel;

import com.vaadin.ui.TextField;

import at.silverstrike.pcc.api.conventions.AbstractedPanel;
import at.silverstrike.pcc.api.conventions.ModuleWithInjectableDependencies;

/**
 * @author DP118M
 * 
 */
public interface TaskEditingPanel extends ModuleWithInjectableDependencies,
        AbstractedPanel {

	void setTaskName(String aTaskNameTextField);
}

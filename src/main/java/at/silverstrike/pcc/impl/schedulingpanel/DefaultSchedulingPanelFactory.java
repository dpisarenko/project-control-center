/**
 * This file is part of Project Control Center (PCC).
 * 
 * PCC (Project Control Center) project is intellectual property of 
 * Dmitri Anatol'evich Pisarenko.
 * 
 * Copyright 2010 Dmitri Anatol'evich Pisarenko
 * All rights reserved
 *
 **/

package at.silverstrike.pcc.impl.schedulingpanel;

import at.silverstrike.pcc.api.schedulingpanel.SchedulingPanel;
import at.silverstrike.pcc.api.schedulingpanel.SchedulingPanelFactory;

public class DefaultSchedulingPanelFactory implements SchedulingPanelFactory {

	@Override
	public SchedulingPanel create() {
		return new DefaultSchedulingPanel();
	}

}

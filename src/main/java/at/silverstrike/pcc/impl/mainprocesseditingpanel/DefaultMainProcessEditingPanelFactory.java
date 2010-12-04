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

package at.silverstrike.pcc.impl.mainprocesseditingpanel;

import at.silverstrike.pcc.api.mainprocesseditingpanel.MainProcessEditingPanel;
import at.silverstrike.pcc.api.mainprocesseditingpanel.MainProcessEditingPanelFactory;

/**
 * @author Dmitri Pisarenko
 *
 */
public class DefaultMainProcessEditingPanelFactory implements
		MainProcessEditingPanelFactory {

	/**
	 * @see at.silverstrike.pcc.api.conventions.Factory#create()
	 */
	@Override
	public MainProcessEditingPanel create() {
		return new DefaultMainProcessEditingPanel();
	}

}

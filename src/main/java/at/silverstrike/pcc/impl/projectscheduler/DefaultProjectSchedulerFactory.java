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

package at.silverstrike.pcc.impl.projectscheduler;

import at.silverstrike.pcc.api.projectscheduler.ProjectScheduler;
import at.silverstrike.pcc.api.projectscheduler.ProjectSchedulerFactory;

/**
 * @author Dmitri Pisarenko
 *
 */
public class DefaultProjectSchedulerFactory implements ProjectSchedulerFactory {

	@Override
	public final ProjectScheduler create() {
		return new DefaultProjectScheduler();
	}

}

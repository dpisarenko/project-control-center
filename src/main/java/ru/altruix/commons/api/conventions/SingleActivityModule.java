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

package ru.altruix.commons.api.conventions;

import ru.altruix.commons.api.di.PccException;

/**
 * Instances of this class represent modules, which have exactly one specific
 * purpose and are used in the following way:
 * 
 * 1) First, input data are provided.
 * 2) Then, the central work process of this module is executed (by calling method run).
 * 3) Results are given to the calling routine.
 * 
 * @author Dmitri Pisarenko
 *
 */
public interface SingleActivityModule {
	void run() throws PccException;
}

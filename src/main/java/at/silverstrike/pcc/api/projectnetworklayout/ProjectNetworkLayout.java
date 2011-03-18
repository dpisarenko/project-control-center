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

package at.silverstrike.pcc.api.projectnetworklayout;

import at.silverstrike.pcc.api.projectgraph.ProjectNetworkVertex;
import edu.uci.ics.jung.algorithms.layout.Layout;

/**
 * @author DP118M
 * 
 */
public interface ProjectNetworkLayout extends
        Layout<ProjectNetworkVertex, String> {

}
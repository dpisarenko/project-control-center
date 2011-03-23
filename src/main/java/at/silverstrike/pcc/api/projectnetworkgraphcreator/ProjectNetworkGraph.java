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

package at.silverstrike.pcc.api.projectnetworkgraphcreator;

import edu.uci.ics.jung.graph.Graph;

/**
 * @author DP118M
 *
 */
public interface ProjectNetworkGraph extends Graph<String, String> {
    String getInitialEventVertex();
    String getFinalEventVertex();
}

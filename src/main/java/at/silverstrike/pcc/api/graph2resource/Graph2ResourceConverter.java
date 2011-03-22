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

package at.silverstrike.pcc.api.graph2resource;

import edu.uci.ics.jung.graph.Graph;
import at.silverstrike.pcc.api.conventions.SingleActivityModule;

/**
 * @author DP118M
 *
 */
public interface Graph2ResourceConverter extends SingleActivityModule {
    void setGraph(final Graph<String, String> aGraph);
    JungResource getResource();
}

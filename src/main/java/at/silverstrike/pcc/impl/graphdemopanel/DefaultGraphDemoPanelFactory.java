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

package at.silverstrike.pcc.impl.graphdemopanel;

import at.silverstrike.pcc.api.graphdemopanel.GraphDemoPanel;
import at.silverstrike.pcc.api.graphdemopanel.GraphDemoPanelFactory;

/**
 * @author DP118M
 * 
 */
public class DefaultGraphDemoPanelFactory implements GraphDemoPanelFactory {

    @Override
    public final GraphDemoPanel create() {
        return new DefaultGraphDemoPanel();
    }

}

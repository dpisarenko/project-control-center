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

import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Injector;
import com.vaadin.ui.Panel;
import at.silverstrike.pcc.api.graphdemopanel.GraphDemoPanel;
import at.silverstrike.pcc.api.projectnetworkgraphcreator.SchedulingObjectDependencyTuple;
import at.silverstrike.pcc.api.projectnetworkgraphpanel.ProjectNetworkGraphPanel;
import at.silverstrike.pcc.api.projectnetworkgraphpanel.ProjectNetworkGraphPanelFactory;

class DefaultGraphDemoPanel extends Panel implements GraphDemoPanel {
    private static final int DEFAULT_HEIGHT_PIXELS = 350;
    private static final int DEFAULT_WIDTH_PIXELS = 600;
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultGraphDemoPanel.class);
    private static final String P1 = "P1";
    private static final String P2 = "P2";
    private static final String P3 = "P3";
    private Injector injector;
    
    @Override
    public Panel toPanel() {
        return this;
    }

    @Override
    public void initGui() {
        final ProjectNetworkGraphPanelFactory factory = this.injector.getInstance(ProjectNetworkGraphPanelFactory.class);
        final ProjectNetworkGraphPanel graphPanel = factory.create();
        
        graphPanel.setInjector(injector);
        graphPanel.initGui();
  
        final List<SchedulingObjectDependencyTuple> tuples =
            getDependencyTuples();

        graphPanel.updatePanel(tuples);
        
        
        final com.vaadin.ui.Layout graphPanelLayout = graphPanel.toLayout();
        
        graphPanelLayout.setWidth(DEFAULT_WIDTH_PIXELS, UNITS_PIXELS);
        graphPanelLayout.setHeight(DEFAULT_HEIGHT_PIXELS, UNITS_PIXELS);

        
        this.addComponent(graphPanelLayout);
    }
    private List<SchedulingObjectDependencyTuple> getDependencyTuples() {
        final List<SchedulingObjectDependencyTuple> tuples =
                new LinkedList<SchedulingObjectDependencyTuple>();
//
//        final SchedulingObjectDependencyTuple tuple1 =
//                new MockSchedulingObjectDependencyTuple();
//        tuple1.setLabel(P1);
//
//        final SchedulingObjectDependencyTuple tuple2 =
//                new MockSchedulingObjectDependencyTuple();
//        tuple2.setLabel(P2);
//        final List<String> p2Dependencies = new LinkedList<String>();
//        p2Dependencies.add(P1);
//        tuple2.setDependencies(p2Dependencies);
//
//        final SchedulingObjectDependencyTuple tuple3 =
//                new MockSchedulingObjectDependencyTuple();
//        tuple3.setLabel(P3);
//
//        tuples.add(tuple1);
//        tuples.add(tuple2);
//        tuples.add(tuple3);
//
//        LOGGER.debug("P1 dependencies: " + tuple1.getDependencies());
//        LOGGER.debug("P2 dependencies: " + tuple2.getDependencies());
//        LOGGER.debug("P3 dependencies: " + tuple3.getDependencies());
//        
        return tuples;
    }
    
    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;
    }
}

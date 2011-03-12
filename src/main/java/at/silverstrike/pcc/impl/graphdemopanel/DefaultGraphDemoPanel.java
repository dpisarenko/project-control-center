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

package at.silverstrike.pcc.impl.graphdemopanel;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import at.silverstrike.pcc.api.graphdemopanel.GraphDemoPanel;

class DefaultGraphDemoPanel extends Panel implements GraphDemoPanel {
    private static final long serialVersionUID = 1L;

    @Override
    public Panel toPanel() {
        return this;
    }

    @Override
    public void initGui() {
        final VerticalLayout layout = new VerticalLayout();
        final Embedded image =
                new Embedded(null, new ThemeResource("../pcc/test/graph.gif"));

        layout.addComponent(image);
        layout.setSizeFull();

        this.addComponent(layout);
    }

    private void createSampleGraph() {
        
    }

}

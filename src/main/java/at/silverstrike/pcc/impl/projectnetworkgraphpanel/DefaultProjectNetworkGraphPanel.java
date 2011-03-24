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

package at.silverstrike.pcc.impl.projectnetworkgraphpanel;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Injector;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

import eu.livotov.tpt.TPTApplication;

import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.graph2resource.Graph2ResourceConverter;
import at.silverstrike.pcc.api.graph2resource.JungResource;
import at.silverstrike.pcc.api.projectnetworkgraphcreator.ProjectNetworkGraph;
import at.silverstrike.pcc.api.projectnetworkgraphcreator.ProjectNetworkGraphCreator;
import at.silverstrike.pcc.api.projectnetworkgraphcreator.SchedulingObjectDependencyTuple;
import at.silverstrike.pcc.api.projectnetworkgraphpanel.ProjectNetworkGraphPanel;

/**
 * @author DP118M
 * 
 */
class DefaultProjectNetworkGraphPanel implements
        ProjectNetworkGraphPanel {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultProjectNetworkGraphPanel.class);
    private VerticalLayout layout;
    private Embedded imageComponent;
    private Injector injector;
    private ProjectNetworkGraphCreator projectNetworkGraphCreator;
    private Graph2ResourceConverter graph2ResourceConverter;
    private JungResource curResource = null;

    @Override
    public void initGui() {
        this.layout = new VerticalLayout();
        this.layout.setSizeFull();

        imageComponent = new Embedded();

        imageComponent.setSizeFull();
        imageComponent.setMimeType(JungResource.MIME_TYPE_SVG);

        this.layout.addComponent(imageComponent);
    }

    @Override
    public void
            updatePanel(
                    final List<SchedulingObjectDependencyTuple> aDependencies) {
        this.projectNetworkGraphCreator.setDependencies(aDependencies);
        try {
            this.projectNetworkGraphCreator.run();
        } catch (final PccException exception) {
            LOGGER.error(ErrorCodes.M_001_UPDATE_PANEL, exception);
            return;
        }

        final ProjectNetworkGraph graph =
                this.projectNetworkGraphCreator.getGraph();

        this.graph2ResourceConverter.setGraph(graph);
        try {
            this.graph2ResourceConverter.run();
        } catch (final PccException exception) {
            LOGGER.error(ErrorCodes.M_001_UPDATE_PANEL, exception);
            return;
        }
        
        final JungResource newResource = this.graph2ResourceConverter.getResource();

        if (this.curResource != null) {
            TPTApplication.getCurrentApplication().removeResource(
                    this.curResource);
        }
        TPTApplication.getCurrentApplication().addResource(newResource);
        this.curResource = newResource;

        this.imageComponent.setSource(this.curResource);
    }

    @Override
    public Layout toLayout() {
        return layout;
    }

    @Override
    public void setInjector(final Injector aInjector) {
        if (aInjector != null) {
            this.injector = aInjector;
            this.projectNetworkGraphCreator =
                    this.injector.getInstance(ProjectNetworkGraphCreator.class);
            this.graph2ResourceConverter =
                    this.injector.getInstance(Graph2ResourceConverter.class);
        }
    }
}

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

package at.silverstrike.pcc.impl.centraleditingpanel;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Injector;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.terminal.Sizeable;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import at.silverstrike.pcc.api.centraleditingpanel.CentralEditingPanel;
import at.silverstrike.pcc.api.centraleditingpanelcontroller.CentralEditingPanelController;
import at.silverstrike.pcc.api.centraleditingpanelcontroller.CentralEditingPanelControllerFactory;
import at.silverstrike.pcc.api.conventions.MessageCodePrefixRegistry;
import at.silverstrike.pcc.api.debugids.DebugIdRegistry;
import at.silverstrike.pcc.api.processpanel.ProcessPanelListener;
import at.silverstrike.pcc.api.projectnetworkgraphcreator.SchedulingObjectDependencyTuple;
import at.silverstrike.pcc.api.projectnetworkgraphpanel.ProjectNetworkGraphPanel;
import at.silverstrike.pcc.api.projectnetworkgraphpanel.ProjectNetworkGraphPanelFactory;
import at.silverstrike.pcc.impl.centraleditingpanelcontroller.DefaultCentralEditingPanelControllerFactory;
import eu.livotov.tpt.i18n.TM;

class DefaultCentralEditingPanel extends Panel implements
        CentralEditingPanel, ProcessPanelListener, ClickListener {
    private static final String NEW_MILESTONE_BUTTON = "025.002";
    private static final int PADDING = 5;
    private static final int ONE_SIXTH_OF_SCREEN_WIDTH = 6;
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultCentralEditingPanel.class);
    private static final int HEIGHT_SCREEN = 600;
    private static final int WIDTH_SCREEN = 1200;
    private static final long serialVersionUID = 1L;
    private static final String NOTIFICATION = "Smth happend";
    private static final int WIDTH_OF_NEW_BUTTONS =
            WIDTH_SCREEN / ONE_SIXTH_OF_SCREEN_WIDTH - PADDING;

    public static final Object PROJECT_PROPERTY_NAME = "name";

    private Panel verticalPanelRight;

    private transient Injector injector;
    private transient CentralEditingPanelController controller;

    private DebugIdRegistry debugIdRegistry;

    private static final String P1 = "P1";
    private static final String P2 = "P2";
    private static final String P3 = "P3";
    private static final int DEFAULT_HEIGHT_PIXELS = 350;
    private static final int DEFAULT_WIDTH_PIXELS = 600;

    
    @Override
    public void setInjector(final Injector aInjector) {
        if (aInjector != null) {
            injector = aInjector;

            final CentralEditingPanelControllerFactory factory =
                    this.injector
                            .getInstance(CentralEditingPanelControllerFactory.class);
            controller = factory.create();
        }
    }

    @Override
    public Panel toPanel() {
        return this;
    }

    @Override
    public void initGui() {
        this.debugIdRegistry = this.injector.getInstance(DebugIdRegistry.class);

        final GridLayout mainGrid = new GridLayout(2, 1);

        mainGrid.setWidth(WIDTH_SCREEN, Sizeable.UNITS_PIXELS);
        mainGrid.setHeight(HEIGHT_SCREEN, Sizeable.UNITS_PIXELS);

        // mainGrid.setSpacing(true);

        final VerticalLayout verticalLayoutLeft = new VerticalLayout();

        
        /**
         * Graph panel (start)
         */
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

//        final Embedded e =
//                new Embedded(null, new ThemeResource("../pcc/test/graph.gif"));
//        e.setWidth(350, UNITS_PIXELS);
//        e.setHeight(171, UNITS_PIXELS);
        
        verticalLayoutLeft.addComponent(graphPanelLayout);
        /**
         * Graph panel (end)
         */

        final GridLayout buttonsNewGrid = new GridLayout(3, 1);
        buttonsNewGrid.setWidth(WIDTH_SCREEN / 2, Sizeable.UNITS_PIXELS);

        final Button newTaskButton = getNewTaskButton();
        buttonsNewGrid.addComponent(newTaskButton, 0, 0);
        buttonsNewGrid.setComponentAlignment(newTaskButton,
                Alignment.MIDDLE_LEFT);

        final Button newMeetingButton = getNewMeetingButton();
        buttonsNewGrid.addComponent(newMeetingButton, 1, 0);
        buttonsNewGrid.setComponentAlignment(newMeetingButton,
                Alignment.MIDDLE_RIGHT);

        final Button newMilestoneButton = getNewMilestoneButton();
        newMilestoneButton.setDebugId(this.debugIdRegistry
                .getDebugId(
                        MessageCodePrefixRegistry.Module.centraleditingpanel,
                        "2-button-newMilestone"));
        buttonsNewGrid.addComponent(newMilestoneButton, 2, 0);
        buttonsNewGrid.setComponentAlignment(newMilestoneButton,
                Alignment.MIDDLE_RIGHT);

        verticalLayoutLeft.addComponent(buttonsNewGrid);

        final HorizontalLayout buttonsPriorityLayout = new HorizontalLayout();
        buttonsPriorityLayout.setSpacing(true);

        final Button priorityPlusButton =
                new Button(
                        TM.get("centraleditingprocesspanel.8-button-priorityUp"));
        priorityPlusButton.addListener(this); // react to clicks
        buttonsPriorityLayout.addComponent(priorityPlusButton);

        final Button priorityMinusButton =
                new Button(
                        TM.get("centraleditingprocesspanel.9-button-priorityDown"));
        priorityMinusButton.addListener(this); // react to clicks
        buttonsPriorityLayout.addComponent(priorityMinusButton);

        verticalLayoutLeft.addComponent(buttonsPriorityLayout);

        final VerticalLayout treeLayout = getTreeLayout();

        final Tree tree = new Tree();
        treeLayout.addComponent(tree);
        verticalLayoutLeft.addComponent(treeLayout);

        // Contents from a (prefilled example) hierarchical container:
        tree.setContainerDataSource(getFilterHierarchicalContainer());

        mainGrid.addComponent(verticalLayoutLeft, 0, 0);

        initController();
        verticalPanelRight = controller.getMeetingPanel();

        mainGrid.addComponent(verticalPanelRight, 1, 0);

        this.addComponent(mainGrid);
    }
    

    private List<SchedulingObjectDependencyTuple> getDependencyTuples() {
        final List<SchedulingObjectDependencyTuple> tuples =
                new LinkedList<SchedulingObjectDependencyTuple>();

        final SchedulingObjectDependencyTuple tuple1 =
                new MockSchedulingObjectDependencyTuple();
        tuple1.setLabel(P1);

        final SchedulingObjectDependencyTuple tuple2 =
                new MockSchedulingObjectDependencyTuple();
        tuple2.setLabel(P2);
        final List<String> p2Dependencies = new LinkedList<String>();
        p2Dependencies.add(P1);
        tuple2.setDependencies(p2Dependencies);

        final SchedulingObjectDependencyTuple tuple3 =
                new MockSchedulingObjectDependencyTuple();
        tuple3.setLabel(P3);

        tuples.add(tuple1);
        tuples.add(tuple2);
        tuples.add(tuple3);

        LOGGER.debug("P1 dependencies: " + tuple1.getDependencies());
        LOGGER.debug("P2 dependencies: " + tuple2.getDependencies());
        LOGGER.debug("P3 dependencies: " + tuple3.getDependencies());
        
        return tuples;
    }

    private void initController() {
        final DefaultCentralEditingPanelControllerFactory factory =
                this.injector
                        .getInstance(DefaultCentralEditingPanelControllerFactory.class);
        this.controller = factory.create();
        this.controller.setInjector(this.injector);
    }

    private VerticalLayout getTreeLayout() {
        final VerticalLayout treeLayout = new VerticalLayout();
        treeLayout.setWidth("90%");
        treeLayout.setSpacing(true);
        treeLayout.setMargin(true);
        treeLayout.addStyleName("border");
        return treeLayout;
    }

    private Button getNewMeetingButton() {
        final Button newMeetingButton =
                new Button(
                        TM.get("centraleditingprocesspanel.7-button-newMeeting"));
        newMeetingButton.addListener(this); // react to clicks
        newMeetingButton.setWidth(WIDTH_OF_NEW_BUTTONS,
                Sizeable.UNITS_PIXELS);
        return newMeetingButton;
    }

    private Button getNewTaskButton() {
        final Button newTaskButton =
                new Button(
                        TM.get("centraleditingprocesspanel.6-button-newTask"));
        newTaskButton.addListener(this); // react to clicks
        newTaskButton.setWidth(WIDTH_OF_NEW_BUTTONS,
                Sizeable.UNITS_PIXELS);
        return newTaskButton;
    }

    private Button getNewMilestoneButton() {
        final Button newMilestoneButton =
                new Button(
                        TM.get("centraleditingprocesspanel.19-button-newMilestone"));
        newMilestoneButton.addListener(this); // react to clicks
        newMilestoneButton.setWidth(WIDTH_OF_NEW_BUTTONS,
                Sizeable.UNITS_PIXELS);
        return newMilestoneButton;
    }

    /*
     * Shows a notification when a button is clicked.
     */
    public void buttonClick(final ClickEvent aEvent) {
        if (NEW_MILESTONE_BUTTON.equals(aEvent.getButton().getDebugId())) {
            getWindow().showNotification("lalala ");
        }
        getWindow().showNotification(NOTIFICATION);
    }

    public static HierarchicalContainer getFilterHierarchicalContainer() {
        return TempTreeObjectModel.getFilterHierarchicalContainer();
    }

    @Override
    public void taskAdded() {
    }
}

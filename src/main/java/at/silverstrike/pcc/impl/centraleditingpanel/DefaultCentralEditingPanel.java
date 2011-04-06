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
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.terminal.Sizeable;
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
import at.silverstrike.pcc.api.conventions.FunctionalBlock;
import at.silverstrike.pcc.api.debugids.DebugIdRegistry;
import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.projectnetworkgraphcreator.SchedulingObjectDependencyTuple;
import at.silverstrike.pcc.api.projectnetworkgraphpanel.ProjectNetworkGraphPanel;
import at.silverstrike.pcc.api.projectnetworkgraphpanel.ProjectNetworkGraphPanelFactory;
import at.silverstrike.pcc.api.projecttreemodel.ProjectTreeContainer;
import at.silverstrike.pcc.api.projecttreemodel.ProjectTreeContainerFactory;
import at.silverstrike.pcc.api.taskeditingpanel.TaskEditingPanel;
import eu.livotov.tpt.TPTApplication;
import eu.livotov.tpt.gui.dialogs.OptionDialog;
import eu.livotov.tpt.i18n.TM;

/**
 * @author DP118M
 * 
 */

class DefaultCentralEditingPanel extends Panel implements
        CentralEditingPanel, ClickListener, ProjectTreeSelectionListenerParent {
    private static final String NEW_EVENT_BUTTON = "025.004";
    private static final String NEW_TASK_BUTTON = "025.003";
    private static final String NEW_MILESTONE_BUTTON = "025.002";
    private static final int PADDING = 5;
    private static final int ONE_SIXTH_OF_SCREEN_WIDTH = 6;
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultCentralEditingPanel.class);
    private static final int HEIGHT_SCREEN = 600;
    private static final int WIDTH_SCREEN = 1200;
    private static final long serialVersionUID = 1L;
    private static final int WIDTH_OF_NEW_BUTTONS =
            WIDTH_SCREEN / ONE_SIXTH_OF_SCREEN_WIDTH - PADDING;

    private GridLayout mainGrid;

    private Panel verticalPanelRight;

    private Panel taskPanel;
    private Panel eventPanel;
    private Panel milestonePanel;

    private transient Injector injector;
    private transient CentralEditingPanelController controller;

    private DebugIdRegistry debugIdRegistry;

    private static final int DEFAULT_HEIGHT_PIXELS = 350;
    private static final int DEFAULT_WIDTH_PIXELS = 600;
    private ProjectTreeContainer treeModel;
    private Tree tree;
    private SchedulingObject curSelection;

    @Override
    public void setInjector(final Injector aInjector) {
        if (aInjector != null) {
            injector = aInjector;
        }
    }

    @Override
    public Panel toPanel() {
        return this;
    }

    @Override
    public void initGui() {
        this.debugIdRegistry = this.injector.getInstance(DebugIdRegistry.class);

        this.mainGrid = new GridLayout(2, 1);
        mainGrid.setWidth(WIDTH_SCREEN, Sizeable.UNITS_PIXELS);
        mainGrid.setHeight(HEIGHT_SCREEN, Sizeable.UNITS_PIXELS);

        final VerticalLayout verticalLayoutLeft = new VerticalLayout();

        /**
         * Graph panel (start)
         */
        final ProjectNetworkGraphPanelFactory factory =
                this.injector
                        .getInstance(ProjectNetworkGraphPanelFactory.class);
        final ProjectNetworkGraphPanel graphPanel = factory.create();

        graphPanel.setInjector(injector);
        graphPanel.initGui();

        final List<SchedulingObjectDependencyTuple> tuples =
                getDependencyTuples();

        graphPanel.updatePanel(tuples);

        final com.vaadin.ui.Layout graphPanelLayout = graphPanel.toLayout();

        graphPanelLayout.setWidth(DEFAULT_WIDTH_PIXELS, UNITS_PIXELS);
        graphPanelLayout.setHeight(DEFAULT_HEIGHT_PIXELS, UNITS_PIXELS);

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
                        FunctionalBlock.centraleditingpanel,
                        "2-button-newMilestone"));
        buttonsNewGrid.addComponent(newMilestoneButton, 2, 0);
        buttonsNewGrid.setComponentAlignment(newMilestoneButton,
                Alignment.MIDDLE_RIGHT);

        verticalLayoutLeft.addComponent(buttonsNewGrid);

        final HorizontalLayout buttonsPriorityLayout = new HorizontalLayout();
        buttonsPriorityLayout.setSpacing(true);

        final Button priorityPlusButton =
                new Button(
                        TM.get("centraleditingpanel.8-button-priorityUp"));
        priorityPlusButton.addListener(this); // react to clicks
        buttonsPriorityLayout.addComponent(priorityPlusButton);

        final Button priorityMinusButton =
                new Button(
                        TM.get("centraleditingpanel.9-button-priorityDown"));
        priorityMinusButton.addListener(this); // react to clicks
        buttonsPriorityLayout.addComponent(priorityMinusButton);

        verticalLayoutLeft.addComponent(buttonsPriorityLayout);

        final VerticalLayout treeLayout = getTreeLayout();

        initTreeModel();

        initTree(verticalLayoutLeft, treeLayout);

        mainGrid.addComponent(verticalLayoutLeft, 0, 0);
        taskPanel = controller.getTaskPanel();
        eventPanel = controller.getEventPanel();
        milestonePanel = controller.getMilestonePanel();
        setRightPanel(taskPanel);

        this.addComponent(mainGrid);
    }

    private void removeRightPanel() {

        mainGrid.removeComponent(verticalPanelRight);
    }

    private void changeRightPanel(final Panel aPanel) {
        removeRightPanel();
        setRightPanel(aPanel);
    }

    private void initTree(final VerticalLayout aLayout,
            final VerticalLayout aTreeLayout) {
        tree = new Tree();
        tree.setContainerDataSource(this.treeModel);
        tree.setImmediate(true);

        tree.setItemCaptionPropertyId(ProjectTreeContainer.PROJECT_PROPERTY_NAME);

        aTreeLayout.addComponent(tree);
        aLayout.addComponent(aTreeLayout);
        tree.addListener(new ProjectTreeSelectionListener(this));
    }

    private void initTreeModel() {
        final ProjectTreeContainerFactory treeModelFactory =
                this.injector.getInstance(ProjectTreeContainerFactory.class);
        this.treeModel = treeModelFactory.create();
        this.treeModel.setInjector(this.injector);
        this.treeModel.init();
        this.treeModel.updateData();
    }

    private void setRightPanel(final Panel aPanel) {
        if (aPanel != null) {
            verticalPanelRight = aPanel;
        }
        mainGrid.addComponent(verticalPanelRight, 1, 0);
    }

    private List<SchedulingObjectDependencyTuple> getDependencyTuples() {
        final List<SchedulingObjectDependencyTuple> tuples =
                new LinkedList<SchedulingObjectDependencyTuple>();

        final String P2_2 = "P2.2";
        final String P2_3 = "P2.3";
        final String P2_4 = "P2.4";
        final String P2_5 = "P2.5";
        final String P2_6 = "P2.6";
        final String P2_7 = "P2.7";
        final String P2_8 = "P2.8";

        tuples.add(getTuple(P2_2, new String[] {}));
        tuples.add(getTuple(P2_3, new String[] { P2_2 }));
        tuples.add(getTuple(P2_4, new String[] { P2_2 }));
        tuples.add(getTuple(P2_5, new String[] { P2_2, P2_4 }));
        tuples.add(getTuple(P2_6, new String[] { P2_5 }));
        tuples.add(getTuple(P2_7, new String[] { P2_2, P2_3, P2_6 }));
        tuples.add(getTuple(P2_8, new String[] { P2_6 }));

        return tuples;
    }

    private SchedulingObjectDependencyTuple getTuple(final String aLabel,
            final String[] aDependencies) {
        final List<String> dependencies = new LinkedList<String>();
        final MockSchedulingObjectDependencyTuple returnValue =
                new MockSchedulingObjectDependencyTuple();

        for (final String curDep : aDependencies) {
            dependencies.add(curDep);
        }

        returnValue.setLabel(aLabel);
        returnValue.setDependencies(dependencies);

        return returnValue;
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
                        TM.get("centraleditingpanel.7-button-newMeeting"));
        newMeetingButton.addListener(this); // react to clicks
        newMeetingButton.setWidth(WIDTH_OF_NEW_BUTTONS,
                Sizeable.UNITS_PIXELS);
        newMeetingButton.setDebugId(this.debugIdRegistry
                .getDebugId(
                        FunctionalBlock.centraleditingpanel,
                        "4-button-newMeeting"));
        return newMeetingButton;
    }

    private Button getNewTaskButton() {
        final Button newTaskButton =
                new Button(
                        TM.get("centraleditingpanel.6-button-newTask"));
        newTaskButton.addListener(this); // react to clicks
        newTaskButton.setWidth(WIDTH_OF_NEW_BUTTONS,
                Sizeable.UNITS_PIXELS);
        newTaskButton.setDebugId(this.debugIdRegistry
                .getDebugId(
                        FunctionalBlock.centraleditingpanel,
                        "3-button-newTask"));
        return newTaskButton;
    }

    private Button getNewMilestoneButton() {
        final Button newMilestoneButton =
                new Button(
                        TM.get("centraleditingpanel.19-button-newMilestone"));
        newMilestoneButton.addListener(this); // react to clicks
        newMilestoneButton.setWidth(WIDTH_OF_NEW_BUTTONS,
                Sizeable.UNITS_PIXELS);
        return newMilestoneButton;
    }

    /*
     * Shows a notification when a button is clicked.
     */
    public void buttonClick(final ClickEvent aEvent) {
        final String debugId = aEvent.getButton().getDebugId();
        final String user = getCurrentUserIdentity();
        final Long parentProjectId = getProjectIdCurrentlySelectedInTree();

        if (NEW_TASK_BUTTON.equals(debugId)) {
            this.controller.createTask(user,
                    parentProjectId);
        } else if (NEW_EVENT_BUTTON.equals(debugId)) {
            this.controller.createEvent(user,
                    parentProjectId);
            this.changeRightPanel(eventPanel);
        } else if (NEW_MILESTONE_BUTTON.equals(debugId)) {
            this.controller.createMilestone(user,
                    parentProjectId);
            this.changeRightPanel(milestonePanel);
        } else {
            LOGGER.error("Unexpected debug ID: {}", debugId);
        }
    }

    /**
     * @return Identity (primary key) of the user currently logged in.
     */
    private String getCurrentUserIdentity() {
        return "test";
    }

    /**
     * @return ID of the project, which is currently selected in the tree.
     */
    private Long getProjectIdCurrentlySelectedInTree() {
        Long projectId = this.curSelection.getId();
        LOGGER.debug("this.curSelection.getId(): {}", projectId);
        return projectId;
    }

    public static HierarchicalContainer getFilterHierarchicalContainer() {
        return TempTreeObjectModel.getFilterHierarchicalContainer();
    }

    @Override
    public void taskCreated(final Task aNewTask) {
        ((TaskEditingPanel) taskPanel).setTaskName(aNewTask.getName());
        this.changeRightPanel(taskPanel);

        updateTree();
    }

    private void updateTree() {
        this.treeModel.updateData();
        this.tree.expandItemsRecursively(ProjectTreeContainer.TREE_ROOT_ID);
    }

    @Override
    public void taskCreationFailure() {
        final OptionDialog dialog =
                new OptionDialog(TPTApplication.getCurrentApplication());

        final String title =
                TM.get("centraleditingpanel.23-taskCreationFailure-title");
        final String message =
                TM.get("centraleditingpanel.24-taskCreationFailure-message");

        dialog.showConfirmationDialog(title, message, null);
    }

    @Override
    public void treeSelectionChanged(final ValueChangeEvent aEvent) {
        if (aEvent == null) {
            return;
        }

        final Object selectedValue = aEvent.getProperty().getValue();

        final OptionDialog dialog =
                new OptionDialog(TPTApplication.getCurrentApplication());

        if (selectedValue != null) {
            final Integer treeItemId = (Integer) selectedValue;

            this.curSelection = this.treeModel.getSchedulingObject(treeItemId);
            dialog.showConfirmationDialog("Test",
                    "Selected scheduling object: " + this.curSelection.getId(),
                    null);

        } else {
            this.curSelection = null;
            dialog.showConfirmationDialog("Nothing selected",
                    "Nothing selected", null);
        }
    }

    @Override
    public void
            setGuiController(final CentralEditingPanelController aController) {
        controller = aController;
    }

}

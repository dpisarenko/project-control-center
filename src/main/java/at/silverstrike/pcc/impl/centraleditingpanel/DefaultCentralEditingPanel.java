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

import org.apache.commons.lang.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Injector;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.AbstractSelect;
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
import at.silverstrike.pcc.api.eventeditingpanelcontroller.EventEditingPanelController;
import at.silverstrike.pcc.api.eventeditingpanelcontroller.EventEditingPanelControllerFactory;
import at.silverstrike.pcc.api.milestoneeditingpanelcontroller.MilestoneEditingPanelController;
import at.silverstrike.pcc.api.milestoneeditingpanelcontroller.MilestoneEditingPanelControllerFactory;
import at.silverstrike.pcc.api.model.Milestone;
import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.projectnetworkgraphcreator.SchedulingObjectDependencyTuple;
import at.silverstrike.pcc.api.projectnetworkgraphpanel.ProjectNetworkGraphPanel;
import at.silverstrike.pcc.api.projectnetworkgraphpanel.ProjectNetworkGraphPanelFactory;
import at.silverstrike.pcc.api.projecttreemodel.ProjectTreeContainer;
import at.silverstrike.pcc.api.projecttreemodel.ProjectTreeContainerFactory;
import at.silverstrike.pcc.api.taskeditingpanelcontroller.TaskEditingPanelController;
import at.silverstrike.pcc.api.taskeditingpanelcontroller.TaskEditingPanelControllerFactory;
import eu.livotov.tpt.TPTApplication;
import eu.livotov.tpt.gui.dialogs.OptionDialog;
import eu.livotov.tpt.i18n.TM;

/**
 * @author DP118M
 * 
 */

class DefaultCentralEditingPanel extends Panel implements CentralEditingPanel,
        ClickListener, ProjectTreeSelectionListenerParent {
    private static final String NEW_EVENT_BUTTON = "025.004";
    private static final String NEW_TASK_BUTTON = "025.003";
    private static final String NEW_MILESTONE_BUTTON = "025.002";
    private static final int PADDING = 5;
    private static final int ONE_SIXTH_OF_SCREEN_WIDTH = 6;
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultCentralEditingPanel.class);
    private static final int WIDTH_SCREEN = 1200;
    private static final long serialVersionUID = 1L;
    private static final int WIDTH_OF_NEW_BUTTONS = WIDTH_SCREEN
            / ONE_SIXTH_OF_SCREEN_WIDTH - PADDING;

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

    private TaskEditingPanelController taskEditingPanelController;
    private EventEditingPanelController eventEditingPanelController;
    private MilestoneEditingPanelController milestoneEditingPanelController;
    private Panel treePanel;
    private Long curProjectId;
    private ProjectNetworkGraphPanel graphPanel;

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
        mainGrid.setSizeFull();

        final VerticalLayout verticalLayoutLeft = new VerticalLayout();

        /**
         * Graph panel (start)
         */
        final ProjectNetworkGraphPanelFactory factory = this.injector
                .getInstance(ProjectNetworkGraphPanelFactory.class);
        graphPanel = factory.create();

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

        final Button newMeetingButton = getNewEventButton();
        buttonsNewGrid.addComponent(newMeetingButton, 1, 0);
        buttonsNewGrid.setComponentAlignment(newMeetingButton,
                Alignment.MIDDLE_RIGHT);

        final Button newMilestoneButton = getNewMilestoneButton();
        newMilestoneButton.setDebugId(this.debugIdRegistry.getDebugId(
                FunctionalBlock.centraleditingpanel, "2-button-newMilestone"));
        buttonsNewGrid.addComponent(newMilestoneButton, 2, 0);
        buttonsNewGrid.setComponentAlignment(newMilestoneButton,
                Alignment.MIDDLE_RIGHT);

        verticalLayoutLeft.addComponent(buttonsNewGrid);

        final HorizontalLayout buttonsPriorityLayout = new HorizontalLayout();
        buttonsPriorityLayout.setSpacing(true);

        final Button priorityPlusButton = new Button(
                TM.get("centraleditingpanel.8-button-priorityUp"));
        priorityPlusButton.addListener(this); // react to clicks
        buttonsPriorityLayout.addComponent(priorityPlusButton);

        final Button priorityMinusButton = new Button(
                TM.get("centraleditingpanel.9-button-priorityDown"));
        priorityMinusButton.addListener(this); // react to clicks
        buttonsPriorityLayout.addComponent(priorityMinusButton);

        verticalLayoutLeft.addComponent(buttonsPriorityLayout);

        final VerticalLayout treeLayout = getTreeLayout();

        initTreeModel();

        this.treeModel.updateData();

        initTree(verticalLayoutLeft, treeLayout);
        updateTree();

        mainGrid.addComponent(verticalLayoutLeft, 0, 0);

        initTaskPanelController();
        initEventPanelController();
        initMilestonePanelController();

        taskPanel = this.taskEditingPanelController.initGui();
        eventPanel = this.eventEditingPanelController.initGui();
        milestonePanel = this.milestoneEditingPanelController.initGui();
        setRightPanel(taskPanel);

        this.addComponent(mainGrid);

        this.redrawProjectNetwork();
    }

    private void initMilestonePanelController() {
        final MilestoneEditingPanelControllerFactory factory = this.injector
                .getInstance(MilestoneEditingPanelControllerFactory.class);
        this.milestoneEditingPanelController = factory.create();
        this.milestoneEditingPanelController.setInjector(this.injector);
    }

    private void initEventPanelController() {
        final EventEditingPanelControllerFactory factory = this.injector
                .getInstance(EventEditingPanelControllerFactory.class);
        this.eventEditingPanelController = factory.create();
        this.eventEditingPanelController.setInjector(this.injector);
    }

    private void initTaskPanelController() {
        final TaskEditingPanelControllerFactory factory = this.injector
                .getInstance(TaskEditingPanelControllerFactory.class);
        this.taskEditingPanelController = factory.create();
        this.taskEditingPanelController.setInjector(this.injector);
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
        tree.setItemCaptionMode(AbstractSelect.ITEM_CAPTION_MODE_PROPERTY);
        tree.expandItemsRecursively(ProjectTreeContainer.TREE_ROOT_ID);

        treePanel = new Panel();
        treePanel.setScrollable(true);
        treePanel.addComponent(tree);
        treePanel.setSizeFull();
        treePanel.setHeight("200px");

        aTreeLayout.addComponent(treePanel);
        aLayout.addComponent(aTreeLayout);
        tree.addListener(new ProjectTreeSelectionListener(this));
    }

    private void initTreeModel() {
        final ProjectTreeContainerFactory treeModelFactory = this.injector
                .getInstance(ProjectTreeContainerFactory.class);
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
        // treeLayout.setWidth("90%");
        treeLayout.setSizeFull();
        treeLayout.setSpacing(true);
        treeLayout.setMargin(true);
        treeLayout.addStyleName("border");

        return treeLayout;
    }

    private Button getNewEventButton() {
        final Button newEventButton = new Button(
                TM.get("centraleditingpanel.7-button-newEvent"));
        newEventButton.addListener(this); // react to clicks
        newEventButton.setWidth(WIDTH_OF_NEW_BUTTONS, Sizeable.UNITS_PIXELS);
        newEventButton.setDebugId(this.debugIdRegistry.getDebugId(
                FunctionalBlock.centraleditingpanel, "4-button-newEvent"));
        return newEventButton;
    }

    private Button getNewTaskButton() {
        final Button newTaskButton = new Button(
                TM.get("centraleditingpanel.6-button-newTask"));
        newTaskButton.addListener(this); // react to clicks
        newTaskButton.setWidth(WIDTH_OF_NEW_BUTTONS, Sizeable.UNITS_PIXELS);
        newTaskButton.setDebugId(this.debugIdRegistry.getDebugId(
                FunctionalBlock.centraleditingpanel, "3-button-newTask"));
        return newTaskButton;
    }

    private Button getNewMilestoneButton() {
        final Button newMilestoneButton = new Button(
                TM.get("centraleditingpanel.19-button-newMilestone"));
        newMilestoneButton.addListener(this); // react to clicks
        newMilestoneButton
                .setWidth(WIDTH_OF_NEW_BUTTONS, Sizeable.UNITS_PIXELS);
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
            this.controller.createTask(user, parentProjectId);
        } else if (NEW_EVENT_BUTTON.equals(debugId)) {
            this.controller.createEvent(user, parentProjectId);
            // this.changeRightPanel(eventPanel);
        } else if (NEW_MILESTONE_BUTTON.equals(debugId)) {
            this.controller.createMilestone(user, parentProjectId);
            // this.changeRightPanel(milestonePanel);
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
        Long projectId = null;

        if (this.curSelection != null) {
            projectId = this.curSelection.getId();
        }

        LOGGER.debug("this.curSelection.getId(): {}", projectId);
        return projectId;
    }

    @Override
    public void taskCreated(final Task aNewTask) {
        this.taskEditingPanelController.setData(aNewTask);
        this.changeRightPanel(taskPanel);

        updateTree();
        this.redrawProjectNetwork();
    }

    private void updateTree() {
        this.treeModel.updateData();
        this.tree.expandItemsRecursively(ProjectTreeContainer.TREE_ROOT_ID);
    }

    @Override
    public void taskCreationFailure() {
        final OptionDialog dialog = new OptionDialog(
                TPTApplication.getCurrentApplication());

        final String title = TM
                .get("centraleditingpanel.23-taskCreationFailure-title");
        final String message = TM
                .get("centraleditingpanel.24-taskCreationFailure-message");

        dialog.showConfirmationDialog(title, message, null);
    }

    @Override
    public void treeSelectionChanged(final ValueChangeEvent aEvent) {
        if (aEvent == null) {
            return;
        }

        final Long oldParentId = this.curProjectId;

        final Object selectedValue = aEvent.getProperty().getValue();

        if (selectedValue != null) {
            final Integer treeItemId = (Integer) selectedValue;

            this.curSelection = this.treeModel.getSchedulingObject(treeItemId);

            if (this.curSelection != null) {
                final SchedulingObject newParent =
                        this.curSelection.getParent();

                if (newParent != null) {
                    this.curProjectId = newParent.getId();
                } else {
                    this.curProjectId = null;
                }
            }

            if (this.curSelection instanceof Task) {
                this.taskEditingPanelController
                        .setData((Task) this.curSelection);
                this.changeRightPanel(taskPanel);
            } else if (this.curSelection instanceof Milestone) {
                /**
                 * Здесь надо вставить в правую часть окна панель для рубежей...
                 */
            } else if (this.curSelection instanceof at.silverstrike.pcc.api.model.Event) {
                /**
                 * ...а здесь - панель для событий.
                 */
            }
        } else {
            this.curSelection = null;
            removeRightPanel();
        }

        /**
         * Надо ли обновлять сетевой график? (начало)
         */
        /**
         * Этот метод (ObjectUtils.equals) правильно обходится с теми случаями,
         * когда один объект равен null, а другой - нет.
         * 
         * В отличии от стандартного метода equals.
         */
        if (!ObjectUtils.equals(oldParentId, this.curProjectId)) {
            this.redrawProjectNetwork();
        }
        /**
         * Надо ли обновлять сетевой график? (конец)
         */

    }

    @Override
    public void
            setGuiController(final CentralEditingPanelController aController) {
        controller = aController;
    }

    @Override
    public void updateTaskNodeInTree(final Task aTask) {
        this.treeModel.updateNodeLettering(aTask);
    }

    @Override
    public void
            eventCreated(final at.silverstrike.pcc.api.model.Event aNewEvent) {
        this.eventEditingPanelController.setData(aNewEvent);
        this.changeRightPanel(eventPanel);
        updateTree();
        this.redrawProjectNetwork();
    }

    @Override
    public void eventCreationFailure() {
        final OptionDialog dialog = new OptionDialog(
                TPTApplication.getCurrentApplication());

        final String title = TM
                .get("centraleditingpanel.25-eventCreationFailure-title");
        final String message = TM
                .get("centraleditingpanel.26-eventCreationFailure-message");

        dialog.showConfirmationDialog(title, message, null);

    }

    @Override
    public void milestoneCreated(final Milestone aMilestone) {
        this.milestoneEditingPanelController.setData(aMilestone);
        this.changeRightPanel(milestonePanel);
        updateTree();
        this.redrawProjectNetwork();
    }

    @Override
    public void milestoneCreationFailure() {
        final OptionDialog dialog = new OptionDialog(
                TPTApplication.getCurrentApplication());

        final String title = TM
                .get("centraleditingpanel.27-milestoneCreationFailure-title");
        final String message = TM
                .get("centraleditingpanel.28-milestoneCreationFailure-message");

        dialog.showConfirmationDialog(title, message, null);

    }

    /*start of my changes
<<<<<<< HEAD
	@Override
	public void taskDeleted(Task aTask) {
		this.taskEditingPanelController.clearPanel();
		updateTree();
		
	}
*/
	@Override
	public void taskDeletionFailure() {
		// TODO Auto-generated method stub
		
	}
/*
	@Override
	public void eventDeleted(at.silverstrike.pcc.api.model.Event aNewEvent) {
		// TODO Auto-generated method stub
		
	}
*/
	@Override
	public void eventDeletionFailure() {
		// TODO Auto-generated method stub
		
	}
/*
	@Override
	public void milestoneDeleted(Milestone aMilestone) {
		// TODO Auto-generated method stub
		
	}
*/
	@Override
	public void milestoneDeletionFailure() {
		// TODO Auto-generated method stub
		
	}

//end of my changes 

    /**
     * Вызов этого метода обновляет сетевой график
     */
    void redrawProjectNetwork() {
        /**
         * Определяем выбранный проект
         * 
         * Выбранный проект - родитель объекта, выбранного в дереве
         * 
         * Идея: Эта информация уже есть в методе, который вызывается при
         * изменении выбора в дереве.
         * 
         * => Пусть идентификатор проекта будет атрибутом класса.
         */

        /**
         * Берём базы данных перечень всех процессов выбранного проекта
         */
        final Persistence persistence =
                this.injector.getInstance(Persistence.class);
        final List<SchedulingObject> projectSchedulingObjects =
                persistence.getSubProcessesWithChildren(this.curProjectId);

        LOGGER.debug("projectSchedulingObjects: {}", projectSchedulingObjects);

        /**
         * Преобразуем каждый расчётный объект в набор данных.
         */
        final List<SchedulingObjectDependencyTuple> tuples =
                new LinkedList<SchedulingObjectDependencyTuple>();

        for (final SchedulingObject curObject : projectSchedulingObjects) {
            final String vertex = curObject.getLabel();
            final List<String> dependencies = new LinkedList<String>();
            /**
             * В графике будем показывать только зависимости из того же проекта.
             */

            if (curObject.getPredecessors() != null) {
                LOGGER.debug("curObject.getPredecessors(): {}",
                        curObject.getPredecessors());
                for (final SchedulingObject curDependency : curObject
                        .getPredecessors()) {
                    Long dependencyParentId = null;

                    if (curDependency.getParent() != null) {
                        dependencyParentId = curDependency.getParent().getId();
                    }

                    if (ObjectUtils.equals(this.curProjectId,
                            dependencyParentId)) {
                        dependencies.add(curDependency.getLabel());
                    }
                }
            }
            LOGGER.debug(
                    "vertex: {}, dependencies: {}",
                    new Object[] {
                            vertex, dependencies });

            tuples.add(getTuple(vertex, dependencies));
        }

        /**
         * Теперь обновляем панель
         */
        this.graphPanel.updatePanel(tuples);
    }

    private SchedulingObjectDependencyTuple getTuple(final String aLabel,
            final List<String> aDependencies) {
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

    @Override
    public void taskDeleted(final Task aDeletedSchedulingObject) {
        this.redrawProjectNetwork();
    }

    @Override
    public void milestoneDeleted(final Milestone aDeletedSchedulingObject) {
        this.redrawProjectNetwork();
    }

    @Override
    public void eventDeleted(
            final at.silverstrike.pcc.api.model.Event aDeletedSchedulingObject) {
        this.redrawProjectNetwork();
    }

}

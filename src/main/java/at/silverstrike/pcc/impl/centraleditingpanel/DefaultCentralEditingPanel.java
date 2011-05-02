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

import org.apache.commons.lang.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.altruix.commons.api.di.PccException;

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
import at.silverstrike.pcc.api.centraleditingpanelbuttonstate.CentralEditingPanelButtonStateCalculator;
import at.silverstrike.pcc.api.centraleditingpanelbuttonstate.CentralEditingPanelButtonStateCalculatorFactory;
import at.silverstrike.pcc.api.centraleditingpanelcontroller.CentralEditingPanelController;
import at.silverstrike.pcc.api.debugids.PccDebugIdRegistry;
import at.silverstrike.pcc.api.eventeditingpanelcontroller.EventEditingPanelController;
import at.silverstrike.pcc.api.eventeditingpanelcontroller.EventEditingPanelControllerFactory;
import at.silverstrike.pcc.api.milestoneeditingpanelcontroller.MilestoneEditingPanelController;
import at.silverstrike.pcc.api.milestoneeditingpanelcontroller.MilestoneEditingPanelControllerFactory;
import at.silverstrike.pcc.api.model.Milestone;
import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.pcc.PccFunctionalBlock;
import at.silverstrike.pcc.api.projectnetworkdatacreator.ProjectNetworkDataCreator;
import at.silverstrike.pcc.api.projectnetworkdatacreator.ProjectNetworkDataCreatorFactory;
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
    private static final String PRIORITY_UP_BUTTON = "025.005";
    private static final String PRIORITY_DOWN_BUTTON = "025.006";
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

    private transient PccDebugIdRegistry debugIdRegistry;

    private static final int DEFAULT_HEIGHT_PIXELS = 350;
    private static final int DEFAULT_WIDTH_PIXELS = 600;
    private ProjectTreeContainer treeModel;
    private Tree tree;
    private transient SchedulingObject curSelection;

    private transient TaskEditingPanelController taskEditingPanelController;
    private transient EventEditingPanelController eventEditingPanelController;
    private transient MilestoneEditingPanelController milestoneEditingPanelController;
    private Panel treePanel;
    private Long curProjectId;
    private transient ProjectNetworkGraphPanel graphPanel;
    private transient CentralEditingPanelButtonStateCalculator buttonStateCalculator;
    private transient ProjectNetworkDataCreator projectNetworkDataCreator;
    private Button newTaskButton;
    private Button newEventButton;
    private Button newMilestoneButton;
    private Button increasePriorityButton;
    private Button decreasePriorityButton;

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
        initButtonStateCalculator();
        initProjectNetworkDataCreator();
        this.debugIdRegistry =
                this.injector.getInstance(PccDebugIdRegistry.class);

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

        final com.vaadin.ui.Layout graphPanelLayout = graphPanel.toLayout();

        graphPanelLayout.setWidth(DEFAULT_WIDTH_PIXELS, UNITS_PIXELS);
        graphPanelLayout.setHeight(DEFAULT_HEIGHT_PIXELS, UNITS_PIXELS);

        verticalLayoutLeft.addComponent(graphPanelLayout);
        /**
         * Graph panel (end)
         */

        final GridLayout buttonsNewGrid = new GridLayout(3, 1);
        buttonsNewGrid.setWidth(WIDTH_SCREEN / 2, Sizeable.UNITS_PIXELS);

        newTaskButton = getNewTaskButton();
        buttonsNewGrid.addComponent(newTaskButton, 0, 0);
        buttonsNewGrid.setComponentAlignment(newTaskButton,
                Alignment.MIDDLE_LEFT);

        newEventButton = getNewEventButton();
        buttonsNewGrid.addComponent(newEventButton, 1, 0);
        buttonsNewGrid.setComponentAlignment(newEventButton,
                Alignment.MIDDLE_RIGHT);

        newMilestoneButton = getNewMilestoneButton();
        buttonsNewGrid.addComponent(newMilestoneButton, 2, 0);
        buttonsNewGrid.setComponentAlignment(newMilestoneButton,
                Alignment.MIDDLE_RIGHT);

        verticalLayoutLeft.addComponent(buttonsNewGrid);

        final HorizontalLayout buttonsPriorityLayout = new HorizontalLayout();
        buttonsPriorityLayout.setSpacing(true);

        increasePriorityButton = getPriorityUpButton();
        buttonsPriorityLayout.addComponent(increasePriorityButton);

        decreasePriorityButton = getPriorityDownButton();
        buttonsPriorityLayout.addComponent(decreasePriorityButton);

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

    private Button getPriorityDownButton() {
        decreasePriorityButton = new Button(
                TM.get("centraleditingpanel.9-button-priorityDown"));
        decreasePriorityButton.addListener(this); // react to clicks
        decreasePriorityButton.setDebugId(this.debugIdRegistry
                .getDebugId(
                        PccFunctionalBlock.centraleditingpanel,
                        "6-button-priorityDown"));
        return decreasePriorityButton;
    }

    private Button getPriorityUpButton() {
        increasePriorityButton = new Button(
                TM.get("centraleditingpanel.8-button-priorityUp"));
        increasePriorityButton.addListener(this); // react to clicks
        increasePriorityButton.setDebugId(this.debugIdRegistry.getDebugId(
                PccFunctionalBlock.centraleditingpanel, "5-button-priorityUp"));
        return increasePriorityButton;
    }

    private void initProjectNetworkDataCreator() {
        final ProjectNetworkDataCreatorFactory factory =
                this.injector
                        .getInstance(ProjectNetworkDataCreatorFactory.class);
        this.projectNetworkDataCreator = factory.create();
        this.projectNetworkDataCreator.setInjector(this.injector);
    }

    private void initButtonStateCalculator() {
        final CentralEditingPanelButtonStateCalculatorFactory bscFactory =
                this.injector
                        .getInstance(CentralEditingPanelButtonStateCalculatorFactory.class);
        this.buttonStateCalculator =
                bscFactory.create();
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

    private VerticalLayout getTreeLayout() {
        final VerticalLayout treeLayout = new VerticalLayout();
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
                PccFunctionalBlock.centraleditingpanel, "4-button-newEvent"));
        return newEventButton;
    }

    private Button getNewTaskButton() {
        final Button newTaskButton = new Button(
                TM.get("centraleditingpanel.6-button-newTask"));
        newTaskButton.addListener(this); // react to clicks
        newTaskButton.setWidth(WIDTH_OF_NEW_BUTTONS, Sizeable.UNITS_PIXELS);
        newTaskButton.setDebugId(this.debugIdRegistry.getDebugId(
                PccFunctionalBlock.centraleditingpanel, "3-button-newTask"));
        return newTaskButton;
    }

    private Button getNewMilestoneButton() {
        final Button newMilestoneButton = new Button(
                TM.get("centraleditingpanel.19-button-newMilestone"));
        newMilestoneButton.setDebugId(this.debugIdRegistry
                .getDebugId(
                        PccFunctionalBlock.centraleditingpanel,
                        "2-button-newMilestone"));
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
        } else if (NEW_MILESTONE_BUTTON.equals(debugId)) {
            this.controller.createMilestone(user, parentProjectId);
        } else if (PRIORITY_UP_BUTTON.equals(debugId)) {
            this.controller.increasePriorityButtonClicked(parentProjectId);
        } else if (PRIORITY_DOWN_BUTTON.equals(debugId)) {
            this.controller.decreasePriorityButtonClicked(parentProjectId);
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
                this.milestoneEditingPanelController
                        .setData((Milestone) this.curSelection);
                this.changeRightPanel(milestonePanel);

            } else if (this.curSelection instanceof at.silverstrike.pcc.api.model.Event) {
                this.eventEditingPanelController
                        .setData((at.silverstrike.pcc.api.model.Event) this.curSelection);
                this.changeRightPanel(eventPanel);
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

        /**
         * Обновляем статус кнопок
         */
        updateButtonState();
    }

    private void updateButtonState() {
        this.buttonStateCalculator.setCurrentSelection(this.curSelection);
        try {
            this.buttonStateCalculator.run();
            this.newTaskButton.setEnabled(this.buttonStateCalculator
                    .isNewTaskButtonEnabled());
            this.newEventButton.setEnabled(this.buttonStateCalculator
                    .isNewEventButtonEnabled());
            this.newMilestoneButton.setEnabled(this.buttonStateCalculator
                    .isNewMilestoneButtonEnabled());
            this.increasePriorityButton.setEnabled(this.buttonStateCalculator
                    .isIncreasePriorityButtonEnabled());
            this.decreasePriorityButton.setEnabled(this.buttonStateCalculator
                    .isDecreasePriorityButtonEnabled());
        } catch (final PccException exception) {
            LOGGER.error("", exception);
        }
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

    @Override
    public void taskDeletionFailure() {
        // TODO Auto-generated method stub

    }

    @Override
    public void eventDeletionFailure() {
        // TODO Auto-generated method stub

    }

    @Override
    public void milestoneDeletionFailure() {
        // TODO Auto-generated method stub

    }

    /**
     * Вызов этого метода обновляет сетевой график
     */
    void redrawProjectNetwork() {
        this.projectNetworkDataCreator.setCurrentProjectId(this.curProjectId);
        try {
            this.projectNetworkDataCreator.run();
            this.graphPanel.updatePanel(this.projectNetworkDataCreator
                    .getDependencyTuples());
        } catch (final PccException exception) {
            LOGGER.error("", exception);
        }
    }

    @Override
    public void
            schedulingObjectDeleted(final SchedulingObject aSchedulingObject) {
        this.redrawProjectNetwork();
        this.updateTree();
    }

    @Override
    public void updateMilestoneNodeInTree(final Milestone aMilestone) {
        this.treeModel.updateNodeLettering(aMilestone);
    }

    @Override
    public void
            updateEventNodeInTree(final at.silverstrike.pcc.api.model.Event aEvent) {
        this.treeModel.updateNodeLettering(aEvent);
    }

    @Override
    public void taskDependenciesChanged(final SchedulingObject aObject) {
        this.redrawProjectNetwork();        
    }

    @Override
    public void taskCompleted(final Task aTask) {
        this.updateTree();
    }
}

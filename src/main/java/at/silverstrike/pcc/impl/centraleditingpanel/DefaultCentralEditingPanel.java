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

package at.silverstrike.pcc.impl.centraleditingpanel;

import java.util.Arrays;
import java.util.List;

import com.google.inject.Injector;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.terminal.Sizeable;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import at.silverstrike.pcc.api.centraleditingpanel.CentralEditingPanel;
import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.dependencieseditingwindow.DependenciesEditingPanel;
import at.silverstrike.pcc.api.dependencieseditingwindow.DependenciesEditingPanelFactory;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.processpanel.ProcessPanelListener;
import at.silverstrike.pcc.api.testtablecreator.TestTableCreator;
import eu.livotov.tpt.i18n.TM;

class DefaultCentralEditingPanel extends Panel implements
        CentralEditingPanel, ProcessPanelListener, ClickListener {

    private static final int HEIGHT_SCREEN = 600;
    private static final int WIDTH_SCREEN = 800;
    private static final long serialVersionUID = 1L;
    private static final String NOTIFICATION = "Smth happend";
    private transient Persistence persistence = null;
    private static final int APPROXIMATELY_QUARTER_OF_SCREEN_WIDTH =
            WIDTH_SCREEN / 4 - 5;

    public static final Object PROJECT_PROPERTY_NAME = "name";

    private static final int PROCESS_NAME_TEXT_FIELD_COLUMNS = 30;
    private static final int PROCESS_NAME_TEXT_FIELD_ROWS = 5;

    private static final String[] filterImpactTypes = { "Passive filters",
            "Active filters" };
    private static final String[] filterTypes =
            { "Sums", "Parities", "Numbers" };
    private static final String[] min = new String[] { "15 min", "30 min",
            "45 min" };
    private static final String[] TEST_COLUMN_NAMES = new String[] { "¹",
            "Project", "Name" };
    private static final List<String[]> TEST_TABLE_DATA =
            Arrays.asList(
                    new String[] { "1.1", "Project 1", "Task 1" },
                    new String[] { "2.1", "Project 4", "Task 5" });

    private MenuBar menubar = new MenuBar();
    private Injector injector;

    @Override
    public void setInjector(final Injector aInjector) {
        if (aInjector != null) {
            injector = aInjector;
            this.persistence = aInjector.getInstance(Persistence.class);
        }
    }

    @Override
    public Panel toPanel() {
        return this;
    }

    @Override
    public void initGui() {
        final MenuBar menubar = new MenuBar();
        menubar.setWidth(WIDTH_SCREEN, Sizeable.UNITS_PIXELS);
        addComponent(menubar);

        // Save reference to individual items so we can add sub-menu items to
        // them
        final MenuBar.MenuItem file =
                menubar.addItem(
                        TM.get("centraleditingprocesspanel.1-menu-file"), null);
        file.addItem(TM.get("centraleditingprocesspanel.2-menu-exportXML"),
                menuCommand);
        file.addItem(TM.get("centraleditingprocesspanel.3-menu-importXML"),
                menuCommand);
        file.addSeparator();
        file.addItem(TM.get("centraleditingprocesspanel.4-menu-exit"),
                menuCommand);

        menubar.addItem(TM.get("centraleditingprocesspanel.5-menu-other"), null);

        final GridLayout mainGrid = new GridLayout(2, 1);

        mainGrid.setWidth(WIDTH_SCREEN, Sizeable.UNITS_PIXELS);
        mainGrid.setHeight(HEIGHT_SCREEN, Sizeable.UNITS_PIXELS);

        // mainGrid.setSpacing(true);

        final VerticalLayout verticalLayoutLeft = new VerticalLayout();
        final Embedded e =
                new Embedded(null, new ThemeResource("../pcc/test/graph.gif"));
        verticalLayoutLeft.addComponent(e);

        final GridLayout buttonsNewGrid = new GridLayout(2, 1);
        buttonsNewGrid.setWidth(WIDTH_SCREEN / 2, Sizeable.UNITS_PIXELS);

        final Button newTaskButton =
                new Button(
                        TM.get("centraleditingprocesspanel.6-button-newTask"));
        newTaskButton.addListener(this); // react to clicks
        newTaskButton.setWidth(APPROXIMATELY_QUARTER_OF_SCREEN_WIDTH,
                Sizeable.UNITS_PIXELS);
        buttonsNewGrid.addComponent(newTaskButton, 0, 0);
        buttonsNewGrid.setComponentAlignment(newTaskButton,
                Alignment.MIDDLE_LEFT);

        final Button newMeetingButton =
                new Button(
                        TM.get("centraleditingprocesspanel.7-button-newMeeting"));
        newMeetingButton.addListener(this); // react to clicks
        newMeetingButton.setWidth(APPROXIMATELY_QUARTER_OF_SCREEN_WIDTH,
                Sizeable.UNITS_PIXELS);
        buttonsNewGrid.addComponent(newMeetingButton, 1, 0);
        buttonsNewGrid.setComponentAlignment(newMeetingButton,
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

        final Tree tree = new Tree();
        VerticalLayout treeLayout = new VerticalLayout();
        treeLayout.setWidth("90%");
        treeLayout.setSpacing(true);
        treeLayout.setMargin(true);
        treeLayout.addStyleName("border");

        treeLayout.addComponent(tree);
        verticalLayoutLeft.addComponent(treeLayout);

        // Contents from a (prefilled example) hierarchical container:
        tree.setContainerDataSource(getFilterHierarchicalContainer());

        mainGrid.addComponent(verticalLayoutLeft, 0, 0);

        final VerticalLayout verticalLayoutRight = new VerticalLayout();

        final Label taskLabel =
                new Label(TM.get("centraleditingprocesspanel.10-label-task"));
        taskLabel.setContentMode(Label.CONTENT_TEXT);
        verticalLayoutRight.addComponent(taskLabel);

        final HorizontalLayout buttonsTaskLayout = new HorizontalLayout();
        buttonsTaskLayout.setSpacing(true);

        final Button saveButton =
                new Button(TM.get("centraleditingprocesspanel.11-button-save"));
        saveButton.addListener(this); // react to clicks
        buttonsTaskLayout.addComponent(saveButton);

        final Button doneButton =
                new Button(TM.get("centraleditingprocesspanel.12-button-done"));
        doneButton.addListener(this); // react to clicks
        buttonsTaskLayout.addComponent(doneButton);

        final Button deleteButton =
                new Button(
                        TM.get("centraleditingprocesspanel.13-button-delete"));
        deleteButton.addListener(this); // react to clicks
        buttonsTaskLayout.addComponent(deleteButton);

        verticalLayoutRight.addComponent(buttonsTaskLayout);

        final TextField taskNameTextField = new TextField();
        taskNameTextField.setColumns(PROCESS_NAME_TEXT_FIELD_COLUMNS);
        taskNameTextField.setRows(PROCESS_NAME_TEXT_FIELD_ROWS);
        verticalLayoutRight.addComponent(taskNameTextField);

        final Label effortLabel =
                new Label(TM.get("centraleditingprocesspanel.14-label-effort"));
        effortLabel.setContentMode(Label.CONTENT_TEXT);
        verticalLayoutRight.addComponent(effortLabel);

        final HorizontalLayout effortLayout = new HorizontalLayout();
        effortLayout.setSpacing(true);

        final Label fromLabel =
                new Label(TM.get("centraleditingprocesspanel.15-label-from"));
        fromLabel.setContentMode(Label.CONTENT_TEXT);
        effortLayout.addComponent(fromLabel);

        final ComboBox from = new ComboBox();
        for (int i = 0; i < min.length - 1; i++) {
            from.addItem(min[i]);
        }
        effortLayout.addComponent(from);

        final Label toLabel =
                new Label(TM.get("centraleditingprocesspanel.16-label-to"));
        toLabel.setContentMode(Label.CONTENT_TEXT);
        effortLayout.addComponent(toLabel);

        final ComboBox to = new ComboBox();
        for (int i = 1; i < min.length; i++) {
            to.addItem(min[i]);
        }
        effortLayout.addComponent(to);

        verticalLayoutRight.addComponent(effortLayout);

        final HorizontalLayout dependLayout = new HorizontalLayout();
        dependLayout.setSpacing(true);

        final Label dependLabel =
                new Label(
                        TM.get("centraleditingprocesspanel.17-label-dependencies"));
        dependLayout.addComponent(dependLabel);

        final Button dependEditButton =
                new Button(TM.get("centraleditingprocesspanel.18-button-edit"));
        dependEditButton.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final ClickEvent aEvent) {
                dependEditButtonClicked();
            }
        });
        dependLayout.addComponent(dependEditButton);

        verticalLayoutRight.addComponent(dependLayout);

        final TestTableCreator creator =
                this.injector.getInstance(TestTableCreator.class);
        creator.setColumnNames(TEST_COLUMN_NAMES);
        creator.setData(TEST_TABLE_DATA);
        try {
            creator.run();
        } catch (PccException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        final Table table = creator.getTable();
        verticalLayoutRight.addComponent(table);

        mainGrid.addComponent(verticalLayoutRight, 1, 0);

        this.addComponent(mainGrid);
    }

    protected void dependEditButtonClicked() {

        final DependenciesEditingPanelFactory factory =
                this.injector
                        .getInstance(DependenciesEditingPanelFactory.class);
        final DependenciesEditingPanel panel = factory.create();
        panel.setInjector(injector);
        panel.initGui();
    }

    private Command menuCommand = new Command() {
        private static final long serialVersionUID = 1L;

        public void menuSelected(final MenuItem selectedItem) {
            getWindow().showNotification("Action " + selectedItem.getText());
        }
    };

    /*
     * Shows a notification when a button is clicked.
     */
    public void buttonClick(ClickEvent event) {
        // ������� �������� � ����������� �� ������
        getWindow().showNotification(NOTIFICATION);
    }

    public static HierarchicalContainer getFilterHierarchicalContainer() {
        return TempTreeObjectModel.getFilterHierarchicalContainer();
    }

    @Override
    public void taskAdded() {
        // TODO Auto-generated method stub

    }

}
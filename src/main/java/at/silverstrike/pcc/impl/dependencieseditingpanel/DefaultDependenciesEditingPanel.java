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

package at.silverstrike.pcc.impl.dependencieseditingpanel;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.altruix.commons.api.di.PccException;

import com.google.inject.Injector;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import eu.livotov.tpt.i18n.TM;
import at.silverstrike.pcc.api.debugids.PccDebugIdRegistry;
import at.silverstrike.pcc.api.dependencieseditingpanel.DependenciesEditingPanel;
import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.pcc.PccFunctionalBlock;
import at.silverstrike.pcc.api.testtablecreator.TestTableCreator;

class DefaultDependenciesEditingPanel extends Panel implements
        DependenciesEditingPanel, ClickListener {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultDependenciesEditingPanel.class);

    private transient Injector injector;
    private transient PccDebugIdRegistry debugIdRegistry;

    private static final String[] TEST_COLUMN_NAMES = new String[] { "�",
            "Project", "Name" };
    private static final List<String[]> TEST_TABLE_DATA =
            Arrays.asList(
                    new String[] { "1.1", "Project 1", "Task 1" },
                    new String[] { "2.1", "Project 4", "Task 5" });
    private Panel panel;

    @Override
    public void setInjector(final Injector aInjector) {
        if (aInjector != null) {
            this.injector = aInjector;
            this.debugIdRegistry =
                    this.injector.getInstance(PccDebugIdRegistry.class);
        }
    }

    @Override
    public void initGui() {
        panel = new Panel();

        final HorizontalLayout dependLayout = new HorizontalLayout();
        dependLayout.setSpacing(true);

        final Label dependLabel =
                new Label(
                        TM.get("dependencieseditingpanel.1-label-dependencies"));
        dependLayout.addComponent(dependLabel);

        final Button dependEditButton = createDependEditButton();
        dependLayout.addComponent(dependEditButton);

        panel.addComponent(dependLayout);

        // final Dependen

        final Table table = createTestTable();
        panel.addComponent(table);

    }

    private Button createDependEditButton() {
        final Button dependEditButton =
                new Button(TM.get("dependencieseditingpanel.2-button-edit"));
        dependEditButton.setDebugId(this.debugIdRegistry.getDebugId(
                PccFunctionalBlock.dependencieseditingpanel,
                "1-button-dependencies"));
        dependEditButton.addListener(this);

        return dependEditButton;
    }

    private Table createTestTable() {
        final TestTableCreator creator =
                this.injector.getInstance(TestTableCreator.class);
        creator.setColumnNames(TEST_COLUMN_NAMES);
        creator.setData(TEST_TABLE_DATA);
        try {
            creator.run();
        } catch (final PccException exception) {
            LOGGER.error(ErrorCodes.M_001_TEST_TABLE_CREATION, exception);
        }
        final Table table = creator.getTable();
        return table;
    }

    @Override
    public void buttonClick(final ClickEvent aEvent) {
        // TODO Auto-generated method stub

    }

    @Override
    public Panel toPanel() {
        return panel;
    }

    @Override
    public void setData(final SchedulingObject aSchedulingObject) {
        // TODO Auto-generated method stub

    }
}
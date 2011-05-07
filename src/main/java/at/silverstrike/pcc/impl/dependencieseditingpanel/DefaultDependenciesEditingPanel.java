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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.altruix.commons.api.di.PccException;

import com.google.inject.Injector;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import eu.livotov.tpt.TPTApplication;
import eu.livotov.tpt.i18n.TM;
import at.silverstrike.pcc.api.debugids.PccDebugIdRegistry;
import at.silverstrike.pcc.api.dependencieseditingdialogcontroller.DependenciesEditingDialogController;
import at.silverstrike.pcc.api.dependencieseditingdialogcontroller.DependenciesEditingDialogControllerFactory;
import at.silverstrike.pcc.api.dependencieseditingpanel.DependenciesEditingPanel;
import at.silverstrike.pcc.api.dependencytablefiller.DependencyTableFiller;
import at.silverstrike.pcc.api.dependencytablefiller.DependencyTableFillerFactory;
import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.pcc.PccFunctionalBlock;

class DefaultDependenciesEditingPanel extends Panel implements
        DependenciesEditingPanel, ClickListener {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultDependenciesEditingPanel.class);

    private transient Injector injector;
    private transient PccDebugIdRegistry debugIdRegistry;
    private transient DependencyTableFiller dependencyTableFiller;

    private SchedulingObject schedulingObject;
    private Table dependenciesTable;

    @Override
    public void setInjector(final Injector aInjector) {
        if (aInjector != null) {
            this.injector = aInjector;
            this.debugIdRegistry =
                    this.injector.getInstance(PccDebugIdRegistry.class);
            final DependencyTableFillerFactory factory =
                    this.injector
                            .getInstance(DependencyTableFillerFactory.class);
            this.dependencyTableFiller = factory.create();
        }
    }

    @Override
    public void initGui() {
        final Label dependLabel =
                new Label(
                        TM.get("dependencieseditingpanel.1-label-dependencies"));
        this.addComponent(dependLabel);

        dependenciesTable = new Table();

        dependenciesTable.setWidth("80%");
        dependenciesTable.setHeight("170px");
        dependenciesTable.addContainerProperty(
                TM.get("dependencieseditingpanel.3-table-no"), Long.class,
                null);
        dependenciesTable.addContainerProperty(
                TM.get("dependencieseditingpanel.4-table-project"),
                String.class, null);
        dependenciesTable.addContainerProperty(
                TM.get("dependencieseditingpanel.5-table-name"), String.class,
                null);
        dependenciesTable.setSelectable(true);
        dependenciesTable.setMultiSelect(false);
        dependenciesTable.setImmediate(true);

        final Button dependEditButton = createDependEditButton();
        this.addComponent(dependEditButton);

        this.addComponent(dependenciesTable);

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

    @Override
    public void buttonClick(final ClickEvent aEvent) {
        letUserEnterDependencies();
    }
    
    private void letUserEnterDependencies() {
        final DependenciesEditingDialogControllerFactory factory =
                this.injector
                        .getInstance(DependenciesEditingDialogControllerFactory.class);
        final DependenciesEditingDialogController controller = factory.create();

        controller.setInjector(this.injector);
        controller.setParentWindow(TPTApplication.getCurrentApplication()
                .getMainWindow());
        controller.setSchedulingObject(this.schedulingObject);
        try {
            controller.run();
        } catch (final PccException exception) {
            LOGGER.error("", exception);
        }
    }

    @Override
    public Panel toPanel() {
        return this;
    }

    @Override
    public void setData(final SchedulingObject aSchedulingObject) {
        schedulingObject = aSchedulingObject;
        this.dependenciesTable.removeAllItems();
        if (aSchedulingObject != null) {
            this.dependencyTableFiller.setTable(this.dependenciesTable);
            this.dependencyTableFiller.setDependencies(aSchedulingObject
                    .getPredecessors());
            try {
                this.dependencyTableFiller.run();
            } catch (final PccException exception) {
                LOGGER.error("", exception);
            }
        }
    }
}
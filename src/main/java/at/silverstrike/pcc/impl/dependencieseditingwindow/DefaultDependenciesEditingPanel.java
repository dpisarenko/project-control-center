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

package at.silverstrike.pcc.impl.dependencieseditingwindow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Injector;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window;

import eu.livotov.tpt.i18n.TM;
import eu.livotov.tpt.TPTApplication;

import at.silverstrike.pcc.api.dependencieseditingwindow.DependenciesEditingPanel;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.processpanel.ProcessPanelListener;
import at.silverstrike.pcc.api.testtablecreator.TestTableCreator;
import at.silverstrike.pcc.impl.centraleditingpanel.TempTableObjectModel;

class DefaultDependenciesEditingPanel extends Panel implements
        DependenciesEditingPanel, ProcessPanelListener, ClickListener {

    private static final long serialVersionUID = 1L;
    private static final String NOTIFICATION = "Smth happend";

    private transient Persistence persistence = null;

    public static final Object PROJECT_PROPERTY_NAME = "name";
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultDependenciesEditingPanel.class);

    private static final String[] min = new String[] { "15 min", "30 min",
            "45 min" };

    @Override
    public void setInjector(Injector aInjector) {
        if (aInjector != null) {
            this.persistence = aInjector.getInstance(Persistence.class);
        }
    }

    Window subwindow;

    @Override
    public void initGui() {

        // Create the window...
        subwindow =
                new Window(
                        TM.get("dependenciescentraleditingprocesspanel.1-window"));
        // ...and make it modal
        subwindow.setModal(true);

        if (subwindow.getParent() != null) {
            // window is already showing
            getWindow()
                    .showNotification(
                            TM.get("dependenciescentraleditingprocesspanel.2-window-notification"));
        } else {
            // Open the subwindow by adding it to the parent
            // window
            // LOGGER.error("FINDSUB", subwindow);
            LOGGER.debug("FINDSUB 1, getWindow()=" + getWindow());
            LOGGER.debug("FINDSUB 2, subwindow=" + subwindow);
            TPTApplication.getCurrentApplication().getMainWindow()
                    .addWindow(subwindow);
        }

        final VerticalLayout verticalLayout =
                (VerticalLayout) subwindow.getContent();
        verticalLayout.setWidth("400px");

        final Label taskLabel =
                new Label(
                        TM.get("dependenciescentraleditingprocesspanel.3-label-tasks"));
        taskLabel.setContentMode(Label.CONTENT_TEXT);
        verticalLayout.addComponent(taskLabel);

        final HorizontalLayout tableAndButtonLayout = new HorizontalLayout();
        tableAndButtonLayout.setSpacing(true);

        TestTableCreator creator =
                this.injector.getInstance(TestTableCreator.class);
        final Table table = creator.getTable();
        tableAndButtonLayout.addComponent(table);

        final Button deleteButton =
                new Button(
                        TM.get("dependenciescentraleditingprocesspanel.4-button-delete"));
        deleteButton.addListener(this); // react to clicks
        tableAndButtonLayout.addComponent(deleteButton);

        verticalLayout.addComponent(tableAndButtonLayout);

        final HorizontalLayout comboAndButtonLayout = new HorizontalLayout();
        comboAndButtonLayout.setSpacing(true);

        ComboBox from = new ComboBox();
        for (int i = 0; i < min.length - 1; i++) {
            from.addItem(min[i]);
        }
        comboAndButtonLayout.addComponent(from);

        final Button addButton =
                new Button(
                        TM.get("dependenciescentraleditingprocesspanel.5-button-add"));
        addButton.addListener(this); // react to clicks
        comboAndButtonLayout.addComponent(addButton);

        verticalLayout.addComponent(comboAndButtonLayout);

    }

    /*
     * Shows a notification when a button is clicked.
     */
    public void buttonClick(final ClickEvent aEvent) {
        TPTApplication.getCurrentApplication().getMainWindow()
                .showNotification(NOTIFICATION);
    }

    @Override
    public void taskAdded() {
        // TODO Auto-generated method stub

    }

}
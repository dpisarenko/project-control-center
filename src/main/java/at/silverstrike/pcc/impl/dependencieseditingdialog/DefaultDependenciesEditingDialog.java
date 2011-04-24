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

package at.silverstrike.pcc.impl.dependencieseditingdialog;

import java.util.List;
import java.util.Set;

import ru.altruix.commons.api.gui.ModalDialogResult;

import com.google.inject.Injector;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Window;

import eu.livotov.tpt.i18n.TM;

import at.silverstrike.pcc.api.dependencieseditingdialog.DependenciesEditingDialog;
import at.silverstrike.pcc.api.model.SchedulingObject;

/**
 * @author DP118M
 * 
 */
final class DefaultDependenciesEditingDialog implements
        DependenciesEditingDialog, ClickListener {
    private static final long serialVersionUID = 1L;
    private static final String OK_BUTTON = "047.001";
    private static final String CANCEL_BUTTON = "047.002";
    private static final String ADD_DEPENDENCY_BUTTON = "047.003";
    private static final String DELETE_BUTTON = "047.004";

    private Set<SchedulingObject> existingDependencies;
    private List<SchedulingObject> availableDependencies;
    private Window parentWindow;
    private ModalDialogResult dialogResult;
    private Set<SchedulingObject> selectedDependencies;
    private Window dialog;
    private Injector injector;

    public ModalDialogResult getDialogResult() {
        return dialogResult;
    }

    public Set<SchedulingObject> getSelectedDependencies() {
        return selectedDependencies;
    }

    public void setExistingDependencies(
            Set<SchedulingObject> existingDependencies) {
        this.existingDependencies = existingDependencies;
    }

    public void setParentWindow(Window parentWindow) {
        this.parentWindow = parentWindow;
    }

    public void
            setAvailableDependencies(
                    List<SchedulingObject> availableDependencies) {
        this.availableDependencies = availableDependencies;
    }

    @Override
    public void initGui() {
        this.dialogResult = ModalDialogResult.UNDEFINED;

        this.dialog = new Window(TM.get("dependencieseditingdialog.1-title"));
        this.dialog.setModal(true);

        final Label message =
                new Label(TM.get("dependencieseditingdialog.4-top-lettering"));
        this.dialog.getContent().addComponent(message);

        final HorizontalLayout tableLayout = new HorizontalLayout();
        final Table dependenciesTable = new Table();

        dependenciesTable.setWidth("80%");
        dependenciesTable.setHeight("170px");

        
        dependenciesTable.addContainerProperty(TM.get("dependencieseditingdialog.7-table-no"), Long.class, null);
        dependenciesTable.addContainerProperty(TM.get("dependencieseditingdialog.8-table-project"), String.class, null);
        dependenciesTable.addContainerProperty(TM.get("dependencieseditingdialog.9-table-name"), String.class, null);
        
        
        
        final Button deleteButton =
                new Button(TM.get("dependencieseditingdialog.6-deleteButton"));
        deleteButton.setDebugId(DELETE_BUTTON);

        final HorizontalLayout addDependencyPanel = getAddDependencyPanel();
        this.dialog.getContent().addComponent(addDependencyPanel);

        final HorizontalLayout buttonPanel = getButtonPanel();
        this.dialog.getContent().addComponent(buttonPanel);
    }

    private HorizontalLayout getAddDependencyPanel() {
        final HorizontalLayout addDependencyPanel = new HorizontalLayout();
        final ComboBox newDependencyComboBox = new ComboBox();
        final Button addDependencyButton =
                new Button(
                        TM.get("dependencieseditingdialog.5-addDependencyButton"));
        addDependencyButton.setDebugId(ADD_DEPENDENCY_BUTTON);

        addDependencyPanel.addComponent(newDependencyComboBox);
        addDependencyPanel.addComponent(addDependencyButton);
        return addDependencyPanel;
    }

    private HorizontalLayout getButtonPanel() {
        final HorizontalLayout buttonPanel = new HorizontalLayout();

        final Button okButton =
                new Button(TM.get("dependencieseditingdialog.2-ok-button"),
                        this);
        okButton.setDebugId(OK_BUTTON);
        buttonPanel.addComponent(okButton);

        final Button cancelButton =
                new Button(TM.get("dependencieseditingdialog.3-cancel-button"),
                        this);
        cancelButton.setDebugId(CANCEL_BUTTON);
        buttonPanel.addComponent(cancelButton);

        buttonPanel.addComponent(okButton);
        buttonPanel.addComponent(cancelButton);
        return buttonPanel;
    }

    @Override
    public Window toWindow() {
        return this.dialog;
    }

    @Override
    public void buttonClick(final ClickEvent aEvent) {
        final String debugId = aEvent.getButton().getDebugId();

        if (OK_BUTTON.equals(debugId)) {
            this.dialogResult = ModalDialogResult.CLOSED_WITH_OK;
            closeDialog();
        } else if (CANCEL_BUTTON.equals(debugId)) {
            this.dialogResult = ModalDialogResult.CLOSED_WITH_CANCEL;
            closeDialog();
        } else if (ADD_DEPENDENCY_BUTTON.equals(debugId)) {

        } else if (DELETE_BUTTON.equals(debugId)) {

        }
    }

    private void closeDialog() {
        this.parentWindow.removeWindow(this.dialog);
    }

    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;
    }
}

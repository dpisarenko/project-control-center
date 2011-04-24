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
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

import eu.livotov.tpt.i18n.TM;

import at.silverstrike.pcc.api.debugids.PccDebugIdRegistry;
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

    private Set<SchedulingObject> existingDependencies;
    private List<SchedulingObject> availableDependencies;
    private Window parentWindow;
    private ModalDialogResult dialogResult;
    private Set<SchedulingObject> selectedDependencies;
    private Window dialog;
    private Injector injector;
    private transient PccDebugIdRegistry debugIdRegistry;

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
        this.debugIdRegistry = this.injector.getInstance(PccDebugIdRegistry.class);

        this.dialogResult = ModalDialogResult.UNDEFINED;

        this.dialog = new Window(TM.get("dependencieseditingdialog.1-title"));
        this.dialog.setModal(true);

        final Label message = new Label("This is a modal subwindow.");
        this.dialog.getContent().addComponent(message);

        final Button okButton =
                new Button(TM.get("dependencieseditingdialog.2-ok-button"),
                        this);
        okButton.setDebugId(OK_BUTTON);
        this.dialog.getContent().addComponent(okButton);
        
        final Button cancelButton = new Button(TM.get("dependencieseditingdialog.3-cancel-button"), this);
        cancelButton.setDebugId(CANCEL_BUTTON);
        this.dialog.getContent().addComponent(cancelButton);
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

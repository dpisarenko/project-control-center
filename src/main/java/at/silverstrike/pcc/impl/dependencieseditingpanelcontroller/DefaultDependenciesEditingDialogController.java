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

package at.silverstrike.pcc.impl.dependencieseditingpanelcontroller;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.altruix.commons.api.di.PccException;
import ru.altruix.commons.api.gui.ModalDialogResult;

import com.google.inject.Injector;
import com.vaadin.ui.Window;

import at.silverstrike.pcc.api.dependencieseditingdialog.DependenciesEditingDialog;
import at.silverstrike.pcc.api.dependencieseditingdialog.DependenciesEditingDialogFactory;
import at.silverstrike.pcc.api.dependencieseditingdialogcontroller.DependenciesEditingDialogController;
import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.persistence.Persistence;

/**
 * @author DP118M
 * 
 */
final class DefaultDependenciesEditingDialogController implements
        DependenciesEditingDialogController {
    private SchedulingObject schedulingObject;
    private Injector injector;
    private Window parentWindow;

    @Override
    public void run() throws PccException {
        if (this.schedulingObject == null) {
            return;
        }
        
        final DependenciesEditingDialogFactory factory =
                this.injector
                        .getInstance(DependenciesEditingDialogFactory.class);

        final DependenciesEditingDialog dialog = factory.create();

        dialog.setExistingDependencies(this.schedulingObject.getPredecessors());
        dialog.setAvailableDependencies(getAvailableDependencies());
        dialog.setParentWindow(this.parentWindow);
        dialog.setInjector(this.injector);
        dialog.setSchedulingObject(this.schedulingObject);

        dialog.initGui();
        
        this.parentWindow.addWindow(dialog.toWindow());
    }

    private List<SchedulingObject> getAvailableDependencies() {
        final Persistence persistence =
                this.injector.getInstance(Persistence.class);

        return persistence.getPotentialDependencies(this.schedulingObject);
    }

    @Override
    public void setSchedulingObject(final SchedulingObject aObject) {
        this.schedulingObject = aObject;
    }

    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;
    }

    @Override
    public void setParentWindow(final Window aWindow) {
        this.parentWindow = aWindow;
    }
}

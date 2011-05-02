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

package at.silverstrike.pcc.impl.dependencieseditingdialogcontroller;

import at.silverstrike.pcc.api.dependencieseditingdialogcontroller.DependenciesEditingDialogController;
import at.silverstrike.pcc.api.dependencieseditingdialogcontroller.DependenciesEditingDialogControllerFactory;

/**
 * @author DP118M
 * 
 */
public final class DefaultDependenciesEditingDialogControllerFactory implements
        DependenciesEditingDialogControllerFactory {

    @Override
    public DependenciesEditingDialogController create() {
        return new DefaultDependenciesEditingDialogController();
    }

}

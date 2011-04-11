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

import at.silverstrike.pcc.api.dependencieseditingdialog.DependenciesEditingDialog;
import at.silverstrike.pcc.api.dependencieseditingdialog.DependenciesEditingDialogFactory;

/**
 * @author DP118M
 * 
 */
public final class DefaultDependenciesEditingDialogFactory implements
        DependenciesEditingDialogFactory {
    @Override
    public DependenciesEditingDialog create() {
        return new DefaultDependenciesEditingDialog();
    }

}

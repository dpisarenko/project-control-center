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

package at.silverstrike.pcc.impl.dependencytablefiller;

import java.util.Set;

import ru.altruix.commons.api.di.PccException;

import com.vaadin.ui.Table;

import at.silverstrike.pcc.api.dependencytablefiller.DependencyTableFiller;
import at.silverstrike.pcc.api.model.SchedulingObject;

/**
 * @author DP118M
 *
 */
class DefaultDependencyTableFiller implements DependencyTableFiller {
    private Table table;
    private Set<SchedulingObject> dependencies;
    
    @Override
    public void setTable(final Table aTable) {
        this.table = aTable;
    }

    @Override
    public void setDependencies(final Set<SchedulingObject> aDependencies) {
        this.dependencies = aDependencies;
    }

    @Override
    public void run() throws PccException {
        if (this.dependencies == null) {
            return;
        }
        for (final SchedulingObject curExistingDependency : this.dependencies) {
            addDependencyItem(this.table, curExistingDependency);
        }
    }

    private void addDependencyItem(final Table aTable,
            final SchedulingObject aCurExistingDependency) {
        final String projectName;

        if (aCurExistingDependency.getParent() != null) {
            projectName = aCurExistingDependency.getParent().getName();
        } else {
            projectName = "";
        }

        aTable.addItem(
                new Object[] { aCurExistingDependency.getId(), projectName,
                        aCurExistingDependency.getName() },
                aCurExistingDependency);
    }
}

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

package at.silverstrike.pcc.api.dependencytablefiller;

import java.util.Set;

import at.silverstrike.pcc.api.model.SchedulingObject;

import com.vaadin.ui.Table;

import ru.altruix.commons.api.conventions.SingleActivityModule;

/**
 * @author DP118M
 *
 */
public interface DependencyTableFiller extends SingleActivityModule {
    void setTable(final Table aTable);
    void setDependencies(final Set<SchedulingObject> aDependencies);

}

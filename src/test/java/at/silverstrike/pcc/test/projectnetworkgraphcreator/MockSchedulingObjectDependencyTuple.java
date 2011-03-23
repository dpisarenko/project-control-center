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

package at.silverstrike.pcc.test.projectnetworkgraphcreator;

import java.util.List;

import at.silverstrike.pcc.api.projectnetworkgraphcreator.SchedulingObjectDependencyTuple;

/**
 * @author DP118M
 * 
 */
final class MockSchedulingObjectDependencyTuple implements
        SchedulingObjectDependencyTuple {
    private String label;
    private List<String> dependencies;

    public String getLabel() {
        return label;
    }

    public void setLabel(final String aLabel) {
        this.label = aLabel;
    }

    public List<String> getDependencies() {
        return dependencies;
    }

    public void setDependencies(final List<String> aDependencies) {
        this.dependencies = aDependencies;
    }

}

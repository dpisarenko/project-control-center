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

package at.silverstrike.pcc.impl.persistence;

import java.util.Set;

import at.silverstrike.pcc.api.model.SchedulingObject;

/**
 * @author DP118M
 * 
 */
class DefaultSchedulingObject implements SchedulingObject {
    private String label;
    private Integer priority;
    private SchedulingObject parent;
    private Long id;
    private String name;
    private Set<SchedulingObject> predecessors;

    public String getLabel() {
        return label;
    }

    public void setLabel(final String aLabel) {
        this.label = aLabel;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(final Integer aPriority) {
        this.priority = aPriority;
    }

    public SchedulingObject getParent() {
        return parent;
    }

    public void setParent(final SchedulingObject aParent) {
        this.parent = aParent;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long aId) {
        this.id = aId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String aName) {
        this.name = aName;
    }

    public Set<SchedulingObject> getPredecessors() {
        return predecessors;
    }

    public void setPredecessors(final Set<SchedulingObject> aPredecessors) {
        this.predecessors = aPredecessors;
    }
}

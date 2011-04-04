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

import at.silverstrike.pcc.api.model.Milestone;
import at.silverstrike.pcc.api.model.SchedulingObject;

/**
 * @author DP118M
 *
 */
class DefaultMilestone implements Milestone {
    private Long id;
    private String name;
    private SchedulingObject parent;
    private Set<SchedulingObject> predecessors;
    private String label;
    private Integer priority;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public SchedulingObject getParent() {
        return parent;
    }
    public void setParent(SchedulingObject parent) {
        this.parent = parent;
    }
    public Set<SchedulingObject> getPredecessors() {
        return predecessors;
    }
    public void setPredecessors(Set<SchedulingObject> predecessors) {
        this.predecessors = predecessors;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public Integer getPriority() {
        return priority;
    }
    public void setPriority(Integer priority) {
        this.priority = priority;
    }    
}

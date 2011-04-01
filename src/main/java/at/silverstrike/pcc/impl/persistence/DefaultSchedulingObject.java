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
    
    public String getLabel() {
        return label;
    }
    public void setLabel(final String label) {
        this.label = label;
    }
    public Integer getPriority() {
        return priority;
    }
    public void setPriority(final Integer priority) {
        this.priority = priority;
    }
    public SchedulingObject getParent() {
        return parent;
    }
    public void setParent(final SchedulingObject parent) {
        this.parent = parent;
    }
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
}

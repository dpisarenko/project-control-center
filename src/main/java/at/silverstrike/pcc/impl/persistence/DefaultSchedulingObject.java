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

import at.silverstrike.pcc.api.model.ProcessState;
import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.model.SchedulingObjectValidationError;
import at.silverstrike.pcc.api.model.UserData;

/**
 * @author DP118M
 * 
 */
class DefaultSchedulingObject implements SchedulingObject {
    private Long id;
    private String name;
    private Integer priority;
    private SchedulingObject parent;
    private Set<SchedulingObject> predecessors;
    private ProcessState state;
    private SchedulingObjectValidationError validationError;
    private UserData user;

    public DefaultSchedulingObject() {
        this.id = -1L;
        this.setName("");
        this.state = ProcessState.REPORTED;
    }

    public String getName() {
        return name;
    }

    public void setName(final String aName) {
        this.name = aName;
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

    public Set<SchedulingObject> getPredecessors() {
        return predecessors;
    }

    public void setPredecessors(final Set<SchedulingObject> aPredecessors) {
        this.predecessors = aPredecessors;
    }

    public void setLabel(final String aLabel) {
    }

    public String getLabel() {
        if (this.id != null) {
            return this.id.toString();
        } else {
            return "";
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long aId) {
        this.id = aId;
    }

    public ProcessState getState() {
        return state;
    }

    public void setState(final ProcessState aState) {
        this.state = aState;
    }

    public SchedulingObjectValidationError getValidationError() {
        return validationError;
    }

    public void
            setValidationError(final SchedulingObjectValidationError aError) {
        this.validationError = aError;
    }

    public UserData getUser() {
        return user;
    }

    public void setUser(final UserData aUser) {
        this.user = aUser;
    }
}

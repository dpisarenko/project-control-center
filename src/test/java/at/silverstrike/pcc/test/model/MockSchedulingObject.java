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

package at.silverstrike.pcc.test.model;

import java.util.Set;

import at.silverstrike.pcc.api.model.ProcessState;
import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.model.SchedulingObjectValidationError;
import at.silverstrike.pcc.api.model.UserData;

/**
 * @author DP118M
 * 
 */
class MockSchedulingObject {
    private Long id;
    private String name;
    private ProcessState state;
    private Integer priority;
    private SchedulingObject parentProcess;
    private Set<SchedulingObject> predecessors;
    private SchedulingObjectValidationError validationError;
    private UserData user;

    public UserData getUserData() {
        return user;
    }

    public void setUserData(UserData user) {
        this.user = user;
    }

    public Long getId() {
        return this.id;
    }

    protected void setId(final Long aId) {
        this.id = aId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String aName) {
        this.name = aName;
    }

    public ProcessState getState() {
        return this.state;
    }

    public void setState(final ProcessState aState) {
        this.state = aState;
    }

    public void setPriority(final Integer aPriority) {
        this.priority = aPriority;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public SchedulingObject getParent() {
        return this.parentProcess;
    }

    public void setParent(final SchedulingObject aParentProcess) {
        this.parentProcess = aParentProcess;
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

    public SchedulingObjectValidationError getValidationError() {
        return validationError;
    }

    public void setValidationError(
            SchedulingObjectValidationError validationError) {
        this.validationError = validationError;
    }

}

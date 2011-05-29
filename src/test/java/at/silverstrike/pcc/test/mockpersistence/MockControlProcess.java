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
package at.silverstrike.pcc.test.mockpersistence;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import at.silverstrike.pcc.api.model.SchedulingObjectValidationError;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.model.ProcessState;
import at.silverstrike.pcc.api.model.ResourceAllocation;
import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.model.UserData;

class MockControlProcess implements Task {
    private Date averageEstimatedEndDateTime;
    private Double bestCaseEffort;

    private Date bestEstimatedEndDateTime;

    private Long id;

    private String name;

    private SchedulingObject parent;

    private Set<SchedulingObject> predecessors;

    private Integer priority;

    private List<ResourceAllocation> resourceAllocations;

    private ProcessState state;

    private Double worstCaseEffort;

    private Date worstEstimatedEndDateTime;

    private SchedulingObjectValidationError validationError;

    private UserData user;

    public MockControlProcess() {
        this.resourceAllocations = new LinkedList<ResourceAllocation>();
    }

    public UserData getUserData() {
        return user;
    }

    public void setUserData(UserData user) {
        this.user = user;
    }

    @Override
    public double getAverageCaseEffort() {
        if ((this.bestCaseEffort != null) && (this.worstCaseEffort == null)) {
            return this.bestCaseEffort;
        } else if ((this.bestCaseEffort == null)
                && (this.worstCaseEffort != null)) {
            return this.worstCaseEffort;
        } else if ((this.bestCaseEffort == null)
                && (this.worstCaseEffort == null)) {
            return 0.;
        } else {
            return (this.bestCaseEffort + this.worstCaseEffort) / 2;
        }
    }

    public Date getAverageEstimatedEndDateTime() {
        return averageEstimatedEndDateTime;
    }

    public Double getBestCaseEffort() {
        return bestCaseEffort;
    }

    public Date getBestEstimatedEndDateTime() {
        return bestEstimatedEndDateTime;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public SchedulingObject getParent() {
        return parent;
    }

    public Set<SchedulingObject> getPredecessors() {
        return predecessors;
    }

    public Integer getPriority() {
        return priority;
    }

    public List<ResourceAllocation> getResourceAllocations() {
        return resourceAllocations;
    }

    public ProcessState getState() {
        return state;
    }

    public Double getWorstCaseEffort() {
        return worstCaseEffort;
    }

    public Date getWorstEstimatedEndDateTime() {
        return worstEstimatedEndDateTime;
    }

    public void setAverageEstimatedEndDateTime(
            final Date aAverageEstimatedEndDateTime) {
        this.averageEstimatedEndDateTime = aAverageEstimatedEndDateTime;
    }

    public void setBestCaseEffort(final Double aEffort) {
        this.bestCaseEffort = aEffort;
    }

    public void
            setBestEstimatedEndDateTime(final Date aDateTime) {
        this.bestEstimatedEndDateTime = aDateTime;
    }

    public void setId(final Long aId) {
        this.id = aId;
    }

    public void setName(final String aName) {
        this.name = aName;
    }

    public void setParent(final SchedulingObject aParent) {
        this.parent = aParent;
    }

    public void setPredecessors(final Set<SchedulingObject> aPredecessors) {
        this.predecessors = aPredecessors;
    }

    public void setPriority(final Integer aPriority) {
        this.priority = aPriority;
    }

    public void setResourceAllocations(
            final List<ResourceAllocation> aResourceAllocations) {
        this.resourceAllocations = aResourceAllocations;
    }

    public void setState(final ProcessState aState) {
        this.state = aState;
    }

    public void setWorstCaseEffort(final Double aEffort) {
        this.worstCaseEffort = aEffort;
    }

    public void setWorstEstimatedEndDateTime(
            final Date aDateTime) {
        this.worstEstimatedEndDateTime = aDateTime;
    }

    @Override
    public void setLabel(final String aLabel) {
    }

    @Override
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

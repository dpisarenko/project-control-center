/**
 * This file is part of Project Control Center (PCC).
 * 
 * PCC (Project Control Center) project is intellectual property of 
 * Dmitri Anatol'evich Pisarenko.
 * 
 * Copyright 2010 Dmitri Anatol'evich Pisarenko
 * All rights reserved
 *
 **/

package at.silverstrike.pcc.impl.persistence;

import java.util.Date;
import java.util.List;
import java.util.Set;

import at.silverstrike.pcc.api.model.ControlProcess;
import at.silverstrike.pcc.api.model.ProcessState;
import at.silverstrike.pcc.api.model.ProcessType;
import at.silverstrike.pcc.api.model.ResourceAllocation;

/**
 * @author Dmitri Pisarenko
 * 
 */
class DefaultControlProcess implements ControlProcess {
    private Long id;
    private String name;
    private ProcessState state;
    private Double bestCaseEffort;
    private Double worstCaseEffort;
    private Integer priority;
    private ProcessType processType;
    private ControlProcess parent;
    private List<ResourceAllocation> resourceAllocations;
    private Date averageEstimatedEndDateTime;
    private Date bestEstimatedEndDateTime;
    private Date worstEstimatedEndDateTime;

    private Set<ControlProcess> predecessors;

    public DefaultControlProcess() {
        this.id = -1L;
        this.setName("");
        this.state = ProcessState.REPORTED;
        this.processType = ProcessType.GOAL;
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

    public ProcessState getState() {
        return state;
    }

    public void setState(final ProcessState aState) {
        this.state = aState;
    }

    public Double getBestCaseEffort() {
        return bestCaseEffort;
    }

    public void setBestCaseEffort(final Double aBestCaseEffort) {
        this.bestCaseEffort = aBestCaseEffort;
    }

    public Double getWorstCaseEffort() {
        return worstCaseEffort;
    }

    public void setWorstCaseEffort(final Double aWorstCaseEffort) {
        this.worstCaseEffort = aWorstCaseEffort;
    }

    @Override
    public double getAverageCaseEffort() {
        return (this.bestCaseEffort + this.worstCaseEffort) / 2.0;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(final Integer aPriority) {
        this.priority = aPriority;
    }

    public List<ResourceAllocation> getResourceAllocations() {
        return resourceAllocations;
    }

    public void setResourceAllocations(
            final List<ResourceAllocation> aResourceAllocations) {
        this.resourceAllocations = aResourceAllocations;
    }

    public ProcessType getProcessType() {
        return processType;
    }

    public void setProcessType(final ProcessType aProcessResultType) {
        this.processType = aProcessResultType;
    }

    public ControlProcess getParent() {
        return parent;
    }

    public void setParent(final ControlProcess aParent) {
        this.parent = aParent;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public Date getAverageEstimatedEndDateTime() {
        return averageEstimatedEndDateTime;
    }

    public void setAverageEstimatedEndDateTime(
            final Date aAverageEstimatedEndDateTime) {
        this.averageEstimatedEndDateTime = aAverageEstimatedEndDateTime;
    }

    public Date getBestEstimatedEndDateTime() {
        return bestEstimatedEndDateTime;
    }

    public void
            setBestEstimatedEndDateTime(final Date aBestEstimatedEndDateTime) {
        this.bestEstimatedEndDateTime = aBestEstimatedEndDateTime;
    }

    public Date getWorstEstimatedEndDateTime() {
        return worstEstimatedEndDateTime;
    }

    public void setWorstEstimatedEndDateTime(
            final Date aWorstEstimatedEndDateTime) {
        this.worstEstimatedEndDateTime = aWorstEstimatedEndDateTime;
    }

    public Set<ControlProcess> getPredecessors() {
        return predecessors;
    }

    public void setPredecessors(final Set<ControlProcess> aPredecessors) {
        this.predecessors = aPredecessors;
    }
}

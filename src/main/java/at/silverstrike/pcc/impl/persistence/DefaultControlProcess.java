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

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public ProcessState getState() {
        return state;
    }

    public void setState(final ProcessState state) {
        this.state = state;
    }

    public Double getBestCaseEffort() {
        return bestCaseEffort;
    }

    public void setBestCaseEffort(final Double bestCaseEffort) {
        this.bestCaseEffort = bestCaseEffort;
    }

    public Double getWorstCaseEffort() {
        return worstCaseEffort;
    }

    public void setWorstCaseEffort(final Double worstCaseEffort) {
        this.worstCaseEffort = worstCaseEffort;
    }

    @Override
    public double getAverageCaseEffort() {
        return (this.bestCaseEffort + this.worstCaseEffort) / 2.0;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(final Integer priority) {
        this.priority = priority;
    }

    public List<ResourceAllocation> getResourceAllocations() {
        return resourceAllocations;
    }

    public void setResourceAllocations(
            final List<ResourceAllocation> resourceAllocations) {
        this.resourceAllocations = resourceAllocations;
    }

    public ProcessType getProcessType() {
        return processType;
    }

    public void setProcessType(final ProcessType processResultType) {
        this.processType = processResultType;
    }

    public ControlProcess getParent() {
        return parent;
    }

    public void setParent(final ControlProcess parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public Date getAverageEstimatedEndDateTime() {
        return averageEstimatedEndDateTime;
    }

    public void setAverageEstimatedEndDateTime(
            final Date averageEstimatedEndDateTime) {
        this.averageEstimatedEndDateTime = averageEstimatedEndDateTime;
    }

    public Date getBestEstimatedEndDateTime() {
        return bestEstimatedEndDateTime;
    }

    public void setBestEstimatedEndDateTime(final Date bestEstimatedEndDateTime) {
        this.bestEstimatedEndDateTime = bestEstimatedEndDateTime;
    }

    public Date getWorstEstimatedEndDateTime() {
        return worstEstimatedEndDateTime;
    }

    public void setWorstEstimatedEndDateTime(
            final Date worstEstimatedEndDateTime) {
        this.worstEstimatedEndDateTime = worstEstimatedEndDateTime;
    }

    public Set<ControlProcess> getPredecessors() {
        return predecessors;
    }

    public void setPredecessors(final Set<ControlProcess> predecessors) {
        this.predecessors = predecessors;
    }

}

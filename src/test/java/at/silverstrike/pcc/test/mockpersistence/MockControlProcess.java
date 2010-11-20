/**
 * This file is part of Project Control Center (PCC).
 * 
 * Project Control Center (PCC) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Project Control Center (PCC) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Project Control Center (PCC).  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2010 Dmitri Anatol'evich Pisarenko
 **/

package at.silverstrike.pcc.test.mockpersistence;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import at.silverstrike.pcc.api.model.ControlProcess;
import at.silverstrike.pcc.api.model.ProcessState;
import at.silverstrike.pcc.api.model.ProcessType;
import at.silverstrike.pcc.api.model.ResourceAllocation;

class MockControlProcess implements ControlProcess {
    private Date averageEstimatedEndDateTime;
    private Double bestCaseEffort;

    private Date bestEstimatedEndDateTime;

    private Long id;

    private String name;

    private ControlProcess parent;

    private Set<ControlProcess> predecessors;

    private Integer priority;

    private ProcessType processType;

    private List<ResourceAllocation> resourceAllocations;

    private ProcessState state;

    private Double worstCaseEffort;

    private Date worstEstimatedEndDateTime;

    public MockControlProcess()
    {
        this.resourceAllocations = new LinkedList<ResourceAllocation>();
    }
    
    @Override
    public double getAverageCaseEffort() {
        return (this.getBestCaseEffort() + this.getWorstCaseEffort()) / 2;
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

    public ControlProcess getParent() {
        return parent;
    }

    public Set<ControlProcess> getPredecessors() {
        return predecessors;
    }

    public Integer getPriority() {
        return priority;
    }

    public ProcessType getProcessType() {
        return processType;
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
            final Date averageEstimatedEndDateTime) {
        this.averageEstimatedEndDateTime = averageEstimatedEndDateTime;
    }

    public void setBestCaseEffort(final Double bestCaseEffort) {
        this.bestCaseEffort = bestCaseEffort;
    }

    public void setBestEstimatedEndDateTime(final Date bestEstimatedEndDateTime) {
        this.bestEstimatedEndDateTime = bestEstimatedEndDateTime;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setParent(final ControlProcess parent) {
        this.parent = parent;
    }

    public void setPredecessors(final Set<ControlProcess> predecessors) {
        this.predecessors = predecessors;
    }

    public void setPriority(final Integer priority) {
        this.priority = priority;
    }

    public void setProcessType(final ProcessType processType) {
        this.processType = processType;
    }

    public void setResourceAllocations(
            final List<ResourceAllocation> resourceAllocations) {
        this.resourceAllocations = resourceAllocations;
    }

    public void setState(final ProcessState state) {
        this.state = state;
    }

    public void setWorstCaseEffort(final Double worstCaseEffort) {
        this.worstCaseEffort = worstCaseEffort;
    }

    public void setWorstEstimatedEndDateTime(
            final Date worstEstimatedEndDateTime) {
        this.worstEstimatedEndDateTime = worstEstimatedEndDateTime;
    }

}

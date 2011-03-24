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

	public MockControlProcess() {
		this.resourceAllocations = new LinkedList<ResourceAllocation>();
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

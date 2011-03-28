package at.silverstrike.pcc.test.model;

import java.util.Date;
import java.util.List;
import java.util.Set;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.model.ProcessState;
import at.silverstrike.pcc.api.model.ProcessType;
import at.silverstrike.pcc.api.model.ResourceAllocation;
import at.silverstrike.pcc.api.model.SchedulingObject;

class MockControlProcess implements Task {

    private Long id;
    private String name;
    private ProcessState state;
    private Double bestCaseEffort;
    private Double averageCaseEffort;
    private Double worstCaseEffort;
    private List<ResourceAllocation> resourceAllocations;
    private Integer priority;
    private Task parentProcess;
    private ProcessType type;
    private Date averageEstimatedEndDateTime;
    private Date bestEstimatedEndDateTime;
    private Date worstEstimatedEndDateTime;
    private Set<SchedulingObject> predecessors;

    public Long getId() {
        return this.id;
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

    public Double getBestCaseEffort() {
        return this.bestCaseEffort;
    }

    public void setBestCaseEffort(final Double aEffortInHours) {
        this.bestCaseEffort = aEffortInHours;
    }

    public double getAverageCaseEffort() {
        return this.averageCaseEffort;
    }

    public Double getWorstCaseEffort() {
        return this.worstCaseEffort;
    }

    public void setWorstCaseEffort(final Double aEffortInHours) {
        this.worstCaseEffort = aEffortInHours;

    }

    public void setResourceAllocations(
            final List<ResourceAllocation> aResourceAllocations) {
        this.resourceAllocations = aResourceAllocations;
    }

    public List<ResourceAllocation> getResourceAllocations() {
        return this.resourceAllocations;
    }

    public void setPriority(final Integer aPriority) {
        this.priority = aPriority;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public Task getParent() {
        return this.parentProcess;
    }

    public void setParent(final Task aParentProcess) {
        this.parentProcess = aParentProcess;
    }

    public void setProcessType(final ProcessType aType) {
        this.type = aType;
    }

    public ProcessType getProcessType() {
        return this.type;
    }

    public Date getAverageEstimatedEndDateTime() {
        return this.averageEstimatedEndDateTime;
    }

    public void setAverageEstimatedEndDateTime(final Date aDate) {
        this.averageEstimatedEndDateTime = aDate;
    }

    public Date getBestEstimatedEndDateTime() {
        return this.bestEstimatedEndDateTime;
    }

    public void setBestEstimatedEndDateTime(final Date aDate) {
        this.bestEstimatedEndDateTime = aDate;
    }

    public Date getWorstEstimatedEndDateTime() {
        return this.worstEstimatedEndDateTime;
    }

    public void setWorstEstimatedEndDateTime(final Date aDate) {
        this.worstEstimatedEndDateTime = aDate;
    }

    public Set<SchedulingObject> getPredecessors() {
        return predecessors;
    }

    public void setPredecessors(final Set<SchedulingObject> aPredecessors) {
        this.predecessors = aPredecessors;
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

}

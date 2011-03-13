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

package at.silverstrike.pcc.impl.projectgraph;

import at.silverstrike.pcc.api.projectgraph.ImaginaryGridLocation;
import at.silverstrike.pcc.api.projectgraph.ProjectNetworkVertex;
import at.silverstrike.pcc.api.projectgraph.ProjectNetworkVertexType;

/**
 * @author DP118M
 * 
 */
class DefaultProjectNetworkVertex implements ProjectNetworkVertex {
    private String label;
    private ProjectNetworkVertexType type;
    private long priority;
    private ImaginaryGridLocation imaginaryGridLocation;
    private Long taskId;
    private int rank;

    public DefaultProjectNetworkVertex() {
        this.imaginaryGridLocation = new DefaultImaginaryGridLocation();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(final String aLabel) {
        this.label = aLabel;
    }

    public ProjectNetworkVertexType getType() {
        return type;
    }

    public void setType(final ProjectNetworkVertexType aType) {
        this.type = aType;
    }

    public long getPriority() {
        return priority;
    }

    public void setPriority(final long aPriority) {
        this.priority = aPriority;
    }

    public ImaginaryGridLocation getImaginaryGridLocation() {
        return imaginaryGridLocation;
    }

    public void
            setImaginaryGridLocation(
                    final ImaginaryGridLocation aImaginaryGridLocation) {
        this.imaginaryGridLocation = aImaginaryGridLocation;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(final Long taskId) {
        this.taskId = taskId;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(final int aRank) {
        this.rank = aRank;
    }
}

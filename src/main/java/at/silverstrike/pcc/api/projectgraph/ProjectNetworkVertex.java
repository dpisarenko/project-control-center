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

package at.silverstrike.pcc.api.projectgraph;


/**
 * @author DP118M
 *
 */
public interface ProjectNetworkVertex {
    void setLabel(final String aLabel);
    String getLabel();
    
    void setType(final ProjectNetworkVertexType aType);
    ProjectNetworkVertexType getType();
    
    void setPriority(final long aPriority);
    long getPriority();
    
    void setImaginaryGridLocation(final ImaginaryGridLocation aLocation);
    ImaginaryGridLocation getImaginaryGridLocation();
    
    void setTaskId(final Long aId);
    Long getTaskId();
}

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

package at.silverstrike.pcc.impl.gcaltasks2pccimporter;

import java.util.List;

/**
 * @author DP118M
 *
 */
final class RelevantTaskInformation {
    private String googleId;
    private String label;
    private List<String> predecessorLabels;
    private at.silverstrike.pcc.api.model.Task pccTask;
    
    public String getGoogleId() {
        return googleId;
    }
    public void setGoogleId(final String aGoogleId) {
        this.googleId = aGoogleId;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(final String aLabel) {
        this.label = aLabel;
    }
    public List<String> getPredecessorLabels() {
        return predecessorLabels;
    }
    public void setPredecessorLabels(final List<String> aPredecessorLabels) {
        this.predecessorLabels = aPredecessorLabels;
    }
    public at.silverstrike.pcc.api.model.Task getPccTask() {
        return pccTask;
    }
    public void setPccTask(final at.silverstrike.pcc.api.model.Task aPccTask) {
        this.pccTask = aPccTask;
    }
    
}

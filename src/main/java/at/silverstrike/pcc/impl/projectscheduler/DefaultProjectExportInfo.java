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

package at.silverstrike.pcc.impl.projectscheduler;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import at.silverstrike.pcc.api.model.ControlProcess;
import at.silverstrike.pcc.api.model.Resource;
import at.silverstrike.pcc.api.projectscheduler.ProjectExportInfo;

/**
 * @author dp118m
 * 
 */
class DefaultProjectExportInfo implements ProjectExportInfo {
    private int schedulingHorizonMonths;
    private String projectName;
    private Date now;
    private String copyright;
    private String currency;
    private List<ControlProcess> controlProcessesToExport;
    private List<Resource> resourcesToExport;

    public int getSchedulingHorizonMonths() {
        return schedulingHorizonMonths;
    }

    public void setSchedulingHorizonMonths(final int schedulingHorizonMonths) {
        this.schedulingHorizonMonths = schedulingHorizonMonths;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(final String projectName) {
        this.projectName = projectName;
    }

    public Date getNow() {
        return now;
    }

    public void setNow(final Date now) {
        this.now = now;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(final String copyright) {
        this.copyright = copyright;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    public List<ControlProcess> getControlProcessesToExport() {
        return controlProcessesToExport;
    }

    public void setControlProcessesToExport(
            final List<ControlProcess> controlProcessesToExport) {
        this.controlProcessesToExport = controlProcessesToExport;
    }

    public List<Resource> getResourcesToExport() {
        return resourcesToExport;
    }

    public void setResourcesToExport(final List<Resource> resourcesToExport) {
        this.resourcesToExport = resourcesToExport;
    }

    @Override
    public String toString() {
        final ToStringBuilder builder = new ToStringBuilder(this);
        
        builder.append("schedulingHorizonMonths", this.schedulingHorizonMonths);
        builder.append("projectName", this.projectName);
        builder.append("now", this.now);
        builder.append("copyright", this.copyright);
        builder.append("currency", this.currency);
        builder.append("controlProcessesToExport", this.controlProcessesToExport);
        builder.append("resourcesToExport", this.resourcesToExport);

        return builder.toString();
    }
}

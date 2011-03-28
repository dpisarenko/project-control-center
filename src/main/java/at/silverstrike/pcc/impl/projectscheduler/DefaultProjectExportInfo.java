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

package at.silverstrike.pcc.impl.projectscheduler;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import at.silverstrike.pcc.api.model.Task;
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
    private List<Task> controlProcessesToExport;
    private List<Resource> resourcesToExport;

    public int getSchedulingHorizonMonths() {
        return schedulingHorizonMonths;
    }

    public void setSchedulingHorizonMonths(final int aSchedulingHorizonMonths) {
        this.schedulingHorizonMonths = aSchedulingHorizonMonths;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(final String aProjectName) {
        this.projectName = aProjectName;
    }

    public Date getNow() {
        return now;
    }

    public void setNow(final Date aNow) {
        this.now = aNow;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(final String aCopyright) {
        this.copyright = aCopyright;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(final String aCurrency) {
        this.currency = aCurrency;
    }

    public List<Task> getControlProcessesToExport() {
        return controlProcessesToExport;
    }

    public void setControlProcessesToExport(
            final List<Task> aControlProcessesToExport) {
        this.controlProcessesToExport = aControlProcessesToExport;
    }

    public List<Resource> getResourcesToExport() {
        return resourcesToExport;
    }

    public void setResourcesToExport(final List<Resource> aResourcesToExport) {
        this.resourcesToExport = aResourcesToExport;
    }

    @Override
    public String toString() {
        final ToStringBuilder builder = new ToStringBuilder(this);

        builder.append("schedulingHorizonMonths", this.schedulingHorizonMonths);
        builder.append("projectName", this.projectName);
        builder.append("now", this.now);
        builder.append("copyright", this.copyright);
        builder.append("currency", this.currency);
        builder.append("controlProcessesToExport",
                this.controlProcessesToExport);
        builder.append("resourcesToExport", this.resourcesToExport);

        return builder.toString();
    }
}

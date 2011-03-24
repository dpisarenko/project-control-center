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
import java.util.List;

import at.silverstrike.pcc.api.model.ControlProcess;
import at.silverstrike.pcc.api.model.Resource;
import at.silverstrike.pcc.api.projectscheduler.ProjectExportInfo;

/**
 * @author dp118m
 *
 */
public class MockProjectExportInfo implements ProjectExportInfo {
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
    public void setSchedulingHorizonMonths(int schedulingHorizonMonths) {
        this.schedulingHorizonMonths = schedulingHorizonMonths;
    }
    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public Date getNow() {
        return now;
    }
    public void setNow(Date now) {
        this.now = now;
    }
    public String getCopyright() {
        return copyright;
    }
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public List<ControlProcess> getControlProcessesToExport() {
        return controlProcessesToExport;
    }
    public void setControlProcessesToExport(
            List<ControlProcess> controlProcessesToExport) {
        this.controlProcessesToExport = controlProcessesToExport;
    }
    public List<Resource> getResourcesToExport() {
        return resourcesToExport;
    }
    public void setResourcesToExport(List<Resource> resourcesToExport) {
        this.resourcesToExport = resourcesToExport;
    }

}

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

package at.silverstrike.pcc.impl.projectscheduler;

import java.util.Date;
import java.util.List;

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

}

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

package at.silverstrike.pcc.api.projectscheduler;

import java.util.Date;
import java.util.List;

import at.silverstrike.pcc.api.model.ControlProcess;
import at.silverstrike.pcc.api.model.Resource;

public interface ProjectExportInfo {
    /**
     * Sets the control processes, which should be exported.
     */
    void setControlProcessesToExport(final List<ControlProcess> aProcesses);
    List<ControlProcess> getControlProcessesToExport();
    
    /**
     * Sets the resources, which should be exported.
     */
    void setResourcesToExport(final List<Resource> aResources);
    List<Resource> getResourcesToExport();
    
    /**
     * Set contents of the ${projectName} placeholder.
     */
    void setProjectName(final String aProjectName);
    String getProjectName();
    
    /**
     * Set contents of the ${now} placeholder.
     */
    void setNow(final Date aNow);
    Date getNow();
    
    /**
     * Set contents of the ${copyright} placeholder.
     */
    void setCopyright(final String aCopyright);
    String getCopyright();
    
    /**
     * Set contents of the ${currency} placeholder.
     */
    void setCurrency(final String aCurrency);
    String getCurrency();
    
    /**
     * Set contents of ${schedulingHorizonMonths} placeholder.
     */
    void setSchedulingHorizonMonths(final int aSchedulingHorizon);
    int getSchedulingHorizonMonths();
}

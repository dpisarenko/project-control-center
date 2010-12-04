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

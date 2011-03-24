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

package at.silverstrike.pcc.api.export2tj3;

import at.silverstrike.pcc.api.conventions.ModuleWithInjectableDependencies;
import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.conventions.SingleActivityModule;
import at.silverstrike.pcc.api.projectscheduler.ProjectExportInfo;

/**
 * Component for converting data from a list of control processes to a
 * TaskJuggler III project file.
 */
public interface TaskJuggler3Exporter extends ModuleWithInjectableDependencies,
        SingleActivityModule {
    void setProjectExportInfo(final ProjectExportInfo aInfo);
    ProjectExportInfo getProjectExportInfo();
    
    /**
     * Performs the export. If it fails, the thrown exception. After the export
     * has finished, the resulting TaskJuggler III file contents can be fetched
     * by calling the method getTaskJugglerIIIProjectFileContents.
     */
    void run() throws PccException;
    
    /**
     * Returns the TaskJuggler III project file contents, which is equivalent to
     * the information contained in the control process objects.
     */
    String getTaskJugglerIIIProjectFileContents();
}

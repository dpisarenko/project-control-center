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
    void setProjectExportInfo(final ProjectExportInfo anInfo);
    ProjectExportInfo getProjectExportInfo();
    
    /**
     * Performs the export. If it fails, the thrown exception. After the export
     * has finished, the resulting TaskJuggler III file contents can be fetched
     * by calling the method getTaskJugglerIIIProjectFileContents.
     */
    void run() throws NoProcessesException, NoResourcesException, PccException;
    
    /**
     * Returns the TaskJuggler III project file contents, which is equivalent to
     * the information contained in the control process objects.
     */
    String getTaskJugglerIIIProjectFileContents();
}

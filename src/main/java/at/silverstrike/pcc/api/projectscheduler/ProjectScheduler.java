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

import at.silverstrike.pcc.api.conventions.ModuleWithInjectableDependencies;
import at.silverstrike.pcc.api.conventions.SingleActivityModule;

/**
 * Represents a module for scheduling the project in TaskJuggler III.
 * 
 * @author Dmitri Pisarenko
 */
public interface ProjectScheduler extends ModuleWithInjectableDependencies,
        SingleActivityModule {
    public static final String TJ3_INPUT_FILE = "pccProject.tjp";
    public static final String BOOKINGS_FILE = "pccBookings.tji.tjp";
    public static final String DEADLINE_CSV_FILE = "pccDeadlines.csv";

    ProjectExportInfo getProjectExportInfo();
    
    void setDirectory(final String aProperty);

}

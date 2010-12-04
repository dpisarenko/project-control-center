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
    void setNow(final Date aDate);
}

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

package at.silverstrike.pcc.api.projectscheduler;

import java.util.Date;

import ru.altruix.commons.api.conventions.SingleActivityModule;
import ru.altruix.commons.api.di.ModuleWithInjectableDependencies;


/**
 * Represents a module for scheduling the project in TaskJuggler III.
 * 
 * @author Dmitri Pisarenko
 */
public interface ProjectScheduler extends ModuleWithInjectableDependencies,
        SingleActivityModule {
    String TJ3_INPUT_FILE = "pccProject.tjp";
    String BOOKINGS_FILE = "pccBookings.tji.tjp";
    String DEADLINE_CSV_FILE = "pccDeadlines.csv";

    ProjectExportInfo getProjectExportInfo();

    void setDirectory(final String aProperty);

    void setNow(final Date aDate);
}

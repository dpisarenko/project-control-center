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

package at.silverstrike.pcc.api.model;

import java.util.Date;

import ru.altruix.commons.api.conventions.UniquelyIdentifiableObject;

/**
 * Instances of this class represent times, when a certain resource is required
 * for attaining a certain goal (performing certain process/task).
 * 
 * E. g. when a worker needs to work on a certain task on 06.06.2010 from 10:00
 * to 12:00, then this information is represented by an instance of this
 * interface.
 * 
 * @author Dmitri Pisarenko
 * 
 */
public interface Booking extends UniquelyIdentifiableObject {
    void setProcess(final Task aProcess);

    Task getProcess();

    void setResource(final Resource aResource);

    Resource getResource();

    void setStartDateTime(final Date aDate);

    Date getStartDateTime();

    void setDuration(final double aDurationInHours);

    double getDuration();

    Date getEndDateTime();
    
    void setUser(final UserData aUserData);
    UserData getUser();
}

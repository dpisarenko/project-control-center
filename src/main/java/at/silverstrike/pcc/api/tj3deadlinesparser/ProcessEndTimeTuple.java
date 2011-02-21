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

package at.silverstrike.pcc.api.tj3deadlinesparser;

import java.util.Date;

/**
 * @author Dmitri Pisarenko
 */
public interface ProcessEndTimeTuple {
    Long getProcessId();

    void setProcessId(final Long aId);

    Date getEndDateTime();

    void setEndDateTime(final Date aDate);
}

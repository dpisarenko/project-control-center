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

/**
 * @author DP118M
 *
 */
public interface Event extends SchedulingObject {
    void setPlace(final String aTitle);
    String getPlace();
    
    void setStartDateTime(final Date aDateTime);
    Date getStartDateTime();
    
    void setEndDateTime(final Date aDateTime);
    Date getEndDateTime();
}

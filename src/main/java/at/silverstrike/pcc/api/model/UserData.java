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

import java.util.List;

public interface UserData {
    void setIdentifier(final String aIdentifier);

    String getIdentifier();

    void setSchedulingData(final List<SchedulingObject> aProcesses);

    List<SchedulingObject> getSchedulingData();

    void setDailyPlans(List<DailyPlan> aDailyPlans);

    List<DailyPlan> getDailyPlans();

    void setBookings(List<Booking> aBookings);

    List<Booking> getBookings();
}

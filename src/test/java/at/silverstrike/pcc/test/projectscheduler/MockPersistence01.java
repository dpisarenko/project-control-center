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

package at.silverstrike.pcc.test.projectscheduler;

import static at.silverstrike.pcc.impl.jruby.RubyDateTimeUtils.getDate;
import static java.util.Calendar.OCTOBER;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;
import at.silverstrike.pcc.api.model.Booking;
import at.silverstrike.pcc.api.model.Event;
import at.silverstrike.pcc.api.model.Milestone;
import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.model.Resource;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.model.UserData;
import at.silverstrike.pcc.api.tj3bookingsparser.BookingTuple;
import at.silverstrike.pcc.api.tj3deadlinesparser.ProcessEndTimeTuple;
import at.silverstrike.pcc.impl.jruby.RubyDateTimeUtils;
import at.silverstrike.pcc.test.mockpersistence.MockBooking;
import at.silverstrike.pcc.test.mockpersistence.MockPersistenceAdapter;

class MockPersistence01 extends MockPersistenceAdapter {
    private static final Date DATE_TIME_2010_10_25_09_00 = getDate(2010,
            OCTOBER,
                    25, 9, 0);

    @Override
    public void updateTaskEndTimes(
            final List<ProcessEndTimeTuple> aEndTimeTuples) {
        Assert.assertNotNull(
                "null tuples collection given to persistence.",
                aEndTimeTuples);
        Assert.assertEquals(
                "Wrong numbers of tuples given to persistence.", 1,
                aEndTimeTuples.size());

        final ProcessEndTimeTuple tuple = aEndTimeTuples.get(0);

        Date date =
                RubyDateTimeUtils.getDate(2010, Calendar.OCTOBER, 25, 11, 30);

        Assert.assertEquals("Task ID is wrong.", new Long(2010), tuple
                .getProcessId());
        Assert.assertEquals("End date is wrong.", date, tuple
                .getEndDateTime());
    }

    @Override
    public List<SchedulingObject> getChildTasks(
            final SchedulingObject aParent) {
        return new LinkedList<SchedulingObject>();
    }

    @Override
    public Booking createBooking() {
        return new MockBooking();
    }

    @Override
    public void updateBookings(final List<BookingTuple> aBookingTuples) {
        Assert.assertNotNull(aBookingTuples);
        Assert.assertEquals(1, aBookingTuples.size());

        final BookingTuple tuple = aBookingTuples.get(0);

        Assert.assertEquals(DATE_TIME_2010_10_25_09_00, tuple.getBooking()
                .getStartDateTime());
        Assert.assertEquals(2.50, tuple.getBooking().getDuration());
        Assert.assertEquals(2010L, tuple.getProcessId());
        Assert.assertEquals(1210L, tuple.getResourceId());
    }

    @Override
    public void generateDailyPlans(final Date aNow) {
    }

    @Override
    public Resource getResource(final Long aResourceId) {
        return null;
    }

    @Override
    public UserData getUserData() {
        return null;
    }

    @Override
    public Event createSubEvent(final String aProcessName, final Long aParentProcessId) {
        return null;
    }

    @Override
    public boolean deleteTask(final Task aTask) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteEvent(final Event aEvent) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteMilestone(final Milestone aMilestone) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void updateMilestone(final Milestone aMilestone) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateEvent(final Event aEvent) {
        // TODO Auto-generated method stub
        
    }

}

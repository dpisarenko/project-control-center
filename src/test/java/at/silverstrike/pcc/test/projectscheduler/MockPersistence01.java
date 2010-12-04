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

package at.silverstrike.pcc.test.projectscheduler;

import static at.silverstrike.pcc.impl.jruby.RubyDateTimeUtils.getDate;
import static java.util.Calendar.OCTOBER;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;
import at.silverstrike.pcc.api.model.Booking;
import at.silverstrike.pcc.api.model.ControlProcess;
import at.silverstrike.pcc.api.model.Resource;
import at.silverstrike.pcc.api.tj3bookingsparser.BookingTuple;
import at.silverstrike.pcc.api.tj3deadlinesparser.ProcessEndTimeTuple;
import at.silverstrike.pcc.impl.jruby.RubyDateTimeUtils;
import at.silverstrike.pcc.test.mockpersistence.MockBooking;
import at.silverstrike.pcc.test.mockpersistence.MockPersistenceAdapter;

class MockPersistence01 extends MockPersistenceAdapter {
    private static final Date DATE_TIME_2010_10_25_09_00 = getDate(2010, OCTOBER,
                    25, 9, 0);

    @Override
    public void updateTaskEndTimes(
            final List<ProcessEndTimeTuple> anEndTimeTuples) {
        Assert.assertNotNull(
                "null tuples collection given to persistence.",
                anEndTimeTuples);
        Assert.assertEquals(
                "Wrong numbers of tuples given to persistence.", 1,
                anEndTimeTuples.size());

        final ProcessEndTimeTuple tuple = anEndTimeTuples.get(0);

        Date date =
                RubyDateTimeUtils.getDate(2010, Calendar.OCTOBER, 25, 11, 30);

        Assert.assertEquals("Task ID is wrong.", new Long(2010), tuple
                .getProcessId());
        Assert.assertEquals("End date is wrong.", date, tuple
                .getEndDateTime());
    }

    @Override
    public List<ControlProcess> getChildTasks(
            final ControlProcess aParent) {
        return new LinkedList<ControlProcess>();
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

        Assert.assertEquals(DATE_TIME_2010_10_25_09_00, tuple.getBooking().getStartDateTime());
        Assert.assertEquals(2.75, tuple.getBooking().getDuration());
        Assert.assertEquals(2010L, tuple.getProcessId());
        Assert.assertEquals(1210L, tuple.getResourceId());
    }

    @Override
    public void generateDailyPlans(final Date aNow) {
    }

    @Override
    public Resource getResource(Long aResourceId) {
        // TODO Auto-generated method stub
        return null;
    }
}

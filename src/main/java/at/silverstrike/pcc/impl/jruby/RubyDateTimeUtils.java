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

package at.silverstrike.pcc.impl.jruby;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author dp118m
 * 
 */
public final class RubyDateTimeUtils {

    public static Date getDate(final int anYear, final int aMonth,
            final int aDay,
            final int anHour, final int aMinute) {

        final GregorianCalendar calendar =
                new GregorianCalendar(anYear, aMonth, aDay, anHour, aMinute);

        return calendar.getTime();
    }
}

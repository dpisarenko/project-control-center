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

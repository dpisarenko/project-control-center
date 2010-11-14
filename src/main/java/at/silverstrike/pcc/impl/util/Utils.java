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

package at.silverstrike.pcc.impl.util;

import static org.apache.commons.lang.StringUtils.isBlank;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

import com.vaadin.ui.TextField;

public class Utils {
    private static final int MINUTES_IN_HOUR = 60;
    
	public static boolean textFieldEmpty(final TextField aTextField)
	{
		final String text = (String)aTextField.getValue();
		return isBlank(text);
	}
	
    public static Date addHours(final Date aDateTime, final double anHours) {
        final int hours = (int)anHours;
        final int minutes = (int)((anHours - (double)hours)*MINUTES_IN_HOUR);
        
        final Date timeWithHoursAdded = DateUtils.addHours(aDateTime, hours);
        
        return DateUtils.addMinutes(timeWithHoursAdded, minutes);
    }
}

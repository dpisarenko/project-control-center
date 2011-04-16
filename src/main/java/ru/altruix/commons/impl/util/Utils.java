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
package ru.altruix.commons.impl.util;

import static org.apache.commons.lang.StringUtils.isBlank;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

import com.vaadin.ui.TextField;

public final class Utils {
    private static final int MINUTES_IN_HOUR = 60;

    /**
     * We need this to fix checkstyle error: Utility classes should not have a
     * public or default constructor.
     */
    private Utils() {
    }

    public static boolean textFieldEmpty(final TextField aTextField) {
        final String text = (String) aTextField.getValue();
        return isBlank(text);
    }

    public static Date addHours(final Date aDateTime, final double aHours) {
        final int hours = (int) aHours;
        final int minutes =
                (int) ((aHours - (double) hours) * MINUTES_IN_HOUR);

        final Date timeWithHoursAdded = DateUtils.addHours(aDateTime, hours);

        return DateUtils.addMinutes(timeWithHoursAdded, minutes);
    }
}

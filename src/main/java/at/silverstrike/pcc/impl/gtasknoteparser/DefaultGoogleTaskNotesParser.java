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

package at.silverstrike.pcc.impl.gtasknoteparser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ru.altruix.commons.api.di.PccException;
import at.silverstrike.pcc.api.gtasknoteparser.GoogleTaskNotesParser;

/**
 * @author DP118M
 * 
 */
class DefaultGoogleTaskNotesParser implements GoogleTaskNotesParser {
    private String notes;
    private boolean effortSpecified;
    private Pattern pattern;
    private double effortInHours;

    public DefaultGoogleTaskNotesParser() {
        // This means N.YYh
        // I. e. max effort is 9.99 hours.
        // The fractional part is optional.
        final String effortRegex = "(\\d(\\.\\d{0,2}?)?)h";

        pattern = Pattern.compile(effortRegex);

    }

    @Override
    public void run() throws PccException {
        final Matcher matcher = this.pattern.matcher(this.notes);
        this.effortSpecified = matcher.matches();

        if (this.effortSpecified) {
            final String effortAsString = matcher.group(1);
            this.effortInHours = Double.parseDouble(effortAsString);
        }
    }

    @Override
    public void setNotes(final String aNotes) {
        this.notes = aNotes;
    }

    @Override
    public boolean isEffortSpecified() {
        return effortSpecified;
    }

    @Override
    public double getEffortInHours() {
        return this.effortInHours;
    }
}

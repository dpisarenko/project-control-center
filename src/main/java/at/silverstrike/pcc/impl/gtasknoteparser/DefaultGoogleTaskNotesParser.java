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

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import ru.altruix.commons.api.di.PccException;
import at.silverstrike.pcc.api.gtasknoteparser.GoogleTaskNotesParser;

/**
 * @author DP118M
 * 
 */
class DefaultGoogleTaskNotesParser implements GoogleTaskNotesParser {

    private static final String PREFIX = "Depends on";
    private static final String HASHTAG = "#";
    private String notes;
    private boolean effortSpecified;
    private Pattern effortPattern;
    private double effortInHours;

    private boolean predecessorsSpecified;
    private List<String> predecessorLabels;
    private Pattern predecessorPattern;

    public DefaultGoogleTaskNotesParser() {
        // This means N.YYh
        // I. e. max effort is 9.99 hours.
        // The fractional part is optional.
        final String effortRegex = ".*(\\d(\\.\\d{0,2}?)?)h.*";

        effortPattern = Pattern.compile(effortRegex);

        final String dependenciesWithPrefixRegex =
                "${prefix}\\:?+(.*)(,\\s.*)*\\s".replace("${prefix}", PREFIX);
        this.predecessorPattern = Pattern.compile(dependenciesWithPrefixRegex);
    }

    @Override
    public void run() throws PccException {
        if (!StringUtils.isBlank(this.notes)) {
            parseEffort();
            parseDependencies();
        } else {
            this.effortSpecified = false;
            this.predecessorsSpecified = false;
        }
    }

    private void parseDependencies() {
        if (this.notes.contains(PREFIX)) {
            parseDependenciesWithPrefix();
        } else if (this.notes.contains(HASHTAG)) {
            parseDependenciesWithHashtags();
        }
    }

    private void parseDependenciesWithHashtags() {
        // TODO Auto-generated method stub

    }

    private void parseDependenciesWithPrefix() {
        final Matcher matcher =
                this.predecessorPattern.matcher(this.notes.trim());
        this.predecessorsSpecified = matcher.matches();

        final int numberOfPredecessors = matcher.groupCount();

        this.predecessorLabels = new LinkedList<String>();
        for (int i = 0; i < numberOfPredecessors; i++) {
            String text = StringUtils.trimToEmpty(matcher.group(i));

            if (text.startsWith(",")) {
                text = text.replace(",", "");
            }

            this.predecessorLabels.add(text);
        }
    }

    private void parseEffort() {
        final Matcher matcher = this.effortPattern.matcher(this.notes.trim());
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

    public boolean arePredecessorsSpecified() {
        return predecessorsSpecified;
    }

    public List<String> getPredecessorLabels() {
        return predecessorLabels;
    }
}

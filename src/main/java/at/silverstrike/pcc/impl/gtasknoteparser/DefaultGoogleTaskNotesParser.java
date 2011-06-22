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
import java.util.StringTokenizer;
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

    public DefaultGoogleTaskNotesParser() {
        // This means N.YYh
        // I. e. max effort is 9.99 hours.
        // The fractional part is optional.
        final String effortRegex = ".*(\\d(\\.\\d{0,2}?)?)h.*";

        effortPattern = Pattern.compile(effortRegex);
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
        } else {
            this.predecessorsSpecified = false;
        }
    }

    private void parseDependenciesWithHashtags() {
        final String effortRegex =
                "(?:^|\\s|[\\p{Punct}&&[^/]])(#[\\p{L}0-9-_]+)";
        final Pattern pattern = Pattern.compile(effortRegex);

        final Matcher matcher = pattern.matcher(this.notes.trim());

        this.predecessorLabels = new LinkedList<String>();
        while (matcher.find()) {
            this.predecessorLabels.add(StringUtils.trim(matcher.group())
                    .substring(1));
        }

        this.predecessorsSpecified = this.predecessorLabels.size() > 0;
    }

    private void parseDependenciesWithPrefix() {
        final String dependencies = this.notes.trim().split(PREFIX)[1];
        final StringTokenizer tokenizer2 =
                new StringTokenizer(dependencies, ",");

        this.predecessorLabels = new LinkedList<String>();
        while (tokenizer2.hasMoreTokens()) {
            this.predecessorLabels.add(tokenizer2.nextToken().trim());
        }

        this.predecessorsSpecified = this.predecessorLabels.size() > 0;
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

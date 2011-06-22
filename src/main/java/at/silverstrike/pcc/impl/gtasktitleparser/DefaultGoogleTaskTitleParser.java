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

package at.silverstrike.pcc.impl.gtasktitleparser;

import org.apache.commons.lang.StringUtils;

import ru.altruix.commons.api.di.PccException;
import at.silverstrike.pcc.api.gtasktitleparser.GoogleTaskTitleParser;

/**
 * @author DP118M
 *
 */
class DefaultGoogleTaskTitleParser implements GoogleTaskTitleParser {
    private static final String LABEL_MARKER = ":";
    private String title;
    private boolean labelSpecified;
    private String label;

    @Override
    public void run() throws PccException {
        if (!StringUtils.isBlank(this.title)) {
            parseLabel();
        } else {
            this.labelSpecified = false;
        }

    }

    private void parseLabel() {
        if (this.title.contains(LABEL_MARKER)) {
            this.labelSpecified = true;
            this.label = StringUtils.split(this.title, LABEL_MARKER)[0];
        } else {
            this.labelSpecified = false;
        }
    }

    
    @Override
    public void setTitle(final String aTitle) {
        this.title = aTitle;
    }

    @Override
    public boolean isLabelSpecified() {
        return this.labelSpecified;
    }

    @Override
    public String getLabel() {
        return this.label;
    }
}

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

import ru.altruix.commons.api.di.PccException;
import at.silverstrike.pcc.api.gtasknoteparser.GoogleTaskNotesParser;

/**
 * @author DP118M
 * 
 */
class DefaultGoogleTaskNotesParser implements GoogleTaskNotesParser {
    private String notes;
    private boolean effortSpecified;

    @Override
    public void run() throws PccException {
        String effortRegex = "h";
    }

    @Override
    public void setNotes(final String aNotes) {
        this.notes = aNotes;
    }

    @Override
    public boolean isEffortSpecified() {
        return effortSpecified;
    }
}

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

package at.silverstrike.pcc.api.export2tj3;

import at.silverstrike.pcc.api.conventions.PccException;

/**
 * This exception is thrown, if TaskJugglerExporter is invoked with invalid
 * timingresolution. Timingresolution must equal-to-or-greater-than 15 min
 */
public class InvalidDurationException extends PccException {
    private static final long serialVersionUID = 1L;

    private Long taskNumber;
    private String taskName;

    public InvalidDurationException(final Long aTaskNumber,
            final String aTaskName) {
        super(
                "Invalid timingresolution. Must be equal-to-or-greater-than 15 min");

        this.taskNumber = aTaskNumber;
        this.taskName = aTaskName;
    }

    public final Long getTaskNumber() {
        return taskNumber;
    }

    /**
     * 
     * @return
     */
    public final String getTaskName() {
        return taskName;
    }
}

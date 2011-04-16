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

import ru.altruix.commons.api.di.PccException;

/**
 * This exception is thrown, if TaskJugglerExporter is invoked without
 * the information about the project to be exported.
 */
public class NoProjectExportInfoException extends PccException {
    private static final long serialVersionUID = 1L;

    public NoProjectExportInfoException() {
        super("No project info specified");
    }
}

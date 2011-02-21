/**
 * This file is part of Project Control Center (PCC).
 * 
 * PCC (Project Control Center) project is intellectual property of 
 * Dmitri Anatol'evich Pisarenko.
 * 
 * Copyright 2010 Dmitri Anatol'evich Pisarenko
 * All rights reserved
 *
 **/

package at.silverstrike.pcc.impl.export2tj3;

import at.silverstrike.pcc.api.export2tj3.TaskJuggler3Exporter;
import at.silverstrike.pcc.api.export2tj3.TaskJuggler3ExporterFactory;

public class DefaultTaskJuggler3ExporterFactory implements
        TaskJuggler3ExporterFactory {

    /**
     * @see at.silverstrike.pcc.api.conventions.Factory#create()
     */
    @Override
    public final TaskJuggler3Exporter create() {
        return new DefaultTaskJuggler3Exporter();
    }

}

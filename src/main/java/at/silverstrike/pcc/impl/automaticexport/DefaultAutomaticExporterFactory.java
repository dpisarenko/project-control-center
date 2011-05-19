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

package at.silverstrike.pcc.impl.automaticexport;

import at.silverstrike.pcc.api.automaticexport.AutomaticExporter;
import at.silverstrike.pcc.api.automaticexport.AutomaticExporterFactory;

/**
 * @author DP118M
 *
 */
public final class DefaultAutomaticExporterFactory implements
        AutomaticExporterFactory {
    @Override
    public AutomaticExporter create() {
        return new DefaultAutomaticExporter();
    }

}

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

import ru.altruix.commons.api.di.PccException;

import com.google.inject.Injector;

import at.silverstrike.pcc.api.automaticexport.AutomaticExporter;

/**
 * @author DP118M
 *
 */
class DefaultAutomaticExporter implements AutomaticExporter {
    private Injector injector;
    
    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;
    }

    @Override
    public void run() throws PccException {
        // TODO Auto-generated method stub

    }

}

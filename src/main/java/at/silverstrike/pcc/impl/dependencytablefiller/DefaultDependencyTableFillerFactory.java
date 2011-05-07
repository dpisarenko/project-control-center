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

package at.silverstrike.pcc.impl.dependencytablefiller;

import at.silverstrike.pcc.api.dependencytablefiller.DependencyTableFiller;
import at.silverstrike.pcc.api.dependencytablefiller.DependencyTableFillerFactory;

/**
 * @author DP118M
 * 
 */
public class DefaultDependencyTableFillerFactory implements
        DependencyTableFillerFactory {

    @Override
    public DependencyTableFiller create() {
        return new DefaultDependencyTableFiller();
    }

}

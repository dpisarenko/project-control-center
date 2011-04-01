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

package at.silverstrike.pcc.impl.projecttreemodel;

import at.silverstrike.pcc.api.projecttreemodel.ProjectTreeContainer;
import at.silverstrike.pcc.api.projecttreemodel.ProjectTreeContainerFactory;

/**
 * @author DP118M
 * 
 */
final class DefaultProjectTreeContainerFactory implements
        ProjectTreeContainerFactory {
    @Override
    public ProjectTreeContainer create() {
        return new DefaultProjectTreeContainer();
    }

}

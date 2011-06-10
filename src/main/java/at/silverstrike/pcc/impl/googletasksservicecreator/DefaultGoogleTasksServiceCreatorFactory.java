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

package at.silverstrike.pcc.impl.googletasksservicecreator;

import at.silverstrike.pcc.api.googletasksservicecreator.GoogleTasksServiceCreator;
import at.silverstrike.pcc.api.googletasksservicecreator.GoogleTasksServiceCreatorFactory;

/**
 * @author DP118M
 *
 */
public class DefaultGoogleTasksServiceCreatorFactory implements
        GoogleTasksServiceCreatorFactory {

    @Override
    public GoogleTasksServiceCreator create() {
        return new DefaultGoogleTasksServiceCreator();
    }

}

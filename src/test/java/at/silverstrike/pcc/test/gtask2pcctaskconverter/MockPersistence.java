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

package at.silverstrike.pcc.test.gtask2pcctaskconverter;

import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.model.UserData;
import at.silverstrike.pcc.test.mockpersistence.MockPersistenceAdapter;
import at.silverstrike.pcc.test.model.MockObjectFactory;

/**
 * @author DP118M
 * 
 */
final class MockPersistence extends MockPersistenceAdapter {
    private static final MockObjectFactory MOCK_OBJECT_FACTORY =
            new MockObjectFactory();

    @Override
    public Task createSubTask(final String aProcessName,
            final Long aParentProcessId,
            final UserData aUser) {
        final Task task = MOCK_OBJECT_FACTORY.createTask();
        
        task.setName(aProcessName);
        task.setParent(null);
        task.setUserData(aUser);

        return task;
    }

}

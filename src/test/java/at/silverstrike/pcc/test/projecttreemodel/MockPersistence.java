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

package at.silverstrike.pcc.test.projecttreemodel;

import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;

import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.model.UserData;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.test.mockpersistence.MockPersistenceAdapter;
import at.silverstrike.pcc.test.model.MockObjectFactory;

/**
 * @author DP118M
 * 
 */
final class MockPersistence extends MockPersistenceAdapter implements Persistence {
    private MockObjectFactory mockObjectFactory = new MockObjectFactory();
    private boolean returnSubProcesses = true;

    void resetReturnSubProcessesFlag() {
        this.returnSubProcesses = true;
    }

    @Override
    public List<SchedulingObject> getSubProcessesWithChildren(
            final Long aProcessId, final UserData aUser) {
        final List<SchedulingObject> returnValue =
                new LinkedList<SchedulingObject>();

        if (returnSubProcesses) {
            final Task task =
                    this.mockObjectFactory.createControlProcess(1230L);

            task.setName("Some name");

            Assert.assertNotNull("ID must not be null.", task.getId());

            returnValue.add(task);
            this.returnSubProcesses = false;
        }

        return returnValue;
    }
}

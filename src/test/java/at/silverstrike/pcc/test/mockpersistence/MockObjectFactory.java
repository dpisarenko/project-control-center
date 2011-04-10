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

package at.silverstrike.pcc.test.mockpersistence;

import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.model.Resource;
import at.silverstrike.pcc.api.model.ResourceAllocation;
import at.silverstrike.pcc.api.persistence.Persistence;

@Deprecated
public final class MockObjectFactory {
    public Task createControlProcess(final Long aId) {
        final MockControlProcess returnValue = new MockControlProcess();

        returnValue.setId(aId);

        return returnValue;
    }

    public Persistence createPersistence() {
        return new MockPersistence();
    }

    public Resource createResource(final Long aId) {
        final MockResource returnValue = new MockResource();

        returnValue.setId(aId);

        return returnValue;
    }

    public ResourceAllocation createResourceAllocation() {
        return new MockResourceAllocation();
    }

}

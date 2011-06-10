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

package at.silverstrike.pcc.test.centraleditingpanelbuttonstate;

import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.test.mockpersistence.MockPersistenceAdapter;

/**
 * @author DP118M
 *
 */
final class MockPersistenceLowestPriorityTask extends MockPersistenceAdapter implements Persistence {


    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#isHighestPriorityObjectInProject(at.silverstrike.pcc.api.model.SchedulingObject, at.silverstrike.pcc.api.model.SchedulingObject)
     */
    @Override
    public boolean isHighestPriorityObjectInProject(final SchedulingObject aArg1,
            final SchedulingObject aArg2) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see at.silverstrike.pcc.api.persistence.Persistence#isLowestPriorityObjectInProject(at.silverstrike.pcc.api.model.SchedulingObject, at.silverstrike.pcc.api.model.SchedulingObject)
     */
    @Override
    public boolean isLowestPriorityObjectInProject(final SchedulingObject aArg1,
            final SchedulingObject aArg2) {
        // TODO Auto-generated method stub
        return true;
    }


}

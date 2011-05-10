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

package at.silverstrike.pcc.impl.incorrectschedulingobjectsmarker;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.altruix.commons.api.di.PccException;

import com.google.inject.Injector;

import at.silverstrike.pcc.api.incorrectschedulingobjectsmarker.IncorrectSchedulingObjectsMarker;
import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.model.SchedulingObjectValidationError;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.persistence.Persistence;

/**
 * @author DP118M
 * 
 */
class DefaultIncorrectSchedulingObjectsMarker implements
        IncorrectSchedulingObjectsMarker {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultIncorrectSchedulingObjectsMarker.class);

    private Injector injector;
    private List<SchedulingObject> schedulingObjects;
    private boolean errorsFound;

    @Override
    public void run() throws PccException {
        final Persistence persistence =
                this.injector.getInstance(Persistence.class);
        final List<Task> tasksToUpdate = new LinkedList<Task>();

        errorsFound = false;

        for (final SchedulingObject curSchedulingObject : schedulingObjects) {
            if (curSchedulingObject instanceof Task) {
                final Task curTask = (Task) curSchedulingObject;
                boolean errorFound = false;
                boolean hasErrorFlagSet =
                        (curTask.getValidationError() != null);

                boolean hasChildren = persistence.hasChildren(curTask);

                if ((!hasChildren) && ((curTask.getBestCaseEffort() == null)
                        || (curTask.getWorstCaseEffort() == null))) {
                    errorsFound = true;
                    errorFound = true;
                    curTask.setValidationError(SchedulingObjectValidationError.EFFORT_NOT_SPECIFIED);
                } else {
                    curTask.setValidationError(null);
                }

                if (errorFound && !hasErrorFlagSet) {
                    tasksToUpdate.add(curTask);
                } else if (!errorFound && hasErrorFlagSet) {
                    tasksToUpdate.add(curTask);
                }
                LOGGER.debug("curTask: {}, errorFound: {}, hasErrorFlagSet: {}, hasChildren: {}", new Object[]{curTask.getName(),
                        errorFound, hasErrorFlagSet, hasChildren});
            }
        }

        for (final Task curTask : tasksToUpdate) {
            persistence.updateTask(curTask);
        }
    }

    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;
    }

    @Override
    public void setSchedulingObjects(final List<SchedulingObject> aObjects) {
        schedulingObjects = aObjects;
    }

    @Override
    public boolean areErrorsFound() {
        return errorsFound;
    }
}

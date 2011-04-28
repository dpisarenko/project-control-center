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

package at.silverstrike.pcc.impl.projectnetworkdatacreator;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.altruix.commons.api.di.PccException;

import com.google.inject.Injector;

import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.projectnetworkdatacreator.ProjectNetworkDataCreator;
import at.silverstrike.pcc.api.projectnetworkgraphcreator.SchedulingObjectDependencyTuple;

/**
 * @author DP118M
 * 
 */
final class DefaultProjectNetworkDataCreator implements
        ProjectNetworkDataCreator {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultProjectNetworkDataCreator.class);

    private List<SchedulingObjectDependencyTuple> dependencyTuples;
    private Injector injector;
    private Long currentProjectId;

    @Override
    public void run() throws PccException {
        final Persistence persistence =
                this.injector.getInstance(Persistence.class);
        final List<SchedulingObject> projectSchedulingObjects =
                persistence.getSubProcessesWithChildren(this.currentProjectId);

        LOGGER.debug("projectSchedulingObjects: {}", projectSchedulingObjects);

        /**
         * Преобразуем каждый расчётный объект в набор данных.
         */
        this.dependencyTuples = new LinkedList<SchedulingObjectDependencyTuple>();

        if (projectSchedulingObjects == null)
        {
            return;
        }
        
        for (final SchedulingObject curObject : projectSchedulingObjects) {
            final String vertex = curObject.getLabel();
            final List<String> dependencies = new LinkedList<String>();
            /**
             * В графике будем показывать только зависимости из того же проекта.
             */

            if (curObject.getPredecessors() != null) {
                LOGGER.debug("curObject.getPredecessors(): {}",
                        curObject.getPredecessors());
                for (final SchedulingObject curDependency : curObject
                        .getPredecessors()) {
                    Long dependencyParentId = null;

                    if (curDependency.getParent() != null) {
                        dependencyParentId = curDependency.getParent().getId();
                    }

                    if (ObjectUtils.equals(this.currentProjectId,
                            dependencyParentId)) {
                        dependencies.add(curDependency.getLabel());
                    }
                }
            }
            LOGGER.debug(
                    "vertex: {}, dependencies: {}",
                    new Object[] {
                            vertex, dependencies });

            dependencyTuples.add(getTuple(vertex, dependencies));
        }

    }

    private SchedulingObjectDependencyTuple getTuple(final String aLabel,
            final List<String> aDependencies) {
        final List<String> dependencies = new LinkedList<String>();
        final MockSchedulingObjectDependencyTuple returnValue =
                new MockSchedulingObjectDependencyTuple();

        for (final String curDep : aDependencies) {
            dependencies.add(curDep);
        }

        returnValue.setLabel(aLabel);
        returnValue.setDependencies(dependencies);

        return returnValue;
    }

    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;
    }

    @Override
    public void setCurrentProjectId(final Long aId) {
        this.currentProjectId = aId;
    }

    @Override
    public List<SchedulingObjectDependencyTuple> getDependencyTuples() {
        return this.dependencyTuples;
    }

}

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

package at.silverstrike.pcc.impl.webguibus;

import at.silverstrike.pcc.api.model.Event;
import at.silverstrike.pcc.api.model.Milestone;
import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.webguibus.WebGuiBusListener;

public abstract class WebGuiBusListenerAdapter implements WebGuiBusListener {

    @Override
    public void taskCreated(final Task aNewTask) {
    }

    @Override
    public void taskCreationFailure() {
    }

    @Override
    public void taskEdited(final Task aTask) {
    }

    @Override
    public void eventCreated(final Event aNewEvent) {
    }

    @Override
    public void eventCreationFailure() {
    }

    @Override
    public void milestoneCreated(final Milestone aMilestone) {
    }

    @Override
    public void milestoneCreationFailure() {
    }

    @Override
    public void taskDeleted(final Task aTask) {
    }

    @Override
    public void eventDeleted(final Event aNewEvent) {
    }

    @Override
    public void milestoneDeleted(final Milestone aMilestone) {
    }

    @Override
    public void taskDeletionFailure() {
    }

    @Override
    public void eventDeletionFailure() {
    }

    @Override
    public void milestoneDeletionFailure() {
    }

    @Override
    public void eventEdited(final Event aEvent) {
    }

    @Override
    public void taskCompleted(final Task aTask) {
    }

    @Override
    public void taskComletedFailure() {
    }

    @Override
    public void taskDependenciesChanged(final SchedulingObject aObject) {

    }

    @Override
    public void milestoneEdited(final Milestone aMilestone) {
    }

    @Override
    public void planCalculated() {
    }

    @Override
    public void tasksImportedFromGoogle() {
    }

}

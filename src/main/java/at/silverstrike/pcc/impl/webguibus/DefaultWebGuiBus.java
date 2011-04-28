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

import java.util.LinkedList;
import java.util.List;

import at.silverstrike.pcc.api.model.Event;
import at.silverstrike.pcc.api.model.Milestone;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.webguibus.WebGuiBus;
import at.silverstrike.pcc.api.webguibus.WebGuiBusListener;

class DefaultWebGuiBus implements WebGuiBus {
    private List<WebGuiBusListener> listeners;

    public DefaultWebGuiBus() {
        this.listeners = new LinkedList<WebGuiBusListener>();
    }

    @Override
    public void addListener(final WebGuiBusListener aListener) {
        if (!this.listeners.contains(aListener)) {
            this.listeners.add(aListener);
        }
    }

    @Override
    public void broadcastTaskCreatedMessage(final Task aNewTask) {
        for (final WebGuiBusListener listener : this.listeners) {
            listener.taskCreated(aNewTask);
        }
    }

    @Override
    public void broadcastTaskCreationFailureMessage() {
        for (final WebGuiBusListener listener : this.listeners) {
            listener.taskCreationFailure();
        }
    }

    @Override
    public void broadcastTaskEditedMessage(final Task aTask) {
        for (final WebGuiBusListener listener : this.listeners) {
            listener.taskEdited(aTask);
        }
    }

    @Override
    public void broadcastEventCreatedMessage(final Event aNewEvent) {
        for (final WebGuiBusListener listener : this.listeners) {
            listener.eventCreated(aNewEvent);
        }
    }

    @Override
    public void broadcastEventCreationFailureMessage() {
        for (final WebGuiBusListener listener : this.listeners) {
            listener.eventCreationFailure();
        }
    }

    @Override
    public void broadcastMilestoneCreatedMessage(final Milestone aMilestone) {
        for (final WebGuiBusListener listener : this.listeners) {
            listener.milestoneCreated(aMilestone);
        }

    }

    @Override
    public void broadcastMilestoneCreationFailureMessage() {
        for (final WebGuiBusListener listener : this.listeners) {
            listener.milestoneCreationFailure();

        }
    }

    @Override
    public void broadcastTaskDeletedMessage(final Task aTask) {
        for (final WebGuiBusListener listener : this.listeners) {
            listener.taskDeleted(aTask);
        }
    }

    @Override
    public void broadcastTaskDeletionFailureMessage() {
        for (final WebGuiBusListener listener : this.listeners) {
            listener.taskDeletionFailure();
        }
    }

    @Override
    public void broadcastEventDeletedMessage(final Event aEvent) {
        for (final WebGuiBusListener listener : this.listeners) {
            listener.eventDeleted(aEvent);
        }
    }

    @Override
    public void broadcastEventDeletionFailureMessage() {
        for (final WebGuiBusListener listener : this.listeners) {
            listener.eventDeletionFailure();
        }
    }

    @Override
    public void broadcastMilestoneDeletedMessage(final Milestone aMilestone) {
        for (final WebGuiBusListener listener : this.listeners) {
            listener.milestoneDeleted(aMilestone);
        }
    }

    @Override
    public void broadcastMilestoneDeletionFailureMessage() {
        for (final WebGuiBusListener listener : this.listeners) {
            listener.milestoneDeletionFailure();
        }
    }

    @Override
    public void broadcastMilestoneEditedMessage(Milestone aMilestone) {
        for (final WebGuiBusListener listener : this.listeners) {
            listener.milestoneEdited(aMilestone);
        }

    }

    @Override
    public void broadcastEventEditedMessage(Event aEvent) {
        for (final WebGuiBusListener listener : this.listeners) {
            listener.eventEdited(aEvent);
        }

    }

    @Override
    public void broadcastIncreasePriorityMessage(Long parentProjectId) {
        for (final WebGuiBusListener listener : this.listeners) {
            // listener.IncreasePriority(parentProjectId);
        }

    }

    @Override
    public void broadcastIncreasePriorityFailureMessage() {
        // TODO Auto-generated method stub

    }

    @Override
    public void broadcastDecreasePriorityMessage(Long parentProjectId) {
        for (final WebGuiBusListener listener : this.listeners) {
            // listener.milestoneEdited(parentProjectId);
        }

    }

    @Override
    public void broadcastDecreasePriorityFailureMessage() {
        // TODO Auto-generated method stub

    }

    @Override
    public void broadcastTaskCompletedMessage(Task aTask) {
        for (final WebGuiBusListener listener : this.listeners) {
            listener.taskCompleted(aTask);
        }
    }

    @Override
    public void broadcastTaskCompletedFailureMessage() {
        for (final WebGuiBusListener listener : this.listeners) {
            listener.taskComletedFailure();
        }

    }

}

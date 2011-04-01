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
}

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

package at.silverstrike.pcc.api.webguibus;

import at.silverstrike.pcc.api.model.Event;
import at.silverstrike.pcc.api.model.Task;

public interface WebGuiBusListener {
    void taskCreated(final Task aNewTask);

    void taskCreationFailure();

    void taskEdited(final Task aTask);

    void eventCreated(final Event aNewEvent);

    void eventCreationFailure();
}

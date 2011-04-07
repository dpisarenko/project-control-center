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
import at.silverstrike.pcc.api.model.Milestone;
import at.silverstrike.pcc.api.model.Task;

public interface WebGuiBus {
    void addListener(final WebGuiBusListener aListener);

    void broadcastTaskCreatedMessage(final Task aNewTask);

    void broadcastTaskCreationFailureMessage();

    /**
     * Сообщение рассылается, когда у существующего дела меняются данные.
     */
    void broadcastTaskEditedMessage(final Task aTask);
    
    /**
     * Сообщение рассылается при успешном создании нового события.
     */
    void broadcastEventCreatedMessage(final Event aNewEvent);

    /**
     * Сообщение рассылается при сбое при попытке создать событие.
     */
    void broadcastEventCreationFailureMessage();

	void broadcastMilestoneCreatedMessage(final Milestone aMilestone);
	
	void broadcastMilestoneCreationFailureMessage();
}

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

package at.silverstrike.pcc.impl.eventeditingpanel;

import at.silverstrike.pcc.api.eventeditingpanel.EventEditingPanel;
import at.silverstrike.pcc.api.eventeditingpanel.EventEditingPanelFactory;

/**
 * @author Dmitri Pisarenko
 * 
 */
public class DefaultMeetingEditingPanelFactory implements
		EventEditingPanelFactory {
    /**
     * @see at.silverstrike.pcc.api.conventions.Factory#create()
     */
    @Override
    public final EventEditingPanel create() {
        return new DefaultMeetingEditingPanel();
    }

}
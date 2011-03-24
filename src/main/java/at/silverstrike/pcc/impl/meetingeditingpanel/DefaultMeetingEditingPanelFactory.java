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

package at.silverstrike.pcc.impl.meetingeditingpanel;

import at.silverstrike.pcc.api.meetingeditingpanel.MeetingEditingPanel;
import at.silverstrike.pcc.api.meetingeditingpanel.MeetingEditingPanelFactory;

/**
 * @author Dmitri Pisarenko
 * 
 */
public class DefaultMeetingEditingPanelFactory implements
		MeetingEditingPanelFactory {
    /**
     * @see at.silverstrike.pcc.api.conventions.Factory#create()
     */
    @Override
    public final MeetingEditingPanel create() {
        return new DefaultMeetingEditingPanel();
    }

}
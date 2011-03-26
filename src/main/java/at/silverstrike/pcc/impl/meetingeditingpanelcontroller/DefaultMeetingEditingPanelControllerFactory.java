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

package at.silverstrike.pcc.impl.meetingeditingpanelcontroller;

import at.silverstrike.pcc.api.meetingeditingpanelcontroller.MeetingEditingPanelController;
import at.silverstrike.pcc.api.meetingeditingpanelcontroller.MeetingEditingPanelControllerFactory;

/**
 * @author DP118M
 *
 */
public class DefaultMeetingEditingPanelControllerFactory implements
        MeetingEditingPanelControllerFactory {

    @Override
    public final MeetingEditingPanelController create() {
        return new DefaultMeetingEditingPanelController();
    }
}

/**
 * This file is part of Project Control Center (PCC).
 * 
 * PCC (Project Control Center) project is intellectual property of 
 * Dmitri Anatol'evich Pisarenko.
 * 
 * Copyright 2010 Dmitri Anatol'evich Pisarenko
 * All rights reserved
 *
 **/

package at.silverstrike.pcc.impl.meetingeditingpanel;

import at.silverstrike.pcc.api.conventions.ModuleWithInjectableDependencies;

interface MeetingEditingPanelController extends ModuleWithInjectableDependencies {
    void dependEditButtonClicked();
    
}
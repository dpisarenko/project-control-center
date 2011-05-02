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

package at.silverstrike.pcc.api.schedulingindicatorpanel;

import at.silverstrike.pcc.api.schedulingguicontroller.SchedulingState;
import ru.altruix.commons.api.vaadin.AbstractedPanel;

/**
 * @author DP118M
 *
 */
public interface SchedulingIndicatorPanel extends AbstractedPanel {
    void displayState(final SchedulingState aState);
}

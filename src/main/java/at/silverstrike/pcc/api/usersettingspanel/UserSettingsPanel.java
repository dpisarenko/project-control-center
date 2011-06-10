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

package at.silverstrike.pcc.api.usersettingspanel;

import ru.altruix.commons.api.gui.ExternallyControlledGuiComponent;
import ru.altruix.commons.api.vaadin.AbstractedPanel;
import at.silverstrike.pcc.api.usersettingspanelcontroller.UserSettingsPanelController;

import com.vaadin.ui.Panel;

/**
 * @author DP118M
 * 
 */
public interface UserSettingsPanel extends
        AbstractedPanel,
        ExternallyControlledGuiComponent<UserSettingsPanelController, Panel> {

}

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
package at.silverstrike.pcc.api.dailyplanpanel;

import at.silverstrike.pcc.api.conventions.AbstractedPanel;
import at.silverstrike.pcc.api.conventions.InitializableGuiComponent;
import at.silverstrike.pcc.api.conventions.ModuleWithInjectableDependencies;

/**
 * @author Dmitri Pisarenko
 *
 */
public interface DailyPlanPanel extends AbstractedPanel,
		ModuleWithInjectableDependencies, InitializableGuiComponent {
    /**
     * Attaches the panel to the application.
     */
    void attach();
}

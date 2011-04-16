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

package ru.altruix.commons.api.vaadin;

import ru.altruix.commons.api.gui.InitializableGuiComponent;

import com.vaadin.ui.Layout;

/**
 * @author DP118M
 *
 */
public interface AbstractedLayout extends InitializableGuiComponent {
    Layout toLayout();
}

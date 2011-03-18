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

package at.silverstrike.pcc.api.conventions;

import com.vaadin.ui.Layout;

/**
 * @author DP118M
 *
 */
public interface AbstractedLayout extends InitializableGuiComponent {
    Layout toLayout();
}

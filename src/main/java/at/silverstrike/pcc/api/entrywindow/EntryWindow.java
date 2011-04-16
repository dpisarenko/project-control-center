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

package at.silverstrike.pcc.api.entrywindow;

import javax.servlet.http.HttpServletRequest;

import ru.altruix.commons.api.di.ModuleWithInjectableDependencies;
import ru.altruix.commons.api.vaadin.AbstractedWindow;
import ru.altruix.commons.api.vaadin.PccWindow;

import at.silverstrike.pcc.api.conventions.InitializableGuiComponent;

public interface EntryWindow extends ModuleWithInjectableDependencies,
        InitializableGuiComponent, AbstractedWindow {

    void setRequest(final HttpServletRequest aRequest);
}

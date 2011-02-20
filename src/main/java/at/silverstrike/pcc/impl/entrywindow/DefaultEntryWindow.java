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

package at.silverstrike.pcc.impl.entrywindow;

import com.google.inject.Injector;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

import eu.livotov.tpt.i18n.TM;

import at.silverstrike.pcc.api.entrywindow.EntryWindow;

class DefaultEntryWindow implements EntryWindow {
	private Window window;
	
	@Override
	public void setInjector(final Injector anInjector) {
	}

	@Override
	public void initGui() {
		window = new Window(TM.get("mainwindow.1-title"));
		
		final Label label = new Label("");
		
		window.addComponent(label);
		
		
	}

	@Override
	public Window getWindow() {
		return this.window;
	}

}

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

package at.silverstrike.pcc;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.silverstrike.pcc.api.entrywindow.EntryWindow;
import at.silverstrike.pcc.api.entrywindow.EntryWindowFactory;
import at.silverstrike.pcc.api.injectorfactory.InjectorFactory;
import at.silverstrike.pcc.api.mainwindow.MainWindow;
import at.silverstrike.pcc.api.mainwindow.MainWindowFactory;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.impl.injectorfactory.DefaultInjectorFactory;

import com.google.inject.Injector;
import com.vaadin.terminal.ParameterHandler;
import com.vaadin.ui.Window;

import eu.livotov.tpt.TPTApplication;
import eu.livotov.tpt.i18n.TM;

public class ProjectControlCenterApplication extends TPTApplication implements
		ParameterHandler {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ProjectControlCenterApplication.class);
	private static final String THEME = "pcc";

	private static final long serialVersionUID = 1L;

	private transient Persistence persistence;

	@Override
	public void close() {
		super.close();
		closeSession();
	}

	protected void closeSession() {
		if (this.persistence != null) {
			this.persistence.closeSession();
		}
	}

	@Override
	public void applicationInit() {
		LOGGER.info("PCC application starts");

		setTheme(THEME);
		TM.getDictionary().setDefaultLanguage("en");

		this.setUser("DP");

		InjectorFactory injectorFactory = new DefaultInjectorFactory();
		Injector injector = injectorFactory.createInjector();

		persistence = injector.getInstance(Persistence.class);

		persistence.openSession();

		final EntryWindowFactory entryWindowFactory = injector
				.getInstance(EntryWindowFactory.class);
		final EntryWindow entryWindow = entryWindowFactory.create();

		entryWindow.setInjector(injector);
		entryWindow.initGui();
		
		setMainWindow(entryWindow.getWindow());
		
		// final MainWindowFactory mainWindowFactory = injector
		// .getInstance(MainWindowFactory.class);
		// final MainWindow mainWindow = mainWindowFactory.create();
		//
		// mainWindow.setInjector(injector);
		// mainWindow.initGui();
		//
		// final Window vaadinWindow = mainWindow.getWindow();
		//
		// vaadinWindow.addParameterHandler(this);
		//
		// setMainWindow(vaadinWindow);
	}

	@Override
	public void firstApplicationStartup() {
	}

	@Override
	public void handleParameters(final Map<String, String[]> aParameters) {
		LOGGER.debug("Parameters (START)");

		if (aParameters != null) {
			for (final String curKey : aParameters.keySet()) {
				LOGGER.debug("Key: " + curKey);

				final StringBuilder builder = new StringBuilder();

				builder.append("Values: ");

				for (final String valueElement : aParameters.get(curKey)) {
					builder.append(valueElement);
					builder.append(", ");
				}

				LOGGER.debug(builder.toString());
			}
		}
		LOGGER.debug("Parameters (END)");
	}
}

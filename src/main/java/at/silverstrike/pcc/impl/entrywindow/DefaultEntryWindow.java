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

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Injector;
import com.vaadin.terminal.ParameterHandler;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

import eu.livotov.tpt.i18n.TM;

import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.culture2lang.CultureToLanguageMapper;
import at.silverstrike.pcc.api.entrywindow.EntryWindow;
import at.silverstrike.pcc.api.parameterdatareader.ParameterDataReader;

class DefaultEntryWindow implements EntryWindow, ParameterHandler {
	private static final long serialVersionUID = 1L;
	private Window window;
	private ParameterDataReader parameterDataReader;
	private CultureToLanguageMapper cultureToLanguageMapper;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(DefaultEntryWindow.class);

	@Override
	public void setInjector(final Injector aInjector) {
		if (aInjector != null) {
			this.parameterDataReader = aInjector
					.getInstance(ParameterDataReader.class);
			this.cultureToLanguageMapper = aInjector
					.getInstance(CultureToLanguageMapper.class);
		}
	}

	@Override
	public void initGui() {
		window = new Window(TM.get("mainwindow.1-title"));
		window.addParameterHandler(this);

		final Label label = new Label("Language: "
				+ TM.getDictionary().getDefaultLanguage());

		window.addComponent(label);

	}

	@Override
	public Window getWindow() {
		return this.window;
	}

	@Override
	public void handleParameters(final Map<String, String[]> aParameters) {
		this.parameterDataReader.setParameters(aParameters);
		try {
			this.parameterDataReader.run();
		} catch (final PccException exception) {
			LOGGER.error("", exception);
		}

		final String culture = this.parameterDataReader.getCulture();

		this.cultureToLanguageMapper.setCulture(culture);
		try {
			this.cultureToLanguageMapper.run();
		} catch (final PccException exception) {
			LOGGER.error("", exception);
		}

		final String language = this.cultureToLanguageMapper.getLanguage();

		TM.getDictionary().setDefaultLanguage(language);
	}

}

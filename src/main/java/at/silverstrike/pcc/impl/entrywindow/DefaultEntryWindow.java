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

import static at.silverstrike.pcc.impl.entrywindow.ErrorCodes.M_001_HANDLE_PARAMETERS_1;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Injector;
import com.vaadin.terminal.ParameterHandler;
import com.vaadin.terminal.gwt.server.HttpServletRequestListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

import eu.livotov.tpt.i18n.TM;

import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.culture2lang.CultureToLanguageMapper;
import at.silverstrike.pcc.api.entrywindow.EntryWindow;
import at.silverstrike.pcc.api.parameterdatareader.ParameterDataReader;

class DefaultEntryWindow implements EntryWindow, ParameterHandler,
        HttpServletRequestListener {
    private static final int OPEN_ID_TEXT_FIELD_COLUMNS = 30;
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultEntryWindow.class);
    private ParameterDataReader parameterDataReader;
    private CultureToLanguageMapper cultureToLanguageMapper;
    private Window window;
    private Panel authPanel;
    private Label openIdLabel;
    private TextField openIdTextField;
    private Button authenticateButton;
    private Label signupLabel;
    private Injector injector;
    private HttpServletRequest request;

    @Override
    public void setInjector(final Injector aInjector) {
        if (aInjector != null) {
            this.parameterDataReader = aInjector
                    .getInstance(ParameterDataReader.class);
            this.cultureToLanguageMapper = aInjector
                    .getInstance(CultureToLanguageMapper.class);
            this.injector = aInjector;
        }
    }

    @Override
    public void initGui() {
        window = new Window();
        window.addParameterHandler(this);
        window.setSizeFull();

        final GridLayout layout = new GridLayout(2, 1);

        layout.setSizeFull();
        initAuthPanel();

        this.signupLabel = new Label("", Label.CONTENT_XHTML);

        layout.addComponent(this.signupLabel, 0, 0);
        layout.addComponent(this.authPanel, 1, 0);

        window.addComponent(layout);

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
            LOGGER.error(M_001_HANDLE_PARAMETERS_1, exception);
        }

        final String culture = this.parameterDataReader.getCulture();

        this.cultureToLanguageMapper.setCulture(culture);
        try {
            this.cultureToLanguageMapper.run();
        } catch (final PccException exception) {
            LOGGER.error(ErrorCodes.M_001_HANDLE_PARAMETERS_2, exception);
        }

        final String language = this.cultureToLanguageMapper.getLanguage();

        LOGGER.debug("{}: Culture='{}', language='{}'", new Object[] {
                ErrorCodes.M_001_HANDLE_PARAMETERS_3, culture, language });
        TM.getDictionary().setDefaultLanguage(language);

        updateControls();
    }

    private void updateControls() {
        this.window.setCaption(TM.get("entrywindow.1-title"));
        this.signupLabel.setValue(TM.get("entrywindow.4-signuplabel"));
        updateCaptionsAuthPanel();
    }

    private void updateCaptionsAuthPanel() {
        this.openIdLabel.setValue(TM.get("entrywindow.2-openIdLabel"));
        this.authenticateButton.setCaption(TM
                .get("entrywindow.3-authenticateButton"));

        this.openIdLabel.setSizeFull();
        this.authenticateButton.setSizeFull();
    }

    private void initAuthPanel() {
        final GridLayout gridLayout = new GridLayout(2, 2);

        this.authPanel = new Panel();

        openIdLabel = new Label();
        openIdTextField = new TextField();
        openIdTextField.setColumns(OPEN_ID_TEXT_FIELD_COLUMNS);
        authenticateButton = new Button();

        final AuthenticateButtonListener listener =
                new AuthenticateButtonListener();
        listener.setInjector(this.injector);
        listener.setRequest(this.request);
        this.authenticateButton.addListener(listener);

        gridLayout.addComponent(openIdLabel, 0, 0);
        gridLayout.addComponent(openIdTextField, 1, 0);
        gridLayout.addComponent(authenticateButton, 0, 1, 1, 1);

        this.authPanel.addComponent(gridLayout);
        this.authPanel.setSizeFull();
    }

    @Override
    public void onRequestStart(final HttpServletRequest aRequest,
            final HttpServletResponse aResponse) {
        this.request = aRequest;
    }

    @Override
    public void onRequestEnd(final HttpServletRequest aRequest,
            final HttpServletResponse aResponse) {
    }
}

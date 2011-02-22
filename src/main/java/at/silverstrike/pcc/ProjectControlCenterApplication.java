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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.entrywindow.EntryWindow;
import at.silverstrike.pcc.api.entrywindow.EntryWindowFactory;
import at.silverstrike.pcc.api.injectorfactory.InjectorFactory;
import at.silverstrike.pcc.api.mainwindow.MainWindow;
import at.silverstrike.pcc.api.mainwindow.MainWindowFactory;
import at.silverstrike.pcc.api.openid.OpenIdAuthenticationResponder;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.impl.injectorfactory.DefaultInjectorFactory;

import com.google.inject.Injector;
import com.vaadin.terminal.gwt.server.HttpServletRequestListener;

import eu.livotov.tpt.TPTApplication;
import eu.livotov.tpt.i18n.TM;

public class ProjectControlCenterApplication extends TPTApplication implements
        HttpServletRequestListener {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ProjectControlCenterApplication.class);
    private static final String THEME = "pcc";
    private static final boolean OPENID_DEBUGGED = true;

    private static final long serialVersionUID = 1L;

    private transient Persistence persistence;
    private Injector injector;
    private EntryWindow entryWindow;
    private HttpServletRequest request;

    @Override
    public final void close() {
        super.close();
        closeSession();
    }

    protected final void closeSession() {
        if (this.persistence != null) {
            this.persistence.closeSession();
        }
    }

    @Override
    public final void applicationInit() {
        LOGGER.info("PCC application starts");

        setTheme(THEME);
        TM.getDictionary().setDefaultLanguage("en");

        this.setUser("DP");

        final InjectorFactory injectorFactory = new DefaultInjectorFactory();
        injector = injectorFactory.createInjector();

        persistence = injector.getInstance(Persistence.class);

        persistence.openSession();

        if (OPENID_DEBUGGED) {
            final EntryWindowFactory entryWindowFactory = injector
                    .getInstance(EntryWindowFactory.class);
            entryWindow = entryWindowFactory.create();

            if (this.request == null) {
                LOGGER.debug("{}: request is null.",
                        new Object[] { "000.001" });
            } else {
                LOGGER.debug("{}: request is not null.",
                        new Object[] { "000.001" });
            }

            entryWindow.setRequest(this.request);
            entryWindow.setInjector(injector);
            entryWindow.initGui();

            setMainWindow(entryWindow.getWindow());
        } else {
            final MainWindowFactory mainWindowFactory = injector
                    .getInstance(MainWindowFactory.class);
            final MainWindow mainWindow = mainWindowFactory.create();

            mainWindow.setInjector(injector);
            mainWindow.initGui();

            setMainWindow(mainWindow.getWindow());
        }

    }

    @Override
    public void firstApplicationStartup() {
    }

    public final void onRequestStart(final HttpServletRequest aRequest,
            final HttpServletResponse aResponse) {

        this.request = aRequest;

        if (this.injector != null) {
            final OpenIdAuthenticationResponder responder = this.injector
                    .getInstance(OpenIdAuthenticationResponder.class);

            LOGGER.debug("{}: request={}",
                    new Object[] { "000.002", aRequest });

            responder.setRequest(aRequest);
            responder.setResponse(aResponse);

            try {
                responder.run();
            } catch (final PccException exception) {
                LOGGER.error("000.003", exception);
            }

            if (responder.isValidationSuccessful()) {
                // Go to the main page
                final MainWindowFactory mainWindowFactory = injector
                        .getInstance(MainWindowFactory.class);
                final MainWindow mainWindow = mainWindowFactory.create();

                mainWindow.setInjector(injector);
                mainWindow.initGui();

                this.setUser(responder.getIdentity());

                setMainWindow(mainWindow.getWindow());

            } else {
                this.setUser(null);

                // Go to the entry page
                this.setMainWindow(this.entryWindow.getWindow());
            }
        }
    }

    @Override
    public void onRequestEnd(HttpServletRequest request,
            HttpServletResponse response) {
        // TODO Auto-generated method stub

    }
}

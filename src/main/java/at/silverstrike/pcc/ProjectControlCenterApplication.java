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

package at.silverstrike.pcc;

import javax.servlet.ServletContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.silverstrike.pcc.api.entrywindow.EntryWindow;
import at.silverstrike.pcc.api.entrywindow.EntryWindowFactory;
import at.silverstrike.pcc.api.mainwindowcontroller.MainWindowController;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.impl.injectorfactory.DefaultInjectorFactory;

import com.google.inject.Injector;
import com.vaadin.terminal.gwt.server.WebApplicationContext;

import eu.livotov.tpt.TPTApplication;
import eu.livotov.tpt.i18n.TM;

public class ProjectControlCenterApplication extends TPTApplication {
    public static final String PARAM_INJECTOR = "PARAM_INJECTOR";
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ProjectControlCenterApplication.class);
    private static final String THEME = "pcc";

    private static final long serialVersionUID = 1L;

    private transient Persistence persistence;
    private transient Injector injector;
    private EntryWindow entryWindow;
    private MainWindowController mainWindowController;

    @Override
    public final void applicationInit() {
        LOGGER.info("PCC application starts");

        setTheme(THEME);
        TM.getDictionary().setDefaultLanguage("en");

        final ServletContext servletContext =
                ((WebApplicationContext) getContext()).getHttpSession()
                        .getServletContext();

        final DefaultInjectorFactory injectorFactory =
                new DefaultInjectorFactory();
        
        final String oauthRedirectUri = servletContext.getInitParameter("OauthRedirectUri");
        final String invitationAdmins = servletContext.getInitParameter("InvitationAdmins");

        injectorFactory.setOauthRedirectUri(oauthRedirectUri);
        injectorFactory.setInvitationAdmins(invitationAdmins);
        injector = injectorFactory.createInjector();

        servletContext.setAttribute(PARAM_INJECTOR, injector);

        persistence = injector.getInstance(Persistence.class);

        persistence.openSession(Persistence.HOST_LOCAL, null, null, Persistence.DB_PRODUCTION);

        if (this.persistence.getUserCount() < 1) {
            this.persistence.createSuperUser();
        }

        final EntryWindowFactory entryWindowFactory = injector
                .getInstance(EntryWindowFactory.class);
        entryWindow = entryWindowFactory.create();
        entryWindow.setInjector(injector);
        entryWindow.initGui();

        setMainWindow(entryWindow.toWindow());
    }

    @Override
    public void firstApplicationStartup() {
    }

}

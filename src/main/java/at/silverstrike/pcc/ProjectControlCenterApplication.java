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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.silverstrike.pcc.api.entrywindow.EntryWindow;
import at.silverstrike.pcc.api.entrywindow.EntryWindowFactory;
import at.silverstrike.pcc.api.invitationguicontroller.InvitationGuiController;
import at.silverstrike.pcc.api.invitationguicontroller.InvitationGuiControllerFactory;
import at.silverstrike.pcc.api.mainwindowcontroller.MainWindowController;
import at.silverstrike.pcc.api.mainwindowcontroller.MainWindowControllerFactory;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.impl.injectorfactory.DefaultInjectorFactory;

import com.google.inject.Injector;
import com.vaadin.terminal.gwt.server.HttpServletRequestListener;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.ui.Window;

import eu.livotov.tpt.TPTApplication;
import eu.livotov.tpt.i18n.TM;

public class ProjectControlCenterApplication extends TPTApplication implements
        HttpServletRequestListener {
    public static final String PARAM_INJECTOR = "PARAM_INJECTOR";
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ProjectControlCenterApplication.class);
    private static final String THEME = "pcc";
    private static final boolean OPENID_DEBUGGED = true;
    private static final boolean TEST_INVITATION = true;

    private static final long serialVersionUID = 1L;

    private transient Persistence persistence;
    private transient Injector injector;
    private EntryWindow entryWindow;
    private transient HttpServletRequest request;
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
        final String taskJugglerPath =
                servletContext.getInitParameter("TaskJuggler path");

        injectorFactory.setTaskJugglerPath(taskJugglerPath);
        injector = injectorFactory.createInjector();

        servletContext.setAttribute(PARAM_INJECTOR, injector);

        persistence = injector.getInstance(Persistence.class);

        persistence.openSession();

        if (this.persistence.getUserCount() < 1) {
            this.persistence.createSuperUser();
        }

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

            entryWindow.setInjector(injector);
            entryWindow.initGui();

            setMainWindow(entryWindow.toWindow());
        } else if (TEST_INVITATION) {
            final InvitationGuiControllerFactory invitationGuiControllerFactory =
                    injector.getInstance(InvitationGuiControllerFactory.class);
            final InvitationGuiController controller =
                    invitationGuiControllerFactory.create();

            controller.setInjector(injector);

            final Window invitationRequestWindow = controller.initGui();

            this.setMainWindow(invitationRequestWindow);
        } else {
            final MainWindowControllerFactory mainWindowControllerFactory =
                    injector.getInstance(MainWindowControllerFactory.class);
            mainWindowController = mainWindowControllerFactory.create();

            mainWindowController.setInjector(injector);

            final Window mainWindow = mainWindowController.initGui();
            this.setMainWindow(mainWindow);
        }
    }

    @Override
    public void firstApplicationStartup() {
    }

    @Override
    public void onRequestStart(final HttpServletRequest aRequest,
            final HttpServletResponse aResponse) {
        final String queryString = aRequest.getQueryString();
        LOGGER.debug("aRequest.getQueryString(): {}, this.mainWindowController={}",
                new Object[] { queryString, this.mainWindowController });

        if (!StringUtils.isBlank(queryString) && queryString.contains("oauth")
                && (this.mainWindowController != null)) {
            this.mainWindowController.setOauthQueryString(queryString);
        }
    }

    @Override
    public void onRequestEnd(final HttpServletRequest aRequest,
            final HttpServletResponse aResponse) {
    }
}

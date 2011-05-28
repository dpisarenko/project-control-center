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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.altruix.commons.api.di.InjectorFactory;

import at.silverstrike.pcc.api.entrywindow.EntryWindow;
import at.silverstrike.pcc.api.entrywindow.EntryWindowFactory;
import at.silverstrike.pcc.api.invitationguicontroller.InvitationGuiController;
import at.silverstrike.pcc.api.invitationguicontroller.InvitationGuiControllerFactory;
import at.silverstrike.pcc.api.mainwindowcontroller.MainWindowController;
import at.silverstrike.pcc.api.mainwindowcontroller.MainWindowControllerFactory;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.impl.injectorfactory.DefaultInjectorFactory;
import at.silverstrike.pcc.impl.openid.OpenIdSingleton;

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
    private static final boolean OPENID_DEBUGGED = false;
    private static final boolean TEST_INVITATION = true;

    private static final long serialVersionUID = 1L;

    private transient Persistence persistence;
    private transient Injector injector;
    private EntryWindow entryWindow;
    private transient HttpServletRequest request;

    @Override
    public final void applicationInit() {
        LOGGER.info("PCC application starts");

        setTheme(THEME);
        TM.getDictionary().setDefaultLanguage("en");

        this.setUser("USR");

        final InjectorFactory injectorFactory = new DefaultInjectorFactory();
        injector = injectorFactory.createInjector();

        ((WebApplicationContext) getContext()).getHttpSession()
                .getServletContext().setAttribute(PARAM_INJECTOR, injector);

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
            final MainWindowController controller =
                    mainWindowControllerFactory.create();

            controller.setInjector(injector);

            final Window mainWindow = controller.initGui();
            this.setMainWindow(mainWindow);
        }
    }

    @Override
    public void firstApplicationStartup() {
    }

    public final void onRequestStart(final HttpServletRequest aRequest,
            final HttpServletResponse aResponse) {
        OpenIdSingleton.getInstance().onStartRequest(aRequest, aResponse);
        //
        // if (!OPENID_DEBUGGED) {
        // return;
        // }
        //
        // this.request = aRequest;
        //
        // if (this.injector != null) {
        // final OpenIdAuthenticationResponder responder = this.injector
        // .getInstance(OpenIdAuthenticationResponder.class);
        //
        // LOGGER.debug("{}: request={}",
        // new Object[] { "000.002", aRequest });
        //
        // responder.setRequest(aRequest);
        // responder.setResponse(aResponse);
        //
        // try {
        // responder.run();
        // } catch (final PccException exception) {
        // LOGGER.error("000.003", exception);
        // }
        //
        // if (responder.isValidationSuccessful()) {
        // // Go to the main page
        // final MainWindowFactory mainWindowFactory = injector
        // .getInstance(MainWindowFactory.class);
        // final MainWindow mainWindow = mainWindowFactory.create();
        //
        // mainWindow.setInjector(injector);
        // mainWindow.initGui();
        //
        // this.setUser(responder.getIdentity());
        //
        // setMainWindow(mainWindow.toWindow());
        //
        // } else {
        // this.setUser(null);
        //
        // // Go to the entry page
        // this.setMainWindow(this.entryWindow.toWindow());
        // }
        // }
    }

    @Override
    public void onRequestEnd(final HttpServletRequest aRequest,
            final HttpServletResponse aResponse) {
    }

}

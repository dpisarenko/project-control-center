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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.altruix.commons.api.di.InjectorFactory;
import ru.altruix.commons.api.di.PccException;

import at.silverstrike.pcc.api.entrywindow.EntryWindow;
import at.silverstrike.pcc.api.entrywindow.EntryWindowFactory;
import at.silverstrike.pcc.api.mainwindow.MainWindow;
import at.silverstrike.pcc.api.mainwindow.MainWindowFactory;
import at.silverstrike.pcc.api.mainwindowcontroller.MainWindowController;
import at.silverstrike.pcc.api.mainwindowcontroller.MainWindowControllerFactory;
import at.silverstrike.pcc.api.model.UserData;
import at.silverstrike.pcc.api.openid.OpenIdAuthenticationResponder;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.xmlserialization.XmlSerializer;
import at.silverstrike.pcc.api.xmlserialization.XmlSerializerFactory;
import at.silverstrike.pcc.impl.injectorfactory.DefaultInjectorFactory;
import at.silverstrike.pcc.impl.xmlserialization.DefaultXmlSerializerFactory;

import com.google.inject.Injector;
import com.vaadin.terminal.gwt.server.HttpServletRequestListener;

import eu.livotov.tpt.TPTApplication;
import eu.livotov.tpt.i18n.TM;

public class ProjectControlCenterApplication extends TPTApplication implements
        HttpServletRequestListener {
    private static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ProjectControlCenterApplication.class);
    private static final String THEME = "pcc";
    private static final boolean OPENID_DEBUGGED = false;

    private static final long serialVersionUID = 1L;

    private transient Persistence persistence;
    private transient Injector injector;
    private EntryWindow entryWindow;
    private transient HttpServletRequest request;

    @Override
    public final void close() {
        exportData();
        closeSession();
        super.close();
    }

    private void exportData() {
        final Persistence persistence =
                this.injector.getInstance(Persistence.class);

        final UserData writtenData = persistence.getUserData();
        ;

        final XmlSerializerFactory serializerFactory =
                new DefaultXmlSerializerFactory();
        final XmlSerializer serializer = serializerFactory.create();

        final String fileName =
                "PCC_exported_data_${datetime}.xml".replace("${datetime}",
                        TIMESTAMP_FORMAT
                                .format(new Date()));

        final File targetFile = new File(fileName);
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(targetFile);
            serializer.setOutputStream(fileOutputStream);
            serializer.setUserData(writtenData);

            serializer.run();
        } catch (final PccException exception) {
            LOGGER.error("", exception);
        } catch (final FileNotFoundException exception) {
            LOGGER.error("", exception);
        } finally {
            IOUtils.closeQuietly(fileOutputStream);
        }
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

        this.setUser("USR");

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

            setMainWindow(entryWindow.toWindow());
        } else {
            final MainWindowControllerFactory mainWindowControllerFactory =
                    injector.getInstance(MainWindowControllerFactory.class);
            final MainWindowController controller =
                    mainWindowControllerFactory.create();

            controller.setInjector(injector);
            controller.initGui(this);
        }

    }

    @Override
    public void firstApplicationStartup() {
    }

    public final void onRequestStart(final HttpServletRequest aRequest,
            final HttpServletResponse aResponse) {

        if (!OPENID_DEBUGGED) {
            return;
        }

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

                setMainWindow(mainWindow.toWindow());

            } else {
                this.setUser(null);

                // Go to the entry page
                this.setMainWindow(this.entryWindow.toWindow());
            }
        }
    }

    @Override
    public void onRequestEnd(final HttpServletRequest aRequest,
            final HttpServletResponse aResponse) {
    }
    
}

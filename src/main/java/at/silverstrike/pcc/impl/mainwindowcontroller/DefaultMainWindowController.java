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

package at.silverstrike.pcc.impl.mainwindowcontroller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.altruix.commons.api.di.PccException;

import com.google.inject.Injector;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Window;

import eu.livotov.tpt.TPTApplication;

import at.silverstrike.pcc.api.automaticexport.AutomaticExporter;
import at.silverstrike.pcc.api.automaticexport.AutomaticExporterFactory;
import at.silverstrike.pcc.api.centraleditingpanelcontroller.CentralEditingPanelController;
import at.silverstrike.pcc.api.centraleditingpanelcontroller.CentralEditingPanelControllerFactory;
import at.silverstrike.pcc.api.mainwindow.MainWindow;
import at.silverstrike.pcc.api.mainwindow.MainWindowFactory;
import at.silverstrike.pcc.api.mainwindowcontroller.MainWindowController;
import at.silverstrike.pcc.api.xmlserialization.XmlDeserializer;
import at.silverstrike.pcc.api.xmlserialization.XmlDeserializerFactory;
import at.silverstrike.pcc.impl.xmlserialization.DefaultXmlDeserializerFactory;

class DefaultMainWindowController implements MainWindowController {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultMainWindowController.class);
    private Injector injector;

    @Override
    public final void setInjector(final Injector aInjector) {
        this.injector = aInjector;
    }

    @Override
    public final void importFromXML() {
        final XmlDeserializerFactory deserializerFactory;
        deserializerFactory = new DefaultXmlDeserializerFactory();
        final XmlDeserializer deserializer = deserializerFactory.create();
        // Deserialize (start)
        FileInputStream fileInputStream = null;
        try {
            File targetFile = null;
            targetFile = new File(
                    "src/test/resources/at/silverstrike/"
                            + "pcc/test/xmlserialization/testExport.xml");
            fileInputStream = new FileInputStream(targetFile);

            deserializer.setInputStream(fileInputStream);
            deserializer.run();
        } catch (final PccException exception) {
            LOGGER.error("", exception);
        } catch (final FileNotFoundException exception) {
            LOGGER.error("", exception);
        } finally {
            IOUtils.closeQuietly(fileInputStream);
        }

        // final UserData readData = deserializer.getUserData();
        // Deserialize (end)
        TPTApplication.getCurrentApplication().getMainWindow()
                .showNotification("111Test for Import111");

    }

    @Override
    public final void exportToXML() {
        final AutomaticExporterFactory factory =
                this.injector.getInstance(AutomaticExporterFactory.class);
        final AutomaticExporter exporter = factory.create();

        exporter.setInjector(this.injector);
        try {
            exporter.run();

            final Window mainWindow =
                    TPTApplication.getCurrentApplication().getMainWindow();

            final FileDownloadResource downloadResource =
                    new FileDownloadResource(exporter.getTargetFile(),
                            TPTApplication.getCurrentApplication());

            mainWindow.open(downloadResource);

        } catch (final PccException exception) {
            LOGGER.error(ErrorCodes.M_002_SERIALIZATION_FAULT, exception);
        }
    }

    @Override
    public Window initGui() {
        final CentralEditingPanelControllerFactory ctlFactory =
                this.injector
                        .getInstance(CentralEditingPanelControllerFactory.class);
        final CentralEditingPanelController ctl = ctlFactory.create();

        ctl.setInjector(this.injector);

        final Panel centralEditingPanel = (Panel) ctl.initGui();

        final MainWindowFactory mainWindowFactory = injector
                .getInstance(MainWindowFactory.class);
        final MainWindow mainWindow = mainWindowFactory.create();

        mainWindow.setInjector(injector);
        mainWindow.setCentralEditingPanel(centralEditingPanel);
        mainWindow.setGuiController(this);
        mainWindow.initGui();
        
        return mainWindow.toWindow();
    }
}

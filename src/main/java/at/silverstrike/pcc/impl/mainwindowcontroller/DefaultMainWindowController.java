package at.silverstrike.pcc.impl.mainwindowcontroller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.google.inject.Injector;
import com.vaadin.ui.Window;

import eu.livotov.tpt.TPTApplication;

import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.mainwindowcontroller.MainWindowController;
import at.silverstrike.pcc.api.model.UserData;
import at.silverstrike.pcc.api.xmlserialization.XmlDeserializer;
import at.silverstrike.pcc.api.xmlserialization.XmlDeserializerFactory;
import at.silverstrike.pcc.api.xmlserialization.XmlSerializer;
import at.silverstrike.pcc.api.xmlserialization.XmlSerializerFactory;
import at.silverstrike.pcc.impl.xmlserialization.DefaultXmlDeserializerFactory;
import at.silverstrike.pcc.impl.xmlserialization.DefaultXmlSerializerFactory;

public class DefaultMainWindowController implements MainWindowController {

    private Injector injector = null;

    @Override
    public void setInjector(Injector aInjector) {
        this.injector = aInjector;

    }

    @Override
    public void importFromXML() {
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
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        }
        deserializer.setInputStream(fileInputStream);
        try {
            deserializer.run();
        } catch (final PccException exception) {
        }

        final UserData readData = deserializer.getUserData();
        // Deserialize (end)
        TPTApplication.getCurrentApplication().getMainWindow()
                .showNotification("111Test for Import111");

    }

    @Override
    public void exportToXML() {

        // Serialize writtenData to targetFile (start)
        // targetFile should be located somewhere
        // in test/resources/at/swdev/
        // test/xmlserialization/testSerializationDeserialization.xml
        final UserData writtenData = null;
        final XmlSerializerFactory serializerFactory =
                new DefaultXmlSerializerFactory();
        final XmlSerializer serializer = serializerFactory.create();
        File targetFile = null;
        targetFile = new File(
                "src/test/resources/at/silverstrike/"
                        + "pcc/test/xmlserialization/testExport.xml");
        FileOutputStream fileOutputStream = null;
        // Init fileOutputStream
        try {
            fileOutputStream = new FileOutputStream(targetFile);
        } catch (final FileNotFoundException exception) {
        }
        serializer.setOutputStream(fileOutputStream);
        serializer.setUserData(writtenData);
        try {
            serializer.run();
        } catch (final PccException exception) {

        }
        // Serialize writtenData to targetFile (end)

        final Window mainWindow = TPTApplication.getCurrentApplication().getMainWindow();
        mainWindow
                .showNotification("222Test for Export222");

        final DownloadableResource resource = new DownloadableResource();
        
        mainWindow.open(resource);
    }
}

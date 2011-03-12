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

package at.silverstrike.pcc.impl.mainprocesseditingpanel;

import static com.vaadin.ui.AbstractSelect.ITEM_CAPTION_MODE_PROPERTY;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.conventions.MessageCodePrefixRegistry;
import at.silverstrike.pcc.api.debugids.DebugIdRegistry;
import at.silverstrike.pcc.api.editingprocesspanel.EditingProcessPanel;
import at.silverstrike.pcc.api.editingprocesspanel.EditingProcessPanelFactory;
import at.silverstrike.pcc.api.mainprocesseditingpanel.MainProcessEditingPanel;
import at.silverstrike.pcc.api.model.ControlProcess;
import at.silverstrike.pcc.api.model.UserData;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.processpanel.ProcessPanel;
import at.silverstrike.pcc.api.processpanel.ProcessPanelFactory;
import at.silverstrike.pcc.api.processpanel.ProcessPanelListener;
import at.silverstrike.pcc.api.xmlserialization.XmlSerializer;
import at.silverstrike.pcc.api.xmlserialization.XmlSerializerFactory;

import com.google.inject.Injector;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.SplitPanel;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window.Notification;

import eu.livotov.tpt.i18n.TM;

/**
 * @author Dmitri Pisarenko
 * 
 */
class DefaultMainProcessEditingPanel extends Panel implements
        MainProcessEditingPanel, ProcessPanelListener {
    private static final String XML_DUMP_TIMESTAMP_DATE_FORMAT = "yyyy_MM_dd_HH_mm_ss";

    private static final int SPLIT_POSITION = 10;

    public static final Object PROJECT_PROPERTY_NAME = "name";

    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultMainProcessEditingPanel.class);

    private static final String XML_DUMP_FILENAME_TEMPLATE =
            "PCC_DATA_${timestamp}.xml";

    private static final Object PROJECT_PROPERTY_ID = "id";
    private static final long serialVersionUID = 1L;

    private static final Integer TREE_ROOT_ID = 0;

    private Button createChildButton;

    private Button createSiblingButton;

    private Button deleteProjectButton;

    private Injector injector;

    private Persistence persistence;

    private EditingProcessPanel processEditingPanel;

    private ProcessPanel processPanel;

    private Tree projectTree;

    private HierarchicalContainer projectTreeData;

    private Long selectedProjectId;

    private DebugIdRegistry debugIdRegistry;

    private Button exportButton;

    private VerticalLayout projectTreeLayout;

    private SplitPanel splitPanel2;

    public DefaultMainProcessEditingPanel() {

    }

    @Override
    public void initGui() {
        this.debugIdRegistry = this.injector.getInstance(DebugIdRegistry.class);

        this.setSizeFull();
        this.setDebugId(this.debugIdRegistry.getDebugId(
                MessageCodePrefixRegistry.Module.mainprocesseditingpanel, "1"));

        final SplitPanel splitPanel1 = new SplitPanel();

        splitPanel1.setOrientation(SplitPanel.ORIENTATION_HORIZONTAL);

        splitPanel1.setSizeFull();

        splitPanel1.setSplitPosition(SPLIT_POSITION);

        splitPanel1.addComponent(getTreePanel());
        splitPanel1.addComponent(getSplitPanel2());

        setContent(splitPanel1);
        this.getContent().setSizeFull();
    }

    @Override
    public void setInjector(final Injector aInjector) {
        injector = aInjector;
        persistence = injector.getInstance(Persistence.class);
    }

    @Override
    public void taskAdded() {
        updateTree();
    }

    /**
     * @see at.silverstrike.pcc.api.conventions.AbstractedPanel#toPanel()
     */
    @Override
    public Panel toPanel() {
        return this;
    }

    protected void createChildButtonClicked() {
        persistence.createChildProcess(selectedProjectId);
        updateTree();

    }

    protected void createSiblingButtonClicked() {
        persistence.createSiblingProcess(selectedProjectId);
        updateTree();
    }

    protected void deleteProjectButtonClicked() {

        if (selectedProjectId == null) {
            getWindow().showNotification(
                    TM.get("mainprocesseditingpanel.6-delete-project"),
                    Notification.TYPE_ERROR_MESSAGE);
        } else {
            persistence.deleteProcess(selectedProjectId);
            updateTree();
        }
    }

    protected void projectTreeValueChanged(final ValueChangeEvent aEvent) {
        final Object itemId = aEvent.getProperty().getValue();

        final Item item = projectTreeData.getItem(itemId);

        if (item != null) {
            selectedProjectId =
                    (Long) item.getItemProperty(PROJECT_PROPERTY_ID).getValue();

            processPanel.setParentProcessId(selectedProjectId);
            processPanel.setProcessesToShow(persistence
                    .getChildTasks(selectedProjectId));

            if (TREE_ROOT_ID.equals(itemId)) {
                deleteProjectButton.setEnabled(false);
                createSiblingButton.setEnabled(false);
            } else if (selectedProjectId != null) {
                deleteProjectButton.setEnabled(true);
                createSiblingButton.setEnabled(true);
                createChildButton.setEnabled(true);
            } else if (selectedProjectId == null) {
                deleteProjectButton.setEnabled(false);
                createSiblingButton.setEnabled(false);
                createChildButton.setEnabled(false);
            }
        }
    }

    private int addNodes(final HierarchicalContainer aContainer,
            final List<ControlProcess> aProcesses, final Integer aParentId,
            final Persistence aPersistence, final int aTreeItemId) {
        int treeItemId = aTreeItemId;
        if (aProcesses == null) {
            return treeItemId;
        }

        for (final ControlProcess process : aProcesses) {
            final int processItemId = treeItemId++;
            final Item processItem = aContainer.addItem(processItemId);

            processItem.getItemProperty(PROJECT_PROPERTY_ID).setValue(
                    process.getId());
            processItem.getItemProperty(PROJECT_PROPERTY_NAME).setValue(
                    process.getName());
            aContainer.setChildrenAllowed(processItemId, true);
            if (aParentId != null) {
                aContainer.setParent(processItemId, aParentId);
            } else {
                aContainer.setParent(processItemId, TREE_ROOT_ID);
            }

            final List<ControlProcess> subProcessesWithChildren =
                    aPersistence.getSubProcessesWithChildren(process.getId());
            treeItemId =
                    addNodes(aContainer, subProcessesWithChildren,
                            processItemId, aPersistence, treeItemId);
        }

        return treeItemId;
    }

    private HorizontalLayout getProcessEditingPanel() {
        final HorizontalLayout panel = new HorizontalLayout();
        final EditingProcessPanelFactory factory =
                injector.getInstance(EditingProcessPanelFactory.class);
        processEditingPanel = factory.create();
        processEditingPanel.setInjector(injector);
        processEditingPanel.initGui();
        final Panel vaadinPanel = processEditingPanel.toPanel();
        vaadinPanel.setSizeFull();
        panel.addComponent(vaadinPanel);

        return panel;
    }

    private HorizontalLayout getProcessListPanel() {
        final HorizontalLayout panel = new HorizontalLayout();
        final ProcessPanelFactory processPanelFactory =
                injector.getInstance(ProcessPanelFactory.class);
        processPanel = processPanelFactory.create();

        processPanel.setInjector(injector);
        processPanel.setProcessPanelListener(this);
        processPanel.initGui();
        processPanel.setProcessesToShow(persistence.getAllNotDeletedTasks());

        final Panel vaadinPanel = processPanel.toPanel();

        vaadinPanel.setSizeFull();

        panel.addComponent(vaadinPanel);

        return panel;
    }

    private HierarchicalContainer getProjectTreeData() {
        final HierarchicalContainer returnValue =
                new HierarchicalContainer();
        returnValue.addContainerProperty(PROJECT_PROPERTY_ID, Long.class,
                null);
        returnValue.addContainerProperty(PROJECT_PROPERTY_NAME,
                String.class, null);

        final List<ControlProcess> topLevelProcesses =
                persistence.getSubProcessesWithChildren(null);

        if (topLevelProcesses != null) {
            LOGGER.debug("topLevelProcesses: " + topLevelProcesses.size());
        }

        final Item root = returnValue.addItem(TREE_ROOT_ID);
        root.getItemProperty(PROJECT_PROPERTY_ID).setValue(null);
        root.getItemProperty(PROJECT_PROPERTY_NAME).setValue(
                TM.get("mainprocesseditingpanel.7-root"));

        addNodes(returnValue, topLevelProcesses, null, persistence, 1);

        return returnValue;
    }

    private Component getSplitPanel2() {
        splitPanel2 = new SplitPanel();

        splitPanel2.setSizeFull();

        splitPanel2.setOrientation(SplitPanel.ORIENTATION_HORIZONTAL);
        splitPanel2.addComponent(getProcessListPanel());
        splitPanel2.addComponent(getProcessEditingPanel());

        processPanel.setEditingProcessPanel(processEditingPanel);

        return splitPanel2;
    }

    private Component getTreePanel() {
        projectTreeLayout = new VerticalLayout();
        projectTree =
                new Tree(
                        TM.get("mainprocesseditingpanel.1-projectTree-caption"));
        projectTree.setImmediate(true);
        updateTree();
        projectTree.setItemCaptionPropertyId(PROJECT_PROPERTY_NAME);
        projectTree.setItemCaptionMode(ITEM_CAPTION_MODE_PROPERTY);
        projectTree.addListener(new Property.ValueChangeListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void valueChange(final ValueChangeEvent aEvent) {
                projectTreeValueChanged(aEvent);
            }
        });

        projectTree.setSizeFull();

        this.setSizeFull();
        projectTreeLayout.addComponent(projectTree);

        createSiblingButton =
                new Button(
                        TM.get("mainprocesseditingpanel.2-createSiblingButton"));

        createSiblingButton.setDebugId(this.debugIdRegistry.getDebugId(
                MessageCodePrefixRegistry.Module.mainprocesseditingpanel, "2"));

        createChildButton =
                new Button(
                        TM.get("mainprocesseditingpanel.5-createChildButton"));
        createChildButton.setDebugId(this.debugIdRegistry.getDebugId(
                MessageCodePrefixRegistry.Module.mainprocesseditingpanel, "3"));

        deleteProjectButton =
                new Button(
                        TM.get("mainprocesseditingpanel.4-deleteProjectButton"));

        deleteProjectButton.setEnabled(false);

        exportButton =
                new Button(TM.get("mainprocesseditingpanel.8-export-xml"));

        projectTreeLayout.addComponent(createSiblingButton);
        projectTreeLayout.addComponent(createChildButton);
        projectTreeLayout.addComponent(deleteProjectButton);
        projectTreeLayout.addComponent(exportButton);

        createSiblingButton.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final ClickEvent aEvent) {
                createSiblingButtonClicked();
            }
        });

        createChildButton.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final ClickEvent aEvent) {
                createChildButtonClicked();
            }
        });

        deleteProjectButton.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final ClickEvent aEvent) {
                deleteProjectButtonClicked();
            }
        });

        exportButton.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final ClickEvent aEvent) {
                exportButtonClicked();
            }
        });

        return projectTreeLayout;
    }

    private DateFormat getXmlDumpTimestampDateFormat()
    {
        return new SimpleDateFormat(XML_DUMP_TIMESTAMP_DATE_FORMAT);
    }
    
    protected void exportButtonClicked() {
        final UserData userData = this.persistence.getUserData();
        final XmlSerializerFactory serializerFactory =
                this.injector.getInstance(XmlSerializerFactory.class);
        final XmlSerializer serializer = serializerFactory.create();

        FileOutputStream fileOutputStream = null;

        try {
            final String filename =
                    XML_DUMP_FILENAME_TEMPLATE.replace("${timestamp}",
                            getXmlDumpTimestampDateFormat().format(new Date()));

            final File file = new File(filename);

            LOGGER.debug("Exporting data to XML file '{}'",
                    file.getAbsolutePath());

            fileOutputStream = new FileOutputStream(file);
            serializer.setOutputStream(fileOutputStream);
            serializer.setUserData(userData);
            serializer.run();
        } catch (final FileNotFoundException exception) {
            LOGGER.error(ErrorCodes.M_001_EXPORT_FAILURE, exception);
        } catch (final PccException exception) {
            LOGGER.error(ErrorCodes.M_001_EXPORT_FAILURE, exception);
        } finally {
            IOUtils.closeQuietly(fileOutputStream);
        }
    }

    private void updateTree() {
        projectTreeData = getProjectTreeData();
        projectTree.setContainerDataSource(projectTreeData);
        projectTree.expandItemsRecursively(TREE_ROOT_ID);
    }
}

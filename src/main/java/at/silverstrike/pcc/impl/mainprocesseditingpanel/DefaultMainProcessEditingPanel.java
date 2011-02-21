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
    public static final Object PROJECT_PROPERTY_NAME = "name";

    private final static Logger LOGGER = LoggerFactory
            .getLogger(DefaultMainProcessEditingPanel.class);

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

    public DefaultMainProcessEditingPanel() {

    }

    @Override
    public void initGui() {
        this.debugIdRegistry = this.injector.getInstance(DebugIdRegistry.class);

        this.setDebugId(this.debugIdRegistry.getDebugId(
                MessageCodePrefixRegistry.Module.mainprocesseditingpanel, "1"));

        final SplitPanel splitPanel1 = new SplitPanel();

        splitPanel1.setOrientation(SplitPanel.ORIENTATION_HORIZONTAL);
        splitPanel1.setHeight("600px");
        splitPanel1.setWidth("100%");

        splitPanel1.setSplitPosition(10);

        splitPanel1.addComponent(getTreePanel());
        splitPanel1.addComponent(getSplitPanel2());

        addComponent(splitPanel1);
    }

    @Override
    public void setInjector(final Injector anInjector) {
        injector = anInjector;
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

    protected void projectTreeValueChanged(final ValueChangeEvent event) {
        final Object itemId = event.getProperty().getValue();

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

    private int addNodes(final HierarchicalContainer container,
            final List<ControlProcess> processes, final Integer parentId,
            final Persistence persistence, final int aTreeItemId) {
        int treeItemId = aTreeItemId;
        if (processes == null) {
            return treeItemId;
        }

        for (final ControlProcess process : processes) {
            final int processItemId = treeItemId++;
            final Item processItem = container.addItem(processItemId);

            processItem.getItemProperty(PROJECT_PROPERTY_ID).setValue(
                    process.getId());
            processItem.getItemProperty(PROJECT_PROPERTY_NAME).setValue(
                    process.getName());
            container.setChildrenAllowed(processItemId, true);
            if (parentId != null) {
                container.setParent(processItemId, parentId);
            } else {
                container.setParent(processItemId, TREE_ROOT_ID);
            }

            final List<ControlProcess> subProcessesWithChildren =
                    persistence.getSubProcessesWithChildren(process.getId());
            treeItemId =
                    addNodes(container, subProcessesWithChildren,
                            processItemId, persistence, treeItemId);
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
        panel.addComponent(processEditingPanel.toPanel());

        return panel;
    }

    private HorizontalLayout getProcessListPanel() {
        final HorizontalLayout panel = new HorizontalLayout();
        final ProcessPanelFactory processPanelFactory =
                injector.getInstance(ProcessPanelFactory.class);
        processPanel = processPanelFactory.create();

        final Persistence persistence = injector.getInstance(Persistence.class);

        processPanel.setInjector(injector);
        processPanel.setProcessPanelListener(this);
        processPanel.initGui();
        processPanel.setProcessesToShow(persistence.getAllNotDeletedTasks());

        panel.addComponent(processPanel.toPanel());

        return panel;
    }

    private HierarchicalContainer getProjectTreeData() {
        final HierarchicalContainer projectTreeData =
                new HierarchicalContainer();
        projectTreeData.addContainerProperty(PROJECT_PROPERTY_ID, Long.class,
                null);
        projectTreeData.addContainerProperty(PROJECT_PROPERTY_NAME,
                String.class, null);

        final List<ControlProcess> topLevelProcesses =
                persistence.getSubProcessesWithChildren(null);

        if (topLevelProcesses != null) {
            LOGGER.debug("topLevelProcesses: " + topLevelProcesses.size());
        }

        final Item root = projectTreeData.addItem(TREE_ROOT_ID);
        root.getItemProperty(PROJECT_PROPERTY_ID).setValue(null);
        root.getItemProperty(PROJECT_PROPERTY_NAME).setValue(
                TM.get("mainprocesseditingpanel.7-root"));

        addNodes(projectTreeData, topLevelProcesses, null, persistence, 1);

        return projectTreeData;
    }

    private Component getSplitPanel2() {
        final SplitPanel splitPanel2 = new SplitPanel();

        splitPanel2.setOrientation(SplitPanel.ORIENTATION_HORIZONTAL);
        splitPanel2.addComponent(getProcessListPanel());
        splitPanel2.addComponent(getProcessEditingPanel());

        processPanel.setEditingProcessPanel(processEditingPanel);

        return splitPanel2;
    }

    private Component getTreePanel() {
        final VerticalLayout layout = new VerticalLayout();
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
            public void valueChange(final ValueChangeEvent event) {
                projectTreeValueChanged(event);
            }
        });

        layout.addComponent(projectTree);

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

        layout.addComponent(createSiblingButton);
        layout.addComponent(createChildButton);
        layout.addComponent(deleteProjectButton);
        layout.addComponent(exportButton);

        createSiblingButton.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final ClickEvent event) {
                createSiblingButtonClicked();
            }
        });

        createChildButton.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final ClickEvent event) {
                createChildButtonClicked();
            }
        });

        deleteProjectButton.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final ClickEvent event) {
                deleteProjectButtonClicked();
            }
        });

        exportButton.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                exportButtonClicked();
            }
        });

        return layout;
    }

    private final static String XML_DUMP_FILENAME_TEMPLATE =
            "PCC_DATA_${timestamp}.xml";
    private DateFormat XML_DUMP_TIMESTAMP_DATE_FORMAT = new SimpleDateFormat(
            "yyyy_MM_dd_HH_mm_ss");

    protected void exportButtonClicked() {
        final UserData userData = this.persistence.getUserData();
        final XmlSerializerFactory serializerFactory =
                this.injector.getInstance(XmlSerializerFactory.class);
        final XmlSerializer serializer = serializerFactory.create();

        FileOutputStream fileOutputStream = null;

        try {
            final String filename =
                    XML_DUMP_FILENAME_TEMPLATE.replace("${timestamp}",
                            XML_DUMP_TIMESTAMP_DATE_FORMAT.format(new Date()));

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

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

package at.silverstrike.pcc.test.projecttreemodel;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author DP118M
 *
 */
public class TestDefaultProjectTreeContainer {
    private final static Logger LOGGER =
        LoggerFactory.getLogger(TestDefaultProjectTreeContainer.class);
    
    /**
     * Test case for reproducing this defect:
     * <pre>
     * java.lang.NullPointerException
     *      at com.vaadin.data.util.IndexedContainer$IndexedContainerProperty.setValue(IndexedContainer.java:1291)
     *      at at.silverstrike.pcc.impl.projecttreemodel.DefaultProjectTreeContainer.addNodes(DefaultProjectTreeContainer.java:73)
     *      at at.silverstrike.pcc.impl.projecttreemodel.DefaultProjectTreeContainer.updateData(DefaultProjectTreeContainer.java:56)
     *      at at.silverstrike.pcc.impl.centraleditingpanel.DefaultCentralEditingPanel.initTreeModel(DefaultCentralEditingPanel.java:207)
     *      at at.silverstrike.pcc.impl.centraleditingpanel.DefaultCentralEditingPanel.initGui(DefaultCentralEditingPanel.java:185)
     *      at at.silverstrike.pcc.impl.mainwindow.DefaultMainWindow.getCentralEditingPanel(DefaultMainWindow.java:202)
     *      at at.silverstrike.pcc.impl.mainwindow.DefaultMainWindow.initGui(DefaultMainWindow.java:89)
     * </pre>
     */
    @Test
    public void testNullPointerExceptionInAddNodes() {
        
    }
}

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

package at.silverstrike.pcc.test.export2tj3;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.silverstrike.pcc.api.model.ControlProcess;
import at.silverstrike.pcc.api.model.Resource;
import at.silverstrike.pcc.api.model.ResourceAllocation;
import at.silverstrike.pcc.test.mockpersistence.MockObjectFactory;

class Helper {
    private final static Logger LOGGER =
        LoggerFactory.getLogger(Helper.class);

    private MockObjectFactory MOCK_OBJECT_FACTORY = new MockObjectFactory();
    
    public String readStringFromFile(final File file) {
        String expectedResult = null;
        
        try {
            expectedResult = FileUtils.readFileToString(file);
        } catch (final IOException exception) {
            LOGGER.error("", exception);
            fail(exception.getMessage());
        }
        return expectedResult;
    }

    public void writeStringToFile(final String actualResult, final File file) {
        try {
            FileUtils.writeStringToFile(file,
                    actualResult);
        } catch (final IOException exception) {
            LOGGER.error("", exception);
            fail(exception.getMessage());
        }
    }

    public Date getDate18October2010() {
        final GregorianCalendar calendar =
                new GregorianCalendar(2010, Calendar.OCTOBER, 18);
        final Date now = calendar.getTime();
        return now;
    }

    public List<ControlProcess> getTestRun01Processes() {
        final List<ControlProcess> processes = new LinkedList<ControlProcess>();
        
        processes.add(MOCK_OBJECT_FACTORY.createControlProcess(null));
        processes.add(MOCK_OBJECT_FACTORY.createControlProcess(null));
        
        return processes;
    }

    public List<Resource> getTestRun01Resources() {
        final List<Resource> resources = new LinkedList<Resource>();
        
        resources.add(MOCK_OBJECT_FACTORY.createResource(null));
        
        return resources;
    }

    public List<Resource> getTestRun03Resources() {
        final List<Resource> resources = new LinkedList<Resource>();

        resources.add(getWorker1210());
        
        return resources;

    }

    private Resource getWorker1210() {
        final Resource worker = MOCK_OBJECT_FACTORY.createResource(1210L);
        
        worker.setAbbreviation("DP");
        worker.setDailyLimitInHours(8);
        return worker;
    }

    public List<ControlProcess> getTestRun03Processes() {
        final List<ControlProcess> processes = new LinkedList<ControlProcess>();
        
        final ControlProcess task = MOCK_OBJECT_FACTORY.createControlProcess(2010L);
        
        task.setName("Some interesting task");
        task.setPriority(200);
        task.setBestCaseEffort(2.5);
        task.setWorstCaseEffort(2.5);
        
        final ResourceAllocation resourceAllocation = MOCK_OBJECT_FACTORY.createResourceAllocation();
        resourceAllocation.setResource(getWorker1210());
        
        assertNotNull(task.getResourceAllocations());
        
        task.getResourceAllocations().add(resourceAllocation);
        
        processes.add(task);
        
        return processes;
    }
    public List<ControlProcess> getTestDefect59Processes() {
        final List<ControlProcess> processes = new LinkedList<ControlProcess>();
        
        final ControlProcess task = MOCK_OBJECT_FACTORY.createControlProcess(null);
        
        task.setName(null);
        task.setPriority(null);
        
        Assert.assertNull(task.getName());
        Assert.assertNull(task.getId());
        Assert.assertNull(task.getPriority());
        Assert.assertNull(task.getBestCaseEffort());
        Assert.assertNull(task.getWorstCaseEffort());
        
        final ResourceAllocation resourceAllocation = MOCK_OBJECT_FACTORY.createResourceAllocation();
        resourceAllocation.setResource(getWorker1210());
        
        assertNotNull(task.getResourceAllocations());
        
        task.getResourceAllocations().add(resourceAllocation);
        
        processes.add(task);
        
        return processes;
    }

}

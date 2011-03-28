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

package at.silverstrike.pcc.test.projectscheduler;

import static junit.framework.Assert.assertNotNull;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.model.Resource;
import at.silverstrike.pcc.api.model.ResourceAllocation;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.projectscheduler.ProjectExportInfo;
import at.silverstrike.pcc.impl.jruby.RubyDateTimeUtils;
import at.silverstrike.pcc.test.mockpersistence.MockObjectFactory;

/**
 * @author dp118m
 *
 */
class Helper {
    private MockObjectFactory MOCK_OBJECT_FACTORY = new MockObjectFactory();

    public void fillProjectInfo01(final ProjectExportInfo anInfo) {
        final MockObjectFactory mockObjectFactory = new MockObjectFactory();
        
        final List<Task> processes = new LinkedList<Task>();
        
        final Task task = mockObjectFactory.createControlProcess(2010L);
        
        task.setName("Some interesting task");
        task.setPriority(200);
        task.setBestCaseEffort(2.5);
        task.setWorstCaseEffort(2.5);
        
        final ResourceAllocation resourceAllocation = mockObjectFactory.createResourceAllocation();
        resourceAllocation.setResource(getWorker1210());
        
        assertNotNull(task.getResourceAllocations());
        
        task.getResourceAllocations().add(resourceAllocation);
        
        processes.add(task);
        
        final List<Resource> resources = new LinkedList<Resource>();
        
        resources.add(getWorker1210());
        
        anInfo.setSchedulingObjectsToExport(processes);
        anInfo.setResourcesToExport(resources);
        anInfo.setCopyright("DP");
        anInfo.setCurrency("EUR");
        anInfo.setNow(RubyDateTimeUtils.getDate(2010, Calendar.OCTOBER, 25, 11, 30));
        anInfo.setProjectName("Sample project");
        anInfo.setSchedulingHorizonMonths(1);
    }
    private Resource getWorker1210() {
        final Resource worker = MOCK_OBJECT_FACTORY.createResource(1210L);
        
        worker.setAbbreviation("DP");
        worker.setDailyLimitInHours(8);
        return worker;
    }
    
    private Resource getWorkerDP(final Persistence aPersistence) {
        final Long id = aPersistence.createHumanResource("DP", "Dmitri", "Anatl'evich", "Pisarenko", 8.0);
        final Resource worker = aPersistence.getResource(id);
        worker.setDailyLimitInHours(8);
        return worker;
    }
    
    public void fillProjectInfo02(final ProjectExportInfo anInfo, final Persistence aPersistence) {
        final List<Task> processes = new LinkedList<Task>();
        
        final Long id = aPersistence.createTask("Some interesting task");
        final Task task = aPersistence.getTask(id);
        
        task.setPriority(200);
        task.setBestCaseEffort(2.5);
        task.setWorstCaseEffort(2.5);
        
        final Resource resource = getWorkerDP(aPersistence);
        
        aPersistence.handoffProcess(id, resource.getId());
        
        assertNotNull(task.getResourceAllocations());
                
        processes.add(task);
        
        final List<Resource> resources = new LinkedList<Resource>();
        
        resources.add(resource);
        
        anInfo.setSchedulingObjectsToExport(processes);
        anInfo.setResourcesToExport(resources);
        anInfo.setCopyright("DP");
        anInfo.setCurrency("EUR");
        anInfo.setNow(RubyDateTimeUtils.getDate(2010, Calendar.OCTOBER, 25, 11, 30));
        anInfo.setProjectName("Sample project");
        anInfo.setSchedulingHorizonMonths(1);

    }
}

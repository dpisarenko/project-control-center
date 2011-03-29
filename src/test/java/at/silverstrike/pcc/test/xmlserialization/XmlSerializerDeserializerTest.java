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

package at.silverstrike.pcc.test.xmlserialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.model.Booking;
import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.model.DailyPlan;
import at.silverstrike.pcc.api.model.DailySchedule;
import at.silverstrike.pcc.api.model.DailyToDoList;
import at.silverstrike.pcc.api.model.ProcessState;
import at.silverstrike.pcc.api.model.ProcessType;
import at.silverstrike.pcc.api.model.Resource;
import at.silverstrike.pcc.api.model.ResourceAllocation;
import at.silverstrike.pcc.api.model.UserData;
import at.silverstrike.pcc.api.xmlserialization.XmlDeserializer;
import at.silverstrike.pcc.api.xmlserialization.XmlDeserializerFactory;
import at.silverstrike.pcc.api.xmlserialization.XmlSerializer;
import at.silverstrike.pcc.api.xmlserialization.XmlSerializerFactory;
import at.silverstrike.pcc.impl.xmlserialization.DefaultXmlDeserializerFactory;
import at.silverstrike.pcc.impl.xmlserialization.DefaultXmlSerializerFactory;
import at.silverstrike.pcc.test.model.MockObjectFactory;

import junit.framework.Assert;
import junit.framework.TestCase;

public class XmlSerializerDeserializerTest extends TestCase {

    @Test
    public final void testSerializationDeserialization() {
        // Init objects under test
        final XmlSerializerFactory serializerFactory =
                new DefaultXmlSerializerFactory();
        final XmlSerializer serializer = serializerFactory.create();
        final XmlDeserializerFactory deserializerFactory;
        deserializerFactory = new DefaultXmlDeserializerFactory();
        final XmlDeserializer deserializer = deserializerFactory.create();
        // Build the object graph (start)
        final MockObjectFactory objectFactory = new MockObjectFactory();
        final UserData writtenData = objectFactory.createUserData();
        // Now add stuff to UserData instance -
        // control processes,
        // nested control processes,
        // bookings,
        // daily plans,
        // to-do lists and
        // schedules.
        final Task process1 = objectFactory.createControlProcess();
        process1.setName("process1");
        process1.setAverageEstimatedEndDateTime(new Date());
        process1.setBestCaseEffort(1.1);
        process1.setBestEstimatedEndDateTime(new Date());
        final Task parent = objectFactory.createControlProcess();
        parent.setAverageEstimatedEndDateTime(new Date());
        parent.setBestCaseEffort(3.3);
        parent.setBestEstimatedEndDateTime(new Date());
        parent.setName("parent");
        parent.setParent(objectFactory.createControlProcess());
        parent.setPredecessors(new HashSet<SchedulingObject>());
        parent.setPriority(1);
        parent.setProcessType(ProcessType.GOAL);
        parent.setResourceAllocations(new ArrayList<ResourceAllocation>());
        parent.setState(ProcessState.ATTAINED);
        parent.setWorstCaseEffort(6.7);
        parent.setWorstEstimatedEndDateTime(new Date());
        process1.setParent(parent);
        final Set<SchedulingObject> predecessors =
                new HashSet<SchedulingObject>();
        final Task firstPredecessors = objectFactory.createControlProcess();
        firstPredecessors.setAverageEstimatedEndDateTime(new Date());
        firstPredecessors.setBestCaseEffort(2.2);
        firstPredecessors.setBestEstimatedEndDateTime(new Date());
        firstPredecessors.setName("firstPredecessors");
        firstPredecessors.setParent(objectFactory.createControlProcess());
        firstPredecessors.setPriority(1);
        firstPredecessors.setProcessType(ProcessType.UNKNOWN);
        final List<ResourceAllocation> resourceAllocations =
                new ArrayList<ResourceAllocation>();
        final ResourceAllocation resourceAllocation = objectFactory
                .createResourceAllocation();
        final Resource resource = objectFactory.createResource();
        resource.setAbbreviation("Abbreviation");
        resource.setDailyLimitInHours(4.4);
        resourceAllocation.setResource(resource);
        resourceAllocations.add(resourceAllocation);
        firstPredecessors.setResourceAllocations(resourceAllocations);
        firstPredecessors.setState(ProcessState.REPORTED);
        firstPredecessors.setWorstCaseEffort(5.5);
        firstPredecessors.setWorstEstimatedEndDateTime(new Date());
        predecessors.add(firstPredecessors);
        process1.setPredecessors(predecessors);
        process1.setPriority(5);
        process1.setProcessType(ProcessType.GOAL);
        process1.setResourceAllocations(new ArrayList<ResourceAllocation>());
        process1.setState(ProcessState.ATTAINED);
        process1.setWorstCaseEffort(7.7);
        process1.setWorstEstimatedEndDateTime(new Date());
        writtenData.setSchedulingData(new ArrayList<SchedulingObject>());
        writtenData.getSchedulingData().add(process1);
        final DailyPlan dailyPlan1 = objectFactory.createDailyPlan();
        dailyPlan1.setDate(new Date());
        dailyPlan1.setResource(objectFactory.createResource());
        final DailySchedule dailySchedule = objectFactory.createDailySchedule();
        dailySchedule.setBookings(new ArrayList<Booking>());
        final Booking booking = objectFactory.createBooking();
        booking.setDuration(3.4);
        booking.setProcess(objectFactory.createControlProcess());
        booking.setResource(objectFactory.createResource());
        booking.setStartDateTime(new Date());
        dailySchedule.getBookings().add(booking);
        dailyPlan1.setSchedule(dailySchedule);
        final DailyToDoList dailyToDoList = objectFactory.createDailyToDoList();
        dailyToDoList.setTasksToCompleteToday(new ArrayList<Task>());
        dailyPlan1.setToDoList(dailyToDoList);
        writtenData.setDailyPlans(new ArrayList<DailyPlan>());
        writtenData.getDailyPlans().add(dailyPlan1);
        writtenData.setIdentifier("1st user data identifier");
        writtenData.setBookings(new ArrayList<Booking>());
        final Booking booking1 = objectFactory.createBooking();
        booking1.setDuration(1.6);
        booking1.setProcess(objectFactory.createControlProcess());
        booking1.setResource(objectFactory.createResource());
        booking1.setStartDateTime(new Date());
        writtenData.getBookings().add(booking1);

        // Build the object graph (end)

        // Serialize writtenData to targetFile (start)
        // targetFile should be located somewhere
        // in test/resources/at/swdev/
        // test/xmlserialization/testSerializationDeserialization.xml
        File targetFile = null;
        targetFile =
                new File(
                        "src/test/resources/at/silverstrike/"
                                + "pcc/test/xmlserialization/testSerializationDeserialization.xml");
        FileOutputStream fileOutputStream = null;
        // Init fileOutputStream
        try {
            fileOutputStream = new FileOutputStream(targetFile);
        } catch (final FileNotFoundException exception) {
            Assert.fail(exception.getMessage());
        }
        serializer.setOutputStream(fileOutputStream);
        serializer.setUserData(writtenData);
        try {
            serializer.run();
        } catch (final PccException exception) {
            Assert.fail(exception.getMessage());
        }
        // Serialize writtenData to targetFile (end)
        // Deserialize (start)
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(targetFile);
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        }
        deserializer.setInputStream(fileInputStream);
        try {
            deserializer.run();
        } catch (final PccException exception) {
            Assert.fail(exception.getMessage());
        }

        final UserData readData = deserializer.getUserData();
        // Deserialize (end)

        // Compare serialized and deserialized object graphs
        Assert.assertNotNull(readData);
        Assert.assertEquals(writtenData.getSchedulingData().size(), readData
                .getSchedulingData().size());

        Assert.assertNotNull(readData);

        // test processes
        final int writtenProcessesSize = writtenData.getSchedulingData().size();
        final int readProcessesSize = readData.getSchedulingData().size();
        Assert.assertEquals(writtenProcessesSize, readProcessesSize);
        if (writtenProcessesSize == readProcessesSize) {
            for (int i = 0; i < writtenProcessesSize; i++) {
                testProcess((Task) writtenData.getSchedulingData().get(i),
                        (Task) readData
                                .getSchedulingData().get(i));
            }
        }

        // test identifier
        Assert.assertEquals(writtenData.getIdentifier(),
                readData.getIdentifier());

        // test bookings
        final int writtenDataBookingsSize = writtenData.getBookings().size();
        final int readDataBookingsSize = readData.getBookings().size();
        Assert.assertEquals(writtenDataBookingsSize, readDataBookingsSize);
        if (writtenDataBookingsSize == readDataBookingsSize) {
            for (int i = 0; i < writtenDataBookingsSize; i++) {
                final Booking written = writtenData.getBookings().get(i);
                final Booking read = readData.getBookings().get(i);
                TestBooking(written, read);
            }
        }

        // test daily plans
        final int writtenDataDailyPlansSize =
                writtenData.getDailyPlans().size();
        final int readDataDailyPlansSize = readData.getDailyPlans().size();
        Assert.assertEquals(writtenDataDailyPlansSize, readDataDailyPlansSize);
        if (writtenDataDailyPlansSize == readDataDailyPlansSize) {
            for (int i = 0; i < writtenDataDailyPlansSize; i++) {
                final DailyPlan written = writtenData.getDailyPlans().get(i);
                final DailyPlan read = readData.getDailyPlans().get(i);
                TestDailyPlan(written, read);
            }
        }
    }

    private void TestDailyPlan(final DailyPlan written, final DailyPlan read) {
        if (written.getDate() != null) {
            Assert.assertEquals(written.getDate(), read.getDate());
        }
        if (written.getId() != null) {
            Assert.assertEquals(written.getId(), read.getId());
        }
        if (written.getResource() != null) {
            TestResource(written.getResource(), read.getResource());
        }
        if (written.getSchedule() != null) {
            TestSchedule(written.getSchedule(), read.getSchedule());
        }
        if (written.getToDoList() != null) {
            TestDailyToDoList(written.getToDoList(), read.getToDoList());
        }
    }

    private void TestDailyToDoList(final DailyToDoList written,
            final DailyToDoList read) {
        if (written.getId() != null) {
            Assert.assertEquals(written.getId(), read.getId());
        }
        if (written.getTasksToCompleteToday() != null) {
            final int writtenProcesses =
                    written.getTasksToCompleteToday().size();
            final int readProcesses = read.getTasksToCompleteToday().size();
            Assert.assertEquals(writtenProcesses, readProcesses);
            if (writtenProcesses == readProcesses) {
                for (int i = 0; i < writtenProcesses; i++) {
                    testProcess(written.getTasksToCompleteToday().get(i), read
                            .getTasksToCompleteToday().get(i));
                }
            }
        }
    }

    private void TestResource(final Resource written, final Resource read) {
        if (written.getAbbreviation() != null) {
            Assert.assertEquals(written.getAbbreviation(),
                    read.getAbbreviation());
        }
        Assert.assertEquals(written.getDailyLimitInHours(),
                read.getDailyLimitInHours());
        if (written.getId() != null) {
            Assert.assertEquals(written.getId(), read.getId());
        }
    }

    private void TestSchedule(final DailySchedule written,
            final DailySchedule read) {
        if (written.getId() != null) {
            Assert.assertEquals(written.getId(), read.getId());
        }
        if (written.getBookings() != null) {
            final int writtenBooking = written.getBookings().size();
            final int readBooking = read.getBookings().size();
            Assert.assertEquals(writtenBooking, readBooking);
            if (writtenBooking == readBooking) {
                for (int i = 0; i < writtenBooking; i++) {
                    TestBooking(written.getBookings().get(i), read
                            .getBookings().get(i));
                }
            }
        }
    }

    private void TestBooking(final Booking written, final Booking read) {
        if (written.getDuration() != 0) {
            Assert.assertEquals(written.getDuration(), read.getDuration());
        }
        if (written.getEndDateTime() != null) {
            Assert.assertEquals(written.getEndDateTime(), read.getEndDateTime());
        }
        if (written.getId() != null) {
            Assert.assertEquals(written.getId(), read.getId());
        }
        if (written.getStartDateTime() != null) {
            Assert.assertEquals(written.getStartDateTime(),
                    read.getStartDateTime());
        }
        if (written.getResource() != null) {
            TestResource(written.getResource(), read.getResource());
        }
        if (written.getProcess() != null) {
            testProcess(written.getProcess(), read.getProcess());
        }
    }

    private void testProcess(final Task written, final Task read) {
        if (written.getName() != null) {
            Assert.assertEquals(written.getName(), read.getName());
        }
        if (written.getAverageEstimatedEndDateTime() != null) {
            Assert.assertEquals(written.getAverageEstimatedEndDateTime(),
                    read.getAverageEstimatedEndDateTime());
        }
        if (written.getBestCaseEffort() != null) {
            Assert.assertEquals(written.getBestCaseEffort(),
                    read.getBestCaseEffort());
        }
        if (written.getBestEstimatedEndDateTime() != null) {
            Assert.assertEquals(written.getBestEstimatedEndDateTime(),
                    read.getBestEstimatedEndDateTime());
        }
        if (written.getId() != null) {
            Assert.assertEquals(written.getId(), read.getId());
        }
        if (written.getParent() != null) {
            testProcess((Task) written.getParent(), (Task) read.getParent());
        }
        if (written.getPredecessors() != null) {
            final int writtenPredecesors = written.getPredecessors().size();
            final int readPredecesors = read.getPredecessors().size();
            Assert.assertEquals(writtenPredecesors, readPredecesors);
            if (writtenPredecesors == readPredecesors) {
                for (int i = 0; i < writtenPredecesors; i++) {
                    testProcess((Task) written.getPredecessors()
                            .toArray()[i], (Task) read
                            .getPredecessors().toArray()[i]);
                }
            }
        }
        if (written.getPriority() != null) {
            Assert.assertEquals(written.getPriority(), read.getPriority());
        }
        if (written.getProcessType() != null) {
            Assert.assertEquals(written.getProcessType(), read.getProcessType());
        }
        if (written.getResourceAllocations() != null) {
            final int writtenResourceAllocation =
                    written.getResourceAllocations()
                            .size();
            final int readResourceAllocation =
                    read.getResourceAllocations().size();
            Assert.assertEquals(writtenResourceAllocation,
                    readResourceAllocation);
            if (writtenResourceAllocation == readResourceAllocation) {
                for (int i = 0; i < writtenResourceAllocation; i++) {
                    TestResourceAllocation(written.getResourceAllocations()
                            .get(i), read.getResourceAllocations().get(i));
                }
            }
        }
        if (written.getState() != null) {
            Assert.assertEquals(written.getState(), read.getState());
        }
        if (written.getWorstCaseEffort() != null) {
            Assert.assertEquals(written.getWorstCaseEffort(),
                    read.getWorstCaseEffort());
        }
        if (written.getWorstEstimatedEndDateTime() != null) {
            Assert.assertEquals(written.getWorstEstimatedEndDateTime(),
                    read.getWorstEstimatedEndDateTime());
        }
    }

    private void TestResourceAllocation(final ResourceAllocation written,
            final ResourceAllocation read) {
        if (written.getId() != null) {
            Assert.assertEquals(written.getId(), read.getId());
        }
        if (written.getResource() != null) {
            TestResource(written.getResource(), read.getResource());
        }
    }
}

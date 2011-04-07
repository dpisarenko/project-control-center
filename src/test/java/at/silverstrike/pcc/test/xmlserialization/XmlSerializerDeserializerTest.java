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
                testBooking(written, read);
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
                testDailyPlan(written, read);
            }
        }
    }

    private void testDailyPlan(final DailyPlan aWritten,
            final DailyPlan aRead) {
        if (aWritten.getDate() != null) {
            Assert.assertEquals(aWritten.getDate(), aRead.getDate());
        }
        if (aWritten.getId() != null) {
            Assert.assertEquals(aWritten.getId(), aRead.getId());
        }
        if (aWritten.getResource() != null) {
            testResource(aWritten.getResource(), aRead.getResource());
        }
        if (aWritten.getSchedule() != null) {
            testSchedule(aWritten.getSchedule(), aRead.getSchedule());
        }
        if (aWritten.getToDoList() != null) {
            testDailyToDoList(aWritten.getToDoList(), aRead.getToDoList());
        }
    }

    private void testDailyToDoList(final DailyToDoList aWritten,
            final DailyToDoList aRead) {
        if (aWritten.getId() != null) {
            Assert.assertEquals(aWritten.getId(), aRead.getId());
        }
        if (aWritten.getTasksToCompleteToday() != null) {
            final int writtenProcesses =
                    aWritten.getTasksToCompleteToday().size();
            final int readProcesses = aRead.getTasksToCompleteToday().size();
            Assert.assertEquals(writtenProcesses, readProcesses);
            if (writtenProcesses == readProcesses) {
                for (int i = 0; i < writtenProcesses; i++) {
                    testProcess(aWritten.getTasksToCompleteToday().get(i),
                            aRead
                                    .getTasksToCompleteToday().get(i));
                }
            }
        }
    }

    private void testResource(final Resource aWritten,
            final Resource aRead) {
        if (aWritten.getAbbreviation() != null) {
            Assert.assertEquals(aWritten.getAbbreviation(),
                    aRead.getAbbreviation());
        }
        Assert.assertEquals(aWritten.getDailyLimitInHours(),
                aRead.getDailyLimitInHours());
        if (aWritten.getId() != null) {
            Assert.assertEquals(aWritten.getId(), aRead.getId());
        }
    }

    private void testSchedule(final DailySchedule aWritten,
            final DailySchedule aRead) {
        if (aWritten.getId() != null) {
            Assert.assertEquals(aWritten.getId(), aRead.getId());
        }
        if (aWritten.getBookings() != null) {
            final int writtenBooking = aWritten.getBookings().size();
            final int readBooking = aRead.getBookings().size();
            Assert.assertEquals(writtenBooking, readBooking);
            if (writtenBooking == readBooking) {
                for (int i = 0; i < writtenBooking; i++) {
                    testBooking(aWritten.getBookings().get(i), aRead
                            .getBookings().get(i));
                }
            }
        }
    }

    private void testBooking(final Booking aWritten, final Booking aRead) {
        if (aWritten.getDuration() != 0) {
            Assert.assertEquals(aWritten.getDuration(), aRead.getDuration());
        }
        if (aWritten.getEndDateTime() != null) {
            Assert.assertEquals(aWritten.getEndDateTime(),
                    aRead.getEndDateTime());
        }
        if (aWritten.getId() != null) {
            Assert.assertEquals(aWritten.getId(), aRead.getId());
        }
        if (aWritten.getStartDateTime() != null) {
            Assert.assertEquals(aWritten.getStartDateTime(),
                    aRead.getStartDateTime());
        }
        if (aWritten.getResource() != null) {
            testResource(aWritten.getResource(), aRead.getResource());
        }
        if (aWritten.getProcess() != null) {
            testProcess(aWritten.getProcess(), aRead.getProcess());
        }
    }

    private void testProcess(final Task aWritten, final Task aRead) {
        if (aWritten.getName() != null) {
            Assert.assertEquals(aWritten.getName(), aRead.getName());
        }
        if (aWritten.getAverageEstimatedEndDateTime() != null) {
            Assert.assertEquals(aWritten.getAverageEstimatedEndDateTime(),
                    aRead.getAverageEstimatedEndDateTime());
        }
        if (aWritten.getBestCaseEffort() != null) {
            Assert.assertEquals(aWritten.getBestCaseEffort(),
                    aRead.getBestCaseEffort());
        }
        if (aWritten.getBestEstimatedEndDateTime() != null) {
            Assert.assertEquals(aWritten.getBestEstimatedEndDateTime(),
                    aRead.getBestEstimatedEndDateTime());
        }
        if (aWritten.getId() != null) {
            Assert.assertEquals(aWritten.getId(), aRead.getId());
        }
        if (aWritten.getParent() != null) {
            testProcess((Task) aWritten.getParent(), (Task) aRead.getParent());
        }
        if (aWritten.getPredecessors() != null) {
            final int writtenPredecesors = aWritten.getPredecessors().size();
            final int readPredecesors = aRead.getPredecessors().size();
            Assert.assertEquals(writtenPredecesors, readPredecesors);
            if (writtenPredecesors == readPredecesors) {
                for (int i = 0; i < writtenPredecesors; i++) {
                    testProcess((Task) aWritten.getPredecessors()
                            .toArray()[i], (Task) aRead
                            .getPredecessors().toArray()[i]);
                }
            }
        }
        if (aWritten.getPriority() != null) {
            Assert.assertEquals(aWritten.getPriority(), aRead.getPriority());
        }

        if (aWritten.getResourceAllocations() != null) {
            final int writtenResourceAllocation =
                    aWritten.getResourceAllocations()
                            .size();
            final int readResourceAllocation =
                    aRead.getResourceAllocations().size();
            Assert.assertEquals(writtenResourceAllocation,
                    readResourceAllocation);
            if (writtenResourceAllocation == readResourceAllocation) {
                for (int i = 0; i < writtenResourceAllocation; i++) {
                    testResourceAllocation(aWritten.getResourceAllocations()
                            .get(i), aRead.getResourceAllocations().get(i));
                }
            }
        }
        if (aWritten.getState() != null) {
            Assert.assertEquals(aWritten.getState(), aRead.getState());
        }
        if (aWritten.getWorstCaseEffort() != null) {
            Assert.assertEquals(aWritten.getWorstCaseEffort(),
                    aRead.getWorstCaseEffort());
        }
        if (aWritten.getWorstEstimatedEndDateTime() != null) {
            Assert.assertEquals(aWritten.getWorstEstimatedEndDateTime(),
                    aRead.getWorstEstimatedEndDateTime());
        }
    }

    private void testResourceAllocation(final ResourceAllocation aWritten,
            final ResourceAllocation aRead) {
        if (aWritten.getId() != null) {
            Assert.assertEquals(aWritten.getId(), aRead.getId());
        }
        if (aWritten.getResource() != null) {
            testResource(aWritten.getResource(), aRead.getResource());
        }
    }
}

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
import at.silverstrike.pcc.api.model.ControlProcess;
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
		final XmlSerializerFactory serializerFactory = new DefaultXmlSerializerFactory();
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
		final ControlProcess process1 = objectFactory.createControlProcess();
		process1.setName("process1");
		process1.setAverageEstimatedEndDateTime(new Date());
		process1.setBestCaseEffort(1.1);
		process1.setBestEstimatedEndDateTime(new Date());
		ControlProcess parent = objectFactory.createControlProcess();
		parent.setAverageEstimatedEndDateTime(new Date());
		parent.setBestCaseEffort(3.3);
		parent.setBestEstimatedEndDateTime(new Date());
		parent.setName("parent");
		parent.setParent(objectFactory.createControlProcess());
		parent.setPredecessors(new HashSet<ControlProcess>());
		parent.setPriority(1);
		parent.setProcessType(ProcessType.GOAL);
		parent.setResourceAllocations(new ArrayList<ResourceAllocation>());
		parent.setState(ProcessState.ATTAINED);
		parent.setWorstCaseEffort(6.7);
		parent.setWorstEstimatedEndDateTime(new Date());
		process1.setParent(parent);
		Set<ControlProcess> predecessors = new HashSet<ControlProcess>();
		ControlProcess firstPredecessors = objectFactory.createControlProcess();
		firstPredecessors.setAverageEstimatedEndDateTime(new Date());
		firstPredecessors.setBestCaseEffort(2.2);
		firstPredecessors.setBestEstimatedEndDateTime(new Date());
		firstPredecessors.setName("firstPredecessors");
		firstPredecessors.setParent(objectFactory.createControlProcess());
		firstPredecessors.setPriority(1);
		firstPredecessors.setProcessType(ProcessType.UNKNOWN);
		List<ResourceAllocation> resourceAllocations = new ArrayList<ResourceAllocation>();
		ResourceAllocation resourceAllocation = objectFactory
				.createResourceAllocation();
		Resource resource = objectFactory.createResource();
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
		writtenData.setProcesses(new ArrayList<ControlProcess>());
		writtenData.getProcesses().add(process1);
		final DailyPlan dailyPlan1 = objectFactory.createDailyPlan();
		dailyPlan1.setDate(new Date());
		dailyPlan1.setResource(objectFactory.createResource());
		DailySchedule dailySchedule = objectFactory.createDailySchedule();
		dailySchedule.setBookings(new ArrayList<Booking>());
		Booking booking = objectFactory.createBooking();
		booking.setDuration(3.4);
		booking.setProcess(objectFactory.createControlProcess());
		booking.setResource(objectFactory.createResource());
		booking.setStartDateTime(new Date());
		dailySchedule.getBookings().add(booking);
		dailyPlan1.setSchedule(dailySchedule);
		DailyToDoList dailyToDoList = objectFactory.createDailyToDoList();
		dailyToDoList.setTasksToCompleteToday(new ArrayList<ControlProcess>());
		dailyPlan1.setToDoList(dailyToDoList);
		writtenData.setDailyPlans(new ArrayList<DailyPlan>());
		writtenData.getDailyPlans().add(dailyPlan1);
		writtenData.setIdentifier("1st user data identifier");
		writtenData.setBookings(new ArrayList<Booking>());
		Booking booking1 = objectFactory.createBooking();
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
		targetFile = new File(
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
		Assert.assertEquals(writtenData.getProcesses().size(), readData
				.getProcesses().size());

		Assert.assertNotNull(readData);

		// test processes
		int writtenProcessesSize = writtenData.getProcesses().size();
		int readProcessesSize = readData.getProcesses().size();
		Assert.assertEquals(writtenProcessesSize, readProcessesSize);
		if (writtenProcessesSize == readProcessesSize) {
			for (int i = 0; i < writtenProcessesSize; i++) {
				TestProcess(writtenData.getProcesses().get(i), readData
						.getProcesses().get(i));
			}
		}

		// test identifier
		Assert.assertEquals(writtenData.getIdentifier(),
				readData.getIdentifier());

		// test bookings
		int writtenDataBookingsSize = writtenData.getBookings().size();
		int readDataBookingsSize = readData.getBookings().size();
		Assert.assertEquals(writtenDataBookingsSize, readDataBookingsSize);
		if (writtenDataBookingsSize == readDataBookingsSize) {
			for (int i = 0; i < writtenDataBookingsSize; i++) {
				Booking written = writtenData.getBookings().get(i);
				Booking read = readData.getBookings().get(i);
				TestBooking(written, read);
			}
		}

		// test daily plans
		int writtenDataDailyPlansSize = writtenData.getDailyPlans().size();
		int readDataDailyPlansSize = readData.getDailyPlans().size();
		Assert.assertEquals(writtenDataDailyPlansSize, readDataDailyPlansSize);
		if (writtenDataDailyPlansSize == readDataDailyPlansSize) {
			for (int i = 0; i < writtenDataDailyPlansSize; i++) {
				DailyPlan written = writtenData.getDailyPlans().get(i);
				DailyPlan read = readData.getDailyPlans().get(i);
				TestDailyPlan(written, read);
			}
		}
	}

	private void TestDailyPlan(DailyPlan written, DailyPlan read) {
		if (written.getDate() != null)
			Assert.assertEquals(written.getDate(), read.getDate());
		if (written.getId() != null)
			Assert.assertEquals(written.getId(), read.getId());
		if (written.getResource() != null)
			TestResource(written.getResource(), read.getResource());
		if (written.getSchedule() != null)
			TestSchedule(written.getSchedule(), read.getSchedule());
		if (written.getToDoList() != null)
			TestDailyToDoList(written.getToDoList(), read.getToDoList());
	}

	private void TestDailyToDoList(DailyToDoList written, DailyToDoList read) {
		if (written.getId() != null)
			Assert.assertEquals(written.getId(), read.getId());
		if (written.getTasksToCompleteToday() != null) {
			int writtenProcesses = written.getTasksToCompleteToday().size();
			int readProcesses = read.getTasksToCompleteToday().size();
			Assert.assertEquals(writtenProcesses, readProcesses);
			if (writtenProcesses == readProcesses) {
				for (int i = 0; i < writtenProcesses; i++) {
					TestProcess(written.getTasksToCompleteToday().get(i), read
							.getTasksToCompleteToday().get(i));
				}
			}
		}
	}

	private void TestResource(Resource written, Resource read) {
		if (written.getAbbreviation() != null)
			Assert.assertEquals(written.getAbbreviation(),
					read.getAbbreviation());
		Assert.assertEquals(written.getDailyLimitInHours(),
				read.getDailyLimitInHours());
		if (written.getId() != null)
			Assert.assertEquals(written.getId(), read.getId());
	}

	private void TestSchedule(DailySchedule written, DailySchedule read) {
		if (written.getId() != null)
			Assert.assertEquals(written.getId(), read.getId());
		if (written.getBookings() != null) {
			int writtenBooking = written.getBookings().size();
			int readBooking = read.getBookings().size();
			Assert.assertEquals(writtenBooking, readBooking);
			if (writtenBooking == readBooking) {
				for (int i = 0; i < writtenBooking; i++) {
					TestBooking(written.getBookings().get(i), read
							.getBookings().get(i));
				}
			}
		}
	}

	private void TestBooking(Booking written, Booking read) {
		if (written.getDuration() != 0)
			Assert.assertEquals(written.getDuration(), read.getDuration());
		if (written.getEndDateTime() != null)
			Assert.assertEquals(written.getEndDateTime(), read.getEndDateTime());
		if (written.getId() != null)
			Assert.assertEquals(written.getId(), read.getId());
		if (written.getStartDateTime() != null)
			Assert.assertEquals(written.getStartDateTime(),
					read.getStartDateTime());
		if (written.getResource() != null)
			TestResource(written.getResource(), read.getResource());
		if (written.getProcess() != null)
			TestProcess(written.getProcess(), read.getProcess());
	}

	private void TestProcess(ControlProcess written, ControlProcess read) {
		if (written.getName() != null)
			Assert.assertEquals(written.getName(), read.getName());
		if (written.getAverageEstimatedEndDateTime() != null)
			Assert.assertEquals(written.getAverageEstimatedEndDateTime(),
					read.getAverageEstimatedEndDateTime());
		if (written.getBestCaseEffort() != null)
			Assert.assertEquals(written.getBestCaseEffort(),
					read.getBestCaseEffort());
		if (written.getBestEstimatedEndDateTime() != null)
			Assert.assertEquals(written.getBestEstimatedEndDateTime(),
					read.getBestEstimatedEndDateTime());
		if (written.getId() != null)
			Assert.assertEquals(written.getId(), read.getId());
		if (written.getParent() != null)
			TestProcess(written.getParent(), read.getParent());
		if (written.getPredecessors() != null) {
			int writtenPredecesors = written.getPredecessors().size();
			int readPredecesors = read.getPredecessors().size();
			Assert.assertEquals(writtenPredecesors, readPredecesors);
			if (writtenPredecesors == readPredecesors) {
				for (int i = 0; i < writtenPredecesors; i++) {
					TestProcess((ControlProcess) written.getPredecessors()
							.toArray()[i], (ControlProcess) read
							.getPredecessors().toArray()[i]);
				}
			}
		}
		if (written.getPriority() != null)
			Assert.assertEquals(written.getPriority(), read.getPriority());
		if (written.getProcessType() != null)
			Assert.assertEquals(written.getProcessType(), read.getProcessType());
		if (written.getResourceAllocations() != null) {
			int writtenResourceAllocation = written.getResourceAllocations()
					.size();
			int readResourceAllocation = read.getResourceAllocations().size();
			Assert.assertEquals(writtenResourceAllocation,
					readResourceAllocation);
			if (writtenResourceAllocation == readResourceAllocation) {
				for (int i = 0; i < writtenResourceAllocation; i++) {
					TestResourceAllocation(written.getResourceAllocations()
							.get(i), read.getResourceAllocations().get(i));
				}
			}
		}
		if (written.getState() != null)
			Assert.assertEquals(written.getState(), read.getState());
		if (written.getWorstCaseEffort() != null)
			Assert.assertEquals(written.getWorstCaseEffort(),
					read.getWorstCaseEffort());
		if (written.getWorstEstimatedEndDateTime() != null)
			Assert.assertEquals(written.getWorstEstimatedEndDateTime(),
					read.getWorstEstimatedEndDateTime());
	}

	private void TestResourceAllocation(ResourceAllocation written,
			ResourceAllocation read) {
		if (written.getId() != null)
			Assert.assertEquals(written.getId(), read.getId());
		if (written.getResource() != null)
			TestResource(written.getResource(), read.getResource());
	}
}
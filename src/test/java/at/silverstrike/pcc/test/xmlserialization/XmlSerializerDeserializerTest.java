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
		targetFile = new File("src/test/resources/at/silverstrike/"
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

	}
}

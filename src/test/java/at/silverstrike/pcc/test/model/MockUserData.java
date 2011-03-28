package at.silverstrike.pcc.test.model;

import java.util.List;
import at.silverstrike.pcc.api.model.Booking;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.model.DailyPlan;
import at.silverstrike.pcc.api.model.UserData;

class MockUserData implements UserData {

	private String identifier;
	private List<Task> processes;
	private List<DailyPlan> dailyPlans;
	private List<Booking> bookings;

	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(final String aIdentifier) {
		this.identifier = aIdentifier;
	}
	public List<Task> getProcesses() {
		return processes;
	}
	public void setProcesses(final List<Task> aProcesses) {
		this.processes = aProcesses;
	}
	public List<DailyPlan> getDailyPlans() {
		return dailyPlans;
	}
	public void setDailyPlans(final List<DailyPlan> aDailyPlans) {
		this.dailyPlans = aDailyPlans;
	}
	public List<Booking> getBookings() {
		return bookings;
	}
	public void setBookings(final List<Booking> aBookings) {
		this.bookings = aBookings;
	}
	
}

package at.silverstrike.pcc.api.model;

import java.util.List;

public interface UserData {
	void setIdentifier(final String aIdentifier);
	String getIdentifier();
	
	void setProcesses(List<ControlProcess> aProcesses);
	List<ControlProcess> getProcesses();
	
	void setDailyPlans(List<DailyPlan> aDailyPlans);
	List<DailyPlan> getDailyPlans();
	
	void setBookings(List<Booking> aBookings);
	List<Booking> getBookings();
}

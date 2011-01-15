package at.silverstrike.pcc.test.model;

import java.util.List;
import at.silverstrike.pcc.api.model.Booking;
import at.silverstrike.pcc.api.model.DailySchedule;


class MockDailySchedule implements DailySchedule {
	
	private Long id;
	private List<Booking> bookings;

	public Long getId() {
		return this.id;
	}

	public void setBookings(final List<Booking> aBookings) {
		this.bookings = aBookings;
	}

	public List<Booking> getBookings() {
		return this.bookings;
	}

}

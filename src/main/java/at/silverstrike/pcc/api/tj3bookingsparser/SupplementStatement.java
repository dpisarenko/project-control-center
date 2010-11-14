package at.silverstrike.pcc.api.tj3bookingsparser;

import java.util.List;

public interface SupplementStatement {
	String getTaskId();

	List<BookingStatement> getBookingStatements();
}

package at.silverstrike.pcc.impl.tj3bookingsparser;

import at.silverstrike.pcc.api.model.Booking;
import at.silverstrike.pcc.api.tj3bookingsparser.BookingTuple;

class DefaultBookingTuple implements BookingTuple {
    private long processId;    
    private long resourceId;
    private Booking booking;
    
    public long getProcessId() {
        return processId;
    }
    public void setProcessId(final long processId) {
        this.processId = processId;
    }
    public long getResourceId() {
        return resourceId;
    }
    public void setResourceId(final long resourceId) {
        this.resourceId = resourceId;
    }
    public Booking getBooking() {
        return booking;
    }
    public void setBooking(final Booking booking) {
        this.booking = booking;
    }
}

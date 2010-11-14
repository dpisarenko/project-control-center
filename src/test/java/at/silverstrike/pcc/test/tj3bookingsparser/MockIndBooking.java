package at.silverstrike.pcc.test.tj3bookingsparser;

import at.silverstrike.pcc.api.tj3bookingsparser.IndBooking;

public class MockIndBooking implements IndBooking{
    
    private String startTime;

    private String duration;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

}

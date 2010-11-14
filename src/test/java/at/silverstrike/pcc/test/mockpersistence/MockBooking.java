package at.silverstrike.pcc.test.mockpersistence;

import java.util.Date;

import at.silverstrike.pcc.api.model.Booking;
import at.silverstrike.pcc.api.model.ControlProcess;
import at.silverstrike.pcc.api.model.Resource;
import at.silverstrike.pcc.impl.util.Utils;

public class MockBooking implements Booking {
    private Long id;
    private ControlProcess process;
    private Resource resource;
    private Date startDateTime;
    private double duration;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ControlProcess getProcess() {
        return process;
    }

    public void setProcess(ControlProcess process) {
        this.process = process;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    @Override
    public Date getEndDateTime() {
        return Utils.addHours(this.startDateTime, this.duration);
    }
}

package at.silverstrike.pcc.test.model;

import java.util.Date;
import at.silverstrike.pcc.api.model.Booking;
import at.silverstrike.pcc.api.model.ControlProcess;
import at.silverstrike.pcc.api.model.Resource;

class MockBooking implements Booking {

	private Long id;
	private ControlProcess process;
	private Resource resource;
	private Date startDateTime;
	private double durationInHours;
	private Date endDateTime;

	public Long getId() {
		return this.id;
	}

	public void setProcess(final ControlProcess aProcess) {
		this.process = aProcess;
	}

	public ControlProcess getProcess() {
		return this.process;
	}

	public void setResource(final Resource aResource) {
		this.resource = aResource;
	}

	public Resource getResource() {
		return this.resource;
	}

	public void setStartDateTime(final Date aDate) {
		this.startDateTime = aDate;
	}

	public Date getStartDateTime() {
		return this.startDateTime;
	}

	public void setDuration(final double aDurationInHours) {
		this.durationInHours = aDurationInHours;
	}

	public double getDuration() {
		return this.durationInHours;
	}

	public Date getEndDateTime() {
		return this.endDateTime;
	}

}

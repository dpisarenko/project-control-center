package at.silverstrike.pcc.test.model;

import java.util.Date;
import at.silverstrike.pcc.api.model.DailyPlan;
import at.silverstrike.pcc.api.model.DailySchedule;
import at.silverstrike.pcc.api.model.DailyToDoList;
import at.silverstrike.pcc.api.model.Resource;

class MockDailyPlan implements DailyPlan {

	private Long id;
	private Resource resource;
	private Date date;
	private DailyToDoList toDoList;
	private DailySchedule schedule;

	public Long getId() {
		return this.id;
	}

	public void setResource(final Resource aResource) {
		this.resource = aResource;
	}

	public Resource getResource() {
		return this.resource;
	}

	public void setDate(final Date aDate) {
		this.date = aDate;
	}

	public Date getDate() {
		return this.date;
	}

	public void setToDoList(final DailyToDoList aToDoList) {
		this.toDoList = aToDoList;
	}

	public DailyToDoList getToDoList() {
		return this.toDoList;
	}

	public void setSchedule(final DailySchedule aSchedule) {
		this.schedule = aSchedule;
	}

	public DailySchedule getSchedule() {
		return this.schedule;
	}

}

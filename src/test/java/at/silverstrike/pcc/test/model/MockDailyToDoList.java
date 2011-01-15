package at.silverstrike.pcc.test.model;

import java.util.List;
import at.silverstrike.pcc.api.model.ControlProcess;
import at.silverstrike.pcc.api.model.DailyToDoList;

class MockDailyToDoList implements DailyToDoList {
	
	private Long id;
	private List<ControlProcess> tasksToCompleteToday;

	public Long getId() {
		return this.id;
	}

	public void setTasksToCompleteToday(
			final List<ControlProcess> aTasksToCompleteToday) {
		this.tasksToCompleteToday = aTasksToCompleteToday;
	}

	public List<ControlProcess> getTasksToCompleteToday() {
		return this.tasksToCompleteToday;
	}

}

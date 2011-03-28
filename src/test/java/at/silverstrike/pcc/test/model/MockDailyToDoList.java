package at.silverstrike.pcc.test.model;

import java.util.List;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.model.DailyToDoList;

class MockDailyToDoList implements DailyToDoList {
	
	private Long id;
	private List<Task> tasksToCompleteToday;

	public Long getId() {
		return this.id;
	}

	public void setTasksToCompleteToday(
			final List<Task> aTasksToCompleteToday) {
		this.tasksToCompleteToday = aTasksToCompleteToday;
	}

	public List<Task> getTasksToCompleteToday() {
		return this.tasksToCompleteToday;
	}

}

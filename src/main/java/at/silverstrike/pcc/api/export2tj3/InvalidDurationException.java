package at.silverstrike.pcc.api.export2tj3;

import at.silverstrike.pcc.api.conventions.PccException;

public class InvalidDurationException  extends PccException {
	private static final long serialVersionUID = 1L;
	
	private Long taskNumber;
	private String taskName;

	public Long getTaskNumber() {
		return taskNumber;
	}
	public void setTaskNumber(Long taskNumber) {
		this.taskNumber = taskNumber;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public InvalidDurationException(final Long aTaskNumber, final String aTaskName)
	{
		super("Invalid duration");
		this.taskNumber = aTaskNumber;
		this.taskName = aTaskName;
	}
}

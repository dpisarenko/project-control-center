package at.silverstrike.pcc.impl.webguibus;

import at.silverstrike.pcc.api.model.Event;
import at.silverstrike.pcc.api.model.Milestone;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.webguibus.WebGuiBusListener;

public abstract class WebGuiBusListenerAdapter implements WebGuiBusListener {

	@Override
	public void taskCreated(Task aNewTask) {
		// TODO Auto-generated method stub

	}

	@Override
	public void taskCreationFailure() {
		// TODO Auto-generated method stub

	}

	@Override
	public void taskEdited(Task aTask) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eventCreated(Event aNewEvent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eventCreationFailure() {
		// TODO Auto-generated method stub

	}

	@Override
	public void milestoneCreated(Milestone aMilestone) {
		// TODO Auto-generated method stub

	}

	@Override
	public void milestoneCreationFailure() {
		// TODO Auto-generated method stub

	}

	@Override
	public void taskDeleted(Task aTask) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eventDeleted(Event aNewEvent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void milestoneDeleted(Milestone aMilestone) {
		// TODO Auto-generated method stub

	}

	@Override
	public void taskDeletionFailure() {
		// TODO Auto-generated method stub

	}

	@Override
	public void eventDeletionFailure() {
		// TODO Auto-generated method stub

	}

	@Override
	public void milestoneDeletionFailure() {
		// TODO Auto-generated method stub

	}

}

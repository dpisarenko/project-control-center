package at.silverstrike.pcc.impl.webguibus;

import at.silverstrike.pcc.api.model.Event;
import at.silverstrike.pcc.api.model.Milestone;
import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.webguibus.WebGuiBusListener;

public abstract class WebGuiBusListenerAdapter implements WebGuiBusListener {

    @Override
    public void taskCreated(final Task aNewTask) {
        // TODO Auto-generated method stub

    }

    @Override
    public void taskCreationFailure() {
        // TODO Auto-generated method stub

    }

    @Override
    public void taskEdited(final Task aTask) {
        // TODO Auto-generated method stub

    }

    @Override
    public void eventCreated(final Event aNewEvent) {
        // TODO Auto-generated method stub

    }

    @Override
    public void eventCreationFailure() {
        // TODO Auto-generated method stub

    }

    @Override
    public void milestoneCreated(final Milestone aMilestone) {
        // TODO Auto-generated method stub

    }

    @Override
    public void milestoneCreationFailure() {
        // TODO Auto-generated method stub

    }

    @Override
    public void taskDeleted(final Task aTask) {
        // TODO Auto-generated method stub

    }

    @Override
    public void eventDeleted(final Event aNewEvent) {
        // TODO Auto-generated method stub

    }

    @Override
    public void milestoneDeleted(final Milestone aMilestone) {
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

    @Override
    public void eventEdited(final Event aEvent) {
        // TODO Auto-generated method stub

    }

    @Override
    public void taskCompleted(final Task aTask) {
        // TODO Auto-generated method stub

    }

    @Override
    public void taskComletedFailure() {
        // TODO Auto-generated method stub

    }

    @Override
    public void taskDependenciesChanged(final SchedulingObject aObject) {

    }
    @Override
    public void milestoneEdited(final Milestone aMilestone) {
        // TODO Auto-generated method stub
        
    }

}

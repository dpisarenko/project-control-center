/**
 * This file is part of Project Control Center (PCC).
 * 
 * PCC (Project Control Center) project is intellectual property of 
 * Dmitri Anatol'evich Pisarenko.
 * 
 * Copyright 2010, 2011 Dmitri Anatol'evich Pisarenko
 * All rights reserved
 *
 **/

package at.silverstrike.pcc.impl.schedulingguicontroller;

import com.google.inject.Injector;
import com.vaadin.ui.Panel;

import at.silverstrike.pcc.api.model.Event;
import at.silverstrike.pcc.api.model.Milestone;
import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.schedulingguicontroller.SchedulingPanelController;
import at.silverstrike.pcc.api.schedulingguicontroller.SchedulingState;
import at.silverstrike.pcc.api.schedulingindicatorpanel.SchedulingIndicatorPanel;
import at.silverstrike.pcc.api.schedulingindicatorpanel.SchedulingIndicatorPanelFactory;
import at.silverstrike.pcc.impl.webguibus.WebGuiBusListenerAdapter;

/**
 * @author DP118M
 * 
 */
class DefaultSchedulingPanelController extends WebGuiBusListenerAdapter
        implements SchedulingPanelController {
    private SchedulingIndicatorPanel panel;
    private SchedulingState state;
    private Injector injector;

    @Override
    public Panel initGui() {
        this.state = SchedulingState.UNDEFINED;

        final SchedulingIndicatorPanelFactory factory =
                this.injector
                        .getInstance(SchedulingIndicatorPanelFactory.class);
        this.panel = factory.create();

        this.panel.initGui();

        return this.panel.toPanel();
    }

    @Override
    public void taskCreated(final Task aNewTask) {
        updatePlans();
    }

    @Override
    public void taskEdited(final Task aTask) {
        updatePlans();
    }

    @Override
    public void eventCreated(final Event aNewEvent) {
        updatePlans();
    }

    @Override
    public void milestoneCreated(final Milestone aMilestone) {
        updatePlans();
    }

    @Override
    public void taskDeleted(final Task aTask) {
        updatePlans();
    }

    @Override
    public void eventDeleted(final Event aNewEvent) {
        updatePlans();
    }

    @Override
    public void milestoneDeleted(final Milestone aMilestone) {
        updatePlans();
    }

    @Override
    public void eventEdited(final Event aEvent) {
        updatePlans();
    }

    @Override
    public void taskCompleted(final Task aTask) {
        updatePlans();
    }

    @Override
    public void taskDependenciesChanged(final SchedulingObject aObject) {
        updatePlans();
    }

    @Override
    public void milestoneEdited(final Milestone aMilestone) {
        updatePlans();
    }

    private void updatePlans() {
        if (!SchedulingState.IN_PROGRESS.equals(this.state)) {
            this.state = SchedulingState.IN_PROGRESS;
            this.panel.displayState(this.state);

            calculatePlan();
        }

        this.panel.displayState(this.state);
    }

    private void calculatePlan() {
        // TODO Auto-generated method stub

    }

    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;
    }
}

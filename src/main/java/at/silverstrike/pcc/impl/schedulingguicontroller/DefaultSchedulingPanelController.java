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

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.altruix.commons.api.di.PccException;

import com.google.inject.Injector;
import com.vaadin.ui.Panel;

import at.silverstrike.pcc.api.export2tj3.InvalidDurationException;
import at.silverstrike.pcc.api.model.Event;
import at.silverstrike.pcc.api.model.Milestone;
import at.silverstrike.pcc.api.model.Resource;
import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.projectscheduler.ProjectScheduler;
import at.silverstrike.pcc.api.projectscheduler.ProjectSchedulerFactory;
import at.silverstrike.pcc.api.schedulingguicontroller.SchedulingPanelController;
import at.silverstrike.pcc.api.schedulingguicontroller.SchedulingState;
import at.silverstrike.pcc.api.schedulingindicatorpanel.SchedulingIndicatorPanel;
import at.silverstrike.pcc.api.schedulingindicatorpanel.SchedulingIndicatorPanelFactory;
import at.silverstrike.pcc.api.webguibus.WebGuiBus;
import at.silverstrike.pcc.impl.webguibus.WebGuiBusListenerAdapter;

/**
 * @author DP118M
 * 
 */
class DefaultSchedulingPanelController extends WebGuiBusListenerAdapter
        implements SchedulingPanelController {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultSchedulingPanelController.class);

    private static final int ONE_MONTH = 1;
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
        this.panel.displayState(this.state);

        final WebGuiBus webGuiBus = this.injector.getInstance(WebGuiBus.class);
        webGuiBus.addListener(this);

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
        final ProjectSchedulerFactory factory = injector
                .getInstance(ProjectSchedulerFactory.class);
        final ProjectScheduler scheduler = factory.create();
        final Persistence persistence =
                this.injector.getInstance(Persistence.class);

        final List<SchedulingObject> schedulingObjects =
                persistence.getAllNotDeletedTasks();

        // Находим все дела с неправильными трудозатратами
        boolean tasksWithInvalidEffortEstimatesFound = false;
        for (final SchedulingObject curSchedulingObject : schedulingObjects) {
            if (curSchedulingObject instanceof Task) {
                final Task curTask = (Task) curSchedulingObject;

                if ((curTask.getBestCaseEffort() == null)
                        || (curTask.getWorstCaseEffort() == null)) {
                    tasksWithInvalidEffortEstimatesFound = true;
//                    curTask.set
                }
            }
        }

        scheduler.getProjectExportInfo().setSchedulingObjectsToExport(
                schedulingObjects);

        final List<Resource> resources = new LinkedList<Resource>();
        resources.addAll(persistence.getAllWorkers());

        scheduler.getProjectExportInfo().setResourcesToExport(resources);

        scheduler.getProjectExportInfo().setProjectName("pcc");

        final Date now = new Date();

        scheduler.getProjectExportInfo().setNow(now);
        scheduler.getProjectExportInfo().setCopyright("Dmitri Pisarenko");
        scheduler.getProjectExportInfo().setCurrency("EUR");
        scheduler.getProjectExportInfo().setSchedulingHorizonMonths(ONE_MONTH);

        scheduler.setDirectory(System.getProperty("user.dir") + "/");
        scheduler.setInjector(injector);
        scheduler.setNow(now);

        try {
            scheduler.run();
            this.state = SchedulingState.PLAN_UP_TO_DATE;
        } catch (final InvalidDurationException exception) {
            this.state = SchedulingState.ERROR;
        } catch (final PccException exception) {
            this.state = SchedulingState.ERROR;
        }
        this.panel.displayState(this.state);
    }

    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;
    }
}

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

import eu.livotov.tpt.TPTApplication;
import at.silverstrike.pcc.api.export2tj3.InvalidDurationException;
import at.silverstrike.pcc.api.incorrectschedulingobjectsmarker.IncorrectSchedulingObjectsMarker;
import at.silverstrike.pcc.api.incorrectschedulingobjectsmarker.IncorrectSchedulingObjectsMarkerFactory;
import at.silverstrike.pcc.api.model.Event;
import at.silverstrike.pcc.api.model.Milestone;
import at.silverstrike.pcc.api.model.Resource;
import at.silverstrike.pcc.api.model.SchedulingObject;
import at.silverstrike.pcc.api.model.Task;
import at.silverstrike.pcc.api.model.UserData;
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
    private static final int ONE_MONTH = 1;
    private SchedulingIndicatorPanel panel;
    private SchedulingState state;
    private Injector injector;
    private IncorrectSchedulingObjectsMarker incorrectSchedulingObjectsMarker;
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultSchedulingPanelController.class);

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

        final IncorrectSchedulingObjectsMarkerFactory markerFactory =
                this.injector
                        .getInstance(IncorrectSchedulingObjectsMarkerFactory.class);
        incorrectSchedulingObjectsMarker = markerFactory.create();
        incorrectSchedulingObjectsMarker.setInjector(this.injector);

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
        final UserData user = (UserData) TPTApplication
                .getCurrentApplication().getUser();
        
        LOGGER.debug("calculatePlan, user: {}", user.getId());
        
        final List<SchedulingObject> schedulingObjects =
                persistence.getAllNotDeletedTasks(user);

        // Находим все дела с неправильными трудозатратами
        incorrectSchedulingObjectsMarker
                .setSchedulingObjects(schedulingObjects);
        try {
            incorrectSchedulingObjectsMarker.run();
        } catch (final PccException exception) {
            LOGGER.error("", exception);
        }

        LOGGER.debug("incorrectSchedulingObjectsMarker.areErrorsFound(): "
                + incorrectSchedulingObjectsMarker.areErrorsFound());

        if (incorrectSchedulingObjectsMarker.areErrorsFound()) {
            this.state = SchedulingState.ERROR;
            return;
        }

        final List<SchedulingObject> schedulingObjectsToExport =
                persistence.getTopLevelTasks(user);

        scheduler.getProjectExportInfo().setSchedulingObjectsToExport(
                schedulingObjectsToExport);

        final List<Resource> resources = new LinkedList<Resource>();
        resources.add(persistence.getCurrentWorker(user));

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

            final WebGuiBus webGuiBus =
                    this.injector.getInstance(WebGuiBus.class);
            webGuiBus.broadcastPlanCalculatedMessage();

        } catch (final InvalidDurationException exception) {
            this.state = SchedulingState.ERROR;
            LOGGER.error("", exception);
        } catch (final PccException exception) {
            this.state = SchedulingState.ERROR;
            LOGGER.error("", exception);
        }
        this.panel.displayState(this.state);
    }

    @Override
    public void setInjector(final Injector aInjector) {
        this.injector = aInjector;
    }
}

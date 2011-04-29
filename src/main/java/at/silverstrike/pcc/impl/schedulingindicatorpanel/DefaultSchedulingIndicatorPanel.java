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

package at.silverstrike.pcc.impl.schedulingindicatorpanel;

import java.util.HashMap;
import java.util.Map;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.ProgressIndicator;

import eu.livotov.tpt.i18n.TM;

import at.silverstrike.pcc.api.schedulingguicontroller.SchedulingState;
import at.silverstrike.pcc.api.schedulingindicatorpanel.SchedulingIndicatorPanel;

/**
 * @author DP118M
 * 
 */
class DefaultSchedulingIndicatorPanel implements SchedulingIndicatorPanel {
    private Map<SchedulingState, String> letteringsBySchedulingStates;
    private Label label;
    private ProgressIndicator progressIndicator;
    private Panel panel;
    
    @Override
    public void initGui() {
        initLetteringsBySchedulingStates();

        this.label = new Label();
        this.label.setWidth("100px");

        progressIndicator = new ProgressIndicator();
        progressIndicator.setIndeterminate(true);
        progressIndicator.setEnabled(false);

        final HorizontalLayout layout = new HorizontalLayout();
        layout.addComponent(label);
        layout.addComponent(progressIndicator);
        
        panel = new Panel(layout);
    }

    private void initLetteringsBySchedulingStates() {
        this.letteringsBySchedulingStates =
                new HashMap<SchedulingState, String>();
        this.letteringsBySchedulingStates.put(SchedulingState.UNDEFINED, "?");
        this.letteringsBySchedulingStates.put(SchedulingState.ERROR,
                TM.get("schedulingindicatorpanel.3-error"));
        this.letteringsBySchedulingStates.put(SchedulingState.PLAN_UP_TO_DATE,
                TM.get("schedulingindicatorpanel.1-up-to-date"));
        this.letteringsBySchedulingStates.put(SchedulingState.IN_PROGRESS,
                TM.get("schedulingindicatorpanel.2-in-progress"));
    }

    @Override
    public void displayState(final SchedulingState aState) {
        String text;

        if (aState == null) {
            text = "?";
        } else {
            text = this.letteringsBySchedulingStates.get(aState);
        }

        this.label.setValue(text);

        if (SchedulingState.IN_PROGRESS.equals(aState)) {
            this.progressIndicator.setEnabled(true);
            this.progressIndicator.setIndeterminate(true);
        } else {
            this.progressIndicator.setIndeterminate(false);
            this.progressIndicator.setEnabled(false);
        }
    }

    @Override
    public Panel toPanel() {
        return this.panel;
    }

}

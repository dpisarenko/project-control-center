/**
 * This file is part of Project Control Center (PCC).
 * 
 * Project Control Center (PCC) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Project Control Center (PCC) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Project Control Center (PCC).  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2010 Dmitri Anatol'evich Pisarenko
 **/

package at.silverstrike.pcc.impl.workerpanel;

import com.google.inject.Injector;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window.Notification;

import eu.livotov.tpt.i18n.TM;

import at.silverstrike.pcc.api.debugids.DebugIdRegistry;
import at.silverstrike.pcc.api.persistence.Persistence;
import at.silverstrike.pcc.api.webguibus.WebGuiBus;
import at.silverstrike.pcc.api.webguibus.WebGuiBusMessageFactory;
import at.silverstrike.pcc.api.webguibus.WorkerAddedMessage;
import at.silverstrike.pcc.api.workerpanel.WorkerPanel;

class DefaultWorkerPanel extends Panel implements WorkerPanel {
    private static final long serialVersionUID = 1L;
    private Injector injector;
    private String errorMessage;
    private TextField abbreviationTextField;
    private TextField firstNameTextField;
    private TextField middleNameTextField;
    private TextField surnameTextField;
    private TextField dailyMaxTextField;
    private DebugIdRegistry debugIdRegistry;
    private WebGuiBus webGuiBus;
    private WebGuiBusMessageFactory webGuiBusMessageFactory;

    public DefaultWorkerPanel() {
    }

    protected void okButtonClicked() {
        if (validate()) {
            Persistence persistence =
                    this.injector.getInstance(Persistence.class);

            final String abbreviation =
                    (String) this.abbreviationTextField.getValue();
            final String firstName =
                    (String) this.firstNameTextField.getValue();
            final String middleName =
                    (String) this.middleNameTextField.getValue();
            final String surname = (String) this.surnameTextField.getValue();

            double dailyMaxWorkTimeInHours = 0.;
            
            try
            {
                dailyMaxWorkTimeInHours = Double.parseDouble((String)this.dailyMaxTextField.getValue());
            }
            catch (final NumberFormatException exception)
            {
                
            }
            
            persistence.createHumanResource(abbreviation, firstName,
                    middleName, surname, dailyMaxWorkTimeInHours);

            final WorkerAddedMessage message =
                    this.webGuiBusMessageFactory.createWorkerAddedMessage();
            this.webGuiBus.broadcastWorkerAddedMessage(message);
        } else {
            getWindow().showNotification(this.errorMessage, null,
                    Notification.TYPE_ERROR_MESSAGE);
        }

    }

    private boolean validate() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public void setInjector(final Injector anInjector) {
        this.injector = anInjector;

        if (anInjector != null) {
            this.debugIdRegistry =
                    anInjector.getInstance(DebugIdRegistry.class);
            this.webGuiBus = anInjector.getInstance(WebGuiBus.class);
            this.webGuiBusMessageFactory =
                    anInjector.getInstance(WebGuiBusMessageFactory.class);
        }
    }

    @Override
    public Panel toPanel() {
        return this;
    }

    @Override
    public void initGui() {
        this.setDebugId(this.debugIdRegistry.getDebugId("workerpanel.1"));

        final GridLayout grid = new GridLayout(2, 6);

        grid.setDebugId(this.debugIdRegistry.getDebugId("workerpanel.2-grid"));
        grid.setWidth("400px");

        final Label abbreviationLabel =
                new Label(TM.get("workerpanel.1-abbreviation"));
        abbreviationTextField = new TextField();
        abbreviationTextField.setDebugId(this.debugIdRegistry
                .getDebugId("workerpanel.3-abbreviationTextField"));

        grid.addComponent(abbreviationLabel, 0, 0);
        grid.addComponent(abbreviationTextField, 1, 0);

        final Label firstNameLabel =
                new Label(TM.get("workerpanel.2-first-name"));
        firstNameTextField = new TextField();
        firstNameTextField.setDebugId(this.debugIdRegistry
                .getDebugId("workerpanel.4-firstNameTextField"));
        grid.addComponent(firstNameLabel, 0, 1);
        grid.addComponent(firstNameTextField, 1, 1);

        final Label middleNameLabel =
                new Label(TM.get("workerpanel.3-middle-name"));
        middleNameTextField = new TextField();
        middleNameTextField.setDebugId(this.debugIdRegistry
                .getDebugId("workerpanel.5-middleNameTextField"));
        grid.addComponent(middleNameLabel, 0, 2);
        grid.addComponent(middleNameTextField, 1, 2);

        final Label surnameLabel = new Label(TM.get("workerpanel.4-surname"));
        surnameTextField = new TextField();
        surnameTextField.setDebugId(this.debugIdRegistry
                .getDebugId("workerpanel.6-surnameTextField"));
        grid.addComponent(surnameLabel, 0, 3);
        grid.addComponent(surnameTextField, 1, 3);

        final Label dailyMaxLabel =
                new Label(TM.get("workerpanel.5-daily-max"));
        dailyMaxTextField = new TextField();
        dailyMaxTextField.setDebugId(this.debugIdRegistry
                .getDebugId("workerpanel.7-dailyMaxTextField"));
        grid.addComponent(dailyMaxLabel, 0, 4);
        grid.addComponent(dailyMaxTextField, 1, 4);

        final HorizontalLayout buttonPanel = new HorizontalLayout();
        buttonPanel.setDebugId(this.debugIdRegistry
                .getDebugId("workerpanel.8-buttonPanel"));

        final Button okButton = new Button(TM.get("application.1-ok"));
        okButton.setDebugId(this.debugIdRegistry
                .getDebugId("workerpanel.9-okButton"));

        final Button cancelButton = new Button(TM.get("application.2-cancel"));
        cancelButton.setDebugId(this.debugIdRegistry
                .getDebugId("workerpanel.10-cancelButton"));

        okButton.addListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final ClickEvent event) {
                okButtonClicked();
            }
        });

        cancelButton.setEnabled(false);

        buttonPanel.addComponent(okButton);
        buttonPanel.addComponent(cancelButton);

        grid.addComponent(buttonPanel, 0, 5, 1, 5);

        this.addComponent(grid);
    }

}

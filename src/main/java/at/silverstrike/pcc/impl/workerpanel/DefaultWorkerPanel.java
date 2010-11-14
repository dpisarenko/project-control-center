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

import at.silverstrike.pcc.api.persistence.Persistence;
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

            persistence.createHumanResource(abbreviation, firstName,
                    middleName, surname);
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
    }

    @Override
    public Panel toPanel() {
        return this;
    }

    @Override
    public void initGui() {
        final GridLayout grid = new GridLayout(2, 6);

        grid.setWidth("400px");

        final Label abbreviationLabel =
                new Label(TM.get("workerpanel.1-abbreviation"));
        abbreviationTextField = new TextField();
        grid.addComponent(abbreviationLabel, 0, 0);
        grid.addComponent(abbreviationTextField, 1, 0);

        final Label firstNameLabel = new Label(TM.get("workerpanel.2-first-name"));
        firstNameTextField = new TextField();
        grid.addComponent(firstNameLabel, 0, 1);
        grid.addComponent(firstNameTextField, 1, 1);

        final Label middleNameLabel = new Label(TM.get("workerpanel.3-middle-name"));
        middleNameTextField = new TextField();
        grid.addComponent(middleNameLabel, 0, 2);
        grid.addComponent(middleNameTextField, 1, 2);

        final Label surnameLabel = new Label(TM.get("workerpanel.4-surname"));
        surnameTextField = new TextField();
        grid.addComponent(surnameLabel, 0, 3);
        grid.addComponent(surnameTextField, 1, 3);

        final Label dailyMaxLabel = new Label(TM.get("workerpanel.5-daily-max"));
        dailyMaxTextField = new TextField();
        grid.addComponent(dailyMaxLabel, 0, 4);
        grid.addComponent(dailyMaxTextField, 1, 4);
        
        final HorizontalLayout buttonPanel = new HorizontalLayout();
        final Button okButton = new Button(TM.get("application.1-ok"));
        final Button cancelButton = new Button(TM.get("application.2-cancel"));

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

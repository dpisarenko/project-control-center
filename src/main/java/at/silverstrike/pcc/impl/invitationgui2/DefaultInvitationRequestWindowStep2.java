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

package at.silverstrike.pcc.impl.invitationgui2;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

import eu.livotov.tpt.i18n.TM;

import at.silverstrike.pcc.api.invitationgui2.InvitationRequestWindowStep2;
import at.silverstrike.pcc.api.invitationguicontroller.InvitationGuiController;

/**
 * @author DP118M
 * 
 */
class DefaultInvitationRequestWindowStep2 implements
        InvitationRequestWindowStep2, ClickListener {
    private static final long serialVersionUID = 1L;
    private InvitationGuiController controller;
    private Window window;

    @Override
    public void initGui() {
        this.window =
                new Window(TM.get("invitationgui2.1-title"));
        this.window.setSizeFull();

        final Label headerLabel = new Label(TM.get("invitationgui2.2-headerLabel"));
        final Label bodyTextTopLabel = new Label(TM.get("invitationgui2.3-bodyTextLabel-top"));
        final Label bodyTextBottomLabel = new Label(TM.get("invitationgui2.8-bodyTextLabel-bottom"));
        final Button nextButton = new Button(TM.get("invitationgui2.4-nextButton"));
        
        nextButton.addListener(this);
        
        final GridLayout textFieldLayout = createTextfieldLayout();
        
        this.window.addComponent(headerLabel);
        this.window.addComponent(bodyTextTopLabel);
        this.window.addComponent(textFieldLayout);
        this.window.addComponent(bodyTextBottomLabel);
        this.window.addComponent(nextButton);


    }

    private GridLayout createTextfieldLayout() {
        final GridLayout layout = new GridLayout(2, 3);
        
        final Label faceBookLabel = new Label(TM.get("invitationgui2.5-facebook"));
        final TextField faceBookTextField = new TextField("");
        
        final Label vkontakteLabel = new Label(TM.get("invitationgui2.6-vkontakte"));
        final TextField vkontakteTextField = new TextField("");
        
        final Label googleLabel = new Label(TM.get("invitationgui2.7-google"));
        final TextField googleTextField = new TextField("");
        
        layout.addComponent(faceBookLabel, 0, 0);
        layout.addComponent(faceBookTextField, 1, 0);
        
        layout.addComponent(vkontakteLabel, 0, 1);
        layout.addComponent(vkontakteTextField, 1, 1);
        
        layout.addComponent(googleLabel, 0, 2);
        layout.addComponent(googleTextField, 1, 2);

        return layout;
    }

    @Override
    public Window toWindow() {
        return this.window;
    }

    @Override
    public void setGuiController(final InvitationGuiController aController) {
        this.controller = aController;
    }

    @Override
    public void buttonClick(final ClickEvent aEvent) {
        this.controller.nextButtonInStep2Pressed();
    }
}

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

package at.silverstrike.pcc.test.invitationrequestadminpanelvisibility;

import junit.framework.Assert;

import org.junit.Test;

import ru.altruix.commons.api.di.PccException;

import at.silverstrike.pcc.api.invitationrequestadminpanelvisibility.
    InvitationRequestAdminPanelVisibilityCalculator;
import at.silverstrike.pcc.api.invitationrequestadminpanelvisibility.
    InvitationRequestAdminPanelVisibilityCalculatorFactory;
import at.silverstrike.pcc.impl.invitationrequestadminpanelvisibility.
    DefaultInvitationRequestAdminPanelVisibilityCalculatorFactory;

/**
 * @author DP118M
 * 
 */
public class TestDefaultInvitationRequestAdminPanelVisibilityCalculator {
    @Test
    public void testRun1() {
        final InvitationRequestAdminPanelVisibilityCalculatorFactory factory =
                new DefaultInvitationRequestAdminPanelVisibilityCalculatorFactory();
        final InvitationRequestAdminPanelVisibilityCalculator objectUnderTest =
                factory.create();
        
        objectUnderTest.setInvitationAdmins("dp@sw-dev.at,dp@altruix.co");
        objectUnderTest.setCurrentUsername("dp@sw-dev.at");
        try {
            objectUnderTest.run();
        } catch (final PccException exception) {
            Assert.fail(exception.getMessage());
        }
        Assert.assertTrue(objectUnderTest.isInvitationPanelVisible());
    }
    @Test
    public void testRun2() {
        final InvitationRequestAdminPanelVisibilityCalculatorFactory factory =
                new DefaultInvitationRequestAdminPanelVisibilityCalculatorFactory();
        final InvitationRequestAdminPanelVisibilityCalculator objectUnderTest =
                factory.create();
        
        objectUnderTest.setInvitationAdmins("dp@sw-dev.at,dp@altruix.co");
        objectUnderTest.setCurrentUsername(null);
        try {
            objectUnderTest.run();
        } catch (final PccException exception) {
            Assert.fail(exception.getMessage());
        }
        Assert.assertFalse(objectUnderTest.isInvitationPanelVisible());
    }
    @Test
    public void testRun3() {
        final InvitationRequestAdminPanelVisibilityCalculatorFactory factory =
                new DefaultInvitationRequestAdminPanelVisibilityCalculatorFactory();
        final InvitationRequestAdminPanelVisibilityCalculator objectUnderTest =
                factory.create();
        
        objectUnderTest.setInvitationAdmins(null);
        objectUnderTest.setCurrentUsername("dp@sw-dev.at");
        try {
            objectUnderTest.run();
        } catch (final PccException exception) {
            Assert.fail(exception.getMessage());
        }
        Assert.assertFalse(objectUnderTest.isInvitationPanelVisible());
    }
    @Test
    public void testRun4() {
        final InvitationRequestAdminPanelVisibilityCalculatorFactory factory =
                new DefaultInvitationRequestAdminPanelVisibilityCalculatorFactory();
        final InvitationRequestAdminPanelVisibilityCalculator objectUnderTest =
                factory.create();
        
        objectUnderTest.setInvitationAdmins("dp@sw-dev.at");
        objectUnderTest.setCurrentUsername("dp@sw-dev.at");
        try {
            objectUnderTest.run();
        } catch (final PccException exception) {
            Assert.fail(exception.getMessage());
        }
        Assert.assertTrue(objectUnderTest.isInvitationPanelVisible());
    }
    @Test
    public void testRun5() {
        final InvitationRequestAdminPanelVisibilityCalculatorFactory factory =
                new DefaultInvitationRequestAdminPanelVisibilityCalculatorFactory();
        final InvitationRequestAdminPanelVisibilityCalculator objectUnderTest =
                factory.create();
        
        objectUnderTest.setInvitationAdmins("dp@sw-dev.at");
        objectUnderTest.setCurrentUsername("dp@altruix.co");
        try {
            objectUnderTest.run();
        } catch (final PccException exception) {
            Assert.fail(exception.getMessage());
        }
        Assert.assertTrue(objectUnderTest.isInvitationPanelVisible());
    }
}

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

package at.silverstrike.pcc.test.schedulingpanel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.SeleneseTestCase;

public final class TestDefect62 extends SeleneseTestCase {
    private static final int SELENIUM_PORT = 4444;
    private static final String INVALID_EFFORT_ESTIMATE_MESSAGE_BOX =
            "//div[@id='016.004']";
    private static final String START_BUTTON = "016.002";

    @Before
    public void setUp() throws Exception {
        selenium =
                new DefaultSelenium("localhost", SELENIUM_PORT, "*chrome",
                        "http://localhost:8080/pcc");
        selenium.start();
    }

    @Test
    public void testDefect62() throws Exception {
        selenium.open("/pcc/");
        selectSchedulingPanel();
        selenium.click(START_BUTTON);

        assertTrue("Expected error message box did not appear",
                selenium.isElementPresent(INVALID_EFFORT_ESTIMATE_MESSAGE_BOX));
    }

    private void selectSchedulingPanel() {
        selenium.click("//div[@id='011.002']/div[1]/table/tbody/tr/td[5]/div/div/div[1]");
    }

    @After
    public void tearDown() throws Exception {
        selenium.stop();
    }

}

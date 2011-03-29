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

package at.silverstrike.pcc.test.www;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.SeleneseTestCase;

public final class GuiTest1 extends SeleneseTestCase {
    @Before
    public void setUp() throws Exception {
        selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://localhost:8080/");
        selenium.start();
    }

    @Test
    public void test2010_11_13_testCase() throws Exception {
        selenium.open("/pcc/");
        selenium.type("//div[@id='1.0']/div[2]/div/div/div[1]/div/div/div/div[1]/div/div/div[2]/div/div/div[1]/div/div/div[2]/div/div/div[2]/div/div/div/div[1]/div/textarea", "An interesting task");
        selenium.click("//div[@id='0.1']/div[1]/table/tbody/tr/td[5]/div/div/div[1]");
        selenium.click("//div[@id='0.1']/div[2]/div/div[5]/div/div[2]/div/div/div[1]/div/div/div/div[1]/div/div/div/div[2]/div/div/span/span");
        selenium.click("//div[@id='0.1']/div[1]/table/tbody/tr/td[3]/div/div/div[1]");
        selenium.click("//div[@id='0.1']/div[2]/div/div[3]/div/div[2]/div/div/div[1]/div/div/span/span");
    }

    @After
    public void tearDown() throws Exception {
        selenium.stop();
    }
}

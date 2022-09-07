package com.steps.tests;

import com.steps.TestSteps;
import com.utils.BrowserManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    private final BrowserManager browserManager = new BrowserManager();
    protected TestSteps steps = new TestSteps();

    @BeforeSuite
    public void setUpDriver() {
        browserManager.maximizeWindow();
    }

    @AfterSuite
    public void closeDriver() {
        browserManager.quitBrowser();
    }
}

package com.steps.tests;

import static com.utils.DataUtils.getInstance;
import com.steps.TestSteps;
import com.utils.BrowserManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import java.time.LocalDate;

public class BaseTest {
    private final BrowserManager browserManager = new BrowserManager();
    protected static final String START_PAGE = getInstance().getStartURL();
    protected static final LocalDate DATE_IN = getInstance().getDateIn();
    protected static final LocalDate DATE_OUT = getInstance().getDateOut();
    protected TestSteps steps = new TestSteps();

    @BeforeSuite
    public void setUpDriver() {
        browserManager.maximizeWindow();
    }

    @AfterClass
    public void deleteCookies() {
        browserManager.deleteAllCookies();
    }

    @AfterSuite
    public void closeDriver() {
        browserManager.quitBrowser();
    }
}

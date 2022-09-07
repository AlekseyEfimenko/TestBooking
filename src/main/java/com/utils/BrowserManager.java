package com.utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import org.apache.log4j.Logger;

public class BrowserManager {
    private final Browser browser;
    private static final Logger LOGGER = Logger.getLogger(BrowserManager.class);

    public BrowserManager() {
        browser = AqualityServices.getBrowser();
    }

    public void maximizeWindow() {
        LOGGER.info("Set windows size to maximum");
        browser.maximize();
    }

    public void navigateTo(String url) {
        LOGGER.info(String.format("Navigate to %1$s", url));
        browser.goTo(url);
    }

    public void waitForPageToLoad() {
        browser.waitForPageToLoad();
    }

    public void quitBrowser() {
        LOGGER.info("Close the driver");
        browser.quit();
    }
}

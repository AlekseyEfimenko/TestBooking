package com.utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;

public class BrowserManager {
    private final Browser browser;

    public BrowserManager() {
        browser = AqualityServices.getBrowser();
    }

    public void maximizeWindow() {
        browser.maximize();
    }

    public void navigateTo(String url) {
        browser.goTo(url);
    }

    public void waitForPageToLoad() {
        browser.waitForPageToLoad();
    }

    public void deleteAllCookies() {
        browser.getDriver().manage().deleteAllCookies();
    }

    public void quitBrowser() {
        browser.quit();
    }
}

package com.steps;

import aquality.selenium.core.logging.Logger;
import com.pages.SearchResultPage;
import com.pages.StartPage;
import com.utils.DataUtils;
import com.utils.BrowserManager;
import org.testng.Assert;
import java.time.LocalDate;

public class TestSteps {
    private static final Logger LOGGER = Logger.getInstance();
    private final BrowserManager browserManager = new BrowserManager();
    private final StartPage startPage = new StartPage();
    private final SearchResultPage resultPage = new SearchResultPage();
    private final DataUtils dataUtils = DataUtils.getInstance();

    public void navigateToSite(String url) {
        browserManager.navigateTo(url);
        browserManager.waitForPageToLoad();
    }

    public void selectCity(String destination) {
        LOGGER.info("Enter the city to visit");
        startPage.enterDestination(destination);
    }

    public void selectDateInAndOut(LocalDate in, LocalDate out) {
        LOGGER.info("Select period to stay");
        startPage.enterPeriod(in, out);
    }

    public void startSearching() {
        startPage.search();
    }

    public void assertCityIsCorrect(String city) {
        LOGGER.info(String.format("Assert that address of all the property cards contains %1$s City", city));
        Assert.assertTrue(DataUtils.getInstance().confirmCity(resultPage, city));
    }

    public void assertDatesAreCorrect(LocalDate in, LocalDate out) {
        LOGGER.info(String.format("Assert that check-in equals: %1$s and check-out equals: %2$s", in, out));
        Assert.assertEquals(dataUtils.getCheckInDate(resultPage), in);
        Assert.assertEquals(dataUtils.getCheckOutDate(resultPage), out);
    }

    public void sortPropertiesASC() {
        LOGGER.info("Sorting search result by price (ASC)");
        resultPage.sortByPrice();
    }

    public void assertPriceIsSorted() {
        LOGGER.info("Check if properties are sorted by price");
        Assert.assertTrue(DataUtils.isSorted(DataUtils.getInstance().getListOfPrices(resultPage)), "Properties are sorted incorrectly");
    }

}
